package com.biocycle.productRequestCRUD.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ProductRequest {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String productRequested;
	
	@NotNull
	@Column(precision = 10, scale = 3)
	private BigDecimal quantity;
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	
	private Boolean isAccepted;

	
	//CONSTRUCTORS 
	public ProductRequest(int id, @NotNull String productRequested, @NotNull BigDecimal quantity,
			@NotNull UnitOfMeasure unitOfMeasure, Boolean isAccepted) {
		super();
		this.id = id;
		this.productRequested = productRequested;
		this.quantity = quantity;
		this.unitOfMeasure = unitOfMeasure;
		this.isAccepted = isAccepted;
	}
	
	public ProductRequest() {
	}
	
	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductRequested() {
		return productRequested;
	}
	public void setProductRequested(String productRequested) {
		this.productRequested = productRequested;
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
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "ProductRequest [id=" + id + ", productRequested=" + productRequested + ", quantity=" + quantity
				+ ", unitOfMeasure=" + unitOfMeasure + ", isAccepted=" + isAccepted + "]";
	} 
	
	
	
	
}
