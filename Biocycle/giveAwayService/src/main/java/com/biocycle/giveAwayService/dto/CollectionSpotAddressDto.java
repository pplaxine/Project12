package com.biocycle.giveAwayService.dto;

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
public class CollectionSpotAddressDto {

	private String spotName;
	private String streetNumber;
	private String streetName;
	private String city;
	private String postCode;
}
