package com.example.demo.rest;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.order.CartDto;
import com.example.demo.dto.order.CartResponse;
import com.example.demo.service.CartService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/carts/mine")
public class CartController {

	@Autowired
	private CartService service;

	// get all info cart
	@GetMapping("/items")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartDto> getCartDetail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		CartDto result = service.getCartByUser(username);
		return new ResponseEntity<CartDto>(result, HttpStatus.OK);
	}

	// get all info cart
	@GetMapping("/items/selected")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartDto> getCartDetailSelected() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		CartDto result = service.getCartByUserSelected(username);
		return new ResponseEntity<CartDto>(result, HttpStatus.OK);
	}

	// get quantity & total price cart
	@GetMapping("/info")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartResponse> getCartInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		Integer items_quantity = service.getQuantityProductByUser(username);
		Integer items_count = service.getQuantityItemByUser(username);
		return new ResponseEntity<CartResponse>(new CartResponse("SUCCESS", items_count, items_quantity),
				HttpStatus.OK);
	}

	// get quantity & total price cart
	@PostMapping("/items/check_quantity")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartResponse> checkQuantityItemInCart(@RequestBody CartDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		CartResponse result = service.checkItemQuantity(dto);
		return new ResponseEntity<CartResponse>(result, HttpStatus.OK);
	}

	@PostMapping("/items")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartResponse> create(@RequestBody CartDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		CartResponse result = service.createCart(dto);
		return new ResponseEntity<CartResponse>(result, HttpStatus.OK);
	}

	// cartid/quantity
	@PutMapping("/items/update")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartResponse> updateQuantity(@RequestBody CartDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		CartResponse result = service.updateCart(dto);
		return new ResponseEntity<CartResponse>(result, HttpStatus.OK);
	}

	// cartid/quantity
	@DeleteMapping("/items/remove")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartResponse> deleteItem(@RequestParam Long product_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		CartResponse result = service.deleteCartDetail(username, product_id);
		return new ResponseEntity<CartResponse>(result, HttpStatus.OK);
	}

	// cartid/quantity
	@DeleteMapping("/items/remove_all")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<Boolean> deleteAllItem() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Boolean result = service.deleteAllCartDetail(username);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	// cartid/quantity
	@PostMapping("/items/selected")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<CartDto> selectedItem(@RequestParam Long product_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		CartDto result = service.handleSelectedItemInCart(username, product_id);
		return new ResponseEntity<CartDto>(result, HttpStatus.OK);
	}

}
