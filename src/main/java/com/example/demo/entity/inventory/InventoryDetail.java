package com.example.demo.entity.inventory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_inventory_detail")
public class InventoryDetail extends BaseEntity {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inventory_id") // 1, 2, 3
	private Inventory inventory;
	
	private Integer import_quantity;
	private Long original_price;
	private String note;
	private String importDate;
	private String updatedDate;
	
	public InventoryDetail() {
		// TODO Auto-generated constructor stub
	}

	public InventoryDetail(Inventory inventory, Integer import_quantity, Long original_price, String note,
			String importDate, String updatedDate) {
		super();
		this.inventory = inventory;
		this.import_quantity = import_quantity;
		this.original_price = original_price;
		this.note = note;
		this.importDate = importDate;
		this.updatedDate = updatedDate;
	}



	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

}
