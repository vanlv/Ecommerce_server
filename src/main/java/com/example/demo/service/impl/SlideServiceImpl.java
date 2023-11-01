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

import com.example.demo.dto.other.SlideDto;
import com.example.demo.entity.other.Slide;
import com.example.demo.repository.SlideRepository;
import com.example.demo.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {

	@Autowired
	private SlideRepository repos;

	@Override
	public Page<SlideDto> getList(Integer page, Integer limit, String sortBy) {
		Page<Slide> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<SlideDto> dtos = list.map(tag -> new SlideDto(tag));

		return dtos;
	}
	
	@Override
	public List<SlideDto> getListDisplay() {
		// TODO Auto-generated method stub
		List<Slide> list = repos.getList();
		List<SlideDto> dtos = new ArrayList<SlideDto>();
		for(Slide item : list) {
			SlideDto dto = new SlideDto(item);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public SlideDto saveOrUpdate(SlideDto dto) {
		if (dto != null) {
			Slide entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
			}
			if (entity == null) {
				entity = new Slide();
			}

			entity.setImage(dto.getImage());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new SlideDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Slide entity = repos.getById(id);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SlideDto getOne(Long id) {
		// TODO Auto-generated method stub
		if(id != null) {
			Slide s = repos.getById(id);
			SlideDto dto = new SlideDto(s);
			return dto;
		}
		return null;
	}

}
