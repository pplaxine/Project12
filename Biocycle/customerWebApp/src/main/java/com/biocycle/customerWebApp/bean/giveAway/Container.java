package com.biocycle.customerWebApp.bean.giveAway;

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
public class Container {
	
	private int id;
	private String description;
	private Boolean accepted;
	private Integer collectionRunId;
	private Boolean isCollected;
	
}
