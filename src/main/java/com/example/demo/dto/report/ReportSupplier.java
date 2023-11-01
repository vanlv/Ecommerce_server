package com.example.demo.dto.report;

public class ReportSupplier {
	private Long id;
	private String supplier_name;
	private String supplier_code;
	private Long quantity_sold;
	private Long total_price;

	public ReportSupplier() {
		super();
	}

	public ReportSupplier(Long id, String supplier_name, String supplier_code, Long quantity_sold,
			Long total_price) {
		super();
		this.id = id;
		this.supplier_name = supplier_name;
		this.supplier_code = supplier_code;
		this.quantity_sold = quantity_sold;
		this.total_price = total_price;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_code() {
		return supplier_code;
	}

	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
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
