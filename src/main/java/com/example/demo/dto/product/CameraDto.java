package com.example.demo.dto.product;

import com.example.demo.entity.product.Camera;

public class CameraDto {
	private String model;
	private String image_processing;
	private String image_quality;
	private String video_quality;
	private String memory_card;
	private String screen_camera;
	private String screen_size_camera;
	private String shutter_speed;

	public CameraDto() {
		super();
	}

	public CameraDto(Camera entity) {
		super();
		this.model = entity.getModel();
		this.image_processing = entity.getImage_processing();
		this.image_quality = entity.getImage_quality();
		this.video_quality = entity.getVideo_quality();
		this.memory_card = entity.getMemory_card();
		this.screen_camera = entity.getScreen_camera();
		this.screen_size_camera = entity.getScreen_size_camera();
		this.shutter_speed = entity.getShutter_speed();
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