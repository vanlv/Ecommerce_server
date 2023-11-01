package com.example.demo.dto.user;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.ShopInfo;

public class ShopInfoDto extends AbstractDTO<ShopInfoDto> {
	private String shop_name;
	private String shop_email;
	private String shop_phone;
	private String shop_address;
	private String shop_description;

	public ShopInfoDto() {
		super();
	}

	public ShopInfoDto(ShopInfo s) {
		super();
		this.setId(s.getId());
		this.shop_name = s.getShop_name();
		this.shop_address = s.getShop_address();
		this.shop_email = s.getShop_email();
		this.shop_phone = s.getShop_phone();
		this.shop_description = s.getShop_description();
	}

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
