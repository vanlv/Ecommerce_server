package com.example.demo.dto.report;

public class ReportBrand {
	private Long id;
	private String brand_name;
	private String brand_code;
	private Long quantity_sold;
	private Long total_price;

	public ReportBrand() {
		super();
	}

	public ReportBrand(Long id, String brand_name, String brand_code, Long quantity_sold, Long total_price) {
		super();
		this.id = id;
		this.brand_name = brand_name;
		this.brand_code = brand_code;
		this.quantity_sold = quantity_sold;
		this.total_price = total_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getBrand_code() {
		return brand_code;
	}

	public void setBrand_code(String brand_code) {
		this.brand_code = brand_code;
	}

	public Long getQuantity_sold() {
		return quantity_sold;
	}

	public void setQuantity_sold(Long quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

}
