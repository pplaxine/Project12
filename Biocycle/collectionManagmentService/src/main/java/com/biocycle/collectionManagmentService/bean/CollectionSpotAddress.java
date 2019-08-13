package com.biocycle.collectionManagmentService.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","spotName","streetNumber","streetName","city","postCode"})
public class CollectionSpotAddress {
	
	private int id;
	private String spotName;
	private String streetNumber;
	private String streetName;
	private String city;
	private String postCode;
}
