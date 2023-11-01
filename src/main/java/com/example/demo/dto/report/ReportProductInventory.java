package com.example.demo.dto.report;

public class ReportProductInventory {

	private Long id; // product id
	private String product_name;
	private String product_sku;
	private String product_category;
	private String product_brand;
	private Integer quantity;
	private Integer quantity_sold;

	public ReportProductInventory() {
		super();
	}

	public ReportProductInventory(Long id, String product_name, String product_sku, String product_category,
			String product_brand, Integer quantity, Integer quantity_sold) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.product_sku = product_sku;
		this.product_category = product_category;
		this.product_brand = product_brand;
		this.quantity = quantity;
		this.quantity_sold = quantity_sold;
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

	public String getProduct_brand() {
		return product_brand;
	}

	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity_sold() {
		return quantity_sold;
	}

	public void setQuantity_sold(Integer quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

}
