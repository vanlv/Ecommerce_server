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

import com.example.demo.dto.other.PromotionDto;
import com.example.demo.entity.other.Promotion;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionRepository repos;

	@Override
	public Page<PromotionDto> getList(Integer page, Integer limit, String sortBy) {
		Page<Promotion> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<PromotionDto> dtos = list.map(tag -> new PromotionDto(tag));

		return dtos;
	}
	
	@Override
	public List<PromotionDto> getListDisplay() {
		// TODO Auto-generated method stub
		List<Promotion> list = repos.getList();
		List<PromotionDto> dtos = new ArrayList<>();
		for(Promotion item : list) {
			PromotionDto dto = new PromotionDto(item);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public PromotionDto saveOrUpdate(PromotionDto dto) {
		if (dto != null) {
			Promotion entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new Promotion();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}

			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new PromotionDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Promotion entity = repos.getById(id);
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
	public Boolean checkCode(Long id, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDto getOne(Long id) {
		if(id != null) {
			Promotion entity = repos.getById(id);
			PromotionDto dto = new PromotionDto(entity);
			return dto;
		}
		return null;
	}

}
