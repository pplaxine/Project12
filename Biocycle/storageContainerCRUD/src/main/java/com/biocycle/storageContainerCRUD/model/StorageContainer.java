package com.biocycle.storageContainerCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class StorageContainer.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new storage container.
 */
@NoArgsConstructor

/**
 * Instantiates a new storage container.
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

/**
 * Sets the checks if is available.
 *
 * @param isAvailable the new checks if is available
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","rowRef","shelfRef","levelRef","isAvailable"})
public class StorageContainer {
	
		/** The id. */
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		/** The row ref. */
		@NotNull
		private int rowRef;
		
		/** The shelf ref. */
		@NotNull
		private String shelfRef;
		
		/** The level ref. */
		@NotNull
		private int levelRef;
		
		/** The is available. */
		private Boolean isAvailable;
		
}
