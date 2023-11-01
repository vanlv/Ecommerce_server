package com.example.demo.dto;

public class SearchDto {
	private int pageIndex;
	private int pageSize;
	private String keyword;
	private String sortBy; // name, price
	private String sortValue; // asc, desc
	private String tag; // tim theo tag
	private String brand; // tim theo thuong hieu
	private String ram; // tim theo thuong hieu
	private String resolution; // tim theo thuong hieu
	private String image; // tim theo thuong hieu
	private String video; // tim theo thuong hieu
	private Integer isSmartTV;
	private Integer is3DTV;
	private String price;
	private String category;
	private String subcategory;
	private Long productId;

	public SearchDto() {
		super();
	}

	public SearchDto(int pageIndex, int pageSize, Long productId) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.productId = productId;
	}

	public SearchDto(int pageIndex, int pageSize, String keyword) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.keyword = keyword;
	}

	public SearchDto(int pageIndex, int pageSize, String keyword, String category, String subcategory) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.category = category;
		this.subcategory = subcategory;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Integer getIsSmartTV() {
		return isSmartTV;
	}

	public void setIsSmartTV(Integer isSmartTV) {
		this.isSmartTV = isSmartTV;
	}

	public Integer getIs3DTV() {
		return is3DTV;
	}

	public void setIs3DTV(Integer is3dtv) {
		is3DTV = is3dtv;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
