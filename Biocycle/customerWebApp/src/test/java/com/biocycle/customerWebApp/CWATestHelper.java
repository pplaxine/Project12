package com.biocycle.customerWebApp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.biocycle.customerWebApp.dto.AddressDto;
import com.biocycle.customerWebApp.dto.CollectionSpotAddressDto;
import com.biocycle.customerWebApp.dto.ContainerDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.view.ContainerViewDto;
import com.biocycle.customerWebApp.dto.view.GiveAwayViewDto;
import com.biocycle.customerWebApp.dto.view.PasswordCreationViewDto;

public class CWATestHelper {
	
	
	//-------- OBJECTS 
	
	//-- OrganisationBeanDto Object and embedded
	
	public static AddressDto getAddressBeanDto() {
		AddressDto addressDto = new AddressDto();
		addressDto.setStreetNumber("14");
		addressDto.setStreetName("rue du test");
		addressDto.setCity("TestCity");
		addressDto.setPostCode("75011");
		return addressDto;
	}
	
	public static CollectionSpotAddressDto getCollectionSpotAddressBeanDto() {
		CollectionSpotAddressDto addressDto = new CollectionSpotAddressDto();
		addressDto.setSpotName("Dépôt Paris 08");
		addressDto.setStreetNumber("14");
		addressDto.setStreetName("rue du test");
		addressDto.setCity("TestCity");
		addressDto.setPostCode("75011");
		return addressDto;
	}

	public static Map<String,CollectionSpotAddressDto> getCollectionAddressList(){
		Map<String,CollectionSpotAddressDto> cal = new HashMap<>();
		cal.put("Dépôt Paris 08", getCollectionSpotAddressBeanDto());
		return cal;
	}
	
	public static OrganisationBeanDto getOrganisationBeanDto(boolean withId) {
		OrganisationBeanDto orga = new OrganisationBeanDto();
		if(withId) {
			orga.setId(1);
		}
		orga.setOrganisationName("OrganisationTest");
		orga.setEmailAddress("test@orange.fr");
		orga.setOrganisationAddress(getAddressBeanDto());
		orga.setCollectionAddressList(getCollectionAddressList());
		orga.setPhoneNumber("0142424242");
		orga.setIsDonor(true);
		orga.setIsValidated(true);
		return orga;
	}
	
	//-- GiveAwayBeanDto Object and embedded
	
	public static ContainerDto getContainerDto() {
		ContainerDto cd = new ContainerDto();
		cd.setDescription("VanillaTest Description");
		cd.setIsCollected(false);
		cd.setAccepted(false);
		return cd;
	}
	
	public static List<ContainerDto> getContainerDtoList(){
		List<ContainerDto> containerDtoList = new ArrayList<>();
		containerDtoList.add(getContainerDto());
		return containerDtoList;
	}
	
	
	
	//-------- VIEWS OBJECTS 
	
	
	//-- OrganisationDtoView Object and embedded
	public static PasswordCreationViewDto getPasswordCreationViewDto() {
		PasswordCreationViewDto passwordCreationViewDto = new PasswordCreationViewDto();
		passwordCreationViewDto.setPassword("test01");
		passwordCreationViewDto.setConfPassword("test01");
		passwordCreationViewDto.setEmail("test@orange.fr");
		return passwordCreationViewDto;
	}
	
	//-- GiveAwayView Object and embedded 
	public static ContainerViewDto getContainterViewDto() {
		ContainerViewDto cvd = new ContainerViewDto();
		cvd.setName("VanillaTest");
		cvd.setDescription("VanillaTest Description");
		cvd.setIsCollected(false);
		cvd.setAccepted(false);
		return cvd;
	}
	
	public static Map<String, ContainerViewDto> getContainerViewDtoMap(){
		Map<String, ContainerViewDto> containerViewDtoMap = new HashMap<>();
		containerViewDtoMap.put("container1", getContainterViewDto());
		return containerViewDtoMap;
	}
	
	public static GiveAwayViewDto getGiveAwayViewDto() {
		GiveAwayViewDto gavd = new GiveAwayViewDto();
		gavd.setOrganisationId(1);
		gavd.setAvailableToBeCollectedFrom(LocalDateTime.now());
		gavd.setCollectionSpotAddress("Dépôt Paris 08");
		gavd.setContainerList(getContainerDtoList());
		return gavd;
	}

}
