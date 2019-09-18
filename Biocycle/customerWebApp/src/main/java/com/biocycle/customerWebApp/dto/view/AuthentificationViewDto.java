package com.biocycle.customerWebApp.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new authentification view dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new authentification view dto.
 *
 * @param email the email
 * @param password the password
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"email","password"})
public class AuthentificationViewDto {
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
}
