package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.category.SubCategoryDto;

@Service
public interface SubCategoryService {
	
	public Page<SubCategoryDto> getList(Integer page, Integer limit, String sortBy);
	
	public Page<SubCategoryDto> getListHide(Integer page, Integer limit, String sortBy);
	
	public Page<SubCategoryDto> getAll(Integer page, Integer limit, String sortBy);
	
	public List<SubCategoryDto> getSubCategoryByCategory(String categoryCode);
	
	public SubCategoryDto saveOrUpdate(SubCategoryDto dto);
	
	public SubCategoryDto getOne(Long id);

	public Boolean deleteSubCategory(Long id);
	
	public Boolean checkCode(Long id, String code);
}
