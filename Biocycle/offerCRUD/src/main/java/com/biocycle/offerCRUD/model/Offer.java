package com.biocycle.offerCRUD.model;

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
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ElementCollection
	@CollectionTable(name = "offer_productBatch_mapping", 
		joinColumns = {@JoinColumn(name="offer_id", referencedColumnName = "id")} 
		)
	@Column(name = "productBatch_id",unique = true)
	@NotEmpty
	private List<Integer> productBatchIdList;
	@NotNull
	private Date availableForCollection;
	@NotNull
	private Date offerEndingDate;
	private Boolean isAccepted;
	
	
	//CONSTRUCTORS 
	public Offer(int id, @NotEmpty List<Integer> productBatchIdList, @NotNull Date availableForCollection,
			@NotNull Date offerEndingDate, Boolean isAccepted) {
		super();
		this.id = id;
		this.productBatchIdList = productBatchIdList;
		this.availableForCollection = availableForCollection;
		this.offerEndingDate = offerEndingDate;
		this.isAccepted = isAccepted;
	}
	
	public Offer() {
	}
	
	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getProductBatchIdList() {
		return productBatchIdList;
	}
	public void setProductBatchIdList(List<Integer> productBatchIdList) {
		this.productBatchIdList = productBatchIdList;
	}
	public Date getAvailableForCollection() {
		return availableForCollection;
	}
	public void setAvailableForCollection(Date availableForCollection) {
		this.availableForCollection = availableForCollection;
	}
	public Date getOfferEndingDate() {
		return offerEndingDate;
	}
	public void setOfferEndingDate(Date offerEndingDate) {
		this.offerEndingDate = offerEndingDate;
	}
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	
	//toString
	@Override
	public String toString() {
		return "Offer [id=" + id + ", productBatchIdList=" + productBatchIdList + ", availableForCollection="
				+ availableForCollection + ", offerEndingDate=" + offerEndingDate + ", isAccepted=" + isAccepted + "]";
	}
	
	
	
	
	
	
	
}
