package com.biocycle.customerWebApp.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.dto.view.ContainerViewDto;
import com.biocycle.customerWebApp.dto.view.GiveAwayViewDto;
import com.biocycle.customerWebApp.service.GiveAwayManager;
import com.biocycle.customerWebApp.service.OrganisationManager;

@Controller
public class DonorSideController {
	
	@Autowired
	private GiveAwayManager giveawayManager;
	@Autowired
	private OrganisationManager organisationManager;
	
	//GIVEAWAY 				---------------------------------------------------------------------------------------
	@RequestMapping("/user/giveaway")
	public String giveAway(ContainerViewDto containerViewDto, Model model, HttpSession session, Principal principal) {
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
	public String container(ContainerViewDto containerViewDto, Model model) {
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
	
	// OFFERS 				---------------------------------------------------------------------------------------	
	@RequestMapping("/user/donations")
	public String offers(Model model, HttpSession session, Principal principal) {
		organisationManager.addUserInfoToSession(session, principal); 
		return giveawayManager.giveAway(model, session);
	}
}
