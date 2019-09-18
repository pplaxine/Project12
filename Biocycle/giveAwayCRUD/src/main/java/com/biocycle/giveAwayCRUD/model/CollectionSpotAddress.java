package com.biocycle.giveAwayCRUD.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class CollectionSpotAddress.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Embeddable

/**
 * Instantiates a new collection spot address.
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
public class CollectionSpotAddress {

	/** The spot name. */
	@NotBlank
	private String spotName;
	
	/** The street number. */
	@NotBlank
	private String streetNumber;
	
	/** The street name. */
	@NotBlank
	private String streetName;
	
	/** The city. */
	@NotBlank
	private String city;
	
	/** The post code. */
	@NotBlank
	private String postCode;
	
}
