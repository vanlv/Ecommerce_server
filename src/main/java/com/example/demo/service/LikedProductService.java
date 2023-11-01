package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.user.LikedProductDto;

@Service
public interface LikedProductService {

	public List<LikedProductDto> getListByUser(String username);

	public LikedProductDto saveOrUpdate(LikedProductDto dto);
	
	public Boolean getByUserAndProduct(String username, Long productId);

	public Boolean delete(String username, Long productId);

}
