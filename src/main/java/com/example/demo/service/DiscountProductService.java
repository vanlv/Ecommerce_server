package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.promotion.DiscountProductDto;

@Service
public interface DiscountProductService {

	public Page<DiscountProductDto> getList(SearchDto dto);
	
	public DiscountProductDto getOneByProductId(Long product_id);
	
	public DiscountProductDto updateDiscountProduct(DiscountProductDto dto);
	
	public Boolean deleteDiscountProduct(Long product_id);

}
