package com.biocycle.entWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.dto.view.RedistributionViewDto;
import com.biocycle.entWebApp.service.ProductManagmentManager;

/**
 * The Class ProductManagmentController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Controller
public class ProductManagmentController {
	
	/** The product managment manager. */
	//ProductBatch Form
	@Autowired
	private ProductManagmentManager productManagmentManager;
	
	
	/**
	 * Product batch form.
	 *
	 * @param productBatchBeanDto the product batch bean dto
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/pme/entry-forms")
	public String productBatchForm(ProductBatchBeanDto productBatchBeanDto, Model model) {
		return productManagmentManager.productBatchForm(productBatchBeanDto, model);
	}
	
	/**
	 * Creates the batch.
	 *
	 * @param productBatchBeanDto the product batch bean dto
	 * @param model the model
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/pme/entry-forms/create")
	public String createBatch(ProductBatchBeanDto productBatchBeanDto, Model model, RedirectAttributes red ) {
		return productManagmentManager.createProductBatch(productBatchBeanDto, model, red);
	}
	
	/**
	 * Product batch list.
	 *
	 * @param model the model
	 * @return the string
	 */
	//ProductBatch List 
	@RequestMapping("/pme/product-batch/list")
	public String productBatchList(Model model) {
		return productManagmentManager.productBatchList(model);
	}
	
	/**
	 * Product batch list.
	 *
	 * @param productBatchId the product batch id
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/pme/product-batch/collect/{productBatchId}")
	public String productBatchList(@PathVariable int productBatchId, RedirectAttributes red) {
		return productManagmentManager.validateCollectionOfProductBatch(productBatchId, red);
	}
	
	/**
	 * Redistribution.
	 *
	 * @param model the model
	 * @return the string
	 */
	//Redistribution 
	@RequestMapping("/pme/redistribution")
	public String redistribution(Model model) {
		return productManagmentManager.redistribution(model);
	}
	
	/**
	 * Redistribution solo.
	 *
	 * @param redistributionId the redistribution id
	 * @param model the model
	 * @param red the red
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping("/pme/redistribution/{redistributionId}")
	public String redistributionSolo(@PathVariable int redistributionId, Model model, RedirectAttributes red, HttpSession session) {
		return productManagmentManager.redistributionSolo(redistributionId, model, red, session);
	}
	
	/**
	 * Adds the product batch bean dto to offer.
	 *
	 * @param redistributionId the redistribution id
	 * @param productBatchBeanDtoId the product batch bean dto id
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping("/pme/redistribution/solo/add/{redistributionId}/{productBatchBeanDtoId}")
	public String addProductBatchBeanDtoToOffer(@PathVariable int redistributionId, @PathVariable int productBatchBeanDtoId, HttpSession session) {
		return productManagmentManager.addProductBatchToOffer(redistributionId, productBatchBeanDtoId, session);		
	}
	
	/**
	 * Removes the product batch bean dto to offer.
	 *
	 * @param redistributionId the redistribution id
	 * @param productBatchBeanDtoId the product batch bean dto id
	 * @param red the red
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping("/pme/redistribution/solo/remove/{redistributionId}/{productBatchBeanDtoId}")
	public String removeProductBatchBeanDtoToOffer(@PathVariable int redistributionId, @PathVariable int productBatchBeanDtoId, RedirectAttributes red, HttpSession session) {
		return productManagmentManager.removeProductBatchToOffer(redistributionId, productBatchBeanDtoId, red, session);		
	}
	
	/**
	 * Adds the offer to redistribution.
	 *
	 * @param redistributionViewDto the redistribution view dto
	 * @param model the model
	 * @param red the red
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping("/pme/redistribution/offer/add")
	public String addOfferToRedistribution(RedistributionViewDto redistributionViewDto, Model model, RedirectAttributes red, HttpSession session) {
		return productManagmentManager.addOfferToRedistribution(redistributionViewDto, model, red, session);
	}
	
	/**
	 * Adds the offer to redistribution.
	 *
	 * @param redistributionId the redistribution id
	 * @param offerId the offer id
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/pme/redistribution/offer/cancel/{redistributionId}/{offerId}")
	public String addOfferToRedistribution(@PathVariable int redistributionId, @PathVariable int offerId, RedirectAttributes red) {
		return productManagmentManager.cancelOffer(redistributionId, offerId, false, red);
	}
}
