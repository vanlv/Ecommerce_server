package com.example.demo.dto.category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.entity.category.Category;

public class CategoryDtoRes {

	private Long id;
	private String name;
	private String code;
	private Integer display;
	private String createdDate;

	public CategoryDtoRes() {
		// TODO Auto-generated constructor stub
	}

	public CategoryDtoRes(Category entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.code = entity.getCode();
		this.display = entity.getDisplay();
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
