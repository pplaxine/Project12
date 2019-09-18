package com.biocycle.staffCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Staff.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity

/**
 * Instantiates a new staff.
 */
@NoArgsConstructor

/**
 * Instantiates a new staff.
 *
 * @param id the id
 * @param userName the user name
 * @param password the password
 * @param name the name
 * @param surname the surname
 * @param role the role
 * @param access the access
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"id","userName","password","name","surname","role","access"})
public class Staff {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** The user name. */
	@NotBlank
	private String userName;
	
	/** The password. */
	@NotBlank
	private String password;
	
	/** The name. */
	@NotBlank
	private String name;
	
	/** The surname. */
	@NotBlank
	private String surname;
	
	/** The role. */
	@NotBlank
	private String role;
	
	/** The access. */
	@NotBlank
	private String access;
	
}
