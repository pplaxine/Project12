package com.biocycle.productBatchCRUD.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ProductBatch.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new product batch.
 */
@NoArgsConstructor

/**
 * Instantiates a new product batch.
 *
 * @param id the id
 * @param name the name
 * @param description the description
 * @param donorId the donor id
 * @param dateOfCollection the date of collection
 * @param toBeUsedBy the to be used by
 * @param quantity the quantity
 * @param unitOfMeasure the unit of measure
 * @param storageContainerId the storage container id
 * @param isAvailable the is available
 * @param isAwaitingForCollection the is awaiting for collection
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","name","description","donorId","dateOfCollection","toBeUsedBy","quantity","unitOfMeasure","storageContainerId","isAvailable", "isAwaitingForCollection"})
public class ProductBatch {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** The name. */
	@NotNull
	private String name;
	
	/** The description. */
	private String description;
	
	/** The donor id. */
	@NotNull
	private int donorId;
	
	/** The date of collection. */
	@NotNull
	private Date dateOfCollection;
	
	/** The to be used by. */
	@NotNull
	private Date toBeUsedBy;
	
	/** The quantity. */
	@NotNull
	@Column(precision = 10, scale = 3)
	private BigDecimal quantity;
	
	/** The unit of measure. */
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	
	/** The storage container id. */
	@ElementCollection
	@CollectionTable(name = "productBatch_storageContainer_mapping", 
		joinColumns = {@JoinColumn(name="productBatch_id", referencedColumnName = "id")} 
		)
	@Column(name = "storageContainer_id",unique = true)
	private List<@NotNull Integer> storageContainerId; 
	
	/** The is available. */
	private Boolean isAvailable; 
	
	/** The is awaiting for collection. */
	private Boolean isAwaitingForCollection;
}
