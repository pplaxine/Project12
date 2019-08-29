package com.biocycle.InventoryService.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.InventoryService.bean.UnitOfMeasure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductBatchBeanDto {

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
	private Boolean isAwaitingForCollection;
}
