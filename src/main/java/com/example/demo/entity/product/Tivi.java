package com.example.demo.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_television")
public class Tivi extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

	@Column(name = "year") // năm ra mắt
	private String year;

	@Column(name = "display_resolution_tv")
	private String display_resolution_tv;

	@Column(name = "type_tv")
	private Integer type_tv; // smart/inverter

	@Column(name = "app_avaiable")
	private String app_avaiable;

	@Column(name = "usb")
	private String usb;

	@Column(name = "is_3d")
	private Integer is3D; // 1 co 0 khong

	@Column(name = "speaker") // so luong loa
	private Integer speaker;

	@Column(name = "techlonogy_sound") // 3
	private String techlonogy_sound;

	@Column(name = "component_video")
	private String component_video;

	@Column(name = "control_by_phone")
	private String control_by_phone;

	@Column(name = "hdmi")
	private String hdmi;

	@Column(name = "image_processing_tv")
	private String image_processing_tv; // cong nghe xu ly hinh anh

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public String getImage_processing_tv() {
		return image_processing_tv;
	}

	public void setImage_processing_tv(String image_processing_tv) {
		this.image_processing_tv = image_processing_tv;
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

	public String getControl_by_phone() {
		return control_by_phone;
	}

	public void setControl_by_phone(String control_by_phone) {
		this.control_by_phone = control_by_phone;
	}

}
