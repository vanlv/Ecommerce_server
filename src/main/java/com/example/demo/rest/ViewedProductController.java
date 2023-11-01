package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.product.ProductListDto;
import com.example.demo.dto.user.ViewedProductDto;
import com.example.demo.service.ViewedProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/viewed")
public class ViewedProductController {

	@Autowired
	private ViewedProductService service;

	@GetMapping("/products")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<ProductListDto>> getByUserAndProduct() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<ProductListDto> result = service.getListByUser(username);
		return new ResponseEntity<List<ProductListDto>>(result, HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ViewedProductDto> create(@RequestBody ViewedProductDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		ViewedProductDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ViewedProductDto>(result, HttpStatus.OK);
	}

	@GetMapping("/most-popular")
	public ResponseEntity<List<ProductListDto>> getListMostPopular() {
		List<ProductListDto> result = service.getListMostPopular();
		return new ResponseEntity<List<ProductListDto>>(result, HttpStatus.OK);
	}

}
