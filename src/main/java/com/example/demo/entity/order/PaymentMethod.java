package com.example.demo.entity.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_payment_method")

public class PaymentMethod extends BaseEntity {

	@OneToMany(mappedBy = "method", cascade = CascadeType.ALL)
	private List<Payment> payments;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "type")
	private Integer type;

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	public PaymentMethod() {
		super();
	}

	public PaymentMethod(String name, String code, Integer type) {
		super();
		this.name = name;
		this.code = code;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

}
