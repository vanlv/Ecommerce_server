package com.example.demo.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductSpecify {

	@JsonInclude(value = Include.NON_NULL)
	private String attributeName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String attributeValue;

	public ProductSpecify() {
		// TODO Auto-generated constructor stub
	}

	public ProductSpecify(String attributeName, String attributeValue) {
		super();
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
