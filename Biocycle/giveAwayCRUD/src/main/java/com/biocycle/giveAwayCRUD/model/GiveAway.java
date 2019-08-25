package com.biocycle.giveAwayCRUD.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","organisationId","availableToBeCollectedFrom","isCollected","collectionDate","containerList"})
public class GiveAway {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int organisationId;
	@Embedded
	@NotNull
	private CollectionSpotAddress collectionSpotAddress;
	@NotNull
	private Date availableToBeCollectedFrom;
	private Date collectionDate; 
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "GIVEAWAY_ID")
	@NotEmpty
	private List<@NotNull Container> containerList;
	private Boolean isCollected;
}
