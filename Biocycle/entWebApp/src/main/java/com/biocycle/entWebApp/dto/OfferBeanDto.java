package com.biocycle.entWebApp.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new offer bean dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new offer bean dto.
 *
 * @param id the id
 * @param productBatchIdList the product batch id list
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
@ToString(of = {"id","productBatchIdList","availableForCollection","offerEndingDate","isAccepted"})
public class OfferBeanDto {
	
	/** The id. */
	private int id;
	
	/** The product batch id list. */
	private List<Integer> productBatchIdList;
	
	/** The available for collection. */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate availableForCollection;
	
	/** The offer ending date. */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate offerEndingDate;
	
	/** Is offer accepted. */
	private Boolean isAccepted;
}
