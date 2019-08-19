package com.biocycle.customerWebApp.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"email","password"})
public class AuthentificationViewDto {
	
	private String email;
	private String password;
}
