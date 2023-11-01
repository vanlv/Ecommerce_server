package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.inventory.SupplierDto;
import com.example.demo.entity.inventory.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository repos;

	@Override
	public Page<SupplierDto> getList(Integer page, Integer limit, String sortBy) {
		Page<Supplier> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<SupplierDto> dtos = list.map(tag -> new SupplierDto(tag));

		return dtos;
	}

	@Override
	public SupplierDto saveOrUpdate(SupplierDto dto) {
		if (dto != null) {
			Supplier entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
			}
			if (entity == null) {
				entity = new Supplier();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setEmail(dto.getEmail());
			entity.setAddress(dto.getAddress());
			entity.setProvince(dto.getProvince());
			entity.setDistrict(dto.getDistrict());
			entity.setWard(dto.getWard());
			entity.setPhone(dto.getPhone());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new SupplierDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Supplier entity = repos.getById(id);
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
		if (code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public SupplierDto getOne(Long id) {
		Supplier entity = repos.getById(id);
		SupplierDto dto = new SupplierDto(entity);
		return dto;
	}

}
