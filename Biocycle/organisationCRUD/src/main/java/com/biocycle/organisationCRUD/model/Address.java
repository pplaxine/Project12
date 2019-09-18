package com.biocycle.organisationCRUD.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 */
@Embeddable

/**
 * Instantiates a new address.
 */
@NoArgsConstructor

/**
 * Instantiates a new address.
 *
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
@ToString(of = {"streetNumber","streetName","city","postCode"})
public class Address {

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
