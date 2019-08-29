package com.biocycle.entWebApp.dto;

import java.math.BigDecimal;

import com.biocycle.entWebApp.bean.productBatch.UnitOfMeasure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","productRequested","quantity","unitOfMeasure","isAccepted"})
public class ProductRequestBeanDto {
	
	private int id;
	private String productRequested;
	private BigDecimal quantity;
	private UnitOfMeasure unitOfMeasure;
	private Boolean isAccepted;

}
