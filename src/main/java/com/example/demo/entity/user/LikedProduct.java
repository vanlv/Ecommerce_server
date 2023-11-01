package com.example.demo.entity.user;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.product.Product;

@Entity
@Table(name = "tbl_product_like")
public class LikedProduct extends BaseEntity {

	@ManyToOne
	private User user;

	@ManyToOne
	private Product product;
	
	public LikedProduct() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
}
