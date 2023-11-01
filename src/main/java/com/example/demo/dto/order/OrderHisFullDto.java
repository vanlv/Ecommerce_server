package com.example.demo.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;

public class OrderHisFullDto {
	private Long id;

	private OrderHisUserDto user;

	private OrderHisInfoDto order_info;

	private List<OrderDetailHisDto> order_details;

	public OrderHisFullDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderHisFullDto(Order entity) {
		// TODO Auto-generated constructor stub
		this.setId(entity.getId());
		this.order_info = new OrderHisInfoDto(entity);
		this.user = new OrderHisUserDto(entity);
		this.order_details = new ArrayList<>();
		for (OrderDetail detail : entity.getOrderDetails()) {
			OrderDetailHisDto dto = new OrderDetailHisDto(detail);
			this.order_details.add(dto);
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderHisUserDto getUser() {
		return user;
	}

	public void setUser(OrderHisUserDto user) {
		this.user = user;
	}

	public OrderHisInfoDto getOrder_info() {
		return order_info;
	}

	public void setOrder_info(OrderHisInfoDto order_info) {
		this.order_info = order_info;
	}

	public List<OrderDetailHisDto> getOrder_details() {
		return order_details;
	}

	public void setOrder_details(List<OrderDetailHisDto> order_details) {
		this.order_details = order_details;
	}

}
