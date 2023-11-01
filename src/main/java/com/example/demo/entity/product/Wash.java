package com.example.demo.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_wash")
public class Wash extends BaseEntity { // máy giặt

	@OneToOne
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

	@Column(name = "wash_weight")
	private String wash_weight; // khoi luong giat

	@Column(name = "wash_mode")
	private String wash_mode;

	@Column(name = "is_fast")
	private Integer is_fast; // che do giat nhanh

	@Column(name = "wash_tub")
	private String wash_tub; // loai long giat (ngang, doc)

	@Column(name = "type_engine") // kiểu động cơ
	private String type_engine;

	@Column(name = "is_inverter")
	private Integer is_inverter;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
