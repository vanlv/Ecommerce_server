package com.example.demo.dto.user;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserDto extends AbstractDTO<UserDto> {
	private String username;
	private String email;
	private String fullName;
	private String phone;
	private String dateOfBirth;

	@JsonIgnore
	private UserAddressDto address;

	private String city;
	private Integer city_id;
	private String district;
	private Integer district_id;
	private String ward;
	private String ward_id;
	private String house;

	@JsonIgnore
	@JsonInclude(value = Include.NON_NULL)
	private SellerDto seller;

	private String cccd;
	private Integer exp;

	private Integer display;

	public UserDto() {
		super();
	}

	public UserDto(User user) {
		super();
		this.setId(user.getId());
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.fullName = user.getFullname();
		this.phone = user.getPhone();
		this.dateOfBirth = user.getDateOfBirth();
		this.address = new UserAddressDto();
		if (address != null) {
			this.address = new UserAddressDto(user.getAddress());
			this.city = this.address.getCity();
			this.district = this.address.getDistrict();
			this.ward = this.address.getWard();
			this.house = this.address.getHouse();
			this.city_id = this.address.getCity_id();
			this.district_id = this.address.getDistrict_id();
			this.ward_id = this.address.getWard_id();
		}
		this.display = user.getDisplay();
	}

	public UserAddressDto getAddress() {
		return address;
	}

	public void setAddress(UserAddressDto address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public String getWard_id() {
		return ward_id;
	}

	public void setWard_id(String ward_id) {
		this.ward_id = ward_id;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public SellerDto getSeller() {
		return seller;
	}

	public void setSeller(SellerDto seller) {
		this.seller = seller;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

}
