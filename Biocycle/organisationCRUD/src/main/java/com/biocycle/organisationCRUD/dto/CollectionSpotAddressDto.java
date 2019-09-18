package com.biocycle.organisationCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// TODO: Auto-generated Javadoc
/**
 * Instantiates a new collection spot address dto.
 */
@NoArgsConstructor

/**
 * Instantiates a new collection spot address dto.
 *
 * @param id the id
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
@ToString(of = {"id","spotName","streetNumber","streetName","city","postCode"})
public class CollectionSpotAddressDto {
	
	/** The id. */
	private int id;
	
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
