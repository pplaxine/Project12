package com.biocycle.giveAwayCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 * Instantiates a new collection spot address dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new collection spot address dto.
 *
 * @param spotName the spot name
 * @param streetNumber the street number
 * @param streetName the street name
 * @param city the city
 * @param postCode the post code
 */
@AllArgsConstructor

/**
 * Gets the post code.
 *
 * @return the post code
 */
@Getter

/**
 * Sets the post code.
 *
 * @param postCode the new post code
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"spotName","streetNumber","streetName","city","postCode"})
public class CollectionSpotAddressDto {

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
