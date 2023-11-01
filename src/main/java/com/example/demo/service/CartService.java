package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.order.CartDto;
import com.example.demo.dto.order.CartResponse;

@Service
public interface CartService {

	// tạo giỏ hàng
	public CartResponse createCart(CartDto dto);

	// cập nhật số lượng sản phẩm trong giỏ hàng
	public CartResponse updateCart(CartDto dto);

	// cập nhật số lượng sản phẩm trong giỏ hàng
	public CartResponse checkItemQuantity(CartDto dto);

	// get giỏ hàng theo users
	public CartDto getCartByUser(String username);
	
	// get giỏ hàng theo users đã chọn sản phẩm để đặt hàng
	public CartDto getCartByUserSelected(String username);

	public Integer getQuantityItemByUser(String username);

	public Integer getQuantityProductByUser(String username);

	// Xoá 1 sản phẩm trong giỏ hàng
	public CartResponse deleteCartDetail(String username, Long product_id);
	
	// Xoá giỏ hàng sau khi đặt hàng thành công
	public Boolean deleteAllCartDetail(String username);
	
	public CartDto handleSelectedItemInCart(String username, Long product_id);

}
