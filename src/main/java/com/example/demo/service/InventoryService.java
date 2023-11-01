package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.inventory.InventoryDetailDto;
import com.example.demo.dto.inventory.InventoryDto;
import com.example.demo.dto.inventory.InventoryDtoRes;
import com.example.demo.dto.product.ColorDto;

@Service
public interface InventoryService {

	public Page<InventoryDtoRes> getList(SearchDto dto);
	
	public List<InventoryDtoRes> getListByProduct(Long productId);
	
	public InventoryDto importOrUpdate(InventoryDto dto);
	
	// huỷ bán 1 sản phẩm có màu nào đó
	public Boolean calcelSellProduct(Long id);
	
	// get các màu chưa có theo id sản phẩm
	
	public List<ColorDto> getAllColorNotExsistProduct(Long productId);
	
	public List<InventoryDetailDto> getDetailInventoryById(Long id);
	
}
