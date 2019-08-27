package com.biocycle.entWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","rowRef","shelfRef","levelRef","isAvailable"})
public class StorageContainerBeanDto {
	
	private int id;
	private int rowRef;
	private String shelfRef;
	private int levelRef;
	private Boolean isAvailable;
}
