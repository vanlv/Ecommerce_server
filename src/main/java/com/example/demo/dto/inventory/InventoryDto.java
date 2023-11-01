package com.example.demo.dto.inventory;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.inventory.InventoryDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class InventoryDto extends AbstractDTO<InventoryDto> {

	@JsonInclude(value = Include.NON_NULL)
	private Integer total_import_item;
	@JsonInclude(value = Include.NON_NULL)
	private Integer quantity_item;
	private Long productId;
	private String color;
	private String category_code;
	private Integer display;

	private List<InventoryDetailDto> inventory_details;

	public InventoryDto() {
		// TODO Auto-generated constructor stub
	}

	public InventoryDto(Inventory entity) {
		super();
		this.setId(entity.getId());
		this.total_import_item = entity.getTotal_import_item();
		this.quantity_item = entity.getQuantity_item();
		this.productId = entity.getProduct().getId();
		this.color = entity.getColor().getName();
		this.category_code = entity.getCategory_code();
		this.inventory_details = new ArrayList<>();
		if (inventory_details != null) {
			for (InventoryDetail detail : entity.getInventory_details()) {
				InventoryDetailDto dto = new InventoryDetailDto(detail);
				this.inventory_details.add(dto);
			}
		}
		this.display = entity.getDisplay();

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public List<InventoryDetailDto> getInventory_details() {
		return inventory_details;
	}

	public void setInventory_details(List<InventoryDetailDto> inventory_details) {
		this.inventory_details = inventory_details;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

}
