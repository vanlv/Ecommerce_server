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

import com.example.demo.dto.other.PromotionDto;
import com.example.demo.service.PromotionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/promotion")
public class PromotionController {

	@Autowired
	private PromotionService service;

	@GetMapping("")
	public ResponseEntity<Page<PromotionDto>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "24") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		Page<PromotionDto> result = service.getList(page, limit, sortBy);
		return new ResponseEntity<Page<PromotionDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PromotionDto>> getAllActive() {
		List<PromotionDto> result = service.getListDisplay();
		return new ResponseEntity<List<PromotionDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<PromotionDto> create(@RequestBody PromotionDto dto) {
		PromotionDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<PromotionDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<PromotionDto> update(@RequestBody PromotionDto dto, @PathVariable Long id) {
		dto.setId(id);
		PromotionDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<PromotionDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<PromotionDto> getById(@PathVariable Long id) {
		PromotionDto result = service.getOne(id);
		return new ResponseEntity<PromotionDto>(result, HttpStatus.OK);
	}

}
