package com.biocycle.storageContainerCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new storage container dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new storage container dto.
 *
 * @param id the id
 * @param rowRef the row ref
 * @param shelfRef the shelf ref
 * @param levelRef the level ref
 * @param isAvailable the is available
 */
@AllArgsConstructor

/**
 * Gets the checks if is available.
 *
 * @return the checks if is available
 */
@Getter
@Setter
@ToString(of = {"id","rowRef","shelfRef","levelRef","isAvailable"})
public class StorageContainerDto {
	
	/** The id. */
	private int id;
	
	/** The row ref. */
	private int rowRef;
	
	/** The shelf ref. */
	private String shelfRef;
	
	/** The level ref. */
	private int levelRef;
	
	/** The is available. */
	private Boolean isAvailable;
}
