package com.example.demo.dto.report;

public class ReportCategory {
	
	private Long id; // category id
	private String category_name;
	private String category_code;
	private Long quantity_sold;
	private Long total_price;

	public ReportCategory() {
		super();
	}

	public ReportCategory(Long id, String category_name, String category_code, Long quantity_sold, Long total_price) {
		super();
		this.id = id;
		this.category_name = category_name;
		this.category_code = category_code;
		this.quantity_sold = quantity_sold;
		this.total_price = total_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
