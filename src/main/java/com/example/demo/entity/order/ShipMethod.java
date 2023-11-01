//package com.example.demo.entity.order;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import com.example.demo.entity.BaseEntity;
//
//@Entity
//@Table(name = "tbl_ship_method")
//public class ShipMethod extends BaseEntity {
//
//	@Column(name = "type")		// 1 giao hàng nhanh, 2 shop tự giao
//	private Integer type;
//
//	@Column(name = "name")
//	private String name; // 1 giao hang nhanh, 2 shop tự giao (giao hàng tiết kiệm)
//
//	@Column(name = "code")
//	private String code;
//	
//	@Column(name = "display")
//	private Integer display; // 1 : show, 0: hidden
//	
//	public Integer getType() {
//		return type;
//	}
//
//	public void setType(Integer type) {
//		this.type = type;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public Integer getDisplay() {
//		return display;
//	}
//
//	public void setDisplay(Integer display) {
//		this.display = display;
//	}
//}
