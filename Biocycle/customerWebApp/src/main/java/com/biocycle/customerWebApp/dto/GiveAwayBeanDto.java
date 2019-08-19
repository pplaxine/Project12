package com.biocycle.customerWebApp.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","organisationId","availableToBeCollectedFrom","collectionDate","containerList"})
public class GiveAwayBeanDto {
	
	private int id;
	private int organisationId;
	private CollectionSpotAddressDto collectionSpotAddress;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime availableToBeCollectedFrom;
	private Date collectionDate; 
	private List<ContainerDto> containerList;
}
