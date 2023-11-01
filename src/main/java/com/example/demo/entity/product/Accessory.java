package com.example.demo.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_accessory")
public class Accessory extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

	@Column(name = "accessory_model")
	private String accessory_model;

	@Column(name = "featute")
	private String featute;

	public Accessory() {
		// TODO Auto-generated constructor stub
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAccessory_model() {
		return accessory_model;
	}

	public void setAccessory_model(String accessory_model) {
		this.accessory_model = accessory_model;
	}

	public String getFeatute() {
		return featute;
	}

	public void setFeatute(String featute) {
		this.featute = featute;
	}

}
