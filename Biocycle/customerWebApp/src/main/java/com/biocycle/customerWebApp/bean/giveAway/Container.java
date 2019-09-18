package com.biocycle.customerWebApp.bean.giveAway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new container.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new container.
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
public class Container {
	
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
