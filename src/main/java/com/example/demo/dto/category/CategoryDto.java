package com.example.demo.dto.category;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;

public class CategoryDto extends AbstractDTO<CategoryDto> {

	private String name;
	private String code;
	private List<SubCategoryDto> subcategories;

	public CategoryDto() {
		super();
	}

	public CategoryDto(Category entity) {
		super();
		this.setId(entity.getId());
//		this.setCreatedDate(entity.getCreatedDate());
		this.name = entity.getName();
		this.code = entity.getCode();
		subcategories = new ArrayList<>();
		for (SubCategory category : entity.getSubcategories()) {
			SubCategoryDto dto = new SubCategoryDto(category);
			subcategories.add(dto);
		}

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

	public List<SubCategoryDto> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubCategoryDto> subcategories) {
		this.subcategories = subcategories;
	}

}
