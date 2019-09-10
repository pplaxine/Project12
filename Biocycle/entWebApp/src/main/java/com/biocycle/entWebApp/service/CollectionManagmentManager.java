package com.biocycle.entWebApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.GiveAwayBeanDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.view.GiveAwayViewDto;
import com.biocycle.entWebApp.helper.EntWebAppHelper;
import com.biocycle.entWebApp.proxy.GiveAwayCRUDMSProxy;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;

@Service
public class CollectionManagmentManager {
	
	@Autowired
	GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	@Autowired
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	
	public String donationOfferList(Model model) {
		
		List<GiveAwayViewDto> giveAwayViewDtoList = new ArrayList<>();
		
		try {
			ResponseEntity<List<GiveAwayBeanDto>> resp = giveAwayCRUDMSProxy.findActiveGiveAway();
			List<GiveAwayBeanDto> giveAwayBeanDtoList =  resp.getBody();
			for (GiveAwayBeanDto giveAwayBeanDto : giveAwayBeanDtoList) {
				OrganisationBeanDto organisationBeanDto = organisationCRUDMSProxy.findOrganisationById(giveAwayBeanDto.getOrganisationId()).getBody();
				GiveAwayViewDto giveAwayViewDto = EntWebAppHelper.giveAwayViewBuilder(organisationBeanDto, giveAwayBeanDto);
				giveAwayViewDtoList.add(giveAwayViewDto);
			}
			
			model.addAttribute("giveAwayViewDtoList", giveAwayViewDtoList);
			
		} catch (ResponseStatusException e) {
			if(e.getStatus() == HttpStatus.NOT_FOUND) {
				String info ="No donation offer awaiting to be validated";
				model.addAttribute("info", info);
				return "donationOfferList.html";
			}else {
				throw e;
			}
		}
		return "donationOfferList.html";
	}
	
	public String updateDonation(int giveAwayId, int containerId, Boolean status, RedirectAttributes red) {
		
		try {
			GiveAwayBeanDto giveAwayBeanDto = giveAwayCRUDMSProxy.findGiveAway(giveAwayId);
			giveAwayBeanDto.getContainerList().forEach(e -> {
				if(e.getId() == containerId) {
					e.setAccepted(status);
				}
			});
			giveAwayCRUDMSProxy.updateGiveAway(giveAwayBeanDto);
			
		} catch (ResponseStatusException e2) {
			String error ="Error occured while accepting donation. Please try again";
			red.addAttribute("error", error);
			return "redirect:/cse/donation/offers";
		}
		
		return "redirect:/cse/donation/offers";
	}
	
	

	
}
