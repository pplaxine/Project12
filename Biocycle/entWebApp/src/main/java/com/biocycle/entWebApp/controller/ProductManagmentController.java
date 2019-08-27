package com.biocycle.entWebApp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.service.ProductManagmentManager;

@Controller
public class ProductManagmentController {
	
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
}
