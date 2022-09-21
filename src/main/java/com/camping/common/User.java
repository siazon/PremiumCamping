package com.camping.common;

import java.io.Serializable;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 30 Jul 2022 15:19:21
 * @version 1.0
 */
public class User implements Serializable {
	private String email;
	private String password;
	private String role;
	private String name;

	public User(String email, String password, String role, String name) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format("email: %s, password: %s, role: %s, name: %s", email, password, role, name);
	}
}
