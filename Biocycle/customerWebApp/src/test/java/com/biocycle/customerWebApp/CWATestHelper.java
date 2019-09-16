package com.biocycle.customerWebApp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.biocycle.customerWebApp.bean.redistribution.UnitOfMeasure;
import com.biocycle.customerWebApp.dto.AddressDto;
import com.biocycle.customerWebApp.dto.CollectionSpotAddressDto;
import com.biocycle.customerWebApp.dto.ContainerDto;
import com.biocycle.customerWebApp.dto.OfferBeanDto;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.ProductBatchBeanDto;
import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;
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
	
	//-- RedistributionBeanDto Object and embedded
	public static OfferBeanDto getOfferBeanDto() {
		OfferBeanDto obd = new OfferBeanDto();
		obd.setId(1);
		obd.setIsAccepted(false);
		obd.setOfferEndingDate(LocalDate.now().plusDays(1));
		obd.setAvailableForCollection(LocalDate.now());
		List<Integer> productBatchIdList = new ArrayList<>();
		productBatchIdList.add(1);
		obd.setProductBatchIdList(productBatchIdList);
		return obd;
	}
	
	public static RedistributionBeanDto getRedistributionBeanDto() {
		RedistributionBeanDto rbd = new RedistributionBeanDto();
		rbd.setId(1);
		rbd.setIsCompleted(false);
		rbd.setOfferId(1);
		rbd.setOrganisationId(2);
		return rbd;
	}
	
	public static List<RedistributionBeanDto> getRedistributionBeanDtoList(){
		List<RedistributionBeanDto> rbdList = new ArrayList<>();
		rbdList.add(getRedistributionBeanDto());
		return rbdList;
	}
	
	public static ProductRequestBeanDto getProductRequestBeanDto() {
		ProductRequestBeanDto prbd = new ProductRequestBeanDto();
		prbd.setId(1);
		prbd.setIsAccepted(false);
		prbd.setProductRequested("productTest");
		prbd.setQuantity(BigDecimal.valueOf(40));
		prbd.setUnitOfMeasure(UnitOfMeasure.KG);
		return prbd;
	}
	
	public static Map<String,ProductRequestBeanDto> getProductRequestBeanDtoMap(){
		Map<String,ProductRequestBeanDto> prbdMap = new HashMap<>();
		prbdMap.put("productTest", getProductRequestBeanDto());
		return prbdMap;
	}
	
	public static ProductBatchBeanDto getProductBatchBeanDto() {
		ProductBatchBeanDto pbbd = new ProductBatchBeanDto();
		pbbd.setDonorId(1);
		pbbd.setDescription("testProductBatch");
		pbbd.setId(1);
		pbbd.setIsAvailable(false);
		pbbd.setIsAwaitingForCollection(false);
		pbbd.setName("TestBatch");
		pbbd.setQuantity(BigDecimal.valueOf(40));
		pbbd.setUnitOfMeasure(UnitOfMeasure.KG);
		pbbd.setToBeUsedBy(LocalDate.now().plusDays(1));
		return pbbd;
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
