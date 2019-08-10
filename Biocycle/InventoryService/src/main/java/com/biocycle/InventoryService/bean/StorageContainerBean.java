package com.biocycle.InventoryService.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StorageContainerBean {
	
	private int id;
	private int rowRef;
	private String shelfRef;
	private int levelRef;
	private Boolean isAvailable;
}
