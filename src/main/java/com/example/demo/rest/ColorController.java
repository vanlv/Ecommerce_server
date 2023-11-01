package com.example.demo.rest;

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

import com.example.demo.dto.product.ColorDto;
import com.example.demo.service.ColorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/color")

public class ColorController {
	@Autowired
	private ColorService service;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ColorDto>> getAllAdmin(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		Page<ColorDto> result = service.getAll(page, limit, sortBy);
		return new ResponseEntity<Page<ColorDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ColorDto> getOne(@PathVariable Long id) {
		ColorDto result = service.getOne(id);
		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ColorDto> create(@RequestBody ColorDto dto) {
		ColorDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ColorDto> update(@RequestBody ColorDto dto, @PathVariable Long id) {
		dto.setId(id);
		ColorDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
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
	public ResponseEntity<ColorDto> getByCode(@PathVariable String color) {
		ColorDto result = service.getOneByColor(color);
		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
	}
}
