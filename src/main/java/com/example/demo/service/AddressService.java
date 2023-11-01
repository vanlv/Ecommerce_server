package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.user.UserAddressDto;

@Service
public interface AddressService {
	
	public UserAddressDto saveOrUpdate(UserAddressDto dto);
	
	public Boolean delete(Long id);
	
	public List<UserAddressDto> getAllAddressByUser(String username);
	
}
