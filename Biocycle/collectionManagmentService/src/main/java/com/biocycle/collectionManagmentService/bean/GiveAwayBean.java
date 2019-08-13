package com.biocycle.collectionManagmentService.bean;

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
@ToString(of = {"id","organisationId","collectionSpotAddress","availableToBeCollectedFrom","collectionDate","containerList"})
public class GiveAwayBean {
	
	private int id;
	private int organisationId;
	private CollectionSpotAddress collectionSpotAddress;
	private Date availableToBeCollectedFrom;
	private Date collectionDate;
	private List<Container> containerList;
}
