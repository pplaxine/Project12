package com.biocycle.giveAwayCRUD.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 * The Class GiveAway.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new give away.
 */
@NoArgsConstructor

/**
 * Instantiates a new give away.
 *
 * @param id the id
 * @param organisationId the organisation id
 * @param collectionSpotAddress the collection spot address
 * @param availableToBeCollectedFrom the available to be collected from
 * @param collectionDate the collection date
 * @param containerList the container list
 * @param isCollected the is collected
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationId","availableToBeCollectedFrom","isCollected","collectionDate","containerList"})
public class GiveAway {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** The organisation id. */
	@NotNull
	private int organisationId;
	
	/** The collection spot address. */
	@Embedded
	@NotNull
	private CollectionSpotAddress collectionSpotAddress;
	
	/** The available to be collected from. */
	@NotNull
	private Date availableToBeCollectedFrom;
	
	/** The collection date. */
	private Date collectionDate; 
	
	/** The container list. */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "give_away_id")
	@NotEmpty
	private List<@NotNull Container> containerList;
	
	/** The is collected. */
	private Boolean isCollected;
}
