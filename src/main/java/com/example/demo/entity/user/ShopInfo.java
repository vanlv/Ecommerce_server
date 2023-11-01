package com.example.demo.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_shop_info")
public class ShopInfo extends BaseEntity {

	@Column(name = "name")
	private String shop_name;

	@Column(name = "email")
	private String shop_email;

	@Column(name = "phone")
	private String shop_phone;

	@Column(name = "address")
	private String shop_address;

	@Column(name = "description")
	private String shop_description;

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_email() {
		return shop_email;
	}

	public void setShop_email(String shop_email) {
		this.shop_email = shop_email;
	}

	public String getShop_phone() {
		return shop_phone;
	}

	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getShop_description() {
		return shop_description;
	}

	public void setShop_description(String shop_description) {
		this.shop_description = shop_description;
	}

}
