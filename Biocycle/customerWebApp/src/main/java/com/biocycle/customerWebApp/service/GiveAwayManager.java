package com.biocycle.customerWebApp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.dto.CollectionSpotAddressDto;
import com.biocycle.customerWebApp.dto.ContainerDto;
import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.view.ContainerViewDto;
import com.biocycle.customerWebApp.dto.view.GiveAwayViewDto;
import com.biocycle.customerWebApp.proxy.GiveAwayCRUDMSProxy;

@Service
public class GiveAwayManager {


	@Autowired
	private GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	
	//---- GIVE AWAY CREATION 
	@SuppressWarnings("unchecked")
	public void addContainerToMapInSession(ContainerViewDto containerViewDto, HttpSession session){
		
		Map<String, ContainerViewDto> containerViewDtoMap;
		
		if(containerViewDto.getName() != null) {
			if(session.getAttribute("containerViewDtoMap") == null) {
				containerViewDtoMap = new HashMap<>();
			}else {
				containerViewDtoMap = (Map<String, ContainerViewDto>)session.getAttribute("containerViewDtoMap");
			}

			//generate id
			containerViewDto.setId(UUID.randomUUID().toString());
			
			containerViewDtoMap.put(containerViewDto.getId(), containerViewDto);
			session.setAttribute("containerViewDtoMap", containerViewDtoMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String createGiveAway(GiveAwayViewDto giveAwayViewDto, Model model, RedirectAttributes red, HttpSession session) {
		
		Map<String, ContainerViewDto> containerViewDtoMap = (Map<String, ContainerViewDto>)session.getAttribute("containerViewDtoMap");
		
		if( containerViewDtoMap == null || containerViewDtoMap.size() <= 0) {
			String error ="You have to add at least one container to your donation.";
			model.addAttribute("error", error);
			return "createGiveAway";
		}
		
		try {
				GiveAwayBeanDto giveAwayBeanDto = new GiveAwayBeanDto();
				giveAwayBeanDto.setIsCollected(false);
				
				//list of container added to giveAway
				List<ContainerDto> containerDtoList = ViewDtoMapToDtoList(containerViewDtoMap);
				giveAwayBeanDto.setContainerList(containerDtoList);
				
				//organisation id 
				OrganisationBeanDto organisationBeanDto = (OrganisationBeanDto)session.getAttribute("organisation");
				giveAwayBeanDto.setOrganisationId(organisationBeanDto.getId());
				
				//collection spot address
				String collectionSpotName = giveAwayViewDto.getCollectionSpotAddress();
				CollectionSpotAddressDto collectionSpotAddressDto = organisationBeanDto.getCollectionAddressList().get(collectionSpotName);
				giveAwayBeanDto.setCollectionSpotAddress(collectionSpotAddressDto);
				
				//date availibility
				giveAwayBeanDto.setAvailableToBeCollectedFrom(giveAwayViewDto.getAvailableToBeCollectedFrom());
				
				//persist give away
				giveAwayCRUDMSProxy.addGiveAway(giveAwayBeanDto);
				containerViewDtoMap.clear();
				
				String info = "Your donation has been taken into account. Thank you";
				red.addFlashAttribute("info", info);
				
						
			} catch (Exception e) {
				if(e.getClass() == ResponseStatusException.class) {
					String error ="Error occured while persisting your donation.";
					model.addAttribute("error", error);
					return "createGiveAway";
				}else {
					String error ="Error occured while persisting your donation.";
					model.addAttribute("error", error);
					e.printStackTrace();
					return "createGiveAway";
				}
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
	
	//---- GIVE AWAY MANAGMENT 
	
	public String giveAway(Model model, HttpSession session) {
		OrganisationBeanDto organisationBeanDto = (OrganisationBeanDto)session.getAttribute("organisation");
		
		try {
			ResponseEntity<List<GiveAwayBeanDto>> giveAwayBeanDtoList = giveAwayCRUDMSProxy.findAllGiveAwayByOrganisationId(organisationBeanDto.getId());
			model.addAttribute("giveAwayBeanDtoList", allContainerRefusedChecker(giveAwayBeanDtoList.getBody()));
			
		} catch (Exception e) {
			if(e.getClass() == ResponseStatusException.class) {
				String error ="Error occured while researching for your donations.";
				model.addAttribute("error", error);
				return "donorPersoSpace";
			}else {
				String error ="Error occured while researching for your donations.";
				model.addAttribute("error", error);
				System.out.println(e.getMessage());
				return "donorPersoSpace";
			}
		}
		
		
		return "donorPersoSpace";
	}
	
	
	
	//UTILITY METHODS
	protected List<ContainerDto> ViewDtoMapToDtoList(Map<String, ContainerViewDto> containerViewDtoMap) {
		List<ContainerDto> containerDtoList = new ArrayList<>();
		containerViewDtoMap.values().forEach(e -> {
			ContainerDto containerDto = containerViewDtoToContainerDto(e);
			containerDtoList.add(containerDto);
		});
		return containerDtoList;
	}
	
	protected ContainerDto containerViewDtoToContainerDto(ContainerViewDto containerViewDto) {
		ContainerDto containerDto = new ContainerDto();
		containerDto.setDescription(containerViewDto.getDescription());
		containerDto.setIsCollected(false);
		return containerDto;
	}
	
	protected List<GiveAwayBeanDto> allContainerRefusedChecker(List<GiveAwayBeanDto> giveAwayBeanDtoListIn) {
		List<GiveAwayBeanDto> giveAwayBeanDtout = new ArrayList<>();  
		giveAwayBeanDtoListIn.forEach(e -> {
			List<ContainerDto> containerDtoList = e.getContainerList();

			boolean allContainerRefused = true;
			for (ContainerDto containerDto : containerDtoList) {
				if(containerDto.getAccepted() == null || containerDto.getAccepted() == true) {		
					allContainerRefused = false;
				}
			}
			
			if(allContainerRefused) {
				e.setCollectionDate(null);
				e.setIsCollected(true);
			}
			giveAwayBeanDtout.add(e);
		});
		return giveAwayBeanDtoListIn;
	}
}
