package com.example.demo.dto.ship;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ShipInfoOrderResponse {
	@JsonInclude(value = Include.NON_NULL)
	private String message;

	@JsonInclude(value = Include.NON_NULL)
	private Boolean success;

	@JsonInclude(value = Include.NON_NULL)
	private String order_code; // ma don hang tren trang giao hang

	@JsonInclude(value = Include.NON_NULL)
	private String partner_id; // ma don hang cua shop gui len

	@JsonInclude(value = Include.NON_NULL)
	private Integer status;

	@JsonInclude(value = Include.NON_NULL)
	private String status_name;

	@JsonInclude(value = Include.NON_NULL)
	private String created_date;

	@JsonInclude(value = Include.NON_NULL)
	private String updated_date;

	@JsonInclude(value = Include.NON_NULL)
	private String pick_date; // ngay lay hang

	@JsonInclude(value = Include.NON_NULL)
	private String deliver_date; // ngay du kien giao hang den khach hang

	@JsonInclude(value = Include.NON_NULL)
	private String to_name; // ho ten nguoi nhan

	@JsonInclude(value = Include.NON_NULL)
	private String to_phone; // sdt nguoi nhan

	@JsonInclude(value = Include.NON_NULL)
	private String to_address; // dia chi nhan hang

	@JsonInclude(value = Include.NON_NULL)
	private Integer weight;

	@JsonInclude(value = Include.NON_NULL)
	private Integer is_freeship;

	@JsonInclude(value = Include.NON_NULL)
	private Long cod_amount; // tien cod thu ho

	@JsonInclude(value = Include.NON_NULL)
	private Long insurance; // tien phi bao hiem

	@JsonInclude(value = Include.NON_NULL)
	private Long ship_fee; // tong phi ship

	@JsonInclude(value = Include.NON_NULL)
	private Long order_value; // gia tri don hang

	public ShipInfoOrderResponse() {
		super();
	}

	public ShipInfoOrderResponse(String message, Boolean success, String order_code, String partner_id, Integer status,
			String status_name, String created_date, String updated_date, String pick_date, String deliver_date,
			String to_name, String to_phone, String to_address, Integer weight, Integer is_freeship, Long cod_amount,
			Long insurance, Long ship_fee, Long order_value) {
		super();
		this.message = message;
		this.success = success;
		this.order_code = order_code;
		this.partner_id = partner_id;
		this.status = status;
		this.status_name = status_name;
		this.created_date = created_date;
		this.updated_date = updated_date;
		this.pick_date = pick_date;
		this.deliver_date = deliver_date;
		this.to_name = to_name;
		this.to_phone = to_phone;
		this.to_address = to_address;
		this.weight = weight;
		this.is_freeship = is_freeship;
		this.cod_amount = cod_amount;
		this.insurance = insurance;
		this.ship_fee = ship_fee;
		this.order_value = order_value;
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

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public String getPick_date() {
		return pick_date;
	}

	public void setPick_date(String pick_date) {
		this.pick_date = pick_date;
	}

	public String getDeliver_date() {
		return deliver_date;
	}

	public void setDeliver_date(String deliver_date) {
		this.deliver_date = deliver_date;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public String getTo_phone() {
		return to_phone;
	}

	public void setTo_phone(String to_phone) {
		this.to_phone = to_phone;
	}

	public String getTo_address() {
		return to_address;
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getIs_freeship() {
		return is_freeship;
	}

	public void setIs_freeship(Integer is_freeship) {
		this.is_freeship = is_freeship;
	}

	public Long getCod_amount() {
		return cod_amount;
	}

	public void setCod_amount(Long cod_amount) {
		this.cod_amount = cod_amount;
	}

	public Long getInsurance() {
		return insurance;
	}

	public void setInsurance(Long insurance) {
		this.insurance = insurance;
	}

	public Long getShip_fee() {
		return ship_fee;
	}

	public void setShip_fee(Long ship_fee) {
		this.ship_fee = ship_fee;
	}

	public Long getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}

}
