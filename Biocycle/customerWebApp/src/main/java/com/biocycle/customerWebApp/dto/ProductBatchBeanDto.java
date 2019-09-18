package com.biocycle.customerWebApp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.customerWebApp.bean.redistribution.UnitOfMeasure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new product batch bean dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new product batch bean dto.
 *
 * @param id the id
 * @param name the name
 * @param description the description
 * @param donorId the donor id
 * @param dateOfCollection the date of collection
 * @param toBeUsedBy the to be used by
 * @param quantity the quantity
 * @param unitOfMeasure the unit of measure
 * @param storageContainerId the storage container id
 * @param isAvailable the is available
 * @param isAwaitingForCollection the is awaiting for collection
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","name","description","donorId","dateOfCollection","toBeUsedBy","quantity","unitOfMeasure","storageContainerId","isAvailable","isAwaitingForCollection"})
public class ProductBatchBeanDto {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The donor id. */
	private int donorId;
	
	/** The date of collection. */
	private Date dateOfCollection;
	
	/** The to be used by. */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate toBeUsedBy;
	
	/** The quantity. */
	private BigDecimal quantity;
	
	/** The unit of measure. */
	private UnitOfMeasure unitOfMeasure;
	
	/** The storage container id. */
	private List<Integer> storageContainerId; 
	
	/** Is productBatch available. */
	private Boolean isAvailable; 
	
	/** Is productBatch awaiting for collection. */
	private Boolean isAwaitingForCollection;
}
