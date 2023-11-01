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

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.inventory.InventoryDetailDto;
import com.example.demo.dto.inventory.InventoryDto;
import com.example.demo.dto.inventory.InventoryDtoRes;
import com.example.demo.dto.product.ColorDto;
import com.example.demo.service.InventoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;

	@GetMapping(value = "/search/{category}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<InventoryDtoRes>> search(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "24") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword, @PathVariable String category) {
		SearchDto dto = new SearchDto(page, limit, keyword, category, null);
		Page<InventoryDtoRes> result = service.getList(dto);
		return new ResponseEntity<Page<InventoryDtoRes>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/product/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<InventoryDtoRes>> getDetailByProductId(@PathVariable(name = "id") Long id) {
		List<InventoryDtoRes> result = service.getListByProduct(id);
		return new ResponseEntity<List<InventoryDtoRes>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/not-exsist-color/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<ColorDto>> getAllColorNotExsistProduct(@PathVariable(name = "id") Long id) {
		List<ColorDto> result = service.getAllColorNotExsistProduct(id);
		return new ResponseEntity<List<ColorDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detail/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<InventoryDetailDto>> getDetailById(@PathVariable(name = "id") Long id) {
		List<InventoryDetailDto> result = service.getDetailInventoryById(id);
		return new ResponseEntity<List<InventoryDetailDto>>(result, HttpStatus.OK);
	}

	@PostMapping("/import-product")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<InventoryDto> importProduct(@RequestBody InventoryDto dto) {
		InventoryDto result = service.importOrUpdate(dto);
		return new ResponseEntity<InventoryDto>(result, HttpStatus.OK);
	}
	
	@PutMapping("/import-product/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<InventoryDto> updateInventory(@RequestBody InventoryDto dto, @PathVariable Long id) {
		dto.setId(id);
		InventoryDto result = service.importOrUpdate(dto);
		return new ResponseEntity<InventoryDto>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/cancel-sell-product/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> calcelSellProduct(@PathVariable Long id) {
		Boolean result = service.calcelSellProduct(id);
		if(result) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Cập nhật thành công!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Cập nhật không thành công!"), HttpStatus.BAD_REQUEST);
		}
	}

}
