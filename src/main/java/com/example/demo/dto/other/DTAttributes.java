package com.example.demo.dto.other;

import java.util.List;

public class DTAttributes {

	private String type;
	private List<String> values;

	public DTAttributes() {
		super();
	}

	public DTAttributes(String type, List<String> values) {
		super();
		this.type = type;
		this.values = values;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
