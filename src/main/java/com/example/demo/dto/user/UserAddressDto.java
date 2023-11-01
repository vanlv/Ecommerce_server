package com.example.demo.dto.user;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.Address;

public class UserAddressDto extends AbstractDTO<UserAddressDto> {

	private String username;
	private String phone;
	private String city;
	private Integer city_id;
	private String district;
	private Integer district_id;
	private String ward;
	private String ward_id;
	private String house;

	public UserAddressDto() {
		// TODO Auto-generated constructor stub
	}

	public UserAddressDto(Address entity) {
		super();
		this.setId(entity.getUser().getId());
		this.phone = entity.getUser().getPhone();
		this.username = entity.getUser().getUsername();
		this.city = entity.getCity();
		this.district = entity.getDistrict();
		this.ward = entity.getWard();
		this.house = entity.getHouse();
		this.city_id = entity.getCity_id();
		this.district_id = entity.getDistrict_id();
		this.ward_id = entity.getWard_id();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
