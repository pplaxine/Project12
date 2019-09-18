package com.biocycle.entWebApp.bean.productBatch;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new product batch bean.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ProductBatchBean {
	
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
	
	/** Is productBatch available. */
	private Boolean isAvailable;
	
	/** Is productBatch awaiting for collection. */
	private Boolean isAwaitingForCollection;
}
