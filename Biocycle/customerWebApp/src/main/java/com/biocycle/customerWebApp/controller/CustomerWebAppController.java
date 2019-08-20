package com.biocycle.customerWebApp.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.view.AuthentificationViewDto;
import com.biocycle.customerWebApp.dto.view.ContainerViewDto;
import com.biocycle.customerWebApp.dto.view.GiveAwayViewDto;
import com.biocycle.customerWebApp.dto.view.PasswordCreationViewDto;
import com.biocycle.customerWebApp.service.GiveAwayManager;
import com.biocycle.customerWebApp.service.OrganisationManager;

@Controller
public class CustomerWebAppController {

	@Autowired
	private GiveAwayManager giveawayManager;
	
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
	
	//REQUEST PARTNERSHIP
	@RequestMapping("/partnership/request")
	public String partnershipRequest(Model model) {
		model.addAttribute("organisationBeanDto", new OrganisationBeanDto());
		return "requestPartnership";
	}
	
	@RequestMapping("/partnership/request/save")
	public String saveRequest(OrganisationBeanDto organisationBeanDto, Model model, RedirectAttributes red) {
		return organisationManager.saveRequest(organisationBeanDto, model, red);
	}
	
	//WHAT IS PARTNERSHIP
	@RequestMapping("/partnership/whatisit")
	public String partnershipInfo(Model model) {
		return "whatIsPartnership";
	}
	
	//PASSWORD CREATION 
	@RequestMapping("/authentification/password/creation")
	public String passwordCreationRequest(Model model) {
		model.addAttribute("passwordCreationViewDto", new PasswordCreationViewDto());
		return "passwordCreation";
	}
	
	@RequestMapping("/authentification/password/save")
	public String savePassword(PasswordCreationViewDto passwordCreationViewDto, Model model, RedirectAttributes red) {
		return organisationManager.savePassword(passwordCreationViewDto, model, red);
	}
	

	//GIVEAWAY 
	@RequestMapping("/user/giveaway")
	public String GiveAway(ContainerViewDto containerViewDto, Model model, HttpSession session, Principal principal) {
		organisationManager.addUserInfoToSession(session, principal); 
		//containers
		giveawayManager.addContainerToMapInSession(containerViewDto, session);
		//giveAway
		model.addAttribute("giveAwayViewDto", new GiveAwayViewDto());
		return "createGiveAway";
	}
	@RequestMapping("/user/giveaway/create")
	public String createGiveAway(GiveAwayViewDto giveAwayViewDto, Model model, RedirectAttributes red, HttpSession session) {
		return giveawayManager.createGiveAway(giveAwayViewDto, model, red, session);
	}
	@RequestMapping("/user/giveaway/container")
	public String Container(ContainerViewDto containerViewDto, Model model) {
		model.addAttribute("containerDto", new ContainerViewDto());
		return "createContainer";
	}
	@RequestMapping("/user/giveaway/container/add")
	public String addContainer(ContainerViewDto containerViewDto, Model model, RedirectAttributes red) {
		red.addFlashAttribute("containerViewDto", containerViewDto);
		return "redirect:/user/giveaway";
	}
	@RequestMapping("/user/giveaway/container/remove/{ref}")
	public String removeContainer(@PathVariable String ref, Model model, HttpSession session) {
		return giveawayManager.removeContainer(ref, model, session);
	}
	

}
