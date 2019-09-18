package com.biocycle.entWebApp.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.entWebApp.bean.organisation.CollectionSpotAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new give away bean dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new give away bean dto.
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
public class GiveAwayBeanDto {
	
	/** The id. */
	private int id;
	
	/** The organisation id. */
	private int organisationId;
	
	/** The collection spot address. */
	private CollectionSpotAddress collectionSpotAddress;
	
	/** The available to be collected from. */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime availableToBeCollectedFrom;
	
	/** The collection date. */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime collectionDate; 
	
	/** The container list. */
	private List<ContainerDto> containerList;
	
	/** Is giveAway collected. */
	private Boolean isCollected;
}
