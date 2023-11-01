package com.example.demo.dto.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.common.CalculateDiscount;
import com.example.demo.dto.AbstractDTO;
import com.example.demo.dto.category.CategoryDtoRes;
import com.example.demo.dto.category.SubcategoryDtoRes;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.promotion.ProductDiscount;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductListDto extends AbstractDTO<ProductListDto> {

	private String createdDate;
	private String name;
	private String slug;
	private Long price;
	private Long list_price;
	private String mainImage;
	private CategoryDtoRes category;
	private SubcategoryDtoRes subcategory;
	@JsonInclude(value = Include.NON_NULL)
	private BrandDtoRes brand;
	@JsonInclude(value = Include.NON_NULL)
	private Integer seller_count;
	@JsonInclude(value = Include.NON_NULL)
	private Integer in_stock = 0;
	private Double percent_discount;

	@JsonInclude(value = Include.NON_NULL)
	private Integer display;

//	@JsonInclude(value = Include.NON_EMPTY)
//	private List<TagDto> tags;

	private String features;

	public ProductListDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductListDto(Product entity) {
		super();
		this.setId(entity.getId());
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.name = entity.getName();
		this.slug = entity.getSlug();
		this.features = entity.getFeatures();
		this.price = entity.getPrice();
		this.list_price = entity.getList_price();
		ProductDiscount discount = entity.getDiscount();
		if (discount.getStatus() == 1) {
			if (discount.getType() != null && discount.getValue() != null) {
				if (discount.getType() == 1) {
					this.price = entity.getPrice() * (100 - discount.getValue()) / 100;
				} else {
					this.price = entity.getPrice() - discount.getValue();
				}
			} else {
				this.price = entity.getPrice();
			}
		} else {
			this.price = entity.getPrice();
		}

		if (this.price != null && this.list_price != null) {
			this.percent_discount = CalculateDiscount.countDiscount(this.price, this.list_price);
		} else {
			this.percent_discount = null;
		}
		Integer count = 0;
		for (Inventory item : entity.getInventories()) {
			if (item.getQuantity_item() == 0) {
				count += 1;
			}
		}
		this.in_stock = count;
		this.mainImage = entity.getMainIamge();
		category = new CategoryDtoRes();
		if (category != null) {
			Category item = entity.getCategory();
			this.category = new CategoryDtoRes(item);
		}
		subcategory = new SubcategoryDtoRes();
		if (subcategory != null) {
			SubCategory item = entity.getSubcategory();
			this.subcategory = new SubcategoryDtoRes(item);
		}

		this.brand = new BrandDtoRes();
		Brand brandEntity = entity.getBrand();
		this.brand = new BrandDtoRes(brandEntity);
//		tags = new ArrayList<>();
//		for (Tag tag : entity.getTags()) {
//			TagDto dto = new TagDto(tag);
//			tags.add(dto);
//		}
		this.display = entity.getDisplay();
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
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

	public Double getPercent_discount() {
		return percent_discount;
	}

	public void setPercent_discount(Double percent_discount) {
		this.percent_discount = percent_discount;
	}

	public Integer getSeller_count() {
		return seller_count;
	}

	public void setSeller_count(Integer seller_count) {
		this.seller_count = seller_count;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public CategoryDtoRes getCategory() {
		return category;
	}

	public void setCategory(CategoryDtoRes category) {
		this.category = category;
	}

	public SubcategoryDtoRes getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubcategoryDtoRes subcategory) {
		this.subcategory = subcategory;
	}

	public BrandDtoRes getBrand() {
		return brand;
	}

	public void setBrand(BrandDtoRes brand) {
		this.brand = brand;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getIn_stock() {
		return in_stock;
	}

	public void setIn_stock(Integer in_stock) {
		this.in_stock = in_stock;
	}

//	public List<TagDto> getTags() {
//		return tags;
//	}
//
//	public void setTags(List<TagDto> tags) {
//		this.tags = tags;
//	}

}
