package com.example.demo.dto.ship;

public class Item {
	private String name;
	private String code;
	private Integer quantity;
	private Long price;
	private Double weight;
	private Integer product_code;

	public Item() {
		super();
	}

	public Item(String name, String code, Integer quantity, Long price) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.price = price;
	}

	public Item(String name, String code, Integer quantity, Long price, Double weight) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.price = price;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getProduct_code() {
		return product_code;
	}

	public void setProduct_code(Integer product_code) {
		this.product_code = product_code;
	}

}
