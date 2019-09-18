package com.biocycle.entWebApp.bean.giveAway;

import java.time.LocalDateTime;
import java.util.List;

import com.biocycle.entWebApp.bean.organisation.CollectionSpotAddress;

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
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
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
	
	/** Is giveAway collected. */
	private Boolean isCollected;
}
