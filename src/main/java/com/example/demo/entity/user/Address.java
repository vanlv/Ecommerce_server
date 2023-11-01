package com.example.demo.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_address")
public class Address extends BaseEntity {

	@Column(name = "city")
	private String city;

	@Column(name = "city_id")
	private Integer city_id;

	@Column(name = "district")
	private String district;

	@Column(name = "district_id")
	private Integer district_id;

	@Column(name = "ward")
	private String ward;

	@Column(name = "ward_id")
	private String ward_id;;

	@Column(name = "house")
	private String house;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	public Address() {
	}

	public Address(String city, String district, String ward, String house) {
		super();
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.house = house;
	}

	public Address(String city, Integer city_id, String district, Integer district_id, String ward, String ward_id,
			String house) {
		super();
		this.city = city;
		this.city_id = city_id;
		this.district = district;
		this.district_id = district_id;
		this.ward = ward;
		this.ward_id = ward_id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
