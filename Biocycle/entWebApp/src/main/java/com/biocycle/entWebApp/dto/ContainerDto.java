package com.biocycle.entWebApp.dto;

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
@Getter 
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
	
	/** Is container collected. */
	private Boolean isCollected;
	
}
