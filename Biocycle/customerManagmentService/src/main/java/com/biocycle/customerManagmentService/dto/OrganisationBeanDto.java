package com.biocycle.customerManagmentService.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@ToString(of = {"id","organisationName","emailAddress","password","organisationAddress","collectionAddressList","phoneNumber","isDonor","isValidated"})
public class OrganisationBeanDto {

	private int id;
	private String organisationName;
	private String emailAddress;
	private String password;
	private AddressDto organisationAddress;
	private Map<String,CollectionSpotAddressDto> collectionAddressList;
	private String phoneNumber;
	private Boolean isDonor;
	private Boolean isValidated;
	
}
