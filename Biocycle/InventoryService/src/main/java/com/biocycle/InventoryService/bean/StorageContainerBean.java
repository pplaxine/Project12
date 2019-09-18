package com.biocycle.InventoryService.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new storage container bean.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class StorageContainerBean {
	
	/** The id. */
	private int id;
	
	/** The row ref. */
	private int rowRef;
	
	/** The shelf ref. */
	private String shelfRef;
	
	/** The level ref. */
	private int levelRef;
	
	/** Is StorageContainer available. */
	private Boolean isAvailable;
}
