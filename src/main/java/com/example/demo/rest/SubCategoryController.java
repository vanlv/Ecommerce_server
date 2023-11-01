package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.example.demo.dto.category.SubCategoryDto;
import com.example.demo.service.SubCategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/subcategory")
public class SubCategoryController {

	@Qualifier("subCategoryServiceImpl")
	@Autowired
	private SubCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<Page<SubCategoryDto>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "24") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		Page<SubCategoryDto> result = service.getList(page, limit, sortBy);
		return new ResponseEntity<Page<SubCategoryDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<SubCategoryDto>> getAllAdmin(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "24") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name="display", defaultValue = "2") Integer display) {
		Page<SubCategoryDto> result = null;
		if(display == 1) {
			result = service.getList(page, limit, sortBy);
		} else if(display == 0) {
			result = service.getListHide(page, limit, sortBy);
		} else {
			result = service.getAll(page, limit, sortBy);
		}
		return new ResponseEntity<Page<SubCategoryDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubCategoryDto> getOne(@PathVariable Long id) {
		SubCategoryDto result = service.getOne(id);
		return new ResponseEntity<SubCategoryDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<SubCategoryDto>> getAllByCategory(@RequestParam(name = "category") String category) {
		List<SubCategoryDto> result = service.getSubCategoryByCategory(category);
		return new ResponseEntity<List<SubCategoryDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<SubCategoryDto> create(@RequestBody SubCategoryDto dto) {
		SubCategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SubCategoryDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<SubCategoryDto> update(@RequestBody SubCategoryDto dto, @PathVariable Long id) {
		dto.setId(id);
		SubCategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SubCategoryDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.deleteSubCategory(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "checkCode")
	public ResponseEntity<Boolean> check(@RequestParam(value ="id", required = false) Long id, @RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
}
