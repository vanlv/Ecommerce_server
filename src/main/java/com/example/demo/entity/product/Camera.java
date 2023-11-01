package com.example.demo.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_camera")
public class Camera extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

	@Column(name = "model")
	private String model;

	@Column(name = "image_processing")
	private String image_processing;

	@Column(name = "image_quality")
	private String image_quality;

	@Column(name = "video_quality")
	private String video_quality;

	@Column(name = "memory_card")
	private String memory_card;

	@Column(name = "screen_camera") // 3
	private String screen_camera;

	@Column(name = "screen_size_camera")
	private String screen_size_camera;

	@Column(name = "shutter_speed")
	private String shutter_speed;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

}
