package com.example.demo.entity.promotion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.product.Product;

@Entity
@Table(name = "tbl_product_discount")
public class ProductDiscount extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

	@Column(name = "type")		// 1: giam gia theo %, 2: giam gia theo gia tri
	private Integer type;

	@Column(name = "value")
	private Long value;

	@Column(name = "status")
	private Integer status;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
