package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.order.PaymentMethodDto;

@Service
public interface PaymentMethodService {

	public Page<PaymentMethodDto> getList(Integer page, Integer limit, String sortBy);
	
	public Page<PaymentMethodDto> getListHide(Integer page, Integer limit, String sortBy);
	
	public Page<PaymentMethodDto> getAll(Integer page, Integer limit, String sortBy);

	public PaymentMethodDto saveOrUpdate(PaymentMethodDto dto);

	public PaymentMethodDto getOne(Long id);

	public Boolean delete(Long id);

	public Boolean checkCode(Long id, String code);
}
