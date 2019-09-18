package com.biocycle.organisationCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/**
 * The Class CollectionSpotAddress.
 */
@Entity

/**
 * Instantiates a new collection spot address.
 */
@NoArgsConstructor

/**
 * Instantiates a new collection spot address.
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
@Table(name = "collection_spot_address")
public class CollectionSpotAddress {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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
