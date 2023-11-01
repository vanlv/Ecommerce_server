package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.order.PaymentMethodDto;
import com.example.demo.entity.order.PaymentMethod;
import com.example.demo.repository.PaymentMethodRepository;
import com.example.demo.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private PaymentMethodRepository repos;
	
	@Override
	public Page<PaymentMethodDto> getList(Integer page, Integer limit, String sortBy) {
		Page<PaymentMethod> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<PaymentMethodDto> dtos = list.map(tag -> new PaymentMethodDto(tag));

		return dtos;
	}
	
	@Override
	public Page<PaymentMethodDto> getListHide(Integer page, Integer limit, String sortBy) {
		Page<PaymentMethod> list = repos.getListHide(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<PaymentMethodDto> dtos = list.map(tag -> new PaymentMethodDto(tag));

		return dtos;
	}
	
	@Override
	public Page<PaymentMethodDto> getAll(Integer page, Integer limit, String sortBy) {
		Page<PaymentMethod> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<PaymentMethodDto> dtos = list.map(tag -> new PaymentMethodDto(tag));

		return dtos;
	}

	@Override
	public PaymentMethodDto saveOrUpdate(PaymentMethodDto dto) {
		if (dto != null) {
			PaymentMethod entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new PaymentMethod();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new PaymentMethodDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			PaymentMethod entity = repos.getById(id);
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
		if(code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public PaymentMethodDto getOne(Long id) {
		// TODO Auto-generated method stub
		PaymentMethod entity = repos.getById(id);
		PaymentMethodDto dto = new PaymentMethodDto(entity);
		return dto;
	}

	

}
