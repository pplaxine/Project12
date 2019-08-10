package com.biocycle.customerManagmentService.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"streetNumber","streetName","city","postCode"})
public class Address {

	private String streetNumber;
	private String streetName;
	private String city;
	private String postCode;
}
