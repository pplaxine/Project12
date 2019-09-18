package com.biocycle.entWebApp.bean.organisation;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new organisation bean.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new organisation bean.
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
public class OrganisationBean {

	/** The id. */
	private int id;
	
	/** The organisation name. */
	private String organisationName;
	
	/** The email address. */
	private String emailAddress;
	
	/** The password. */
	private String password;
	
	/** The organisation address. */
	private Address organisationAddress;
	
	/** The collection address list. */
	private Map<String,CollectionSpotAddress> collectionAddressList;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** Is organisation a donor. */
	private Boolean isDonor;
	
	/** Is organisation validated. */
	private Boolean isValidated;
	
}
