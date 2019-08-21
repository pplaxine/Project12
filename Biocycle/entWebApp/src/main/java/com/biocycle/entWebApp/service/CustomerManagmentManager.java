package com.biocycle.entWebApp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.bean.organisation.OrganisationBean;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.mapper.OrganisationBeanDtoMapper;
import com.biocycle.entWebApp.helper.entWebAppHelper;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;

@Service
public class CustomerManagmentManager {
	
	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@Autowired
	private OrganisationBeanDtoMapper organisationBeanDtoMapper;
	
	public String partnershipRequests(Model model, HttpSession session) {
		
		try {
			//Not validated organisation 
			ResponseEntity<List<OrganisationBeanDto>> resp = organisationCRUDMSProxy.findAllOrganisationByIsValidated(false);
			List<OrganisationBean> organisationBeanList = entWebAppHelper.dtoListToEntityList(resp.getBody(), organisationBeanDtoMapper);
			
			model.addAttribute("organisationBeanList", organisationBeanList);
		} catch (ResponseStatusException e) {
		
			if(e.getStatus() == HttpStatus.NOT_FOUND) {
				String info ="No partnership awaiting to be validated";
				model.addAttribute("info", info);
				return "partnershipRequests";
			}else {
				throw e;
			}
		}
		
		return "partnershipRequests";
	}
	
	public String validatePartnership(int organisationId, RedirectAttributes red) {
		
		try {
			
			ResponseEntity<OrganisationBeanDto> resp = organisationCRUDMSProxy.findOrganisationById(organisationId);
			OrganisationBeanDto organisationBeanDto = resp.getBody();
			organisationBeanDto.setIsValidated(true);
			organisationCRUDMSProxy.updateOrganisation(organisationBeanDto);
			
		} catch (ResponseStatusException e) {
			if(e.getStatus() == HttpStatus.NOT_FOUND) {
				String error ="Error while validating partnership. Please try again.";
				red.addFlashAttribute("error", error);
				return "redirect:/cme/partnerships/requests";
			}else {
				throw e;
			}
		}
		return "redirect:/cme/partnerships/requests";
	}
	
}
