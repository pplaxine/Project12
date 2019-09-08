package com.biocycle.InventoryService.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.biocycle.InventoryService.bean.UnitOfMeasure;

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
public class ProductBatch {
	
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
	private Boolean isAwaitingForCollection;
}
