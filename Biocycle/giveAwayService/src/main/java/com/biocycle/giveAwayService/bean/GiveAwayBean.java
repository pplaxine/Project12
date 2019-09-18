package com.biocycle.giveAwayService.bean;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new give away bean.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new give away bean.
 *
 * @param id the id
 * @param organisationId the organisation id
 * @param collectionSpotAddress the collection spot address
 * @param availableToBeCollectedFrom the available to be collected from
 * @param collectionDate the collection date
 * @param containerList the container list
 * @param isCollected the is collected
 */
@AllArgsConstructor

/**
 * Gets the checks if is collected.
 *
 * @return the checks if is collected
 */
@Getter
@Setter
@ToString(of = {"id","organisationId","collectionSpotAddress","availableToBeCollectedFrom","collectionDate","containerList","isCollected"})
public class GiveAwayBean {
	
	/** The id. */
	private int id;
	
	/** The organisation id. */
	private int organisationId;
	
	/** The collection spot address. */
	private CollectionSpotAddress collectionSpotAddress;
	
	/** The available to be collected from. */
	private LocalDateTime availableToBeCollectedFrom;
	
	/** The collection date. */
	private LocalDateTime collectionDate;  
	
	/** The container list. */
	private List<Container> containerList;
	
	/** The is collected. */
	private Boolean isCollected;
}
