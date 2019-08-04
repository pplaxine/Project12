package com.biocycle.redistributionCRUD.model;

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
public class Redistribution {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int organisationId;
	
	@ElementCollection
	@CollectionTable(name = "redistribution_productRequest_mapping", 
		joinColumns = {@JoinColumn(name="redistribution_id", referencedColumnName = "id")} 
		)
	@Column(name = "productRequest_id",unique = true)
	@NotEmpty
	private List<Integer> productRequestId;
	@Column(unique = true)
	private Integer offerId;
	private Boolean isCompleted;
	
	
	//CONSTRUCTORS 
	public Redistribution(int id, @NotNull int organisationId, @NotEmpty List<Integer> productRequestId,
			Integer offerId, Boolean isCompleted) {
		super();
		this.id = id;
		this.organisationId = organisationId;
		this.productRequestId = productRequestId;
		this.offerId = offerId;
		this.isCompleted = isCompleted;
	}
	
	public Redistribution() {
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
	public List<Integer> getProductRequestId() {
		return productRequestId;
	}
	public void setProductRequestId(List<Integer> productRequestId) {
		this.productRequestId = productRequestId;
	}
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	public Boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	
	//toString
	@Override
	public String toString() {
		return "Redistribution [id=" + id + ", organisationId=" + organisationId + ", productRequestId="
				+ productRequestId + ", offerId=" + offerId + ", isCompleted=" + isCompleted + "]";
	}
	
	
	
	
	
}
