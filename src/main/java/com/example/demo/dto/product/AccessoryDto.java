package com.example.demo.dto.product;

import com.example.demo.entity.product.Accessory;

public class AccessoryDto {

	private String feature;
	private String accessory_model;

	public AccessoryDto() {
		super();
	}

	public AccessoryDto(Accessory entity) {
		super();
		this.feature = entity.getFeatute();
		this.accessory_model = entity.getAccessory_model();
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getAccessory_model() {
		return accessory_model;
	}

	public void setAccessory_model(String accessory_model) {
		this.accessory_model = accessory_model;
	}

}
