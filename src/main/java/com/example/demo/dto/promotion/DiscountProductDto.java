package com.example.demo.dto.promotion;

import com.example.demo.entity.promotion.ProductDiscount;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DiscountProductDto {
	private Long id; // productid
	private Integer status;
	@JsonInclude(value = Include.NON_NULL)
	private Integer type;
	@JsonInclude(value = Include.NON_NULL)
	private Long value;
	private String productName;
	private String productMainImage;
	private Long price;
	@JsonInclude(value = Include.NON_NULL)
	private Long real_price;
	private Long quantity_item;

	public DiscountProductDto() {
		super();
	}

	public DiscountProductDto(Long id, String productName, String productMainImage, Long price, Long quantity_item,
			Integer status, Integer type, Long value) {
		super();
		this.id = id;
		this.status = status;
		this.type = type;
		this.value = value;
		this.productName = productName;
		this.productMainImage = productMainImage;
		this.price = price;
		this.quantity_item = quantity_item;
	}

	public DiscountProductDto(ProductDiscount entity) {
		super();
		this.id = entity.getProduct().getId();
		this.status = entity.getStatus();
		this.type = entity.getType();
		this.value = entity.getValue();
		this.productName = entity.getProduct().getName();
		this.productMainImage = entity.getProduct().getMainIamge();
		this.price = entity.getProduct().getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductMainImage() {
		return productMainImage;
	}

	public void setProductMainImage(String productMainImage) {
		this.productMainImage = productMainImage;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getReal_price() {
		return real_price;
	}

	public void setReal_price(Long real_price) {
		this.real_price = real_price;
	}

	public Long getQuantity_item() {
		return quantity_item;
	}

	public void setQuantity_item(Long quantity_item) {
		this.quantity_item = quantity_item;
	}

}
