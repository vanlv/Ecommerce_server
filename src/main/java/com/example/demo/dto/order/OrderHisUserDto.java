package com.example.demo.dto.order;

import com.example.demo.entity.order.Order;

public class OrderHisUserDto {

	private String username;
	private String user_fullname;
	private String phone;
	private String dateOfBirth;
	private String email;

	public OrderHisUserDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderHisUserDto(Order entity) {
		this.dateOfBirth = entity.getUser().getDateOfBirth();
		this.email = entity.getUser().getEmail();
		this.phone = entity.getShipment().getPhone();
		this.username = entity.getUser().getUsername();
		this.user_fullname = entity.getShipment().getCustomer_name();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_fullname() {
		return user_fullname;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
