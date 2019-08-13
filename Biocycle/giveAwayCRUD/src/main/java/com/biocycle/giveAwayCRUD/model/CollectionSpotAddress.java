package com.biocycle.giveAwayCRUD.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"spotName","streetNumber","streetName","city","postCode"})
public class CollectionSpotAddress {

	@NotBlank
	private String spotName;
	@NotBlank
	private String streetNumber;
	@NotBlank
	private String streetName;
	@NotBlank
	private String city;
	@NotBlank
	private String postCode;
	
}
