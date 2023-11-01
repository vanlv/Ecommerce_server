package com.example.demo.entity.order;

import com.example.demo.entity.product.Product;

public class Item {

	private Product product;
	private Integer quantity;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
