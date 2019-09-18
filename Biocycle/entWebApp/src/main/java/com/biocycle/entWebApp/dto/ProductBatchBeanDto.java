package com.biocycle.entWebApp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.entWebApp.bean.productBatch.UnitOfMeasure;

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
 * Gets the checks if is awaiting for collection.
 *
 * @return the checks if is awaiting for collection
 */
@Getter
@Setter
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
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfCollection;
	
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
