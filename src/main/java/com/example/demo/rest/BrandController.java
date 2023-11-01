package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.product.BrandDto;
import com.example.demo.service.BrandService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/brand")
public class BrandController {

	@Autowired
	private BrandService service;

	@GetMapping("")
	public ResponseEntity<Page<BrandDto>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "24") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		Page<BrandDto> result = service.getList(page, limit, sortBy);
		return new ResponseEntity<Page<BrandDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<List<BrandDto>> getByCategory(@RequestParam(name = "category", defaultValue = "") String category) {
		List<BrandDto> result = service.getListByCategory(category);
		return new ResponseEntity<List<BrandDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<BrandDto>> getAllAdmin(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "24") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "display", defaultValue = "2") Integer display) {
		Page<BrandDto> result = null;
		if (display == 1) {
			result = service.getList(page, limit, sortBy);
		} else if (display == 0) {
			result = service.getListHide(page, limit, sortBy);
		} else {
			result = service.getAll(page, limit, sortBy);
		}
		return new ResponseEntity<Page<BrandDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BrandDto> getOne(@PathVariable Long id) {
		BrandDto result = service.getOne(id);
		return new ResponseEntity<BrandDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<BrandDto> create(@RequestBody BrandDto dto) {
		BrandDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<BrandDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<BrandDto> update(@RequestBody BrandDto dto, @PathVariable Long id) {
		dto.setId(id);
		BrandDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<BrandDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@GetMapping(value = "checkCode")
	public ResponseEntity<Boolean> check(@RequestParam(value = "id", required = false) Long id,
			@RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/get-one/{code}")
	public ResponseEntity<BrandDto> getByCode(@PathVariable String code) {
		BrandDto result = service.getOneByCode(code);
		return new ResponseEntity<BrandDto>(result, HttpStatus.OK);
	}

}
