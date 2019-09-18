package com.biocycle.entWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new storage container bean dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new storage container bean dto.
 *
 * @param id the id
 * @param rowRef the row ref
 * @param shelfRef the shelf ref
 * @param levelRef the level ref
 * @param isAvailable the is available
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","rowRef","shelfRef","levelRef","isAvailable"})
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
