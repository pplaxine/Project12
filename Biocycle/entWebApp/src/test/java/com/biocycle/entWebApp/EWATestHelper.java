package com.biocycle.entWebApp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.biocycle.entWebApp.bean.organisation.CollectionSpotAddress;
import com.biocycle.entWebApp.bean.productBatch.UnitOfMeasure;
import com.biocycle.entWebApp.dto.AddressDto;
import com.biocycle.entWebApp.dto.CollectionSpotAddressDto;
import com.biocycle.entWebApp.dto.ContainerDto;
import com.biocycle.entWebApp.dto.GiveAwayBeanDto;
import com.biocycle.entWebApp.dto.OfferBeanDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.dto.StorageContainerBeanDto;
import com.biocycle.entWebApp.dto.view.OfferViewDto;
import com.biocycle.entWebApp.dto.view.RedistributionViewDto;

public class EWATestHelper {
	
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
		
		public static CollectionSpotAddress getCollectionSpotAddress() {
			CollectionSpotAddress csa = new CollectionSpotAddress();
			csa.setSpotName("Dépôt Paris 08");
			csa.setStreetNumber("14");
			csa.setStreetName("rue du test");
			csa.setCity("TestCity");
			csa.setPostCode("75011");
			return csa;
		}
		
		public static GiveAwayBeanDto getGiveAwayBeanDto() {
			GiveAwayBeanDto gabd = new GiveAwayBeanDto();
			
			gabd.setAvailableToBeCollectedFrom(LocalDateTime.now());
			gabd.setContainerList(getContainerDtoList());
			gabd.setOrganisationId(1);
			gabd.setCollectionSpotAddress(getCollectionSpotAddress());
			return gabd;
		}
		
		public static List<GiveAwayBeanDto> getGiveAwayBeanDtoList(){
			List<GiveAwayBeanDto> giveAwayBeanDtoList = new ArrayList<>();
			giveAwayBeanDtoList.add(getGiveAwayBeanDto());
			return giveAwayBeanDtoList;
		}
		
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
			rbd.setProductRequestId(getStorageContainerIdList());
			rbd.setOrganisationId(2);
			return rbd;
		}
		
		public static List<RedistributionBeanDto> getRedistributionBeanDtoList(){
			List<RedistributionBeanDto> rbdList = new ArrayList<>();
			rbdList.add(getRedistributionBeanDto());
			return rbdList;
		}
		
		public static List<ProductRequestBeanDto> getProductRequestBeanDtoList(){
			List<ProductRequestBeanDto> ProductRequestBeanDtoList = new ArrayList<>();
			ProductRequestBeanDtoList.add(getProductRequestBeanDto());
			return ProductRequestBeanDtoList;
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
		
		//-- ProductBatchBeanDto Object and embedded
		
		public static Map<Integer, ProductBatchBeanDto> getProductBatchBeanDtoMap(){
			Map<Integer, ProductBatchBeanDto> productBatchBeanDtoMap = new HashMap<>();
			productBatchBeanDtoMap.put(1, getProductBatchBeanDto());
			return productBatchBeanDtoMap;
		}
		
		public static StorageContainerBeanDto getStorageContainerBeanDto(boolean available) {
			StorageContainerBeanDto scbd = new StorageContainerBeanDto();
			scbd.setId(1);
			scbd.setLevelRef(1);
			scbd.setShelfRef("A");
			scbd.setRowRef(1);
			scbd.setIsAvailable(available);
			return scbd;	
		}
		
		public static List<StorageContainerBeanDto> getStorageContainerBeanDtoList(boolean StorageContainerAvailable){
			List<StorageContainerBeanDto> StorageContainerBeanDtoList = new ArrayList<>();
			StorageContainerBeanDtoList.add(getStorageContainerBeanDto(StorageContainerAvailable));
			return StorageContainerBeanDtoList;
		}
		
		public static List<Integer> getStorageContainerIdList(){
			List<Integer> storageContainerIdList = new ArrayList<>();
			storageContainerIdList.add(2);
			return storageContainerIdList;
		}
		
		public static List<ProductBatchBeanDto> getProductBatchBeanDtoList(){
			List<ProductBatchBeanDto> productBatchbeanDtoList = new ArrayList<>();
			productBatchbeanDtoList.add(getProductBatchBeanDto());
			return productBatchbeanDtoList;
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
			pbbd.setStorageContainerId(getStorageContainerIdList());
			return pbbd;
		}
		
		//-------- VIEW OBJECTS
		public static OfferViewDto getOfferViewDto() {
			OfferViewDto ovd = new OfferViewDto();
			ovd.setId(1);
			ovd.setOfferEndingDate(LocalDate.now().plusDays(1));
			ovd.setProductBatchBeanDtoList(getProductBatchBeanDtoList());
			return ovd;
		}
		
		
		public static RedistributionViewDto getRedistributionViewDto() {
			RedistributionViewDto rvd = new RedistributionViewDto();
			rvd.setId(1);
			rvd.setOfferViewDto(getOfferViewDto());
			rvd.setOrganisationBeanDto(getOrganisationBeanDto(true));
			rvd.setProductRequestBeanDtoList(getProductRequestBeanDtoList());
			return rvd;
		}

}
