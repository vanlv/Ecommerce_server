package com.example.demo.dto.order;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.Payment;

import java.util.Objects;

public class PaymentDto extends AbstractDTO<PaymentDto> {

	private String bankName;
	private String method_code;
	private String datePayment;
	private String tradingCode;
	private Integer status;

	public PaymentDto() {
		// TODO Auto-generated constructor stub
	}

	public PaymentDto(Payment entity) {
		super();
		this.bankName = entity.getBankName();
		if (Objects.nonNull(entity.getMethod())){
			this.method_code = entity.getMethod().getCode();
		}
		this.datePayment = entity.getDatePayment();
		this.tradingCode = entity.getTradingCode();
		this.status = entity.getStatus();
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMethod_code() {
		return method_code;
	}

	public void setMethod_code(String method_code) {
		this.method_code = method_code;
	}

	public String getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(String datePayment) {
		this.datePayment = datePayment;
	}

	public String getTradingCode() {
		return tradingCode;
	}

	public void setTradingCode(String tradingCode) {
		this.tradingCode = tradingCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
