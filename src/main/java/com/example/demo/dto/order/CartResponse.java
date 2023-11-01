package com.example.demo.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CartResponse {

	@JsonInclude(value = Include.NON_NULL)
	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private Integer items_quantity;
	
	@JsonInclude(value = Include.NON_NULL)
	private Integer items_count;

	public CartResponse() {
		// TODO Auto-generated constructor stub
	}

	public CartResponse(String message) {
		super();
		this.message = message;
	}

	public CartResponse(String message, Integer items_quantity, Integer items_count) {
		super();
		this.message = message;
		this.items_quantity = items_quantity;
		this.items_count = items_count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getItems_quantity() {
		return items_quantity;
	}

	public void setItems_quantity(Integer items_quantity) {
		this.items_quantity = items_quantity;
	}

	public Integer getItems_count() {
		return items_count;
	}

	public void setItems_count(Integer items_count) {
		this.items_count = items_count;
	}

}
