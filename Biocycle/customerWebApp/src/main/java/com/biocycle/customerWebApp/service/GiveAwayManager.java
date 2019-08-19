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

import com.biocycle.customerWebApp.dto.ContainerDto;
import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.view.ContainerViewDto;
import com.biocycle.customerWebApp.proxy.GiveAwayCRUDMSProxy;

@Service
public class GiveAwayManager {


	@Autowired
	private GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	
	@SuppressWarnings("unchecked")
	public void addContainerToMapInSession(ContainerViewDto containerViewDto, HttpSession session){
		
		Map<String, ContainerViewDto> containerViewDtoMap;
		
		if(containerViewDto.getName() != null) {
			if(session.getAttribute("containerViewDtoMap") == null) {
				containerViewDtoMap = new HashMap<>();
			}else {
				containerViewDtoMap = (Map<String, ContainerViewDto>)session.getAttribute("containerViewDtoMap");
			}

			containerViewDtoMap.put(containerViewDto.getName(), containerViewDto);
			session.setAttribute("containerViewDtoMap", containerViewDtoMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String createGiveAway(GiveAwayBeanDto giveAwayBeanDto, Model model, RedirectAttributes red, HttpSession session) {
		
		
		
		Map<String, ContainerViewDto> containerViewDtoMap = (Map<String, ContainerViewDto>)session.getAttribute("containerViewDtoMap");
		
		if( containerViewDtoMap ==null || containerViewDtoMap.size() <= 0) {
			String error ="You have to add at least one container to your donation.";
			model.addAttribute("error", error);
			return "createGiveAway";
		}
		
		try {
				
				//list of container added to giveAway
				List<ContainerDto> containerDtoList = ViewDtoMapToDtoList(containerViewDtoMap);
				giveAwayBeanDto.setContainerList(containerDtoList);
				
				//organisation id 
				OrganisationBeanDto organisationBeanDto = (OrganisationBeanDto)session.getAttribute("organisation");
				giveAwayBeanDto.setOrganisationId(organisationBeanDto.getId());
				
				//persist give away
				giveAwayCRUDMSProxy.addGiveAway(giveAwayBeanDto);
				containerViewDtoMap.clear();
			} catch (ResponseStatusException e) {
				String error ="Error occured while persisting your donation.";
				model.addAttribute("error", error);
				return "createGiveAway";
			}
		
		return "redirect:/user/giveaway";
	}
	
	@SuppressWarnings("unchecked")
	public String removeContainer(String ref, Model model, HttpSession session) {
		Map<String, ContainerViewDto> containerViewDtoMap = (Map<String, ContainerViewDto>)session.getAttribute("containerViewDtoMap");
		containerViewDtoMap.remove(ref);
		session.setAttribute("containerViewDtoMap", containerViewDtoMap);
		return "redirect:/user/giveaway";
	}
	
	
	//UTILITY METHODS
	private List<ContainerDto> ViewDtoMapToDtoList(Map<String, ContainerViewDto> containerViewDtoMap) {
		List<ContainerDto> containerDtoList = new ArrayList<>();
		containerViewDtoMap.values().forEach(e -> {
			ContainerDto containerDto = containerViewDtoToContainerDto(e);
			containerDtoList.add(containerDto);
		});
		return containerDtoList;
	}
	
	private ContainerDto containerViewDtoToContainerDto(ContainerViewDto containerViewDto) {
		ContainerDto containerDto = new ContainerDto();
		containerDto.setDescription(containerViewDto.getDescription());
		return containerDto;
	}
	
}
