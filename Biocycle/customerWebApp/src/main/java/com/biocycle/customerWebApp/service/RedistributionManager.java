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

/**
 * The Class RedistributionManager.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Service
public class RedistributionManager {
	
	/** The product dispatch service proxy. */
	@Autowired
	private ProductDispatchServiceProxy productDispatchServiceProxy;
	
	/** The product request CRUDMS proxy. */
	@Autowired
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	
	/** The redistribution CRUDMS proxy. */
	@Autowired
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	
	/** The offer CRUDMS proxy. */
	@Autowired
	private OfferCRUDMSProxy offerCRUDMSProxy;
	
	/** The product batch CRUDMS proxy. */
	@Autowired
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	
	//---- HOME PAGE PRODUCT SOON TO EXPIRED  ---------------------------------------------------------------------
	
	/**
	 * Product soon to be expired.
	 *
	 * @param model the model
	 * @return the string
	 */
	public String productSoonToBeExpired(Model model){
		
		try {
			ResponseEntity<List<ProductBatchBeanDto>> resp = productBatchCRUDMSProxy.getProductSoonToExpire();
			List<ProductBatchBeanDto> listProductBatchBeanDto = resp.getBody();
			model.addAttribute("listProductBatchBeanDto", listProductBatchBeanDto);
			
		} catch (ResponseStatusException e) {
				String error ="Error occured while searching for offers. Please try again";
				model.addAttribute("error", error);
				return "home";
		}
		return "home";
	}
	
	
	//---- REDISTRIBUTION CREATION ---------------------------------------------------------------------
	
	/**
	 * Adds the product request.
	 *
	 * @param productRequestBeanDto the product request bean dto
	 * @param model the model
	 * @param red the red
	 * @param session the session
	 * @return the string
	 */
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
	
	/**
	 * Removes the product request.
	 *
	 * @param ref the ref
	 * @param model the model
	 * @param session the session
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	public String removeProductRequest(String ref, Model model, HttpSession session) {
		Map<String, ProductRequestBeanDto> productRequestBeanDtoMap = (Map<String, ProductRequestBeanDto>)session.getAttribute("productRequestBeanDtoMap");
		productRequestBeanDtoMap.remove(ref);
		session.setAttribute("productRequestBeanDtoMap", productRequestBeanDtoMap);
		return "redirect:/user2/redistributions/productrequests/form";
	}
	
	/**
	 * Adds the redistribution.
	 *
	 * @param productRequestBeanDto the product request bean dto
	 * @param model the model
	 * @param red the red
	 * @param session the session
	 * @return the string
	 */
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
	
	/**
	 * Offers.
	 *
	 * @param model the model
	 * @param session the session
	 * @param principal the principal
	 * @return the string
	 */
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
	
	/**
	 * Update offer status.
	 *
	 * @param offerId the offer id
	 * @param isAccepted the is accepted
	 * @param red the red
	 * @return the string
	 */
	public String updateOfferStatus(int offerId, Boolean isAccepted,RedirectAttributes red) { //update make list of productbatchlistid empty 
		try {
			//update offer
			OfferBeanDto offerBeanDto = offerCRUDMSProxy.findOfferById(offerId).getBody();
			offerBeanDto.setIsAccepted(isAccepted);
			offerCRUDMSProxy.updateOffer(offerBeanDto);
			
			if(!isAccepted) {
				List<Integer> productBatchIdList = offerBeanDto.getProductBatchIdList();
				for (Integer productBatchId : productBatchIdList) {
					productBatchCRUDMSProxy.updateProductBatchIsAwaitingToBeCollectedStatus(productBatchId, false);	//update productbatch
				}
			}
			
		} catch (ResponseStatusException e) {
			if(e.getStatus() == HttpStatus.NOT_FOUND) {
				String error ="No offer could be found.Please try again";
				red.addFlashAttribute("error", error);
				return "redirect:/user2/offers";
			}else if(e.getStatus() == HttpStatus.NO_CONTENT) {
				String error ="Offer status could not be updated.Please try again";
				red.addFlashAttribute("error", error);
				return "redirect:/user2/offers";
			}else {
				String error ="Error occured while taking in account your response. Please try again";
				red.addFlashAttribute("error", error);
				return "redirect:/user2/offers";
			}
		}
		
		return "redirect:/user2/offers";
	}
	
	
	//UTILITY METHODS ----------------------------------------------------------------------
	
	/**
	 * Redistribution bean dto list to redistribution view dto list.
	 *
	 * @param redistributionBeanDtoList the redistribution bean dto list
	 * @return the list
	 * @throws ResponseStatusException the response status exception
	 */
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
					
					if(rvd.getOfferViewDto() != null) {
						List<ProductBatchBeanDto> productBatchBeanDtoList = rvd.getOfferViewDto().getProductBatchBeanList();
						
						//update status 
						for (ProductBatchBeanDto pbbd : productBatchBeanDtoList) {
							if(pbbd.getIsAvailable() == false && pbbd.getIsAwaitingForCollection() == false) {
								redistributionBeanDto.setIsCompleted(true); //
								redistributionCRUDMSProxy.updateRedistribution(redistributionBeanDto); 
								ProductRequestBeanDto productRequestBeanDto = productRequestCRUDMSProxy.findProductRequestById(redistributionBeanDto.getId());
								productRequestBeanDto.setIsAccepted(true);
								productRequestCRUDMSProxy.updateProductRequest(productRequestBeanDto);
								rvd.setIsCompleted(true);
							}
						}
						
					}
					
					
				}

				redistributionViewDtoList.add(rvd);
			}

		} catch (ResponseStatusException e) {
			throw e; 
		}
		
		return redistributionViewDtoList;
	}
	
	
	/**
	 * Offer bean dto to offer view dto.
	 *
	 * @param offerBeanDto the offer bean dto
	 * @return the offer view dto
	 * @throws ResponseStatusException the response status exception
	 */
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
	
	/**
	 * Gets the all product requests by id list.
	 *
	 * @param productRequestIdList the product request id list
	 * @return the all product requests by id list
	 * @throws ResponseStatusException the response status exception
	 */
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
	
	/**
	 * Gets the offer bean dto by id.
	 *
	 * @param offerId the offer id
	 * @return the offer bean dto by id
	 * @throws ResponseStatusException the response status exception
	 */
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
	
	/**
	 * Gets the list product batch by product batch id list.
	 *
	 * @param productBatchId the product batch id
	 * @return the list product batch by product batch id list
	 * @throws ResponseStatusException the response status exception
	 */
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
