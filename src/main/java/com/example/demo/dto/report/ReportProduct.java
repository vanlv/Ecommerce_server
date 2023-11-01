package com.example.demo.dto.report;

public class ReportProduct {
	private Long id; // mã đơn hàng
	private Integer status;
	private String status_order_name;
	private Integer quantity;
	private Long total_price; // tong tien don hang/san pham
	private String create_time;

	public ReportProduct() {
		super();
	}

	public ReportProduct(Long id, Integer status, Integer quantity, Long total_price, String create_time) {
		super();
		this.id = id;
		this.status = status;
		this.quantity = quantity;
		this.total_price = total_price;
		this.create_time = create_time;
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

	public String getStatus_order_name() {
		return status_order_name;
	}

	public void setStatus_order_name(String status_order_name) {
		this.status_order_name = status_order_name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
