package com.biocycle.giveAwayCRUD.dto;

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
@ToString(of = {"id","organisationId","availableToBeCollectedFrom","collectionDate","containerList"})
public class GiveAwayDto {
	
	private int id;
	private int organisationId;
	private Date availableToBeCollectedFrom;
	private Date collectionDate; 
	private List<ContainerDto> containerList;
}
