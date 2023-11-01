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

import com.example.demo.dto.category.CategoryDto;
import com.example.demo.dto.category.CategoryDtoRes;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repos;

	@Autowired
	private SubCategoryRepository subRepos;

	@Override
	public Page<CategoryDtoRes> getList(Integer page, Integer limit, String sortBy) {
//		List<Category> list = repos.getList();
//		List<CategoryDtoNew> dtos = new ArrayList<>();
//		for (Category entity : list) {
//			CategoryDtoNew dto = new CategoryDtoNew(entity);
//			dtos.add(dto);
//		}
		Page<Category> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<CategoryDtoRes> dtos = list.map(tag -> new CategoryDtoRes(tag));
		return dtos;
	}

	@Override
	public Page<CategoryDtoRes> getListHide(Integer page, Integer limit, String sortBy) {
//		List<Category> list = repos.getListHide();
//		List<CategoryDtoNew> dtos = new ArrayList<>();
//		for (Category entity : list) {
//			CategoryDtoNew dto = new CategoryDtoNew(entity);
//			dtos.add(dto);
//		}
		Page<Category> list = repos.getListHide(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<CategoryDtoRes> dtos = list.map(tag -> new CategoryDtoRes(tag));
		return dtos;
	}

	@Override
	public Page<CategoryDtoRes> getAll(Integer page, Integer limit, String sortBy) {
//		List<Category> list = repos.findAll();
//		List<CategoryDtoNew> dtos = new ArrayList<>();
//		for (Category entity : list) {
//			CategoryDtoNew dto = new CategoryDtoNew(entity);
//			dtos.add(dto);
//		}
		Page<Category> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<CategoryDtoRes> dtos = list.map(tag -> new CategoryDtoRes(tag));
		return dtos;
	}

	@Override
	public List<CategoryDto> getAllCategoryWithSub() {

		List<CategoryDto> list = new ArrayList<>();

		List<SubCategory> listSub = new ArrayList<>();

		Page<Category> listEntity = repos.getList(null);
		List<Category> entities = listEntity.toList();
		for (Category entity : entities) {
			SubCategory subDto = subRepos.findOneByCode(entity.getCode());
			listSub.add(subDto);
			CategoryDto dto = new CategoryDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public CategoryDto saveOrUpdate(CategoryDto dto) {
		if (dto != null) {
			Category entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new Category();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new CategoryDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		if (id != null) {
			Category entity = repos.getById(id);
			if (entity.getDisplay() == 1) {
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
	public List<CategoryDto> getAllCategory(String categoryCode) {
		return null;
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
	public CategoryDto getOneCategory(String code) {
		CategoryDto dto = new CategoryDto();
		if (code != null) {
			Category category = repos.findOneByCode(code);
			dto = new CategoryDto(category);
		}
		return dto;
	}

	@Override
	public CategoryDto getOne(Long id) {
		Category category = repos.getById(id);
		CategoryDto dto = new CategoryDto(category);
		return dto;
	}

}
