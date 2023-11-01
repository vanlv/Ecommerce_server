package com.example.demo.entity.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.order.OrderDetail;

@Entity
@Table(name = "tbl_product_color")
public class Color extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Inventory> inventories;

	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Color() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
