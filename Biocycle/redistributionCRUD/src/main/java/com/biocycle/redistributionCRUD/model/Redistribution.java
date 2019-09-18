package com.biocycle.redistributionCRUD.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
 * The Class Redistribution.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new redistribution.
 */
@NoArgsConstructor

/**
 * Instantiates a new redistribution.
 *
 * @param id the id
 * @param organisationId the organisation id
 * @param productRequestId the product request id
 * @param offerId the offer id
 * @param isCompleted the is completed
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationId","productRequestId","offerId","isCompleted"})
public class Redistribution {
		
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** The organisation id. */
	@NotNull
	private int organisationId;
	
	/** The product request id. */
	@ElementCollection
	@CollectionTable(name = "redistribution_productRequest_mapping", 
		joinColumns = {@JoinColumn(name="redistribution_id", referencedColumnName = "id")} 
		)
	@Column(name = "productRequest_id",unique = true)
	private List<Integer> productRequestId;
	
	/** The offer id. */
	@Column(unique = true)
	private Integer offerId;
	
	/** The is completed. */
	private Boolean isCompleted;

}
