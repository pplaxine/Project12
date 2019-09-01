package com.biocycle.entWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.dto.view.RedistributionViewDto;
import com.biocycle.entWebApp.service.ProductManagmentManager;

@Controller
public class ProductManagmentController {
	
	//ProductBatch Form
	@Autowired
	private ProductManagmentManager productManagmentManager;
	
	
	@RequestMapping("/pme/entry-forms")
	public String productBatchForm(ProductBatchBeanDto productBatchBeanDto, Model model) {
		return productManagmentManager.productBatchForm(productBatchBeanDto, model);
	}
	@RequestMapping("/pme/entry-forms/create")
	public String createBatch(ProductBatchBeanDto productBatchBeanDto, Model model, RedirectAttributes red ) {
		return productManagmentManager.createProductBatch(productBatchBeanDto, model, red);
	}
	
	//ProductBatch List 
	@RequestMapping("/pme/product-batch/list")
	public String productBatchList(Model model) {
		return productManagmentManager.productBatchList(model);
	}
	
	@RequestMapping("/pme/product-batch/collect/{productBatchId}")
	public String productBatchList(@PathVariable int productBatchId, RedirectAttributes red) {
		return productManagmentManager.validateCollectionOfProductBatch(productBatchId, red);
	}
	
	//Redistribution 
	@RequestMapping("/pme/redistribution")
	public String redistribution(Model model) {
		return productManagmentManager.redistribution(model);
	}
	
	@RequestMapping("/pme/redistribution/{redistributionId}")
	public String redistributionSolo(@PathVariable int redistributionId, Model model, RedirectAttributes red, HttpSession session) {
		return productManagmentManager.redistributionSolo(redistributionId, model, red, session);
	}
	
	@RequestMapping("/pme/redistribution/solo/add/{redistributionId}/{productBatchBeanDtoId}")
	public String addProductBatchBeanDtoToOffer(@PathVariable int redistributionId, @PathVariable int productBatchBeanDtoId, HttpSession session) {
		return productManagmentManager.addProductBatchToOffer(redistributionId, productBatchBeanDtoId, session);		
	}
	
	@RequestMapping("/pme/redistribution/solo/remove/{redistributionId}/{productBatchBeanDtoId}")
	public String removeProductBatchBeanDtoToOffer(@PathVariable int redistributionId, @PathVariable int productBatchBeanDtoId, RedirectAttributes red, HttpSession session) {
		return productManagmentManager.removeProductBatchToOffer(redistributionId, productBatchBeanDtoId, red, session);		
	}
	
	@RequestMapping("/pme/redistribution/offer/add")
	public String addOfferToRedistribution(RedistributionViewDto redistributionViewDto, Model model, RedirectAttributes red, HttpSession session) {
		return productManagmentManager.addOfferToRedistribution(redistributionViewDto, model, red, session);
	}
	
	@RequestMapping("/pme/redistribution/offer/cancel/{redistributionId}/{offerId}")
	public String addOfferToRedistribution(@PathVariable int redistributionId, @PathVariable int offerId, RedirectAttributes red) {
		return productManagmentManager.cancelOffer(redistributionId, offerId, false, red);
	}
}
