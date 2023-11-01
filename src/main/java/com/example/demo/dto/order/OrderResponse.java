package com.example.demo.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class OrderResponse {
	@JsonInclude(value = Include.NON_NULL)
	private String message;
	@JsonInclude(value = Include.NON_NULL)
	private Long order_id;
	@JsonInclude(value = Include.NON_NULL)
	private Integer quantity;

	@JsonInclude(value = Include.NON_NULL)
	private Long revenue;

	public OrderResponse() {
		super();
	}

	public OrderResponse(String message, Long order_id) {
		super();
		this.message = message;
		this.order_id = order_id;
	}

	public OrderResponse(String message, Long order_id, Long revenue) {
		super();
		this.message = message;
		this.order_id = order_id;
		this.revenue = revenue;
	}

	public OrderResponse(String message, Integer quantity) {
		super();
		this.message = message;
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

}
