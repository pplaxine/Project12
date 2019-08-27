package com.biocycle.productBatchCRUD.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.productBatchCRUD.model.UnitOfMeasure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","name","description","donorId","dateOfCollection","toBeUsedBy","quantity","unitOfMeasure","storageContainerId","isAvailable"})
public class ProductBatchDto {

	private int id;
	private String name;
	private String description;
	private int donorId;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfCollection;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate toBeUsedBy;
	private BigDecimal quantity;
	private UnitOfMeasure unitOfMeasure;
	private List<Integer> storageContainerId; 
	private Boolean isAvailable; 

}
