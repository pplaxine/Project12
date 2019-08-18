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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.bean.productRequest.ProductRequestBean;
import com.biocycle.customerWebApp.bean.redistribution.RedistributionBean;
import com.biocycle.customerWebApp.dto.CollectionSpotAddressDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.PasswordCreationDto;
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
	public String saveRequest(OrganisationBeanDto organisationBeanDto, Model model, RedirectAttributes red) {
		
		try {
			ResponseEntity<Void> resp = customerManagmentServiceProxy.addOrganisation(organisationBeanDto);
			
			if(resp.getStatusCodeValue() == 201) {
				String info = "Your profile has been saved successfully and will be reviewed very shortly. Thank you.";
				red.addFlashAttribute("info", info);	
				return "redirect:/partnership/request";

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
	
	//PASSWORD CREATION 
	@RequestMapping("/authentification/password/creation")
	public String passwordCreationRequest(Model model) {
		model.addAttribute("passwordCreationDto", new PasswordCreationDto());
		
		return "passwordCreation";
	}
	
	@RequestMapping("/authentification/password/save")
	public String savePassword(PasswordCreationDto passwordCreationDto, Model model, RedirectAttributes red) {
		
		//Password different
		if(!passwordCreationDto.getPassword().equals(passwordCreationDto.getConfPassword())) {
			String error ="Password and password confirmation must not be different.";
			model.addAttribute("error", error);
			return "passwordCreation";
		}
		//min length
		if(passwordCreationDto.getPassword().length() < 4 ) {
			String error ="Password length must be longer than 3 characters.";
			model.addAttribute("error", error);
			return "passwordCreation";
		}
		
		//Persist Password
		OrganisationBeanDto organisationBeanDto = new OrganisationBeanDto();
		organisationBeanDto.setEmailAddress(passwordCreationDto.getEmail());
		organisationBeanDto.setPassword(passwordCreationDto.getPassword());
		
		try {
			ResponseEntity<Void> resp = customerManagmentServiceProxy.addPassword(organisationBeanDto);

			if(resp.getStatusCodeValue() == 200) {
				String info = "Your password has been registrered successfully.";
				red.addFlashAttribute("info", info);	
				return "redirect:/authentification/password/creation";
			}else {
				String error ="Error occured while saving your password. Please try again.";
				model.addAttribute("error", error);
				return "passwordCreation";
			}
		} catch (ResponseStatusException e) {
			if(e.getStatus() == HttpStatus.CONFLICT) {
				String error ="A password has been already created for this account.";
				model.addAttribute("error", error);
				return "passwordCreation";
			}else if(e.getStatus() == HttpStatus.NOT_FOUND) {
				String error ="No account related to this email could be found.";
				model.addAttribute("error", error);
				return "passwordCreation";
			}else {
				throw e;
			}
		}
	
	}
	
	
	
	@RequestMapping("/authentification")
	public String authentificationRequest(Model model) {
		return "authentification";
	}
}
