package com.biocycle.customerWebApp.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@ToString(of = {"name","description","accepted","collectionRunId","isCollected"})
public class ContainerViewDto {
	
	private String name;
	private String description;
	private Boolean accepted;
	private Integer collectionRunId;
	private Boolean isCollected;
	
}
