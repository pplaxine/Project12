package com.biocycle.organisationCRUD.model;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id","organisationName","emailAddress","password","organisationAddress","collectionAddressList","phoneNumber","isDonor","isValidated"})
public class Organisation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//to start sequence at existing values 
	private int id;
	@NotBlank
	@Getter @Setter
	private String organisationName;
	@NotBlank
	@Email
	@Getter @Setter
	@Column(unique = true)
	private String emailAddress;
	@JsonIgnore
	@Getter @Setter
	private String password;
	
	@Embedded
	@NotNull
	@Getter @Setter
	private Address organisationAddress;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "organisation_address_mapping",
		joinColumns = {@JoinColumn(name="organisation_id", referencedColumnName = "id")},
		inverseJoinColumns =  {@JoinColumn(name = "address_id", referencedColumnName = "id")}
	)
	@MapKey(name = "spotName")	 
	@Getter @Setter
	private Map<String,CollectionSpotAddress> collectionAddressList;
	@NotBlank
	@Getter @Setter
	private String phoneNumber;	
	@Getter @Setter
	private Boolean isDonor;
	@Getter @Setter
	private Boolean isValidated;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	

	
}
