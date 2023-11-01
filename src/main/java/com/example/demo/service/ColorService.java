package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.product.ColorDto;

@Service
public interface ColorService {

	public Page<ColorDto> getAll(Integer page, Integer limit, String sortBy);

	public ColorDto getOne(Long id);

	public ColorDto getOneByColor(String color);

	public ColorDto saveOrUpdate(ColorDto dto);

	public Boolean delete(Long id);

	public Boolean checkCode(Long id, String code);
}
