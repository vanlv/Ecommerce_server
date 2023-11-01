package com.example.demo.dto.product;

import com.example.demo.entity.product.Tivi;

public class TiviDto {
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

	public TiviDto() {
		super();
	}

	public TiviDto(Tivi entity) {
		super();
		this.year = entity.getYear();
		this.display_resolution_tv = entity.getDisplay_resolution_tv();
		this.type_tv = entity.getType_tv();
		this.app_avaiable = entity.getApp_avaiable();
		this.usb = entity.getUsb();
		this.is3D = entity.getIs3D();
		this.speaker = entity.getSpeaker();
		this.techlonogy_sound = entity.getTechlonogy_sound();
		this.component_video = entity.getComponent_video();
		this.hdmi = entity.getHdmi();
		this.control_by_phone = entity.getControl_by_phone();
		this.image_processing_tv = entity.getImage_processing_tv();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getImage_processing_tv() {
		return image_processing_tv;
	}

	public void setImage_processing_tv(String image_processing_tv) {
		this.image_processing_tv = image_processing_tv;
	}

	public String getControl_by_phone() {
		return control_by_phone;
	}

	public void setControl_by_phone(String control_by_phone) {
		this.control_by_phone = control_by_phone;
	}

}