package com.biocycle.entWebApp.bean.productBatch;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
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
	
}
