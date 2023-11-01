package com.example.demo.dto.auth;

import com.example.demo.entity.user.User;

public class LoginDto {

	private String username;
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
