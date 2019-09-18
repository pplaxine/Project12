package com.biocycle.InventoryService.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.biocycle.InventoryService.bean.UnitOfMeasure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new product batch.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new product batch.
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
@ToString(of = {"id","name","description","donorId","dateOfCollection","toBeUsedBy","quantity","unitOfMeasure","storageContainerId","isAvailable"})
public class ProductBatch {
	
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
	private Date toBeUsedBy;
	
	/** The quantity. */
	private BigDecimal quantity;
	
	/** The unit of measure. */
	private UnitOfMeasure unitOfMeasure;
	
	/** The storage container id. */
	private List<Integer> storageContainerId; 
	
	/** The is available. */
	private Boolean isAvailable;
	
	/** The is awaiting for collection. */
	private Boolean isAwaitingForCollection;
}
