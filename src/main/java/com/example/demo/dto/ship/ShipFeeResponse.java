package com.example.demo.dto.ship;

public class ShipFeeResponse {
	private Long only_ship_fee;
	private Long total_ship_fee;
	private Long insurance_fee;
	private String message;
	private Boolean success;

	public ShipFeeResponse(Long only_ship_fee, Long total_ship_fee, Long insurance_fee, String message,
			Boolean success) {
		super();
		this.only_ship_fee = only_ship_fee;
		this.total_ship_fee = total_ship_fee;
		this.insurance_fee = insurance_fee;
		this.message = message;
		this.success = success;
	}

	public ShipFeeResponse() {
		super();
	}

	public Long getOnly_ship_fee() {
		return only_ship_fee;
	}

	public void setOnly_ship_fee(Long only_ship_fee) {
		this.only_ship_fee = only_ship_fee;
	}

	public Long getTotal_ship_fee() {
		return total_ship_fee;
	}

	public void setTotal_ship_fee(Long total_ship_fee) {
		this.total_ship_fee = total_ship_fee;
	}

	public Long getInsurance_fee() {
		return insurance_fee;
	}

	public void setInsurance_fee(Long insurance_fee) {
		this.insurance_fee = insurance_fee;
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

}
