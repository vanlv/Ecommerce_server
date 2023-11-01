package com.example.demo.dto.other;

import java.util.List;

public class ProductTagsTags {
	private List<List<String>> tags;

	public ProductTagsTags() {
		super();
	}

	public ProductTagsTags(List<List<String>> tags) {
		super();
		this.tags = tags;
	}

	public List<List<String>> getTags() {
		return tags;
	}

	public void setTags(List<List<String>> tags) {
		this.tags = tags;
	}

}
