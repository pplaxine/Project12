package com.biocycle.customerWebApp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.bean.redistribution.RedistributionBean;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;
import com.biocycle.customerWebApp.proxy.ProductDispatchServiceProxy;

@Service
public class RedistributionManager {
	
	@Autowired
	private ProductDispatchServiceProxy productDispatchServiceProxy;
	
	//---- REDISTRIBUTION CREATION
	
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
	
	
}
