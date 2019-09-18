package com.biocycle.entWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new staff bean dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new staff bean dto.
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
public class StaffBeanDto {
	
	/** The id. */
	private int id;
	
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;
	
	/** The role. */
	private String role;
	
	/** The access. */
	private String access;
}
