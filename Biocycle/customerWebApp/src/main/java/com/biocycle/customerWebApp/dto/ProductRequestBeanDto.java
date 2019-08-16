package com.biocycle.customerWebApp.dto;

import java.math.BigDecimal;

import com.biocycle.customerWebApp.bean.UnitOfMeasure;

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
