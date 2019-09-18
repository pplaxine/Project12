package com.biocycle.customerWebApp.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new password creation view dto.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoArgsConstructor

/**
 * Instantiates a new password creation view dto.
 *
 * @param email the email
 * @param password the password
 * @param confPassword the conf password
 */
@AllArgsConstructor
@Getter
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(of = {"email","password","confPassword"})
public class PasswordCreationViewDto {
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
	
	/** The conf password. */
	private String confPassword;
}
