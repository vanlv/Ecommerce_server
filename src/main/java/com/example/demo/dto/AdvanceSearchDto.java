package com.example.demo.dto;

public class AdvanceSearchDto {
	private int pageIndex;
	private int pageSize;
	private String name;
	private String sku;
	private Integer display;
	private String brand; // tim theo thuong hieu
	private String supplier;
	private String category;

	// order
	private Integer last_date;
	private Integer status;

	public AdvanceSearchDto() {
		super();
	}

	public AdvanceSearchDto(int pageIndex, int pageSize, Integer status) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.status = status;
	}

	public AdvanceSearchDto(int pageIndex, int pageSize, String name, String sku, Integer display, String brand,
			String supplier, String category) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.name = name;
		this.sku = sku;
		this.display = display;
		this.brand = brand;
		this.supplier = supplier;
		this.category = category;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getLast_date() {
		return last_date;
	}

	public void setLast_date(Integer last_date) {
		this.last_date = last_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
