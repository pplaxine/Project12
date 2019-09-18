package com.biocycle.entWebApp.dto.view;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.entWebApp.bean.organisation.CollectionSpotAddress;
import com.biocycle.entWebApp.dto.ContainerDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
 * @param organisationBeanDto the organisation bean dto
 * @param collectionSpotAddress the collection spot address
 * @param availableToBeCollectedFrom the available to be collected from
 * @param collectionDate the collection date
 * @param containerList the container list
 * @param isCollected the is collected
 * @param containerValidationDone the container validation done
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationBeanDto","collectionSpotAddress","availableToBeCollectedFrom","collectionDate","containerList","isCollected"})
public class GiveAwayViewDto {
	
	/** The id. */
	private int id;
	
	/** The organisation bean dto. */
	private OrganisationBeanDto organisationBeanDto;
	
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
	
	/** The container validation done. */
	private Boolean containerValidationDone;
}
