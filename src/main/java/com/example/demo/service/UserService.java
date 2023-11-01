package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.user.UserDto;

@Service
public interface UserService {
	public UserDto getCurrentUser(Long id);
	
	public Page<UserDto> getList(SearchDto dto);
	
	public Page<UserDto> getListByRole(String role, SearchDto dto);
	
}
