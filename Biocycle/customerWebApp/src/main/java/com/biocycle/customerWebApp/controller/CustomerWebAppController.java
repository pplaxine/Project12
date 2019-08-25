package com.biocycle.customerWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.dto.ContainerDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.view.PasswordCreationViewDto;
import com.biocycle.customerWebApp.service.OrganisationManager;

@Controller
public class CustomerWebAppController {


	
	@Autowired
	private OrganisationManager organisationManager;
	
	@RequestMapping("/")
	public String home(Model model, HttpSession session) {
		
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
	
	//REQUEST PARTNERSHIP 	---------------------------------------------------------------------------------------
	@RequestMapping("/partnership/request")
	public String partnershipRequest(Model model) {
		model.addAttribute("organisationBeanDto", new OrganisationBeanDto());
		return "requestPartnership";
	}
	
	@RequestMapping("/partnership/request/save")
	public String saveRequest(OrganisationBeanDto organisationBeanDto, Model model, RedirectAttributes red) {
		return organisationManager.saveRequest(organisationBeanDto, model, red);
	}
	
	//WHAT IS PARTNERSHIP	---------------------------------------------------------------------------------------
	@RequestMapping("/partnership/whatisit")
	public String partnershipInfo(Model model) {
		return "whatIsPartnership";
	}
	
	//PASSWORD CREATION		---------------------------------------------------------------------------------------
	@RequestMapping("/authentification/password/creation")
	public String passwordCreationRequest(Model model) {
		model.addAttribute("passwordCreationViewDto", new PasswordCreationViewDto());
		return "passwordCreation";
	}
	@RequestMapping("/authentification/password/save")
	public String savePassword(PasswordCreationViewDto passwordCreationViewDto, Model model, RedirectAttributes red) {
		return organisationManager.savePassword(passwordCreationViewDto, model, red);
	}
	

	
	//TEST
	@RequestMapping("/test")
	public String muController() {
		
		ContainerDto container = new ContainerDto( 1, "First Container", true, 3, false);
		
		
		return container.toString();
	}
	

}
