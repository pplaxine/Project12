package com.biocycle.organisationCRUD.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Embeddable
public class Address {

	private String streetNumber;
	private String streetName;
	private String city;
	private String postCode;

	
	//CONSTRUTORS 
	public Address(String streetNumber, String streetName, String city, String postCode) {
		super();
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.postCode = postCode;
	}

	public Address() {
	}


	//G&S
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	//TO STRING 
	@Override
	public String toString() {
		return "Address [streetNumber=" + streetNumber + ", streetName="
				+ streetName + ", city=" + city + ", postCode=" + postCode + "]";
	}

	
		
	
	
		
	
	
}
