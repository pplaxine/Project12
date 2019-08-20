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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GiveAwayViewDto {
	
	private int id;
	private int organisationId;
	private String collectionSpotAddress;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime availableToBeCollectedFrom;
	private Date collectionDate; 
	private List<ContainerDto> containerList;
}
