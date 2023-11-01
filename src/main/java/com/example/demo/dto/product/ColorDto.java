package com.example.demo.dto.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Color;

public class ColorDto extends AbstractDTO<ColorDto> {

	private String name;
	private Integer display;
	private String createdDate;

	public ColorDto() {
		// TODO Auto-generated constructor stub
	}

	public ColorDto(String name) {
		super();
		this.name = name;
	}



	public ColorDto(Color entity) {
		super();
		this.setId(entity.getId());
		this.name = entity.getName();
		this.display = entity.getDisplay();
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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