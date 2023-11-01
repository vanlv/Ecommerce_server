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

import com.example.demo.dto.other.SlideDto;
import com.example.demo.service.SlideService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/slide")
public class SlideController {

	@Autowired
	private SlideService service;

	@GetMapping("")
	public ResponseEntity<Page<SlideDto>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "24") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		Page<SlideDto> result = service.getList(page, limit, sortBy);
		return new ResponseEntity<Page<SlideDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<SlideDto>> getAllActive() {
		List<SlideDto> result = service.getListDisplay();
		return new ResponseEntity<List<SlideDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<SlideDto> create(@RequestBody SlideDto dto) {
		SlideDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SlideDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<SlideDto> update(@RequestBody SlideDto dto, @PathVariable Long id) {
		dto.setId(id);
		SlideDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SlideDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<SlideDto> getOne(@PathVariable Long id) {
		SlideDto result = service.getOne(id);
		return new ResponseEntity<SlideDto>(result, HttpStatus.OK);
	}

}
