package com.example.demo.dto.product;

import com.example.demo.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductTopSale extends AbstractDTO<ProductTopSale>{
	private Long id;
	private String name;
	private String slug;
	private Long price;
	private Long list_price;
	private String mainImage;
	private String brandName;
	private String brandMadeIn;
	@JsonInclude(value = Include.NON_NULL)
	private Long seller_count;
	private Double percent_discount;
	
	
	
	public ProductTopSale() {
		super();
	}
	
	
	public ProductTopSale(Long id, String name, String slug, Long price, Long list_price, String mainImage, String brandName,
			String brandMadeIn, Long seller_count) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.price = price;
		this.list_price = list_price;
		this.mainImage = mainImage;
		this.brandName = brandName;
		this.brandMadeIn = brandMadeIn;
		this.seller_count = seller_count;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getList_price() {
		return list_price;
	}
	public void setList_price(Long list_price) {
		this.list_price = list_price;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandMadeIn() {
		return brandMadeIn;
	}
	public void setBrandMadeIn(String brandMadeIn) {
		this.brandMadeIn = brandMadeIn;
	}
	public Long getSeller_count() {
		return seller_count;
	}
	public void setSeller_count(Long seller_count) {
		this.seller_count = seller_count;
	}
	public Double getPercent_discount() {
		return percent_discount;
	}
	public void setPercent_discount(Double percent_discount) {
		this.percent_discount = percent_discount;
	}
	
}
