package com.biocycle.entWebApp.dto.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new offer view dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new offer view dto.
 *
 * @param id the id
 * @param productBatchBeanDtoList the product batch bean dto list
 * @param availableForCollection the available for collection
 * @param offerEndingDate the offer ending date
 * @param isAccepted the is accepted
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","productBatchBeanDtoList","availableForCollection","offerEndingDate","isAccepted"})
public class OfferViewDto {
	
	/** The id. */
	private int id;
	
	/** The product batch bean dto list. */
	private List<ProductBatchBeanDto> productBatchBeanDtoList;
	
	/** The available for collection. */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate availableForCollection;
	
	/** The offer ending date. */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate offerEndingDate;
	
	/** Is oofer accepted. */
	private Boolean isAccepted;
}
