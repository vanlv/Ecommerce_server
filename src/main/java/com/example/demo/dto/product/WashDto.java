package com.example.demo.dto.product;

import com.example.demo.entity.product.Wash;

public class WashDto {
	private String wash_weight;
	private String wash_mode;
	private Integer is_fast;
	private String wash_tub;
	private Integer is_inverter;
	private String type_engine;

	public WashDto() {
		super();
	}

	public WashDto(Wash entity) {
		super();
		this.wash_weight = entity.getWash_weight();
		this.wash_mode = entity.getWash_mode();
		this.is_fast = entity.getIs_fast();
		this.wash_tub = entity.getWash_tub();
		this.is_inverter = entity.getIs_inverter();
		this.type_engine = entity.getType_engine();
	}

	public String getWash_weight() {
		return wash_weight;
	}

	public void setWash_weight(String wash_weight) {
		this.wash_weight = wash_weight;
	}

	public String getWash_mode() {
		return wash_mode;
	}

	public void setWash_mode(String wash_mode) {
		this.wash_mode = wash_mode;
	}

	public Integer getIs_fast() {
		return is_fast;
	}

	public void setIs_fast(Integer is_fast) {
		this.is_fast = is_fast;
	}

	public String getWash_tub() {
		return wash_tub;
	}

	public void setWash_tub(String wash_tub) {
		this.wash_tub = wash_tub;
	}

	public Integer getIs_inverter() {
		return is_inverter;
	}

	public void setIs_inverter(Integer is_inverter) {
		this.is_inverter = is_inverter;
	}

	public String getType_engine() {
		return type_engine;
	}

	public void setType_engine(String type_engine) {
		this.type_engine = type_engine;
	}

}
