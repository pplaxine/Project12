package com.biocycle.customerManagmentService.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	
	public ResponseEntity<Void> addPassword(OrganisationBeanDto organisationBeanDto){
		
		String email = organisationBeanDto.getEmailAddress();
		
		try {
			ResponseEntity<OrganisationBeanDto> resp = organisationCRUDMSProxy.findOrganisationByEmail(email);
			OrganisationBeanDto organisationBeanDtoResp = resp.getBody();
			
			if(organisationBeanDtoResp.getPassword() != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			
			organisationBeanDtoResp.setPassword(organisationBeanDto.getPassword());
			return organisationCRUDMSProxy.updateOrganisation(organisationBeanDtoResp);
			
					
		} catch (ResponseStatusException e) {
			if(e.getStatus() == HttpStatus.NOT_FOUND ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			throw e;
		}

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
