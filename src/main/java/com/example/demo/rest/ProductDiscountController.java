package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.promotion.DiscountProductDto;
import com.example.demo.service.DiscountProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/product_discount")
public class ProductDiscountController {

	@Autowired
	private DiscountProductService service;
	
	// Lấy các sản phẩm hiển thị lên trang chủ, có trạng thái hiển thị là 1
		@GetMapping(value = "/search")
		public ResponseEntity<Page<DiscountProductDto>> searchByPage(@RequestParam(name = "page", defaultValue = "1") int page,
				@RequestParam(name = "limit", defaultValue = "24") int limit,
				@RequestParam(name = "keyword", defaultValue = "") String keyword,
				@RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy,
				@RequestParam(name = "sortValue", defaultValue = "DESC") String sortValue,
				@RequestParam(name = "brand", defaultValue = "") String brand,
				@RequestParam(name = "price", defaultValue = "") String price) {
			SearchDto dto = new SearchDto(page, limit, keyword);
			dto.setSortBy(sortBy);
			dto.setSortValue(sortValue);
			dto.setBrand(brand);
			dto.setPrice(price);
			Page<DiscountProductDto> result = service.getList(dto);
			return new ResponseEntity<Page<DiscountProductDto>>(result, HttpStatus.OK);
		}

	@PutMapping(value = "/{product_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<DiscountProductDto> update(@RequestBody DiscountProductDto dto, @PathVariable Long product_id) {
		dto.setId(product_id);
		DiscountProductDto result = service.updateDiscountProduct(dto);
		return new ResponseEntity<DiscountProductDto>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{product_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long product_id) {
		Boolean result = service.deleteDiscountProduct(product_id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{product_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<DiscountProductDto> getOneByProductId(@PathVariable Long product_id) {
		DiscountProductDto result = service.getOneByProductId(product_id);
		return new ResponseEntity<DiscountProductDto>(result, HttpStatus.OK);
	}

}
