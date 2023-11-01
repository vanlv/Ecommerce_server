package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.product.BrandDto;

@Service
public interface BrandService {
	public Page<BrandDto> getList(Integer page, Integer limit, String sortBy);
	
	public Page<BrandDto> getListHide(Integer page, Integer limit, String sortBy);
	
	public Page<BrandDto> getAll(Integer page, Integer limit, String sortBy);

	public BrandDto getOne(Long id);
	
	public List<BrandDto> getListByCategory(String category);
	
	public BrandDto getOneByCode(String code);
	
	public BrandDto saveOrUpdate(BrandDto dto);

	public Boolean delete(Long id);

	public Boolean checkCode(Long id, String code);
}
