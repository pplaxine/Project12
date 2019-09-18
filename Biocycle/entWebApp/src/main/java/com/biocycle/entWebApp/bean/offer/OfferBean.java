package com.biocycle.entWebApp.bean.offer;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new offer bean.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new offer bean.
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
public class OfferBean {
	
	/** The id. */
	private int id;
	
	/** The product batch id list. */
	private List<Integer> productBatchIdList;
	
	/** The available for collection. */
	private Date availableForCollection;
	
	/** The offer ending date. */
	private Date offerEndingDate;
	
	/** Is offer accepted. */
	private Boolean isAccepted;
}
