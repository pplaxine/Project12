package com.biocycle.giveAwayCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Instantiates a new container dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new container dto.
 *
 * @param id the id
 * @param description the description
 * @param accepted the accepted
 * @param collectionRunId the collection run id
 * @param isCollected the is collected
 */
@AllArgsConstructor

/**
 * Gets the checks if is collected.
 *
 * @return the checks if is collected
 */
@Getter 

/**
 * Sets the checks if is collected.
 *
 * @param isCollected the new checks if is collected
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","description","accepted","collectionRunId","isCollected"})
public class ContainerDto {
	
	/** The id. */
	private int id;
	
	/** The description. */
	private String description;
	
	/** The accepted. */
	private Boolean accepted;
	
	/** The collection run id. */
	private Integer collectionRunId;
	
	/** The is collected. */
	private Boolean isCollected;
	
}
