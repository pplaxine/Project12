package com.biocycle.customerWebApp.dto.view;

import java.util.List;

import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;

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
 * @param organisationId the organisation id
 * @param productRequestBeanDtoList the product request bean dto list
 * @param offerViewDto the offer view dto
 * @param isCompleted the is completed
 */
@AllArgsConstructor
@Getter 
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationId","productRequestBeanDtoList","offerViewDto","isCompleted"})
public class RedistributionViewDto {
	
	/** The id. */
	private int id;
	
	/** The organisation id. */
	private int organisationId;
	
	/** The product request bean dto list. */
	private List<ProductRequestBeanDto> productRequestBeanDtoList;
	
	/** The offer view dto. */
	private OfferViewDto offerViewDto;
	
	/** Is redistribution completed. */
	private Boolean isCompleted;
}
