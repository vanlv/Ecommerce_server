package com.example.demo.dto.auth;

import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.User;

public class RegisterDto extends AbstractDTO<RegisterDto> {
	private String username;
	private String password;
	private String passwordNew;
	private String email;
	private String fullName;
	private String phone;
	private String city;
	private String dateOfBirth;
	private String district;
	private String ward;
	private String house;
	private String cccd;
	private Integer exp;

	private List<String> role;

	public RegisterDto() {
		super();
	}

	public RegisterDto(User entity) {
		super();
		this.setId(entity.getId());
		this.username = entity.getUsername();
		this.password = entity.getPassword();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.fullName = entity.getFullname();
		this.dateOfBirth = entity.getDateOfBirth();
		this.city = entity.getAddress().getCity();
		this.district = entity.getAddress().getDistrict();
		this.ward = entity.getAddress().getWard();
		this.house = entity.getAddress().getHouse();
		this.cccd = entity.getSeller().getCccd();
		this.exp = entity.getSeller().getExp();
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

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

}
