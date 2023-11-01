package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.product.ColorDto;
import com.example.demo.entity.product.Color;
import com.example.demo.repository.ColorRepository;
import com.example.demo.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorRepository repos;

	@Override
	public Page<ColorDto> getAll(Integer page, Integer limit, String sortBy) {
		Page<Color> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));
		Page<ColorDto> dtos = list.map(tag -> new ColorDto(tag));
		return dtos;
	}

	@Override
	public ColorDto getOne(Long id) {
		Color brand = repos.getById(id);
		ColorDto dto = new ColorDto(brand);
		return dto;
	}

	@Override
	public ColorDto getOneByColor(String color) {
		Color entity = repos.findOneByName(color);
		ColorDto dto = new ColorDto(entity);
		return dto;
	}

	@Override
	public ColorDto saveOrUpdate(ColorDto dto) {
		if (dto != null) {
			Color entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new Color();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}
			entity.setName(dto.getName());;
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new ColorDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Color entity = repos.getById(id);
			if(entity.getDisplay() == 1) {
				entity.setDisplay(0);
			} else {
				entity.setDisplay(1);
			}
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

}
