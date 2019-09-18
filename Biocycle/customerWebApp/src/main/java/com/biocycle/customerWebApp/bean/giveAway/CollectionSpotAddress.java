package com.biocycle.customerWebApp.bean.giveAway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Instantiates a new collection spot address.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new collection spot address.
 *
 * @param spotName the spot name
 * @param streetNumber the street number
 * @param streetName the street name
 * @param city the city
 * @param postCode the post code
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"spotName","streetNumber","streetName","city","postCode"})

public class CollectionSpotAddress {

	/** The spot name. */
	private String spotName;
	
	/** The street number. */
	private String streetNumber;
	
	/** The street name. */
	private String streetName;
	
	/** The city. */
	private String city;
	
	/** The post code. */
	private String postCode;
}
