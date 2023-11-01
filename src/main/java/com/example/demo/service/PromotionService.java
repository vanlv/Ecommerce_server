package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.other.PromotionDto;

@Service
public interface PromotionService {

	public Page<PromotionDto> getList(Integer page, Integer limit, String sortBy);
	
	public List<PromotionDto> getListDisplay();

	public PromotionDto saveOrUpdate(PromotionDto dto);

	public Boolean delete(Long id);
	
	public PromotionDto getOne(Long id);
	
	public Boolean checkCode(Long id, String code);
	
}
