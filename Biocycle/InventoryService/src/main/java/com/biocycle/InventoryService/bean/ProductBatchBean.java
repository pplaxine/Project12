package com.biocycle.InventoryService.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductBatchBean {
	
	private int id;
	private String name;
	private String description;
	private int donorId;
	private Date dateOfCollection;
	private Date toBeUsedBy;
	private BigDecimal quantity;
	private UnitOfMeasure unitOfMeasure;
	private List<Integer> storageContainerId; 
	private Boolean isAvailable;
	
	public ProductBatchBean(){
	}
	
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
	public List<Integer> getStorageContainerId() {
		return storageContainerId;
	}
	public void setStorageContainerId(List<Integer> storageContainerId) {
		this.storageContainerId = storageContainerId;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	} 
	
	
	
}
