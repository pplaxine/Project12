package com.biocycle.giveAwayCRUD.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Instantiates a new give away dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new give away dto.
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

/**
 * Sets the checks if is collected.
 *
 * @param isCollected the new checks if is collected
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationId","availableToBeCollectedFrom","collectionDate","containerList"})
public class GiveAwayDto {
	
	/** The id. */
	private int id;
	
	/** The organisation id. */
	private int organisationId;
	
	/** The collection spot address. */
	private CollectionSpotAddressDto collectionSpotAddress;
	
	/** The available to be collected from. */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime availableToBeCollectedFrom;
	
	/** The collection date. */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime collectionDate; 
	
	/** The container list. */
	private List<ContainerDto> containerList;
	
	/** The is collected. */
	private Boolean isCollected;
}
