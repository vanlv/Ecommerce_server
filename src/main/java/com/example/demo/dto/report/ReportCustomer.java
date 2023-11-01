package com.example.demo.dto.report;

public class ReportCustomer {
	private Long id; // customer id
	private String customer_name;
	private String customer_phone;
	private Long order_id;
	private Long quantity_buy;
	private Long total_price;

	public ReportCustomer() {
		super();
	}

	public ReportCustomer(Long id, String customer_name, String customer_phone, Long order_id, Long quantity_buy,
			Long total_price) {
		super();
		this.id = id;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.order_id = order_id;
		this.quantity_buy = quantity_buy;
		this.total_price = total_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getQuantity_buy() {
		return quantity_buy;
	}

	public void setQuantity_buy(Long quantity_buy) {
		this.quantity_buy = quantity_buy;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

}
