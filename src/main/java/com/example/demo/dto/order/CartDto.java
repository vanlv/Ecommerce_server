package com.example.demo.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.order.CartDetail;

public class CartDto extends AbstractDTO<CartDto> {

	private String username;
	private Integer items_quantity;
	private Integer items_count = 0;
	private Long total_price = 0L;
	private List<CartDetailDto> cart_details;
	private String mesage;
	// total kich thuoc & khoi luong
	private Integer weight;
	private Integer length;
	private Integer width;
	private Integer height;

	public CartDto() {
		// TODO Auto-generated constructor stub
	}

	public CartDto(Cart entity) {
		// TODO Auto-generated constructor stub
		this.setId(entity.getId());
		this.username = entity.getUser().getUsername();
		this.cart_details = new ArrayList<>();
		if (this.cart_details != null) {
			for (CartDetail detail : entity.getCart_details()) {
				CartDetailDto dto = new CartDetailDto(detail);
				this.items_count += detail.getQuantity();
				this.total_price += detail.getProduct().getPrice() * detail.getQuantity();
				this.cart_details.add(dto);
			}
		}
		this.items_quantity = this.cart_details.size();

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CartDetailDto> getCart_details() {
		return cart_details;
	}

	public void setCart_details(List<CartDetailDto> cart_details) {
		this.cart_details = cart_details;
	}

	public Integer getItems_quantity() {
		return items_quantity;
	}

	public void setItems_quantity(Integer items_quantity) {
		this.items_quantity = items_quantity;
	}

	public Integer getItems_count() {
		return items_count;
	}

	public void setItems_count(Integer items_count) {
		this.items_count = items_count;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}
