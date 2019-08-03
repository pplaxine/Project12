package com.biocycle.organisationCRUD.model;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Organisation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//to start sequence at existing values 
	private int id;
	@NotBlank
	private String organisationName;
	@NotBlank
	@Email
	private String emailAddress;
	@JsonIgnore
	private String password;
	
	@Embedded
	@NotNull
	private Address organisationAddress;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "organisation_address_mapping",
		joinColumns = {@JoinColumn(name="organisation_id", referencedColumnName = "id")},
		inverseJoinColumns =  {@JoinColumn(name = "address_id", referencedColumnName = "id")}
	)
	@MapKey(name = "spotName")	//see is String can be added 
	private Map<String,CollectionSpotAddress> collectionAddressList;
	@NotBlank
	private String phoneNumber;	
	private Boolean isDonor;
	private Boolean isValidated;
	
	
	//CONSTRUCTORS 
	public Organisation(int id, String organisationName, String emailAddress, String password,
			Address organisationAddress, Map<String, CollectionSpotAddress> collectionAddressList, String phoneNumber,
			Boolean isDonor, Boolean isValidated) {
		super();
		this.id = id;
		this.organisationName = organisationName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.organisationAddress = organisationAddress;
		this.collectionAddressList = collectionAddressList;
		this.phoneNumber = phoneNumber;
		this.isDonor = isDonor;
		this.isValidated = isValidated;
	}
	
	public Organisation() {
	}

	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getOrganisationAddress() {
		return organisationAddress;
	}
	public void setOrganisationAddress(Address organisationAddress) {
		this.organisationAddress = organisationAddress;
	}
	public Map<String, CollectionSpotAddress> getCollectionAddressList() {
		return collectionAddressList;
	}
	public void setCollectionAddressList(Map<String, CollectionSpotAddress> collectionAddressList) {
		this.collectionAddressList = collectionAddressList;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getIsDonor() {
		return isDonor;
	}
	public void setIsDonor(Boolean isDonor) {
		this.isDonor = isDonor;
	}
	public Boolean getIsValidated() {
		return isValidated;
	}
	public void setIsValidated(Boolean isValidated) {
		this.isValidated = isValidated;
	}

	
	//To String
	@Override
	public String toString() {
		return "Organisation [id=" + id + ", organisationName=" + organisationName + ", emailAddress=" + emailAddress
				+ ", password=" + password + ", organisationAddress=" + organisationAddress + ", collectionAddressList="
				+ collectionAddressList + ", phoneNumber=" + phoneNumber + ", isDonor=" + isDonor + ", isValidated="
				+ isValidated + "]";
	}
}
