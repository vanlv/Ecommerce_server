package com.example.demo.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.CalculateDiscount;
import com.example.demo.dto.AbstractDTO;
import com.example.demo.dto.category.CategoryDtoRes;
import com.example.demo.dto.category.SubcategoryDtoRes;
import com.example.demo.dto.inventory.InventoryProductDto;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.product.Accessory;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Camera;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Technology;
import com.example.demo.entity.product.Tivi;
import com.example.demo.entity.product.Wash;
import com.example.demo.entity.promotion.ProductDiscount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductDtoRes extends AbstractDTO<ProductDtoRes> {

	private Integer type; // loai san phamr

	@JsonInclude(value = Include.NON_NULL)
	private String name;

	@JsonInclude(value = Include.NON_NULL)
	private String sku;

	@JsonInclude(value = Include.NON_NULL)
	private String slug;

	@JsonInclude(value = Include.NON_NULL)
	private String description;

	@JsonInclude(value = Include.NON_NULL)
	private Long price;

	@JsonInclude(value = Include.NON_NULL)
	private Long list_price;

	@JsonInclude(value = Include.NON_NULL)
	private Double percent_discount;

	@JsonInclude(value = Include.NON_NULL)
	private String mainImage;

	@JsonInclude(value = Include.NON_NULL)
	private String features;

	private Integer weight;
	private Integer length;
	private Integer width;
	private Integer height;

	@JsonInclude(value = Include.NON_NULL)
	private Integer in_stock;

	@JsonInclude(value = Include.NON_NULL)
	private Integer seller_count;

	@JsonInclude(value = Include.NON_NULL)
	private Integer review_count;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> images;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> tags;

	@JsonInclude(value = Include.NON_NULL)
	private CategoryDtoRes category;

	@JsonInclude(value = Include.NON_NULL)
	private SubcategoryDtoRes subcategory;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<ProductSpecify> product_specs;

	// brand
	@JsonInclude(value = Include.NON_NULL)
	private BrandDtoRes brand;

	// ---------------- Technology --------------
	@JsonInclude(value = Include.NON_NULL)
	private TechDto technology;

	// ---------------- Camera --------------
	@JsonInclude(value = Include.NON_NULL)
	private CameraDto camera;

	// ---------------- Tivi --------------
	@JsonInclude(value = Include.NON_NULL)
	private TiviDto tivi;

	// ---------------- Wash --------------
	@JsonInclude(value = Include.NON_NULL)
	private WashDto wash;

	// ---------------- Accessory --------------
	@JsonInclude(value = Include.NON_NULL)
	private AccessoryDto accessory;

	@JsonInclude(value = Include.NON_NULL)
	private List<InventoryProductDto> inventories;

	public ProductDtoRes() {
		// TODO Auto-generated constructor stub
	}

	public ProductDtoRes(Product entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();
		this.name = entity.getName();
		this.sku = entity.getSku();
		this.slug = entity.getSlug();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.list_price = entity.getList_price();
		this.features = entity.getFeatures();
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

		this.mainImage = entity.getMainIamge();
		this.weight = entity.getWeight();
		this.length = entity.getLength();
		this.width = entity.getWidth();
		this.height = entity.getHeight();

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

		brand = new BrandDtoRes();
		if (brand != null) {
			Brand brandEntity = entity.getBrand();
			brand = new BrandDtoRes(brandEntity);
		}

		images = new ArrayList<>();
		for (Image image : entity.getImages()) {
			ImageDto dto = new ImageDto(image);
			images.add(dto.getUrl());
		}
		images.add(0, this.mainImage);
//
//		tags = new ArrayList<>();
//		for (Tag tag : entity.getTags()) {
//			String item = tag.getCode();
//			tags.add(item);
//		}

		this.product_specs = new ArrayList<>();
		if (entity.getSizeWeight() != null) {
			this.product_specs.add(new ProductSpecify("Trọng lượng & Kích thước", entity.getSizeWeight()));
		}
		if (entity.getMaterial() != null) {
			this.product_specs.add(new ProductSpecify("Thành phần", entity.getMaterial()));
		}

		switch (this.type) {
		case 1:
//			this.technology = new TechDto();
//			if (this.technology != null) {
			Technology item = entity.getTechnology();
			if (item != null) {
				if (item.getScreen() != null) {
					this.product_specs.add(new ProductSpecify("Màn hình", item.getScreen()));
				}
				if (item.getScreen_size() != null) {
					this.product_specs.add(new ProductSpecify("Kích thước Màn hình", item.getScreen_size()));
				}
				if (item.getOperatorSystem() != null) {
					this.product_specs.add(new ProductSpecify("Hệ điều hành", item.getOperatorSystem()));
				}
				if (item.getRam() != null) {
					this.product_specs.add(new ProductSpecify("RAM", item.getRam()));
				}
				if (item.getPin() != null) {
					this.product_specs.add(new ProductSpecify("PIN", item.getPin()));
				}
				if (item.getChip() != null) {
					this.product_specs.add(new ProductSpecify("Chip", item.getChip()));
				}
				if (item.getCamera() != null) {
					this.product_specs.add(new ProductSpecify("Camera", item.getCamera()));
				}
				if (item.getDisplay_resolution() != null) {
					this.product_specs.add(new ProductSpecify("Độ phân giải", item.getDisplay_resolution()));
				}
				if (item.getDesign() != null) {
					this.product_specs.add(new ProductSpecify("Thiết kế", item.getDesign()));
				}

				if (item.getReleaseTime() != null) {
					this.product_specs.add(new ProductSpecify("Thời điểm ra mắt", item.getReleaseTime()));
				}
				if (this.category.getCode().equalsIgnoreCase("dien-thoai")
						|| this.category.getCode().equalsIgnoreCase("dienthoai")
						|| this.category.getCode().equalsIgnoreCase("may-tinh-bang")
						|| this.category.getCode().equalsIgnoreCase("maytinhbang")) {
					if (item.getFrontCamera() != null) {
						this.product_specs.add(new ProductSpecify("Camera trước", item.getFrontCamera()));
					}
					if (item.getBehindCamera() != null) {
						this.product_specs.add(new ProductSpecify("Camera sau", item.getBehindCamera()));
					}
					if (item.getInternalMemory() != null) {
						this.product_specs.add(new ProductSpecify("Bộ nhớ trong", item.getInternalMemory()));
					}
					if (item.getSim() != null) {
						this.product_specs.add(new ProductSpecify("Công nghệ SIM", item.getSim()));
					}
					if (item.getNumber_sim() != null) {
						this.product_specs.add(new ProductSpecify("Số SIM", item.getNumber_sim().toString()));
					}
					if (item.getAccessory() != null) {
						this.product_specs.add(new ProductSpecify("Phụ kiện đi kèm", item.getAccessory()));
					}
				}
				if (this.category.getCode().equalsIgnoreCase("laptop")) {
					if (item.getCpu() != null) {
						this.product_specs.add(new ProductSpecify("CPU", item.getCpu()));
					}
					if (item.getBus() != null) {
						this.product_specs.add(new ProductSpecify("BUS", item.getBus()));
					}
					if (item.getHardWare() != null) {
						this.product_specs.add(new ProductSpecify("Phần cứng", item.getHardWare()));
					}
					if (item.getCard() != null) {
						this.product_specs.add(new ProductSpecify("Card màn hình", item.getCard()));
					}
				}
			}
//			}
			break;
		case 2:
//			this.camera = new CameraDto();
//			if (this.camera != null) {
			this.product_specs.add(new ProductSpecify("Xuất xứ sản phẩm", this.brand.getMadeIn()));
			Camera cameraEntity = entity.getCamera();
			if (cameraEntity != null) {
				if (cameraEntity.getImage_processing() != null) {
					this.product_specs.add(new ProductSpecify("Bộ xử lý ảnh", cameraEntity.getImage_processing()));
				}
				if (cameraEntity.getImage_quality() != null) {
					this.product_specs.add(new ProductSpecify("Chất lượng hình ảnh", cameraEntity.getImage_quality()));
				}
				if (cameraEntity.getVideo_quality() != null) {
					this.product_specs.add(new ProductSpecify("Chất lượng video", cameraEntity.getVideo_quality()));
				}
				if (cameraEntity.getMemory_card() != null) {
					this.product_specs.add(new ProductSpecify("Thẻ nhớ tương thích", cameraEntity.getMemory_card()));
				}
				if (cameraEntity.getScreen_camera() != null) {
					this.product_specs.add(new ProductSpecify("Công nghệ màn hình", cameraEntity.getScreen_camera()));
				}
				if (cameraEntity.getScreen_size_camera() != null) {
					this.product_specs
							.add(new ProductSpecify("Kích thước màn hình", cameraEntity.getScreen_size_camera()));
				}
				if (cameraEntity.getShutter_speed() != null) {
					this.product_specs.add(new ProductSpecify("Tốc độ chụp", cameraEntity.getShutter_speed()));
				}
			}

//			}
			break;
		case 3:
			this.product_specs.add(new ProductSpecify("Xuất xứ sản phẩm", this.brand.getMadeIn()));
			this.tivi = new TiviDto();
			if (this.tivi != null) {
				Tivi tv = entity.getTivi();
				if (tv.getYear() != null) {
					this.product_specs.add(new ProductSpecify("Năm ra mắt", tv.getYear()));
				}
				if (tv.getDisplay_resolution_tv() != null) {
					this.product_specs.add(new ProductSpecify("Độ phân giải", tv.getDisplay_resolution_tv()));
				}
				if (tv.getType_tv() != null) {
					this.product_specs
							.add(new ProductSpecify("Loại TV", tv.getType_tv() == 1 ? "Smart TV" : "Inverter TV"));
				}
				if (tv.getApp_avaiable() != null) {
					this.product_specs.add(new ProductSpecify("Ứng dụng có sẵn", tv.getApp_avaiable()));
				}
				if (tv.getUsb() != null) {
					this.product_specs.add(new ProductSpecify("USB", tv.getUsb()));
				}
				if (tv.getIs3D() != null) {
					this.product_specs.add(new ProductSpecify("3D", tv.getIs3D() == 1 ? "Có" : "Không"));
				}
				if (tv.getSpeaker() != null) {
					this.product_specs.add(new ProductSpecify("Số lượng loa", tv.getSpeaker().toString()));
				}
				if (tv.getTechlonogy_sound() != null) {
					this.product_specs.add(new ProductSpecify("Công nghệ âm thanh", tv.getTechlonogy_sound()));
				}
				if (tv.getComponent_video() != null) {
					this.product_specs.add(new ProductSpecify("Component Video", tv.getComponent_video()));
				}
				if (tv.getHdmi() != null) {
					this.product_specs.add(new ProductSpecify("Cổng HDMI", tv.getHdmi()));
				}
				if (tv.getImage_processing_tv() != null) {
					this.product_specs.add(new ProductSpecify("Công nghệ xử lý hình ảnh", tv.getImage_processing_tv()));
				}
			}
			break;
		case 4:
			this.product_specs.add(new ProductSpecify("Xuất xứ sản phẩm", this.brand.getMadeIn()));
			this.wash = new WashDto();
			if (this.wash != null) {
				Wash washEntity = entity.getWash();
				if (washEntity.getWash_weight() != null) {
					this.product_specs.add(new ProductSpecify("Khối lượng giặt", washEntity.getWash_weight()));
				}
				if (washEntity.getWash_mode() != null) {
					this.product_specs.add(new ProductSpecify("Chế độ giặt", washEntity.getWash_mode()));
				}
				if (washEntity.getIs_fast() != null) {
					this.product_specs.add(
							new ProductSpecify("Chế độ giặt nhanh", washEntity.getIs_fast() == 1 ? "Có" : "Không"));
				}
				if (washEntity.getWash_tub() != null) {
					this.product_specs.add(new ProductSpecify("Loại lồng", washEntity.getWash_tub()));
				}
				if (washEntity.getIs_inverter() != null) {
					this.product_specs.add(new ProductSpecify("Công nghệ Inverter",
							washEntity.getIs_inverter() == 1 ? "Có" : "Không"));
				}
			}
			break;
		case 5:
			this.product_specs.add(new ProductSpecify("Xuất xứ sản phẩm", this.brand.getMadeIn()));
			this.accessory = new AccessoryDto();
			if (this.wash != null) {
				Accessory accessoryEntity = entity.getAccessory();
				if (accessoryEntity.getAccessory_model() != null) {
					this.product_specs.add(new ProductSpecify("Model", accessoryEntity.getAccessory_model()));
				}
				if (accessoryEntity.getFeatute() != null) {
					this.product_specs.add(new ProductSpecify("Tính năng", accessoryEntity.getFeatute()));
				}
			}
			break;
		default:
			break;
		}
		this.inventories = new ArrayList<>();
		if (inventories != null) {
			List<Inventory> invs = entity.getInventories();
			for (Inventory item : invs) {
				InventoryProductDto dto = new InventoryProductDto(item);
				this.inventories.add(dto);
			}
		}
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

	public Double getPercent_discount() {
		return percent_discount;
	}

	public void setPercent_discount(Double percent_discount) {
		this.percent_discount = percent_discount;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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

	@JsonIgnore
	public TechDto getTechnology() {
		return technology;
	}

	public void setTechnology(TechDto technology) {
		this.technology = technology;
	}

	@JsonIgnore
	public CameraDto getCamera() {
		return camera;
	}

	public void setCamera(CameraDto camera) {
		this.camera = camera;
	}

	@JsonIgnore
	public TiviDto getTivi() {
		return tivi;
	}

	public void setTivi(TiviDto tivi) {
		this.tivi = tivi;
	}

	@JsonIgnore
	public WashDto getWash() {
		return wash;
	}

	public void setWash(WashDto wash) {
		this.wash = wash;
	}

	@JsonIgnore
	public AccessoryDto getAccessory() {
		return accessory;
	}

	public void setAccessory(AccessoryDto accessory) {
		this.accessory = accessory;
	}

	public BrandDtoRes getBrand() {
		return brand;
	}

	public void setBrand(BrandDtoRes brand) {
		this.brand = brand;
	}

	public Integer getIn_stock() {
		return in_stock;
	}

	public void setIn_stock(Integer in_stock) {
		this.in_stock = in_stock;
	}

	public Integer getSeller_count() {
		return seller_count;
	}

	public void setSeller_count(Integer seller_count) {
		this.seller_count = seller_count;
	}

	public Integer getReview_count() {
		return review_count;
	}

	public void setReview_count(Integer review_count) {
		this.review_count = review_count;
	}

	public List<ProductSpecify> getProduct_specs() {
		return product_specs;
	}

	public void setProduct_specs(List<ProductSpecify> product_specs) {
		this.product_specs = product_specs;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<InventoryProductDto> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryProductDto> inventories) {
		this.inventories = inventories;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
