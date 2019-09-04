package com.biocycle.organisationCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","spotName","streetNumber","streetName","city","postCode"})
@Table(name = "collection_spot_address")
public class CollectionSpotAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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
