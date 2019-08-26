package com.biocycle.customerWebApp.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.bean.redistribution.RedistributionBean;
import com.biocycle.customerWebApp.dto.OfferBeanDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.ProductBatchBeanDto;
import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;
import com.biocycle.customerWebApp.dto.view.OfferViewDto;
import com.biocycle.customerWebApp.dto.view.RedistributionViewDto;
import com.biocycle.customerWebApp.proxy.OfferCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.ProductDispatchServiceProxy;
import com.biocycle.customerWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.RedistributionCRUDMSProxy;

@Service
public class RedistributionManager {
	
	@Autowired
	private ProductDispatchServiceProxy productDispatchServiceProxy;
	@Autowired
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	@Autowired
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@Autowired
	private OfferCRUDMSProxy offerCRUDMSProxy;
	@Autowired
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	
	
	//---- REDISTRIBUTION CREATION ---------------------------------------------------------------------
	
	//-- ProductRequest
	@SuppressWarnings("unchecked")
	public String addProductRequest(ProductRequestBeanDto productRequestBeanDto, Model model,RedirectAttributes red, HttpSession session) {
		
		if(productRequestBeanDto != null) {
			
			//insert product in session 
			Map<String,ProductRequestBeanDto> productRequestBeanDtoMap;
			
			if(session.getAttribute("productRequestBeanDtoMap") == null) {
				productRequestBeanDtoMap = new HashMap<>();
			}else {
				productRequestBeanDtoMap = (Map<String, ProductRequestBeanDto>)session.getAttribute("productRequestBeanDtoMap");
			}
			
			productRequestBeanDtoMap.put(productRequestBeanDto.getProductRequested(),productRequestBeanDto);
			session.setAttribute("productRequestBeanDtoMap", productRequestBeanDtoMap);
			
			return "redirect:/user2/redistributions/productrequests/form";
		}
		
		String error ="Error occured while adding the product request. Please try again";
		model.addAttribute("error", error);
		
		return "productRequestForm";
	}
	
	@SuppressWarnings("unchecked")
	public String removeProductRequest(String ref, Model model, HttpSession session) {
		Map<String, ProductRequestBeanDto> productRequestBeanDtoMap = (Map<String, ProductRequestBeanDto>)session.getAttribute("productRequestBeanDtoMap");
		productRequestBeanDtoMap.remove(ref);
		session.setAttribute("productRequestBeanDtoMap", productRequestBeanDtoMap);
		return "redirect:/user2/redistributions/productrequests/form";
	}
	
	//-- Redistribution
	@SuppressWarnings("unchecked")
	public String addRedistribution(ProductRequestBeanDto productRequestBeanDto, Model model, RedirectAttributes red, HttpSession session) {
		
		Map<String,ProductRequestBeanDto> productRequestBeanDtoMap = (Map<String, ProductRequestBeanDto>)session.getAttribute("productRequestBeanDtoMap");
		if( productRequestBeanDtoMap == null || productRequestBeanDtoMap.size() <= 0) {
			String error ="You have to add at least one product to your request.";
			model.addAttribute("error", error);
			return "productRequestForm";
		}
		
		//Object creation
		
		//-- organisation id 
		OrganisationBeanDto organisationBeanDto = (OrganisationBeanDto)session.getAttribute("organisation");
		int organisationId = organisationBeanDto.getId();
		
		//-- productRequestlist 
		List<ProductRequestBeanDto> productRequestBeanDtoList = new ArrayList<>(productRequestBeanDtoMap.values());
		
		try {
			//persist request
			productDispatchServiceProxy.addRedistributionForRequest(organisationId, productRequestBeanDtoList);
			productRequestBeanDtoMap.clear();
			String info = "Your request has been registred.";
			red.addFlashAttribute("info", info);
			
		} catch (Exception e) {
			if(e.getClass() == ResponseStatusException.class) {
				String error ="Error occured while persisting your request.";
				model.addAttribute("error", error);
				return "productRequestForm";
			}else {
				String error ="Error occured while persisting your request.";
				model.addAttribute("error", error);
				e.printStackTrace();
				return "productRequestForm";
			}
		}
		return "redirect:/user2/redistributions/productrequests/form";
	}
	
	//---- OFFERS MANAGMENT	---------------------------------------------------------------------
	public String offers(Model model, HttpSession session, Principal principal) {
		//retrieve organisation id 
		OrganisationBeanDto organisationBeanDto = (OrganisationBeanDto)session.getAttribute("organisation");
		int organisationId = organisationBeanDto.getId();
		
		try {

			//retrieves all redistribution 
		List<RedistributionBeanDto> redistributionBeanDtoList = redistributionCRUDMSProxy.getAllRedistributionByOrganisationId(organisationId).getBody();
		List<RedistributionViewDto> redistributionViewDtoList = redistributionBeanDtoListToRedistributionViewDtoList(redistributionBeanDtoList);
		
		model.addAttribute("redistributionViewDtoList", redistributionViewDtoList);
		
			
		} catch (ResponseStatusException e) {
			if(e.getStatus() == HttpStatus.NOT_FOUND) {
				String error ="No offer could be found.";
				model.addAttribute("error", error);
				return "receiverPersoSpace";
			}else {
				String error ="Error occured while searching for offers. Please try again";
				model.addAttribute("error", error);
				return "receiverPersoSpace";
			}
		}
		
		return "receiverPersoSpace";
	}
	
	
	//UTILITY METHODS ----------------------------------------------------------------------
	
	private List<RedistributionViewDto> redistributionBeanDtoListToRedistributionViewDtoList(List<RedistributionBeanDto> redistributionBeanDtoList) throws ResponseStatusException {
		List<RedistributionViewDto> redistributionViewDtoList = new ArrayList<>();
		
		try {
			for (RedistributionBeanDto redistributionBeanDto : redistributionBeanDtoList) {
				RedistributionViewDto rvd = new RedistributionViewDto();
				
				//id
				rvd.setId(redistributionBeanDto.getId());
				
				//productRequest from id 
				if(redistributionBeanDto.getProductRequestId() != null) {
					List<ProductRequestBeanDto> productRequestBeanDtoList = getAllProductRequestsByIdList(redistributionBeanDto.getProductRequestId());
					rvd.setProductRequestBeanDtoList(productRequestBeanDtoList);
				}
				//offer from id
				if(redistributionBeanDto.getOfferId() != null) {
				OfferBeanDto offerBeanDto = getOfferBeanDtoById(redistributionBeanDto.getOfferId());
				OfferViewDto offerViewDto = offerBeanDtoToOfferViewDto(offerBeanDto);
				rvd.setOfferViewDto(offerViewDto);
				}
				//is completed 
				if(redistributionBeanDto.getIsCompleted() != null) {
				rvd.setIsCompleted(redistributionBeanDto.getIsCompleted());
				}

				redistributionViewDtoList.add(rvd);
			}

		} catch (ResponseStatusException e) {
			throw e; 
		}
		
		return redistributionViewDtoList;
	}
	
	
	private OfferViewDto offerBeanDtoToOfferViewDto(OfferBeanDto offerBeanDto) throws ResponseStatusException {
		if(offerBeanDto == null) {
			return null;
		}
		
		OfferViewDto offerViewDto = new OfferViewDto();
		
		if(offerBeanDto.getId() != 0) {
			offerViewDto.setId(offerBeanDto.getId());
		}
		if(offerBeanDto.getAvailableForCollection() != null) {
			offerViewDto.setAvailableForCollection(offerBeanDto.getAvailableForCollection());
		}
		if(offerBeanDto.getOfferEndingDate() != null) {
			offerViewDto.setOfferEndingDate(offerBeanDto.getOfferEndingDate());
		}
		if(offerBeanDto.getIsAccepted() != null) {
			offerViewDto.setIsAccepted(offerBeanDto.getIsAccepted());
		}
		
		if(offerBeanDto.getProductBatchIdList() != null) {
			// get productBatch
			List<Integer> productBatchIdList = offerBeanDto.getProductBatchIdList();
			try {
				List<ProductBatchBeanDto> productBatchBeanList = getListProductBatchByProductBatchIdList(productBatchIdList);
				offerViewDto.setProductBatchBeanList(productBatchBeanList);
			} catch (ResponseStatusException e) {
				throw e;
			}
		}
		return offerViewDto;
	}
	
	
	//--- fetching methods 
	
	private List<ProductRequestBeanDto> getAllProductRequestsByIdList(List<Integer> productRequestIdList) throws ResponseStatusException {
		List<ProductRequestBeanDto> productRequestBeanDtoList = new ArrayList<>();

		for (Integer id : productRequestIdList) {
			try {
				ProductRequestBeanDto productRequestBeanDto = productRequestCRUDMSProxy.findProductRequestById(id);
				productRequestBeanDtoList.add(productRequestBeanDto);
			} catch (ResponseStatusException e) {
				throw e;
			}
		}
		return productRequestBeanDtoList;
	}
	
	private OfferBeanDto getOfferBeanDtoById(int offerId) throws ResponseStatusException {
		
		try {
			return offerCRUDMSProxy.findOfferById(offerId).getBody();
		} catch (ResponseStatusException e) {
			if(e.getStatus().equals(HttpStatus.NOT_FOUND)) {
				return null;
			}else {
				throw e;
			}
		}
	}
	
	private List<ProductBatchBeanDto> getListProductBatchByProductBatchIdList(List<Integer> productBatchId) throws ResponseStatusException {
		List<ProductBatchBeanDto> productBatchBeanDtoList = new ArrayList<>();
		
		for (Integer pbId : productBatchId) {
			try {
				ProductBatchBeanDto productBatchBeanDto = productBatchCRUDMSProxy.findProductBatchById(pbId);
				productBatchBeanDtoList.add(productBatchBeanDto);
			} catch (ResponseStatusException e) {
				throw e;
			}
		}
		return productBatchBeanDtoList;
	}

	
	

	
}
