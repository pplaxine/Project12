package com.biocycle.entWebApp.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.dto.StorageContainerBeanDto;
import com.biocycle.entWebApp.dto.view.RedistributionViewDto;
import com.biocycle.entWebApp.helper.entWebAppHelper;
import com.biocycle.entWebApp.proxy.InventoryServiceProxy;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.entWebApp.proxy.RedistributionCRUDMSProxy;
import com.biocycle.entWebApp.proxy.StorageContainerCRUDMSProxy;

@Service
public class ProductManagmentManager {

	@Autowired
	private InventoryServiceProxy inventoryServiceProxy;
	@Autowired
	private CustomerManagmentManager customerManagmentManager;
	@Autowired
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	@Autowired
	private StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	@Autowired
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@Autowired
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	
	//PRODUCT BATCH FORM ------------------------------------------------------------------------------
	public String productBatchForm(ProductBatchBeanDto productBatchBeanDto, Model model) {
		
		try {
			List<OrganisationBeanDto> organisationBeanDtoList = customerManagmentManager.getAllDonor();
			model.addAttribute("organisationBeanDtoList", organisationBeanDtoList);

		} catch (ResponseStatusException e) {
			String error ="Error occured while getting organisations names. Please try again.";
			model.addAttribute("error", error);
			return "productBatchForm";
		}
		return "productBatchForm";
	}
	
	public String createProductBatch(ProductBatchBeanDto productBatchBeanDto, Model model, RedirectAttributes red) {

		int numberOfContainer = productBatchBeanDto.getStorageContainerId().get(0);
		productBatchBeanDto.setStorageContainerId(null);
		productBatchBeanDto.setDateOfCollection(LocalDate.now());
		productBatchBeanDto.setIsAvailable(true);
		try {
			//persist productBatch 
			ResponseEntity<Void> resp = inventoryServiceProxy.createEntry(productBatchBeanDto, numberOfContainer);
			URI location = resp.getHeaders().getLocation();
			
			int productBatchId = Integer.parseInt(getURILastPart(location));
					
			//retrieve productBatch with id 
			ProductBatchBeanDto productBatchBeanDtoResp = productBatchCRUDMSProxy.findProductBatchById(productBatchId).getBody();
			List<Integer> storageContainerIdList = productBatchBeanDtoResp.getStorageContainerId();
			Integer[] containerIdArray = storageContainerIdList.toArray(new Integer[storageContainerIdList.size()]);
			
			
			//retrieve containers 
			Optional<List<StorageContainerBeanDto>> storageContainerBeanDtoList = storageContainerCRUDMSProxy.findStorageContainerFromIdList(containerIdArray);
			if(storageContainerBeanDtoList.isPresent()) {
				red.addFlashAttribute("storageContainerBeanDtoList", storageContainerBeanDtoList.get());
			}
			
			String info = "Entry successfully registered.";
			red.addFlashAttribute("info", info);
			return "redirect:/pme/entry-forms";
			
		} catch (ResponseStatusException rse) {
			
			List<OrganisationBeanDto> organisationBeanDtoList = customerManagmentManager.getAllDonor();
			model.addAttribute("organisationBeanDtoList", organisationBeanDtoList);
			
			if(rse.getStatus() == HttpStatus.INSUFFICIENT_STORAGE || rse.getStatus() == HttpStatus.NOT_FOUND) {
				String error ="No more storage space available.";
				model.addAttribute("error", error);
				return "productBatchForm";
			}else {
				String error ="Could not register your entry. Please try again.";
				model.addAttribute("error", error);
				return "productBatchForm";
			}
		} catch (Exception e) {
			
			List<OrganisationBeanDto> organisationBeanDtoList = customerManagmentManager.getAllDonor();
			model.addAttribute("organisationBeanDtoList", organisationBeanDtoList);
			String error= e.getLocalizedMessage();
			model.addAttribute("error", error);
			return "productBatchForm";
		}
	}
	
	//PRODUCTBATCH LIST ------------------------------------------------------------------------------
	
	public String productBatchList(Model model) {
		try {
			ResponseEntity<List<ProductBatchBeanDto>> resp = productBatchCRUDMSProxy.findAllProductBatch();
			List<ProductBatchBeanDto> productBatchBeanDtoActiveList = filterProductBatchActive(resp.getBody());
			productBatchBeanDtoActiveList.sort((ProductBatchBeanDto pbb1, ProductBatchBeanDto pbb2) -> pbb1.getToBeUsedBy().compareTo(pbb2.getToBeUsedBy()));

			model.addAttribute("productBatchBeanDtoActiveList",  productBatchBeanDtoActiveList);
			
		} catch (ResponseStatusException rse) {
			
			String error ="No productBatch could be found.";
			model.addAttribute("error", error);
			System.out.println(rse.getReason());
			
		} catch (Exception e) {
			model.addAttribute("error", "Error occured. Please Try again.");
		}
			
		return "productBatchList";
	}
	
	//REDISTRIBUTION ------------------------------------------------------------------------------
	
	public String redistribution(Model model) {
		try {
		//Filters 
		ResponseEntity<List<RedistributionBeanDto>> resp = redistributionCRUDMSProxy.findAllActiveRedistributions();
		List<RedistributionBeanDto> redistributionWithRequestList = filterRedistributionWithoutOffer(filterRedistributionWithRequestOnly(resp.getBody()));
		
		//Bean to View 
			List<RedistributionViewDto> redistributionViewDtoList = beanDtoListToViewDtoList(redistributionWithRequestList);
			model.addAttribute("redistributionViewDtoList", redistributionViewDtoList);
		} catch (ResponseStatusException rse) {
			
			String error ="No redistribution could be found.";
			model.addAttribute("error", error);
			System.out.println(rse.getReason());
			
		} catch (Exception e) {
			model.addAttribute("error", "Error occured. Please Try again.");
		}
		
		return "redistribution";
	}
	
	public String redistributionSolo(int redistributionId, Model model, RedirectAttributes red, HttpSession session) {
		
		List<ProductBatchBeanDto> productBatchBeanDtoForOfferList;
		// in session 
		if(session.getAttribute("productBatchBeanDtoForOfferList") == null) {
			
			//--productBatchavailable
			ResponseEntity<List<ProductBatchBeanDto>> resp = productBatchCRUDMSProxy.findAllProductBatch();
			List<ProductBatchBeanDto> productBatchBeanDtoActiveAndAvailableForOfferList = filterProductBatchAvailableForOffer(resp.getBody());
			productBatchBeanDtoActiveAndAvailableForOfferList.sort((ProductBatchBeanDto pbb1, ProductBatchBeanDto pbb2) -> pbb1.getToBeUsedBy().compareTo(pbb2.getToBeUsedBy()));
			productBatchBeanDtoForOfferList = productBatchBeanDtoActiveAndAvailableForOfferList;
		}else {
			productBatchBeanDtoForOfferList = (List<ProductBatchBeanDto>)session.getAttribute("productBatchBeanDtoForOfferList");
		}
		
		try {
			//--redistributions
			RedistributionBeanDto redistributionBeanDto = redistributionCRUDMSProxy.findRedistributionById(redistributionId).getBody();
			//Bean to View 
			RedistributionViewDto redistributionViewDto = redistributionBeanDtoToViewDto(redistributionBeanDto);
			model.addAttribute("redistributionViewDto", redistributionViewDto);
			
			//--ProductBatch in session
			session.setAttribute("productBatchBeanDtoForOfferList", productBatchBeanDtoForOfferList);
			
		} catch (ResponseStatusException rse) {
			String error ="No redistribution could be found.";
			red.addFlashAttribute("error", error);
			System.out.println(rse.getReason());
			return "redirect:/pme/redistribution";
		} catch (Exception e) {
			red.addFlashAttribute("error", "Error occured. Please Try again.");
			return "redirect:/pme/redistribution";
		}
		
		return "redistributionSolo";
	}
	
	@SuppressWarnings("unchecked")
	public String addProductBatchToRedistributionRequest(int redistributionId ,int productBatchBeanDtoId ,HttpSession session) {
		List<ProductBatchBeanDto> refreshedProductBatchBeanDtoList = new ArrayList<>();
		ProductBatchBeanDto productBatchBeanDto = null;
		//retrieve productBatch 
		if(session.getAttribute("productBatchBeanDtoForOfferList") != null) {
			List<ProductBatchBeanDto> productBatchBeanDtoList = (List<ProductBatchBeanDto>)session.getAttribute("productBatchBeanDtoForOfferList");
			for (ProductBatchBeanDto pbbd : productBatchBeanDtoList) {
				if(pbbd.getId() != productBatchBeanDtoId) {
					refreshedProductBatchBeanDtoList.add(pbbd);
				}else {
					productBatchBeanDto = pbbd;
				}
			}
		}
		session.setAttribute("productBatchBeanDtoForOfferList", refreshedProductBatchBeanDtoList);

		//add to offer 
		Map<Integer,ProductBatchBeanDto> productBatchBeanDtoMap;
		if(session.getAttribute("productBatchBeanDtoMap") == null) {
			productBatchBeanDtoMap = new HashMap<>();
		}else {
			productBatchBeanDtoMap = (Map<Integer, ProductBatchBeanDto>)session.getAttribute("productBatchBeanDtoMap");
		}
		
		productBatchBeanDtoMap.put(productBatchBeanDto.getId(), productBatchBeanDto);
		session.setAttribute("productBatchBeanDtoMap",productBatchBeanDtoMap);
		
		return "redirect:/pme/redistribution/"+redistributionId;
	}
	
	@SuppressWarnings("unchecked")
	public String removeProductBatchToRedistributionRequest(int redistributionId ,int productBatchBeanDtoId ,RedirectAttributes red, HttpSession session) {
		Map<Integer, ProductBatchBeanDto> productBatchBeanDtoMap;
		ProductBatchBeanDto productBatchBeanDto;
		
		if(session.getAttribute("productBatchBeanDtoMap") != null && session.getAttribute("productBatchBeanDtoForOfferList") !=null) {
			//retrieve ProductBatch from session 
			productBatchBeanDtoMap = (Map<Integer, ProductBatchBeanDto>)session.getAttribute("productBatchBeanDtoMap");
			productBatchBeanDto = productBatchBeanDtoMap.get(productBatchBeanDtoId);
			//remove from offers
			productBatchBeanDtoMap.remove(productBatchBeanDtoId);
			session.setAttribute("productBatchBeanDtoMap", productBatchBeanDtoMap);
			
			//add To ProductBatchList
			List<ProductBatchBeanDto> productBatchBeanDtoList = (List<ProductBatchBeanDto>)session.getAttribute("productBatchBeanDtoForOfferList");
			productBatchBeanDtoList.add(productBatchBeanDto);
			session.setAttribute("productBatchBeanDtoForOfferList", productBatchBeanDtoList);
		}else{
			String error ="Error occured while trying to remove product from offer";
			red.addFlashAttribute("error", error);
			return "redirect:/pme/redistribution/"+redistributionId;
		}
		return "redirect:/pme/redistribution/"+redistributionId;
	}
	
	
	
	//UTILITY METHODS -------------------------------------------------------------------------------------------------------------
	private String getURILastPart(URI location) {
		String[] parts = location.getPath().split("/");
		return parts[parts.length-1];
	}
	
	//Redistribution BeanList to ViewList 
	private List<RedistributionViewDto> beanDtoListToViewDtoList(List<RedistributionBeanDto> redistributionWithRequestList) throws ResponseStatusException{
		List<RedistributionViewDto> redistributionViewDtoList = new ArrayList<>();
		
		for (RedistributionBeanDto rbd : redistributionWithRequestList) {
			try {
				RedistributionViewDto redistributionViewDto = redistributionBeanDtoToViewDto(rbd);
				redistributionViewDtoList.add(redistributionViewDto);
			} catch (ResponseStatusException e) {
				throw e;
			}
		}
		
		return redistributionViewDtoList; 
	}
	
	//Redistribution BeantoView
	private RedistributionViewDto redistributionBeanDtoToViewDto(RedistributionBeanDto redistributionBeanDto) {
		OrganisationBeanDto organisationBeanDto = organisationCRUDMSProxy.findOrganisationById(redistributionBeanDto.getOrganisationId()).getBody();
		List<ProductRequestBeanDto> productRequestBeanDtoList = getProductRequestBeanFromIdList(redistributionBeanDto.getProductRequestId());
		return entWebAppHelper.redistributionViewBuilder(redistributionBeanDto, null, productRequestBeanDtoList, organisationBeanDto);
		
	}
	
	private List<ProductRequestBeanDto> getProductRequestBeanFromIdList(List<Integer> productRequestIdList) throws ResponseStatusException{
		List<ProductRequestBeanDto> productRequestBeanDtoList = new ArrayList<>();
		for (Integer productRequestId : productRequestIdList) {
			try {
				ProductRequestBeanDto productRequestBeanDto = productRequestCRUDMSProxy.findProductRequestById(productRequestId);
				productRequestBeanDtoList.add(productRequestBeanDto);
			} catch (ResponseStatusException e) {
				throw e;
			}
		}
		
		return productRequestBeanDtoList;
	}
	
	//FILTERS 
	private List<RedistributionBeanDto> filterRedistributionWithRequestOnly(List<RedistributionBeanDto> redistributionBeanDtoList){
		List<RedistributionBeanDto> redistributionWithRequestOnly = new ArrayList<>();
		redistributionWithRequestOnly = redistributionBeanDtoList
														.stream()
														.filter(e -> e.getProductRequestId() != null || !e.getProductRequestId().isEmpty())
														.collect(Collectors.toList());
		return redistributionWithRequestOnly;
	}
	
	private List<RedistributionBeanDto> filterRedistributionWithoutOffer(List<RedistributionBeanDto> redistributionBeanDtoList){
		List<RedistributionBeanDto> redistributionWithoutOffer = new ArrayList<>();
		redistributionWithoutOffer = redistributionBeanDtoList
														.stream()
														.filter(e -> e.getOfferId() == null)
														.collect(Collectors.toList());
		return redistributionWithoutOffer;
	}
	
	private List<ProductBatchBeanDto> filterProductBatchActive(List<ProductBatchBeanDto> productBatchBeanDtoList){
		List<ProductBatchBeanDto> productBatchBeanDtoActiveList = new ArrayList<>();
		productBatchBeanDtoActiveList = productBatchBeanDtoList
														.stream()
														.filter(e -> e.getIsAvailable() == true)
														.collect(Collectors.toList());
		return productBatchBeanDtoActiveList;
	}
	
	private List<ProductBatchBeanDto> filterProductBatchAvailableForOffer(List<ProductBatchBeanDto> productBatchBeanDtoList){
		List<ProductBatchBeanDto> productBatchBeanDtoForOfferList = new ArrayList<>();
		productBatchBeanDtoForOfferList = productBatchBeanDtoList
														.stream()
														.filter(e -> e.getIsAvailable() == true)
														.filter(e -> e.getIsAwaitingForCollection() == null || e.getIsAwaitingForCollection() == false)
														.collect(Collectors.toList());
		return productBatchBeanDtoForOfferList;
	}
	
}
