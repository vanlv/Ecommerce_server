package com.example.demo.dto.inventory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.inventory.InventoryDetail;

public class InventoryDetailDto extends AbstractDTO<InventoryDetailDto> {

	private Long inventoryId;
	private Integer import_quantity;
	private Long original_price;
	private String note;
	private String importDate;

	public InventoryDetailDto() {
		// TODO Auto-generated constructor stub
	}

	public InventoryDetailDto(InventoryDetail entity) {
		super();
		this.setId(entity.getId());
		this.inventoryId = entity.getInventory().getId();
		this.import_quantity = entity.getImport_quantity();
		this.original_price = entity.getOriginal_price();
		this.note = entity.getNote();
		try {
			this.importDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getImportDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getImport_quantity() {
		return import_quantity;
	}

	public void setImport_quantity(Integer import_quantity) {
		this.import_quantity = import_quantity;
	}

	public Long getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(Long original_price) {
		this.original_price = original_price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}
	
}
