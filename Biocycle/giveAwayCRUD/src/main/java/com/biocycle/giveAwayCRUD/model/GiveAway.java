package com.biocycle.giveAwayCRUD.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class GiveAway {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int organisationId;
	private Date availableToBeCollectedFrom;
	private Date collectionDate; 
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "GIVEAWAY_ID")
	private List<Container> containerList;
	
	
	//CONSTRUCTORS 
	public GiveAway(int id, int organisationId, Date availableToBeCollectedFrom, Date collectionDate,
			List<Container> containerList) {
		super();
		this.id = id;
		this.organisationId = organisationId;
		this.availableToBeCollectedFrom = availableToBeCollectedFrom;
		this.collectionDate = collectionDate;
		this.containerList = containerList;
	}
	
	public GiveAway() {
	}

	
	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	public Date getAvailableToBeCollectedFrom() {
		return availableToBeCollectedFrom;
	}
	public void setAvailableToBeCollectedFrom(Date availableToBeCollectedFrom) {
		this.availableToBeCollectedFrom = availableToBeCollectedFrom;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	public List<Container> getContainerList() {
		return containerList;
	}
	public void setContainerList(List<Container> containerList) {
		this.containerList = containerList;
	}

	
	//toString
	@Override
	public String toString() {
		return "GiveAway [id=" + id + ", organisationId=" + organisationId + ", availableToBeCollectedFrom="
				+ availableToBeCollectedFrom + ", collectionDate=" + collectionDate + ", containerList=" + containerList
				+ "]";
	}
	
	
	
}
