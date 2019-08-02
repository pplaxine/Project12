package com.biocycle.collectionRunCRUD.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CollectionRun {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date collectionRunDate;
	private Integer collectorId;
	private Date startTime;
	private Date endTime; 
	@ElementCollection
	@CollectionTable(name = "collectionRun_container_mapping", 
		joinColumns = {@JoinColumn(name="collectionRun_id", referencedColumnName = "id")} 
		)
	@Column(name = "container_id",unique = true)
	@NotEmpty
	private List<@NotNull Integer> containerIdList;
	
	
	//CONSTRUCTORS 
	public CollectionRun(int id, Date collectionRunDate, Integer collectorId, Date startTime, Date endTime,
			List<Integer> containerIdList) {
		super();
		this.id = id;
		this.collectionRunDate = collectionRunDate;
		this.collectorId = collectorId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.containerIdList = containerIdList;
	}
	
	public CollectionRun() {
		
	}
	
	
	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCollectionRunDate() {
		return collectionRunDate;
	}
	public void setCollectionRunDate(Date collectionRunDate) {
		this.collectionRunDate = collectionRunDate;
	}
	public Integer getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<Integer> getContainerIdList() {
		return containerIdList;
	}
	public void setContainerIdList(List<Integer> containerIdList) {
		this.containerIdList = containerIdList;
	}

	
	//toString
	@Override
	public String toString() {
		return "CollectionRun [id=" + id + ", collectionRunDate=" + collectionRunDate + ", collectorId=" + collectorId
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", containerIdList=" + containerIdList
				+ "]";
	}
	
	
	
	
	
	
	
}
