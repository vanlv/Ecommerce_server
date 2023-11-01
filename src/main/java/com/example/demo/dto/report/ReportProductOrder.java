package com.example.demo.dto.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ReportProductOrder {

	@JsonInclude(value = Include.NON_NULL)
	private Long id; // product id
	
	@JsonInclude(value = Include.NON_NULL)
	private String product_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String product_sku;
	
	@JsonInclude(value = Include.NON_NULL)
	private String product_category;
	
	@JsonInclude(value = Include.NON_NULL)
	private String product_brand;
	
	@JsonInclude(value = Include.NON_NULL)
	private Long quantity_sold;
	
	@JsonInclude(value = Include.NON_NULL)
	private Long order_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private Long total_price;

	public ReportProductOrder() {
		super();
	}

	public ReportProductOrder(Long id, String product_name, String product_sku, String product_category,
			String product_brand, Long quantity_sold, Long total_price) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.product_sku = product_sku;
		this.product_category = product_category;
		this.product_brand = product_brand;
		this.quantity_sold = quantity_sold;
		this.total_price = total_price;
	}

	public ReportProductOrder(Long id, String product_name, String product_sku, String product_category,
			String product_brand, Long order_id, Long quantity_sold, Long total_price) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.product_sku = product_sku;
		this.product_category = product_category;
		this.product_brand = product_brand;
		this.order_id = order_id;
		this.quantity_sold = quantity_sold;
		this.total_price = total_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_sku() {
		return product_sku;
	}

	public void setProduct_sku(String product_sku) {
		this.product_sku = product_sku;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getProduct_brand() {
		return product_brand;
	}

	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}

	public Long getQuantity_sold() {
		return quantity_sold;
	}

	public void setQuantity_sold(Long quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

}
