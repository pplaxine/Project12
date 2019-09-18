package com.biocycle.customerWebApp.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new container view dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new container view dto.
 *
 * @param id the id
 * @param name the name
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
@ToString(of = {"name","description","accepted","collectionRunId","isCollected"})
public class ContainerViewDto {
	
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The accepted. */
	private Boolean accepted;
	
	/** The collection run id. */
	private Integer collectionRunId;
	
	/** Is container collected. */
	private Boolean isCollected;
	
}
