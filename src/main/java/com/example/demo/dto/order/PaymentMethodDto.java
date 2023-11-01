package com.example.demo.dto.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.PaymentMethod;

public class PaymentMethodDto extends AbstractDTO<PaymentMethodDto> {

	private String name;
	private String code;
	private Integer type;
	private Integer display;
	private String createdDate;

	public PaymentMethodDto() {
		// TODO Auto-generated constructor stub
	}

	public PaymentMethodDto(PaymentMethod entity) {
		super();
		this.setId(entity.getId());
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.name = entity.getName();
		this.code = entity.getCode();
		this.type = entity.getType();
		this.display = entity.getDisplay();
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
