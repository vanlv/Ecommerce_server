//package com.example.demo.entity.product;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import com.example.demo.entity.BaseEntity;
//
//@Entity
//@Table(name = "tbl_tag")
//public class Tag extends BaseEntity {
//
//	@Column(name = "name")
//	private String name;
//
//	@Column(name = "code")
//	private String code;
//
//	@ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
//	private List<Product> products; // 1
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
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//}
