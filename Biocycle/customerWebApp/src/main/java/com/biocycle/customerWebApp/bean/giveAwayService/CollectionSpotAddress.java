package com.biocycle.customerWebApp.bean.giveAwayService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"spotName","streetNumber","streetName","city","postCode"})
public class CollectionSpotAddress {

	private String spotName;
	private String streetNumber;
	private String streetName;
	private String city;
	private String postCode;
}
