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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ProductRequest.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new product request.
 */
@NoArgsConstructor

/**
 * Instantiates a new product request.
 *
 * @param id the id
 * @param productRequested the product requested
 * @param quantity the quantity
 * @param unitOfMeasure the unit of measure
 * @param isAccepted the is accepted
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","productRequested","quantity","unitOfMeasure","isAccepted"})
public class ProductRequest {
		
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** The product requested. */
	@NotNull
	private String productRequested;
	
	/** The quantity. */
	@NotNull
	@Column(precision = 10, scale = 3)
	private BigDecimal quantity;
	
	/** The unit of measure. */
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	
	/** The is accepted. */
	private Boolean isAccepted;
	
}
