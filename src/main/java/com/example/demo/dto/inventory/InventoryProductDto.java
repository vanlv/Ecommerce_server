package com.example.demo.dto.inventory;

import com.example.demo.entity.inventory.Inventory;

public class InventoryProductDto {
	private Integer total_import_item;
	private Integer quantity_item;
	private Integer sold;
	private String color;

	public InventoryProductDto() {
		// TODO Auto-generated constructor stub
	}

	public InventoryProductDto(Inventory entity) {
		super();
		this.total_import_item = entity.getTotal_import_item();
		this.quantity_item = entity.getQuantity_item();
		this.sold = this.total_import_item - this.quantity_item;
		this.color = entity.getColor().getName();
	}

	public Integer getTotal_import_item() {
		return total_import_item;
	}

	public void setTotal_import_item(Integer total_import_item) {
		this.total_import_item = total_import_item;
	}

	public Integer getQuantity_item() {
		return quantity_item;
	}

	public void setQuantity_item(Integer quantity_item) {
		this.quantity_item = quantity_item;
	}

	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
