package com.biocycle.productRequestCRUD.dto;

import java.math.BigDecimal;

import com.biocycle.productRequestCRUD.model.UnitOfMeasure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new product request dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new product request dto.
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
public class ProductRequestDto {
	
	/** The id. */
	private int id;
	
	/** The product requested. */
	private String productRequested;
	
	/** The quantity. */
	private BigDecimal quantity;
	
	/** The unit of measure. */
	private UnitOfMeasure unitOfMeasure;
	
	/** The is accepted. */
	private Boolean isAccepted;

}
