package com.biocycle.staffCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@NotBlank
	private String role;
	@NotBlank
	private String access;
	
	
	//CONSTRUCTORS
	public Staff(int id, @NotBlank String userName, @NotBlank String password, @NotBlank String name,
			@NotBlank String surname, @NotBlank String role, @NotBlank String access) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.access = access;
	}
	
	public Staff() {
	}
	
	//G&S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}

	
	//toString
	@Override
	public String toString() {
		return "Staff [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name + ", surname="
				+ surname + ", role=" + role + ", access=" + access + "]";
	} 
	
	
	
	
	
}
