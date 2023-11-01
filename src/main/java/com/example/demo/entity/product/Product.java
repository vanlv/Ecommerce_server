package com.example.demo.entity.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.inventory.Supplier;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.promotion.ProductDiscount;
import com.example.demo.entity.user.Comment;

@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

	@Column(name = "type")
	private Integer type;

	@Column(name = "name") // 1, 2, 3
	private String name;

	@Column(name = "sku") // 1, 2, 3
	private String sku;

	@Column(name = "slug")
	private String slug;

	@Column(name = "description", columnDefinition = "TEXT") // 1, 2, 3
	private String description;

	@Column(name = "price") // 1, 2, 3 // gia thuc te ban ra
	private Long price;

	@Column(name = "list_price") // 1, 2, 3 // gia niem yet
	private Long list_price;

	@Column(name = "main_image")
	private String mainIamge;

	@Column(name = "material") // 3
	private String material;

	@Column(name = "size_weight") // khối lượng, kích thước sản phẩm
	private String sizeWeight;

	@Column(name = "weight")
	private Integer weight; // -- Bắt buộc, khối lượng đóng gói

	@Column(name = "length")
	private Integer length; // -- Bắt buộc, chiều dài khi đóng gói

	@Column(name = "width")
	private Integer width; // -- Bắt buộc, chiều rộng khi đóng gói

	@Column(name = "height")
	private Integer height; // -- Bắt buộc, chiều cao khi đóng gói

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	@Column(name = "features", columnDefinition = "TEXT")
	private String features;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id") // 1, 2, 3
	private SubCategory subcategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id") // 1, 2, 3
	private Category category;

//	@ManyToMany(cascade = CascadeType.PERSIST)
//	@JoinTable(name = "tbl_product_tag", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
//	private List<Tag> tags;

//	----------------	ELECTRIC	--------------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Technology technology;

//	----------------	Camera	--------------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Camera camera;

//	----------------	tivi	--------------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Tivi tivi;

//	----------------	Wash	--------------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Wash wash;

//	----------------	ACCESSORY	--------------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Accessory accessory;

//  ---------------		Discount	-------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ProductDiscount discount;;

//	----------------	BRAND	--------------------

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id") // 1, 2, 3
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id") // 1, 2, 3
	private Supplier supplier;

//	----------------	INVENTORY	--------------------
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Inventory> inventories;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Image> images; // 1, 2, 3

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Product() {
		super();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getMainIamge() {
		return mainIamge;
	}

	public void setMainIamge(String mainIamge) {
		this.mainIamge = mainIamge;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getSizeWeight() {
		return sizeWeight;
	}

	public void setSizeWeight(String sizeWeight) {
		this.sizeWeight = sizeWeight;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Tivi getTivi() {
		return tivi;
	}

	public void setTivi(Tivi tivi) {
		this.tivi = tivi;
	}

	public Wash getWash() {
		return wash;
	}

	public void setWash(Wash wash) {
		this.wash = wash;
	}

	public Accessory getAccessory() {
		return accessory;
	}

	public void setAccessory(Accessory accessory) {
		this.accessory = accessory;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public ProductDiscount getDiscount() {
		return discount;
	}

	public void setDiscount(ProductDiscount discount) {
		this.discount = discount;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

//	public List<Tag> getTags() {
//		return tags;
//	}
//
//	public void setTags(List<Tag> tags) {
//		this.tags = tags;
//	}

}
