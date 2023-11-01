package com.example.demo.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_order_payment")
public class Payment extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod method;

	@Column(name = "bank_name")
	private String bankName;

//	@Column(name = "method")
//	private String method;

	@Column(name = "date_payment")
	private String datePayment;

	@Column(name = "trading_code")
	private String tradingCode;

	@Column(name = "status")
	private Integer status;

	public Payment() {
		super();
	}

	public Payment(Order order, PaymentMethod method, String bankName, String datePayment,
			String tradingCode, Integer status) {
		super();
		this.order = order;
		this.method = method;
		this.bankName = bankName;
		this.datePayment = datePayment;
		this.tradingCode = tradingCode;
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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
