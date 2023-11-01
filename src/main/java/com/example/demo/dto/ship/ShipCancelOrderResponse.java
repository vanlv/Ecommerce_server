package com.example.demo.dto.ship;

public class ShipCancelOrderResponse {
	private String message;
	private Boolean success;
	private String order_code;

	public ShipCancelOrderResponse(String message, Boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public ShipCancelOrderResponse(String message, Boolean success, String order_code) {
		super();
		this.message = message;
		this.success = success;
		this.order_code = order_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

}
