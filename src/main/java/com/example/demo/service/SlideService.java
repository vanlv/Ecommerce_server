package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.other.SlideDto;

@Service
public interface SlideService {
	public Page<SlideDto> getList(Integer page, Integer limit, String sortBy);
	
	public List<SlideDto> getListDisplay();

	public SlideDto saveOrUpdate(SlideDto dto);

	public Boolean delete(Long id);
	
	public SlideDto getOne(Long id);
	
	public Boolean checkCode(Long id, String code);
}
