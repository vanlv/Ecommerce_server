package com.example.demo.dto.other;

import java.util.List;

public class ProductTags {
	private List<String> tags;

	public ProductTags() {
		super();
	}

	public ProductTags(List<String> tags) {
		super();
		this.tags = tags;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
