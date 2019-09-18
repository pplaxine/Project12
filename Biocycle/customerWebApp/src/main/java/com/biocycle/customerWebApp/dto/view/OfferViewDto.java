package com.biocycle.customerWebApp.dto.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.customerWebApp.dto.ProductBatchBeanDto;

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
 * @param productBatchBeanList the product batch bean list
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
@ToString(of = {"id","productBatchBeanList","availableForCollection","offerEndingDate","isAccepted"})
public class OfferViewDto {
	
	/** The id. */
	private Integer id;
	
	/** The product batch bean list. */
	private List<ProductBatchBeanDto> productBatchBeanList;
	
	/** The available for collection. */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate availableForCollection;
	
	/** The offer ending date. */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate offerEndingDate;
	
	/** Is offer accepted. */
	private Boolean isAccepted;
	
}
