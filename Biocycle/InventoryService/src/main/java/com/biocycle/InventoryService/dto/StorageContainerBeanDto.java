package com.biocycle.InventoryService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new storage container bean dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class StorageContainerBeanDto {
	
	/** The id. */
	private int id;
	
	/** The row ref. */
	private int rowRef;
	
	/** The shelf ref. */
	private String shelfRef;
	
	/** The level ref. */
	private int levelRef;
	
	/** Is storageContainer available. */
	private Boolean isAvailable;
}
