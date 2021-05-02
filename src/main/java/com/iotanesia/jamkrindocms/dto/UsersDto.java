package com.iotanesia.jamkrindocms.dto;

import java.io.Serializable;

public class UsersDto implements Serializable{
	
	private Integer idUser;
	private String email;
	private String role;
	private String name;
	private String passwordUser;
	private String rememberToken;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	public String getRememberToken() {
		return rememberToken;
	}
	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
