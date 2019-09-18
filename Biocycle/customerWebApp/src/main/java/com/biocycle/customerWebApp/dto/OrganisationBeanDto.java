package com.biocycle.customerWebApp.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new organisation bean dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new organisation bean dto.
 *
 * @param id the id
 * @param organisationName the organisation name
 * @param emailAddress the email address
 * @param password the password
 * @param organisationAddress the organisation address
 * @param collectionAddressList the collection address list
 * @param phoneNumber the phone number
 * @param isDonor the is donor
 * @param isValidated the is validated
 */
@AllArgsConstructor
@Getter 
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationName","emailAddress","password","organisationAddress","collectionAddressList","phoneNumber","isDonor","isValidated"})
public class OrganisationBeanDto {

	/** The id. */
	private int id;
	
	/** The organisation name. */
	private String organisationName;
	
	/** The email address. */
	private String emailAddress;
	
	/** The password. */
	private String password;
	
	/** The organisation address. */
	private AddressDto organisationAddress;
	
	/** The collection address list. */
	private Map<String,CollectionSpotAddressDto> collectionAddressList;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** Is organisation a donor. */
	private Boolean isDonor;
	
	/** Is organisation validated. */
	private Boolean isValidated;
	
}
