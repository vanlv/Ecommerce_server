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

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductDtoRes;
import com.example.demo.dto.product.ProductListDto;
import com.example.demo.dto.product.ProductTopSale;
import com.example.demo.service.CommentService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

	@Qualifier("productServiceImpl")
	@Autowired
	private ProductService service;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CommentService commentservice;

	// Lấy các sản phẩm hiển thị lên trang chủ, có trạng thái hiển thị là 1
	@GetMapping(value = "/search")
	public ResponseEntity<Page<ProductListDto>> searchByPage(@RequestParam(name = "page", defaultValue = "1") int page,
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
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	// lấy toàn bộ sản phẩm có trạng thái 1 theo danh mục
	@GetMapping(value = "/danh-muc/{category}", name = "getByCategory")
	public ResponseEntity<Page<ProductListDto>> searchByPageCategory(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "24") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy,
			@RequestParam(name = "sortValue", defaultValue = "DESC") String sortValue,
			@RequestParam(name = "brand", defaultValue = "") String brand,
			@RequestParam(name = "price", defaultValue = "") String price, 
			@RequestParam(name = "ram", defaultValue = "") String ram, 
			@RequestParam(name = "resolution", defaultValue = "") String resolution, 
			@RequestParam(name = "image", defaultValue = "") String image, 
			@RequestParam(name = "video", defaultValue = "") String video, 
			@RequestParam(name = "smart_tv", defaultValue = "") Integer smart_tv, 
			@RequestParam(name = "tv_3d", defaultValue = "") Integer tv_3d, 
			@PathVariable String category) {
		SearchDto dto = new SearchDto(page, limit, keyword, category, null);
		dto.setSortBy(sortBy);
		dto.setSortValue(sortValue);
		dto.setBrand(brand);
		dto.setPrice(price);
		dto.setRam(ram);
		dto.setImage(image);
		dto.setVideo(video);
		dto.setResolution(resolution);
		dto.setIs3DTV(tv_3d);
		dto.setIsSmartTV(smart_tv);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	// lấy toàn bộ sản phẩm có trạng thái 1 theo danh mục & danh mục con
	@GetMapping(value = "/danh-muc/{category}/{subcategory}")
	public ResponseEntity<Page<ProductListDto>> searchByPageSubCategory(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "24") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy,
			@RequestParam(name = "sortValue", defaultValue = "DESC") String sortValue,
			@RequestParam(name = "brand", defaultValue = "") String brand, 
			@RequestParam(name = "price", defaultValue = "") String price,
			@RequestParam(name = "ram", defaultValue = "") String ram, 
			@RequestParam(name = "resolution", defaultValue = "") String resolution, 
			@RequestParam(name = "image", defaultValue = "") String image, 
			@RequestParam(name = "video", defaultValue = "") String video,
			@RequestParam(name = "smart_tv", defaultValue = "") Integer smart_tv, 
			@RequestParam(name = "tv_3d", defaultValue = "") Integer tv_3d, 
			@PathVariable String category,
			@PathVariable String subcategory) {
		SearchDto dto = new SearchDto(page, limit, keyword, category, subcategory);
		dto.setSortBy(sortBy);
		dto.setSortValue(sortValue);
		dto.setBrand(brand);
		dto.setPrice(price);
		dto.setRam(ram);
		dto.setImage(image);
		dto.setVideo(video);
		dto.setResolution(resolution);
		dto.setIs3DTV(tv_3d);
		dto.setIsSmartTV(smart_tv);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	// lấy toàn bộ sản phẩm có trạng thái 1 theo thương hiệu
	@GetMapping(value = "/brand")
	public ResponseEntity<List<ProductListDto>> getProductListByBrandNotExists(@RequestParam Long productId,
			@RequestParam String brandCode) {
		List<ProductListDto> result = service.getAllByBrand(productId, brandCode);
		return new ResponseEntity<List<ProductListDto>>(result, HttpStatus.OK);
	}

	// lấy toàn bộ sản phẩm có trạng thái 1 theo thương hiệu
	@GetMapping(value = "/all/{brandCode}")
	public ResponseEntity<Page<ProductListDto>> getProductListByBrand(@PathVariable String brandCode,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "24") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy) {
		Page<ProductListDto> result = service.getAllProductByBrand(brandCode, page, limit, sortBy);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	// lấy thông tin sản phẩm theo id
	@GetMapping(value = "/san-pham/{id}", name = "getProductByID")
	public ResponseEntity<ProductDtoRes> getProduct(@PathVariable Long id, @RequestParam(name="color", defaultValue = "") String color) {
		ProductDtoRes result = service.getProductById(id, color);
		Integer seller_count = orderService.getQuantityProductSeller(id);
		Integer comment_count = commentservice.countAllCommentByProduct(id);
		result.setSeller_count(seller_count);
		result.setReview_count(comment_count);
		return new ResponseEntity<ProductDtoRes>(result, HttpStatus.OK);
	}

	// lấy thông tin sản phẩm theo id để thêm vào csdl
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<ProductDto> getProductByIdToCreate(@PathVariable Long id) {
		ProductDto result = service.getDetailProduct(id);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	// danh sách sản phẩm bán chạy
	@GetMapping("/top_sale")
	public ResponseEntity<Page<ProductTopSale>> topSaleProduct(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "12") Integer limit) { // ds sản phẩm bán chạy nhất
		SearchDto dto = new SearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		Page<ProductTopSale> result = service.topSaleProduct(dto);
		return new ResponseEntity<Page<ProductTopSale>>(result, HttpStatus.OK);
	}

	// lấy toàn bộ sản phẩm trong csdl
	@GetMapping(value = "/get/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ProductListDto>> getAllProduct(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "24") int limit,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "sku", defaultValue = "") String sku,
			@RequestParam(name = "category", defaultValue = "") String category,
			@RequestParam(name = "brand", defaultValue = "") String brand,
			@RequestParam(name = "supplier", defaultValue = "") String supplier,
			@RequestParam(name = "display", defaultValue = "2") Integer display) {
		AdvanceSearchDto dto = new AdvanceSearchDto(page, limit, name, sku, display, brand, supplier, category);
		Page<ProductListDto> result = service.getAllProduct(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	// thêm sản phẩm vào csdl
	@PostMapping("/add-product")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto) {
		ProductDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	// cập nhật sản phẩm theo id
	@PutMapping(value = "/update-product/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ProductDto> updateLaptop(@RequestBody ProductDto dto, @PathVariable Long id) {
		dto.setId(id);
		ProductDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	// xoá mềm sản phẩm theo id
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
