package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.inventory.SupplierDto;

@Service
public interface SupplierService {

	public Page<SupplierDto> getList(Integer page, Integer limit, String sortBy);

	public SupplierDto saveOrUpdate(SupplierDto dto);

	public SupplierDto getOne(Long id);

	public Boolean delete(Long id);

	public Boolean checkCode(Long id, String code);

}
