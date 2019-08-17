package com.biocycle.customerManagmentService.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biocycle.customerManagmentService.bean.Address;
import com.biocycle.customerManagmentService.bean.CollectionSpotAddress;
import com.biocycle.customerManagmentService.bean.OrganisationBean;
import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;
import com.biocycle.customerManagmentService.dto.mapper.OrganisationBeanDtoMapper;
import com.biocycle.customerManagmentService.proxy.OrganisationCRUDMSProxy;

@Service
public class OrganisationManager {

	private final String FIRST_ADDRESS_SPOT_NAME = "Headquarters";
	
	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@Autowired
	private OrganisationBeanDtoMapper organisationBeanDtoMapper;

	
	public ResponseEntity<Void> addOrganisation(OrganisationBeanDto organisationBeanDto){
		
		OrganisationBean organisationBean = organisationBeanDtoMapper.organisatonBeanDtoToOrganisationBean(organisationBeanDto);
		organisationBean = addFirstSpotAddressFromOrganisationAddress(organisationBean);
		organisationBean.setIsValidated(false);
		
		OrganisationBeanDto organisationBeanDtoToSend = organisationBeanDtoMapper.organisationBeanToOrganisationBeanDto(organisationBean);
		
		return organisationCRUDMSProxy.addOrganisation(organisationBeanDtoToSend);
	}
	
	//UTILITY METHOD 
	private OrganisationBean addFirstSpotAddressFromOrganisationAddress(OrganisationBean organisationBean) {
		if ( organisationBean == null ) {
            return null;
        }
		Map<String, CollectionSpotAddress> collectionSpotAddressMap = new HashMap<String, CollectionSpotAddress>();
		CollectionSpotAddress collectionSpotAddress = new CollectionSpotAddress();
		
		Address address = organisationBean.getOrganisationAddress();
		collectionSpotAddress.setSpotName(FIRST_ADDRESS_SPOT_NAME);
		collectionSpotAddress.setStreetNumber(address.getStreetNumber());
		collectionSpotAddress.setStreetName(address.getStreetName());
		collectionSpotAddress.setCity(address.getCity());
		collectionSpotAddress.setPostCode(address.getPostCode());

		collectionSpotAddressMap.put(FIRST_ADDRESS_SPOT_NAME, collectionSpotAddress);
		organisationBean.setCollectionAddressList(collectionSpotAddressMap);
		
		return organisationBean;
		
	}
}
