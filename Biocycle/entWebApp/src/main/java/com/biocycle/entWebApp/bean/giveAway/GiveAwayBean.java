package com.biocycle.entWebApp.bean.giveAway;

import java.time.LocalDateTime;
import java.util.List;

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
public class GiveAwayBean {
	
	private int id;
	private int organisationId;
	private CollectionSpotAddress collectionSpotAddress;
	private LocalDateTime availableToBeCollectedFrom;
	private LocalDateTime collectionDate;  
	private List<Container> containerList;
	private Boolean isCollected;
}
