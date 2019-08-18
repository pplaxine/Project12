package com.biocycle.customerWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"email","password","confPassword"})
public class PasswordCreationDto {
	
	private String email;
	private String password;
	private String confPassword;
}
