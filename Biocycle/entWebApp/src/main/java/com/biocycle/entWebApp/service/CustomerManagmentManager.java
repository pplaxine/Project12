package com.biocycle.entWebApp.service;

import java.util.List;
import java.util.stream.Collectors;

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
import com.biocycle.entWebApp.helper.EntWebAppHelper;
import com.biocycle.entWebApp.proxy.MailServiceProxy;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;

/**
 * The Class CustomerManagmentManager.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Service
public class CustomerManagmentManager {
	
	/** The organisation CRUDMS proxy. */
	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	
	/** The organisation bean dto mapper. */
	@Autowired
	private OrganisationBeanDtoMapper organisationBeanDtoMapper;
	
	/** The mail service proxy. */
	@Autowired
	private MailServiceProxy mailServiceProxy;
	
	/**
	 * Partnership requests.
	 *
	 * @param model the model
	 * @return the string
	 */
	public String partnershipRequests(Model model) {
		
		try {
			ResponseEntity<List<OrganisationBeanDto>> resp = organisationCRUDMSProxy.findAllOrganisationByIsValidated(false);
			List<OrganisationBean> organisationBeanList = EntWebAppHelper.dtoListToEntityList(resp.getBody(), organisationBeanDtoMapper);
			
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
	
	/**
	 * Validate partnership.
	 *
	 * @param organisationId the organisation id
	 * @param red the red
	 * @return the string
	 */
	public String validatePartnership(int organisationId, RedirectAttributes red) {
		
		try {
			
			ResponseEntity<OrganisationBeanDto> resp = organisationCRUDMSProxy.findOrganisationById(organisationId);
			OrganisationBeanDto organisationBeanDto = resp.getBody();
			organisationBeanDto.setIsValidated(true);
			organisationCRUDMSProxy.updateOrganisation(organisationBeanDto);
			
			
			//String emailAddress = organisationBeanDto.getEmailAddress(); fixed address for demo
			String emailAddress = "p.plaxine@orange.fr";
			
			mailServiceProxy.sendPartnershipAcceptedEmail(organisationBeanDto.getOrganisationName(), emailAddress);
			
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
	
	/**
	 * Gets the all donor.
	 *
	 * @return the all donor
	 */
	public List<OrganisationBeanDto> getAllDonor() {
		try {
			List<OrganisationBeanDto> organisationBeanDtoList = organisationCRUDMSProxy.findAllOrganisationByIsValidated(true).getBody();
			List<OrganisationBeanDto> organisationBeanDtoListFiltered = organisationBeanDtoList.stream()
						.filter(e -> e.getIsDonor() == true)
						.collect(Collectors.toList());
			return organisationBeanDtoListFiltered;
		} catch (ResponseStatusException e) {
			throw e;
		}
	}
	
}
