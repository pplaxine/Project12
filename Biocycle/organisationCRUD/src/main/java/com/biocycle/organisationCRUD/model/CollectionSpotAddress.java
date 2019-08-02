package com.biocycle.organisationCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollectionSpotAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String spotName;
	private String streetNumber;
	private String streetName;
	private String city;
	private String postCode;

	
	//CONSTRUTORS 
	public CollectionSpotAddress(int id, String spotName, String streetNumber, String streetName, String city, String postCode) {
		super();
		this.id = id;
		this.spotName = spotName;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.postCode = postCode;
	}
	
	public CollectionSpotAddress() {
	}

	
	//G&S	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
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
		return "Address2 [id=" + id + ", spotName=" + spotName + ", streetNumber=" + streetNumber + ", streetName="
				+ streetName + ", city=" + city + ", postCode=" + postCode + "]";
	}

	

	
		
	
	
		
	
	
}
