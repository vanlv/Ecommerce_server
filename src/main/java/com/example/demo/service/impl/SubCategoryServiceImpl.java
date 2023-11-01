package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.category.SubCategoryDto;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository repos;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<SubCategoryDto> getList(Integer page, Integer limit, String sortBy) {
		Page<SubCategory> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<SubCategoryDto> dtos = list.map(tag -> new SubCategoryDto(tag));

		return dtos;
	}

	@Override
	public Page<SubCategoryDto> getListHide(Integer page, Integer limit, String sortBy) {
		Page<SubCategory> list = repos.getListHide(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<SubCategoryDto> dtos = list.map(tag -> new SubCategoryDto(tag));

		return dtos;
	}

	@Override
	public Page<SubCategoryDto> getAll(Integer page, Integer limit, String sortBy) {
		Page<SubCategory> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<SubCategoryDto> dtos = list.map(tag -> new SubCategoryDto(tag));

		return dtos;
	}

	@Override
	public List<SubCategoryDto> getSubCategoryByCategory(String categoryCode) {
		List<SubCategoryDto> list = new ArrayList<>();
		List<SubCategory> entities = repos.findAllByCategoryCode(categoryCode);
		for (SubCategory entity : entities) {
			SubCategoryDto dto = new SubCategoryDto(entity);
			list.add(dto);
		}

		return list;
	}

	@Override
	public SubCategoryDto saveOrUpdate(SubCategoryDto dto) {

		Category category = categoryRepository.findOneByCode(dto.getCategoryCode());

		if (dto != null) {
			SubCategory entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new SubCategory();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCategory(category);
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new SubCategoryDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteSubCategory(Long id) {
		if (id != null) {
			SubCategory entity = repos.getById(id);
			if(entity.getDisplay() == 1) {
				entity.setDisplay(0);
			} else {
				entity.setDisplay(1);
			}
			entity = repos.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkCode(Long id, String code) {
		if (code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public SubCategoryDto getOne(Long id) {
		// TODO Auto-generated method stub
		SubCategory entity = repos.getById(id);
		SubCategoryDto dto = new SubCategoryDto(entity);
		return dto;
	}

}
