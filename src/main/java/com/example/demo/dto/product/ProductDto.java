package com.example.demo.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;

public class ProductDto extends AbstractDTO<ProductDto> {
	private Integer type;
	private String name;
	private String sku;
	private String slug;
	private String description;
	private Long price;
	private Long list_price;
	private String mainImage;
	private Integer weight;
	private Integer length;
	private Integer width;
	private Integer height;
	private List<String> images;
	private String category;
	private String subcategory;
	private String sizeWeight;
	private String material;
	private String features;

	// electric
	private String screen;
	private String screen_size;
	private String operatorSystem;
	private String ram;
	private String pin;
	private String chip;
	private String design;
	private String display_resolution;
	private String camera;
	private String releaseTime;

	// phone
	private String frontCamera;
	private String behindCamera;
	private String internalMemory;
	private String sim;
	private Integer number_sim;
	private String accessory;

	// laptop
	private String cpu;
	private String hardWare;
	private String card;
	private String bus;

	// camera
	private String model;
	private String image_processing;
	private String image_quality;
	private String video_quality;
	private String memory_card;
	private String screen_camera;
	private String screen_size_camera;
	private String shutter_speed;
	// tivi
	private String year;
	private String display_resolution_tv;
	private Integer type_tv;
	private String app_avaiable;
	private String usb;
	private Integer is3D;
	private Integer speaker;
	private String techlonogy_sound;
	private String component_video;
	private String hdmi;
	private String control_by_phone;
	private String image_processing_tv;

	// may giat - wash
	private String wash_weight;
	private String wash_mode;
	private Integer is_fast;
	private String wash_tub;
	private Integer is_inverter;
	private String type_engine;

	// thiet bi phu kien
	private String feature;
	private String accessory_model;

	// brand
	private String brand;

	// supplier
	private String supplier;

//	@JsonInclude(value = Include.NON_NULL)
//	private List<TagDto> tags;

	// color
	private List<String> colors;

	public ProductDto() {
		super();
	}

	public ProductDto(Product entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();
		this.name = entity.getName();
		this.sku = entity.getSku();
		this.slug = entity.getSlug();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.list_price = entity.getList_price();
		this.mainImage = entity.getMainIamge();
		this.sizeWeight = entity.getSizeWeight();
		this.material = entity.getMaterial();
		this.weight = entity.getWeight();
		this.length = entity.getLength();
		this.width = entity.getWidth();
		this.height = entity.getHeight();
		this.category = entity.getCategory().getCode();
		this.subcategory = entity.getSubcategory().getCode();
		this.brand = entity.getBrand().getCode();
		this.supplier = entity.getSupplier().getCode();
		this.features = entity.getFeatures();

		images = new ArrayList<>();
		for (Image image : entity.getImages()) {
			ImageDto dto = new ImageDto(image);
			images.add(dto.getUrl());
		}

//		tags = new ArrayList<>();
//		for (Tag tag : entity.getTags()) {
//			TagDto dto = new TagDto(tag);
//			tags.add(dto);
//		}

		switch (this.type) {
		case 1:
			// laptop + phone + tablet
			// electric
			this.screen = entity.getTechnology().getScreen();
			this.screen_size = entity.getTechnology().getScreen_size();
			this.operatorSystem = entity.getTechnology().getOperatorSystem();
			this.ram = entity.getTechnology().getRam();
			this.pin = entity.getTechnology().getPin();
			this.chip = entity.getTechnology().getChip();
			this.design = entity.getTechnology().getDesign();
			this.display_resolution = entity.getTechnology().getDisplay_resolution();
			this.camera = entity.getTechnology().getCamera();
			this.releaseTime = entity.getTechnology().getReleaseTime();
			// phone
			this.frontCamera = entity.getTechnology().getFrontCamera();
			this.behindCamera = entity.getTechnology().getBehindCamera();
			this.internalMemory = entity.getTechnology().getInternalMemory();
			this.sim = entity.getTechnology().getSim();
			this.accessory = entity.getTechnology().getAccessory();
			this.number_sim = entity.getTechnology().getNumber_sim();
			// laptop
			this.cpu = entity.getTechnology().getCpu();
			this.hardWare = entity.getTechnology().getHardWare();
			this.card = entity.getTechnology().getCard();
			this.bus = entity.getTechnology().getBus();
			break;
		case 2:
			// camera
			this.model = entity.getCamera().getModel();
			this.image_processing = entity.getCamera().getImage_processing();
			this.image_quality = entity.getCamera().getImage_quality();
			this.video_quality = entity.getCamera().getVideo_quality();
			this.memory_card = entity.getCamera().getMemory_card();
			this.screen_camera = entity.getCamera().getScreen_camera();
			this.screen_size_camera = entity.getCamera().getScreen_size_camera();
			this.shutter_speed = entity.getCamera().getShutter_speed();
			break;

		case 3:
			// tivi
			this.year = entity.getTivi().getYear();
			this.display_resolution_tv = entity.getTivi().getDisplay_resolution_tv();
			this.type_tv = entity.getTivi().getType_tv();
			this.app_avaiable = entity.getTivi().getApp_avaiable();
			this.usb = entity.getTivi().getUsb();
			this.is3D = entity.getTivi().getIs3D();
			this.speaker = entity.getTivi().getSpeaker();
			this.techlonogy_sound = entity.getTivi().getTechlonogy_sound();
			this.component_video = entity.getTivi().getComponent_video();
			this.hdmi = entity.getTivi().getHdmi();
			this.control_by_phone = entity.getTivi().getControl_by_phone();
			this.image_processing_tv = entity.getTivi().getImage_processing_tv();
			break;
		case 4:
			// wash
			this.wash_weight = entity.getWash().getWash_weight();
			this.wash_mode = entity.getWash().getWash_mode();
			this.is_fast = entity.getWash().getIs_fast();
			this.wash_tub = entity.getWash().getWash_tub();
			this.is_inverter = entity.getWash().getIs_inverter();
			this.type_engine = entity.getWash().getType_engine();
			break;
		case 5:
			this.accessory_model = entity.getAccessory().getAccessory_model();
			this.feature = entity.getAccessory().getFeatute();
			break;
		default:
			break;
		}

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

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getOperatorSystem() {
		return operatorSystem;
	}

	public void setOperatorSystem(String operatorSystem) {
		this.operatorSystem = operatorSystem;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getSizeWeight() {
		return sizeWeight;
	}

	public void setSizeWeight(String sizeWeight) {
		this.sizeWeight = sizeWeight;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public String getBehindCamera() {
		return behindCamera;
	}

	public void setBehindCamera(String behindCamera) {
		this.behindCamera = behindCamera;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public String getInternalMemory() {
		return internalMemory;
	}

	public void setInternalMemory(String internalMemory) {
		this.internalMemory = internalMemory;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getHardWare() {
		return hardWare;
	}

	public void setHardWare(String hardWare) {
		this.hardWare = hardWare;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getScreen_size() {
		return screen_size;
	}

	public void setScreen_size(String screen_size) {
		this.screen_size = screen_size;
	}

	public String getDisplay_resolution() {
		return display_resolution;
	}

	public void setDisplay_resolution(String display_resolution) {
		this.display_resolution = display_resolution;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public Integer getNumber_sim() {
		return number_sim;
	}

	public void setNumber_sim(Integer number_sim) {
		this.number_sim = number_sim;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getWash_weight() {
		return wash_weight;
	}

	public void setWash_weight(String wash_weight) {
		this.wash_weight = wash_weight;
	}

	public String getWash_mode() {
		return wash_mode;
	}

	public void setWash_mode(String wash_mode) {
		this.wash_mode = wash_mode;
	}

	public Integer getIs_fast() {
		return is_fast;
	}

	public void setIs_fast(Integer is_fast) {
		this.is_fast = is_fast;
	}

	public String getWash_tub() {
		return wash_tub;
	}

	public void setWash_tub(String wash_tub) {
		this.wash_tub = wash_tub;
	}

	public Integer getIs_inverter() {
		return is_inverter;
	}

	public void setIs_inverter(Integer is_inverter) {
		this.is_inverter = is_inverter;
	}

	public String getImage_processing() {
		return image_processing;
	}

	public void setImage_processing(String image_processing) {
		this.image_processing = image_processing;
	}

	public String getImage_quality() {
		return image_quality;
	}

	public void setImage_quality(String image_quality) {
		this.image_quality = image_quality;
	}

	public String getVideo_quality() {
		return video_quality;
	}

	public void setVideo_quality(String video_quality) {
		this.video_quality = video_quality;
	}

	public String getMemory_card() {
		return memory_card;
	}

	public void setMemory_card(String memory_card) {
		this.memory_card = memory_card;
	}

	public String getScreen_camera() {
		return screen_camera;
	}

	public void setScreen_camera(String screen_camera) {
		this.screen_camera = screen_camera;
	}

	public String getScreen_size_camera() {
		return screen_size_camera;
	}

	public void setScreen_size_camera(String screen_size_camera) {
		this.screen_size_camera = screen_size_camera;
	}

	public String getShutter_speed() {
		return shutter_speed;
	}

	public void setShutter_speed(String shutter_speed) {
		this.shutter_speed = shutter_speed;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDisplay_resolution_tv() {
		return display_resolution_tv;
	}

	public void setDisplay_resolution_tv(String display_resolution_tv) {
		this.display_resolution_tv = display_resolution_tv;
	}

	public Integer getType_tv() {
		return type_tv;
	}

	public void setType_tv(Integer type_tv) {
		this.type_tv = type_tv;
	}

	public String getApp_avaiable() {
		return app_avaiable;
	}

	public void setApp_avaiable(String app_avaiable) {
		this.app_avaiable = app_avaiable;
	}

	public String getUsb() {
		return usb;
	}

	public void setUsb(String usb) {
		this.usb = usb;
	}

	public Integer getIs3D() {
		return is3D;
	}

	public void setIs3D(Integer is3d) {
		is3D = is3d;
	}

	public Integer getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Integer speaker) {
		this.speaker = speaker;
	}

	public String getTechlonogy_sound() {
		return techlonogy_sound;
	}

	public void setTechlonogy_sound(String techlonogy_sound) {
		this.techlonogy_sound = techlonogy_sound;
	}

	public String getComponent_video() {
		return component_video;
	}

	public void setComponent_video(String component_video) {
		this.component_video = component_video;
	}

	public String getHdmi() {
		return hdmi;
	}

	public void setHdmi(String hdmi) {
		this.hdmi = hdmi;
	}

	public String getImage_processing_tv() {
		return image_processing_tv;
	}

	public void setImage_processing_tv(String image_processing_tv) {
		this.image_processing_tv = image_processing_tv;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getControl_by_phone() {
		return control_by_phone;
	}

	public void setControl_by_phone(String control_by_phone) {
		this.control_by_phone = control_by_phone;
	}

	public String getType_engine() {
		return type_engine;
	}

	public void setType_engine(String type_engine) {
		this.type_engine = type_engine;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getAccessory_model() {
		return accessory_model;
	}

	public void setAccessory_model(String accessory_model) {
		this.accessory_model = accessory_model;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

//	public List<TagDto> getTags() {
//		return tags;
//	}
//
//	public void setTags(List<TagDto> tags) {
//		this.tags = tags;
//	}

}
