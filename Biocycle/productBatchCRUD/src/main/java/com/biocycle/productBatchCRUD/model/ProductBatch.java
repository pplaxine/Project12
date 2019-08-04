package com.biocycle.productBatchCRUD.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class ProductBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	private String description;
	@NotNull
	private int donorId;
	@NotNull
	private Date dateOfCollection;
	@NotNull
	private Date toBeUsedBy;
	
	@NotNull
	@Column(precision = 10, scale = 3)
	private BigDecimal quantity;
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	
	@ElementCollection
	@CollectionTable(name = "productBatch_storageContainer_mapping", 
		joinColumns = {@JoinColumn(name="productBatch_id", referencedColumnName = "id")} 
		)
	@Column(name = "storageContainer_id",unique = true)
	@NotEmpty
	private List<@NotNull Integer> storageContainerId; 
	private Boolean isAvailable; 
	
	
	//CONSTRUCTORS 
	public ProductBatch(int id, @NotNull String name, String description, @NotNull int donorId,
			@NotNull Date dateOfCollection, @NotNull Date toBeUsedBy, @NotNull BigDecimal quantity,
			@NotNull UnitOfMeasure unitOfMeasure, @NotEmpty List<@NotNull Integer> storageContainerId,
			Boolean isAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.donorId = donorId;
		this.dateOfCollection = dateOfCollection;
		this.toBeUsedBy = toBeUsedBy;
		this.quantity = quantity;
		this.unitOfMeasure = unitOfMeasure;
		this.storageContainerId = storageContainerId;
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

	
	//toString
	@Override
	public String toString() {
		return "ProductBatch [id=" + id + ", name=" + name + ", description=" + description + ", donorId=" + donorId
				+ ", dateOfCollection=" + dateOfCollection + ", toBeUsedBy=" + toBeUsedBy + ", quantity=" + quantity
				+ ", unitOfMeasure=" + unitOfMeasure + ", storageContainerId=" + storageContainerId + ", isAvailable="
				+ isAvailable + "]";
	}


	

	

}
