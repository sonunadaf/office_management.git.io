package com.sodio.proj.office_management.controller.login.dto;

import com.sodio.proj.office_management.util.ResponseDTO;

public class UserDTO extends ResponseDTO {

	private String name;
	private String email;
	private String userRole;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
