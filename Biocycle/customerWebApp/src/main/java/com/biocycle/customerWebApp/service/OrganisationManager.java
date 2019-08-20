package com.biocycle.customerWebApp.service;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.view.PasswordCreationViewDto;
import com.biocycle.customerWebApp.proxy.CustomerManagmentServiceProxy;
import com.biocycle.customerWebApp.proxy.OrganisationCRUDMSProxy;

@Service
public class OrganisationManager {
	
	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@Autowired
	private CustomerManagmentServiceProxy customerManagmentServiceProxy;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public void addUserInfoToSession(HttpSession session, Principal principal) {
		
		if(session.getAttribute("organisation") == null) {
			try {
				//get email from Security 
				OrganisationBeanDto organisationBeanDto = organisationCRUDMSProxy.findOrganisationByEmail(principal.getName()).getBody();		
				session.setAttribute("organisation", organisationBeanDto);
			} catch (ResponseStatusException e) {
				if(e.getStatus() == HttpStatus.NOT_FOUND) {
					System.out.println(e.getMessage());
				}else {
					throw e;
				}
			}
		}
	}
	
	public String  saveRequest(OrganisationBeanDto organisationBeanDto, Model model, RedirectAttributes red) {
		
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
	
	
	public String savePassword(PasswordCreationViewDto passwordCreationViewDto, Model model, RedirectAttributes red) {
		//Password different
		if(!passwordCreationViewDto.getPassword().equals(passwordCreationViewDto.getConfPassword())) {
			String error ="Password and password confirmation must not be different.";
			model.addAttribute("error", error);
			return "passwordCreation";
		}
		//min length
		if(passwordCreationViewDto.getPassword().length() < 4 ) {
			String error ="Password length must be longer than 3 characters.";
			model.addAttribute("error", error);
			return "passwordCreation";
		}
		
		//Persist Password
		OrganisationBeanDto organisationBeanDto = new OrganisationBeanDto();
		organisationBeanDto.setEmailAddress(passwordCreationViewDto.getEmail());
		organisationBeanDto.setPassword(bCryptPasswordEncoder.encode(passwordCreationViewDto.getPassword()));
		
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
}
