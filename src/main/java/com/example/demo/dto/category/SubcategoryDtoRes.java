package com.example.demo.dto.category;

import com.example.demo.entity.category.SubCategory;

public class SubcategoryDtoRes {

	private String name;
	private String code;
	private String category_code;
	private String category_name;

	public SubcategoryDtoRes() {
		super();
	}
	
	public SubcategoryDtoRes(SubCategory entity) {
		this.name = entity.getName();
		this.code = entity.getCode();
		this.category_code = entity.getCategory().getCode();
		this.category_name = entity.getCategory().getName();
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

	
	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
}
