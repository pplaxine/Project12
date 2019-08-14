package com.biocycle.productDispatchService.dto;

import java.math.BigDecimal;

import com.biocycle.productDispatchService.bean.UnitOfMeasure;

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
