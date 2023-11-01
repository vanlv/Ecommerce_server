package com.example.demo.dto.inventory;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.inventory.Inventory;

public class InventoryDtoRes extends AbstractDTO<InventoryDtoRes> {

	private Long id; // inventory id
	private Long productId;
	private String productName;
	private String productMainImage;
	private String category_code;
	private String category_name;
	private Long total_import_item; // tổng số lượng nhập
	private Long quantity_item; // số lương còn lại trong kho hàng
	private Long sold; // só lượng đã bán
	private String color;
	private Integer display;

	public InventoryDtoRes() {
		// TODO Auto-generated constructor stub
	}

	public InventoryDtoRes(Long id, String productName, String productMainImage, String category_name,
			Long total_import_item, Long quantity_item) {
		super();
		this.id = id;
		this.productName = productName;
		this.productMainImage = productMainImage;
		this.category_name = category_name;
		this.total_import_item = total_import_item;
		this.quantity_item = quantity_item;
	}

	public InventoryDtoRes(Inventory entity) {
		super();
		this.setId(entity.getId());
		this.total_import_item = Long.valueOf(entity.getTotal_import_item());
		this.quantity_item = Long.valueOf(entity.getQuantity_item());
		this.sold = this.total_import_item - this.quantity_item;
		this.productId = entity.getProduct().getId();
		this.productName = entity.getProduct().getName();
		this.productMainImage = entity.getProduct().getMainIamge();
		this.category_code = entity.getCategory_code();
		this.color = entity.getColor().getName();
		this.display = entity.getDisplay();
	}

	public Long getTotal_import_item() {
		return total_import_item;
	}

	public void setTotal_import_item(Long total_import_item) {
		this.total_import_item = total_import_item;
	}

	public Long getQuantity_item() {
		return quantity_item;
	}

	public void setQuantity_item(Long quantity_item) {
		this.quantity_item = quantity_item;
	}

	public Long getSold() {
		return sold;
	}

	public void setSold(Long sold) {
		this.sold = sold;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductMainImage() {
		return productMainImage;
	}

	public void setProductMainImage(String productMainImage) {
		this.productMainImage = productMainImage;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

}
