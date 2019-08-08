package com.biocycle.organisationCRUD.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ToString(of = {"streetNumber","streetName","city","postCode"})
public class Address {

	@NotBlank
	private String streetNumber;
	@NotBlank
	private String streetName;
	@NotBlank
	private String city;
	@NotBlank
	private String postCode;

}
