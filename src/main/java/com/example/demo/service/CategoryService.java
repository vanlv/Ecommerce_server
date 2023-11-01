package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.category.CategoryDto;
import com.example.demo.dto.category.CategoryDtoRes;

@Service
public interface CategoryService {

	// admin
	public Page<CategoryDtoRes> getList(Integer page, Integer limit, String sortBy);

	public Page<CategoryDtoRes> getListHide(Integer page, Integer limit, String sortBy);

	public Page<CategoryDtoRes> getAll(Integer page, Integer limit, String sortBy);
	
	// user

	public List<CategoryDto> getAllCategoryWithSub();

	public List<CategoryDto> getAllCategory(String category);

	public CategoryDto saveOrUpdate(CategoryDto dto);

	public CategoryDto getOneCategory(String code);

	public CategoryDto getOne(Long id);

	public Boolean deleteCategory(Long id);

	public Boolean checkCode(Long id, String code);
}
