package com.example.demo.dto.inventory;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.inventory.Supplier;

public class SupplierDto extends AbstractDTO<SupplierDto> {

	private String name;
	private String code;
	private String email;
	private String phone;
	private String address;
	private String province;
	private String district;
	private String ward;

	public SupplierDto() {
		// TODO Auto-generated constructor stub
	}

	public SupplierDto(Supplier entity) {
		super();
		this.setId(entity.getId());
		this.name = entity.getName();
		this.code = entity.getCode();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.address = entity.getAddress();
		this.province = entity.getProvince();
		this.district = entity.getDistrict();
		this.ward = entity.getWard();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

}
