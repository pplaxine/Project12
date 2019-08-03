package com.biocycle.productBatchCRUD.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private int donorId; 
	private Date dateOfCollection;
	private Date toBeUsedBy;
	
	@Column(precision = 10, scale = 3)
	private BigDecimal quantity;
	@Enumerated(value = EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	private Boolean isAvailable; 
	
	
	//CONSTRUCTORS 
	public ProductBatch(int id, String name, String description, int donorId, Date dateOfCollection, Date toBeUsedBy,
			BigDecimal quantity, UnitOfMeasure unitOfMeasure, Boolean isAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.donorId = donorId;
		this.dateOfCollection = dateOfCollection;
		this.toBeUsedBy = toBeUsedBy;
		this.quantity = quantity;
		this.unitOfMeasure = unitOfMeasure;
		this.isAvailable = isAvailable;
	}

	public ProductBatch() {
		
	}

	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	public Date getDateOfCollection() {
		return dateOfCollection;
	}
	public void setDateOfCollection(Date dateOfCollection) {
		this.dateOfCollection = dateOfCollection;
	}
	public Date getToBeUsedBy() {
		return toBeUsedBy;
	}
	public void setToBeUsedBy(Date toBeUsedBy) {
		this.toBeUsedBy = toBeUsedBy;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	
	//toString
	@Override
	public String toString() {
		return "ProductBatch [id=" + id + ", name=" + name + ", description=" + description + ", donorId=" + donorId
				+ ", dateOfCollection=" + dateOfCollection + ", toBeUsedBy=" + toBeUsedBy + ", quantity=" + quantity
				+ ", unitOfMeasure=" + unitOfMeasure + ", isAvailable=" + isAvailable + "]";
	}

	

}
