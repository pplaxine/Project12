package com.biocycle.InventoryService.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
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
	private Boolean isAwaitingForCollection;
	
}
