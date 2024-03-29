package com.biocycle.productDispatchService.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new redistribution bean.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new redistribution bean.
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
public class RedistributionBean {
	
	/** The id. */
	private int id;
	
	/** The organisation id. */
	private int organisationId;
	
	/** The product request id. */
	private List<Integer> productRequestId;
	
	/** The offer id. */
	private Integer offerId;
	
	/** Is redistribution completed. */
	private Boolean isCompleted;
	
}
