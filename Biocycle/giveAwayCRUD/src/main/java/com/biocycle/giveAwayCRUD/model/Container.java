package com.biocycle.giveAwayCRUD.model;

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
 * The Class Container.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new container.
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** The description. */
	@NotNull
	private String description;
	
	/** The accepted. */
	private Boolean accepted;
	
	/** The collection run id. */
	private Integer collectionRunId;
	
	/** The is collected. */
	private Boolean isCollected;
	
}
