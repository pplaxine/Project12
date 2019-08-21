package com.biocycle.entWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","userName","password","name","surname","role","access"})
public class StaffBeanDto {
	
	private int id;
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String role;
	private String access;
}
