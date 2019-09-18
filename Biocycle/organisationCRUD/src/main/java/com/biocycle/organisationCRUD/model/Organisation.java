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


// TODO: Auto-generated Javadoc
/**
 * The Class Organisation.
 */
@Entity

/**
 * Instantiates a new organisation.
 */
@NoArgsConstructor

/**
 * Instantiates a new organisation.
 *
 * @param id the id
 * @param organisationName the organisation name
 * @param emailAddress the email address
 * @param password the password
 * @param organisationAddress the organisation address
 * @param collectionAddressList the collection address list
 * @param phoneNumber the phone number
 * @param isDonor the is donor
 * @param isValidated the is validated
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","organisationName","emailAddress","password","organisationAddress","collectionAddressList","phoneNumber","isDonor","isValidated"})
public class Organisation {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//to start sequence at existing values 
	private int id;
	
	/** The organisation name. */
	@NotBlank
	
	/**
	 * Gets the organisation name.
	 *
	 * @return the organisation name
	 */
	@Getter 
 /**
  * Sets the organisation name.
  *
  * @param organisationName the new organisation name
  */
 @Setter
	private String organisationName;
	
	/** The email address. */
	@NotBlank
	@Email
	
	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	@Getter 
 /**
  * Sets the email address.
  *
  * @param emailAddress the new email address
  */
 @Setter
	@Column(unique = true)
	private String emailAddress;
	
	/** The password. */
	@JsonIgnore
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	@Getter 
 /**
  * Sets the password.
  *
  * @param password the new password
  */
 @Setter
	private String password;
	
	/** The organisation address. */
	@Embedded
	@NotNull
	
	/**
	 * Gets the organisation address.
	 *
	 * @return the organisation address
	 */
	@Getter 
 /**
  * Sets the organisation address.
  *
  * @param organisationAddress the new organisation address
  */
 @Setter
	private Address organisationAddress;
	
	/** The collection address list. */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "organisation_address_mapping",
		joinColumns = {@JoinColumn(name="organisation_id", referencedColumnName = "id")},
		inverseJoinColumns =  {@JoinColumn(name = "address_id", referencedColumnName = "id")}
	)
	@MapKey(name = "spotName")	 
	
	/**
	 * Gets the collection address list.
	 *
	 * @return the collection address list
	 */
	@Getter 
 /**
  * Sets the collection address list.
  *
  * @param collectionAddressList the collection address list
  */
 @Setter
	private Map<String,CollectionSpotAddress> collectionAddressList;
	
	/** The phone number. */
	@NotBlank
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	@Getter 
 /**
  * Sets the phone number.
  *
  * @param phoneNumber the new phone number
  */
 @Setter
	private String phoneNumber;	
	
	/**
	 * Gets the checks if is donor.
	 *
	 * @return the checks if is donor
	 */
	@Getter 
 /**
  * Sets the checks if is donor.
  *
  * @param isDonor the new checks if is donor
  */
 @Setter
	private Boolean isDonor;
	
	/**
	 * Gets the checks if is validated.
	 *
	 * @return the checks if is validated
	 */
	@Getter 
 /**
  * Sets the checks if is validated.
  *
  * @param isValidated the new checks if is validated
  */
 @Setter
	private Boolean isValidated;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	

	
}
