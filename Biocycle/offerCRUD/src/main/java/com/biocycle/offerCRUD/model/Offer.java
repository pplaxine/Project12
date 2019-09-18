package com.biocycle.offerCRUD.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Offer.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new offer.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new offer.
 *
 * @param id the id
 * @param productBatchIdList the product batch id list
 * @param availableForCollection the available for collection
 * @param offerEndingDate the offer ending date
 * @param isAccepted the is accepted
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","productBatchIdList","availableForCollection","offerEndingDate","isAccepted"})
public class Offer {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** The product batch id list. */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable( name = "offer_productBatch_mapping", 
		joinColumns = {@JoinColumn(name="offer_id", referencedColumnName = "id")} 
		)
	@Column(name = "productBatch_id")
	@NotEmpty
	private List<Integer> productBatchIdList;
	
	/** The available for collection. */
	@NotNull
	private Date availableForCollection;
	
	/** The offer ending date. */
	@NotNull
	private Date offerEndingDate;
	
	/** The is accepted. */
	private Boolean isAccepted;
	
}
