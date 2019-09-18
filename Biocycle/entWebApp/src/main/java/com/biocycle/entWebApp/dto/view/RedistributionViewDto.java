package com.biocycle.entWebApp.dto.view;

import java.util.List;

import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new redistribution view dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new redistribution view dto.
 *
 * @param id the id
 * @param organisationBeanDto the organisation bean dto
 * @param productRequestBeanDtoList the product request bean dto list
 * @param offerViewDto the offer view dto
 * @param isCompleted the is completed
 */
@AllArgsConstructor

/**
 * Gets the checks if is completed.
 *
 * @return the checks if is completed
 */
@Getter

/**
 * Sets the checks if is completed.
 *
 * @param isCompleted the new checks if is completed
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationBeanDto","productRequestBeanDtoList","offerViewDto","isCompleted"})
public class RedistributionViewDto {
	
	/** The id. */
	private int id;
	
	/** The organisation bean dto. */
	private OrganisationBeanDto organisationBeanDto;
	
	/** The product request bean dto list. */
	private List<ProductRequestBeanDto> productRequestBeanDtoList;
	
	/** The offer view dto. */
	private OfferViewDto offerViewDto;
	
	/** Is redistribution completed. */
	private Boolean isCompleted;
	
}
