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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","organisationId","collectionSpotAddress","availableToBeCollectedFrom","collectionDate","containerList","isCollected"})
public class GiveAwayBeanDto {
	
	private int id;
	private int organisationId;
	private CollectionSpotAddress collectionSpotAddress;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime availableToBeCollectedFrom;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime collectionDate; 
	private List<ContainerDto> containerList;
	private Boolean isCollected;
}
