package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.LikedProductDto;
import com.example.demo.service.LikedProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/liked")
public class LikedProductController {

	@Autowired
	private LikedProductService service;

	@GetMapping("/products")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<List<LikedProductDto>> getByUserAndProduct() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<LikedProductDto> result = service.getListByUser(username);
		return new ResponseEntity<List<LikedProductDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/product")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> getOneByProduct(@RequestParam Long productId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Boolean result = service.getByUserAndProduct(username, productId);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@PostMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<LikedProductDto> create(@RequestBody LikedProductDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		LikedProductDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<LikedProductDto>(result, HttpStatus.OK);
	}

	@DeleteMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> delete(@RequestParam Long productId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Boolean result = service.delete(username, productId);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
