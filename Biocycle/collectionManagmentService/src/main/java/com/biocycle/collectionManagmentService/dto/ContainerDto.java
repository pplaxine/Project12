package com.biocycle.collectionManagmentService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@ToString(of = {"id","description","accepted","collectionRunId","isCollected"})
public class ContainerDto {
	
	private int id;
	private String description;
	private Boolean accepted;
	private Integer collectionRunId;
	private Boolean isCollected;
	
}
