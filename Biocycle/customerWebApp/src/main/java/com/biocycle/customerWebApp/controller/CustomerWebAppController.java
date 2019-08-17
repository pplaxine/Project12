package com.biocycle.customerWebApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.biocycle.customerWebApp.bean.productRequest.ProductRequestBean;
import com.biocycle.customerWebApp.bean.redistribution.RedistributionBean;
import com.biocycle.customerWebApp.dto.CollectionSpotAddressDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;
import com.biocycle.customerWebApp.dto.mapper.ProductRequestBeanDtoMapper;
import com.biocycle.customerWebApp.dto.mapper.RedistributionBeanDtoMapper;
import com.biocycle.customerWebApp.proxy.CustomerManagmentServiceProxy;
import com.biocycle.customerWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.RedistributionCRUDMSProxy;

@Controller
public class CustomerWebAppController {
	
	@Autowired
	private CustomerManagmentServiceProxy customerManagmentServiceProxy;
	
	@RequestMapping("/")
	public String home(Model model) {
		
//		//retrieve redistribution 
//		ResponseEntity<RedistributionBeanDto> resp = redistributionCRUDMSProxy.getRedistributionById(1);
//		RedistributionBeanDto rbd =  resp.getBody();
//		RedistributionBean rb = redistributionBeanDtoMapper.redistributionBeanDtoToRedistributionBean(rbd);
//		
//		//retrieve productRequests
//		List<Integer> productRequestIdList = rb.getProductRequestId();
//		List<ProductRequestBean> productRequestBeanList = new ArrayList<>();
//		
//		for (Integer productRequestId : productRequestIdList) {
//			
//			ProductRequestBeanDto prb = productRequestCRUDMSProxy.findProductRequestById(productRequestId);
//			ProductRequestBean pr = productRequestBeanDtoMapper.productRequestBeanDtoToProductRequestBean(prb);
//			productRequestBeanList.add(pr);
//		}
//		
//		
//		
//		//send to view 
//		model.addAttribute("rb", rb );
//		model.addAttribute("prList", productRequestBeanList);
		
		return "home";
	}
	
	//REQUEST PARTNERSHIP
	@RequestMapping("/partnership/request")
	public String partnershipRequest(Model model) {
		model.addAttribute("organisationBeanDto", new OrganisationBeanDto());
		return "requestPartnership";
	}
	
	@RequestMapping("/partnership/request/save")
	public String saveRequest(OrganisationBeanDto organisationBeanDto, Model model) {
		
		try {
			ResponseEntity<Void> resp = customerManagmentServiceProxy.addOrganisation(organisationBeanDto);
			
			if(resp.getStatusCodeValue() == 201) {
				String info = "Your profile has been saved successfully and will be reviewed very shortly. Thank you.";
				model.addAttribute("info", info);
				
				return "requestPartnership";
			}else {
				String error ="Error occured while saving your request. Please try again.";
				model.addAttribute("error", error);
				return "requestPartnership";
			}
		
		} catch (ResponseStatusException e) {
			
			String error ="This email address is already registered.";
			model.addAttribute("error", error);
			return "requestPartnership";
		}
		
	}
	
	//WHAT IS PARTNERSHIP
	@RequestMapping("/partnership/whatisit")
	public String partnershipInfo(Model model) {
		return "whatIsPartnership";
	}
	
	
	@RequestMapping("/authentification/password/creation")
	public String passwordCreationRequest(Model model) {
		return "passwordCreation";
	}
	
	@RequestMapping("/authentification")
	public String authentificationRequest(Model model) {
		return "authentification";
	}
}
