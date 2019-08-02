package com.biocycle.giveAwayCRUD.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Container {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private Boolean accepted;
	private Integer collectionRunId;
	private Boolean isCollected;

	
	//CONSTRUCTORS 
	public Container(int id, String description, Boolean accepted, Integer collectionRunId, Boolean isCollected) {
		super();
		this.id = id;
		this.description = description;
		this.accepted = accepted;
		this.collectionRunId = collectionRunId;
		this.isCollected = isCollected;
	}
	
	public Container() {
	}

	
	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public Integer getCollectionRunId() {
		return collectionRunId;
	}
	public void setCollectionRunId(Integer collectionRunId) {
		this.collectionRunId = collectionRunId;
	}
	public Boolean getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Boolean isCollected) {
		this.isCollected = isCollected;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "Container [id=" + id + ", description=" + description + ", accepted=" + accepted + ", collectionRunId="
				+ collectionRunId + ", isCollected=" + isCollected + "]";
	}
	
	
	
	
}
