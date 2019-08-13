package com.biocycle.collectionManagmentService.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","organisationId","collectionSpotAddressDto","availableToBeCollectedFrom","collectionDate","containerList"})
public class GiveAwayBeanDto {
	
	private int id;
	private int organisationId;
	private CollectionSpotAddressDto collectionSpotAddressDto;
	private Date availableToBeCollectedFrom;
	private Date collectionDate; 
	private List<ContainerDto> containerList;
}