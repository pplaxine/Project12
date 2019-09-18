package com.biocycle.customerWebApp.dto.view;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.customerWebApp.dto.ContainerDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new give away view dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new give away view dto.
 *
 * @param id the id
 * @param organisationId the organisation id
 * @param collectionSpotAddress the collection spot address
 * @param availableToBeCollectedFrom the available to be collected from
 * @param collectionDate the collection date
 * @param containerList the container list
 */
@AllArgsConstructor
@Getter
@Setter
public class GiveAwayViewDto {
	
	/** The id. */
	private int id;
	
	/** The organisation id. */
	private int organisationId;
	
	/** The collection spot address. */
	private String collectionSpotAddress;
	
	/** The available to be collected from. */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime availableToBeCollectedFrom;
	
	/** The collection date. */
	private Date collectionDate; 
	
	/** The container list. */
	private List<ContainerDto> containerList;
}
