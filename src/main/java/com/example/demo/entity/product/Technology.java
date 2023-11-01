package com.example.demo.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_technology")
public class Technology extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

	@Column(name = "screen") // 3
	private String screen;

	@Column(name = "screen_size") // 3
	private String screen_size;

	@Column(name = "operator_system") // 3
	private String operatorSystem;

	@Column(name = "ram") // 3
	private String ram;

	@Column(name = "pin") // 3
	private String pin;

	@Column(name = "chip")
	private String chip;

	@Column(name = "design") // 3
	private String design;

	@Column(name = "display_resolution")
	private String display_resolution;

	@Column(name = "camera")
	private String camera;

	@Column(name = "release_time") // 3
	private String releaseTime;

//	----------------	PHONE	--------------------

	@Column(name = "front_camera")
	private String frontCamera;

	@Column(name = "behind_camera")
	private String behindCamera;

	@Column(name = "internal_memory")
	private String internalMemory;

	@Column(name = "sim")
	private String sim;

	@Column(name = "accessory")
	private String accessory;

	@Column(name = "number_sim")
	private Integer number_sim;

//	----------------	LAPTOP	--------------------

	@Column(name = "cpu")
	private String cpu;

	@Column(name = "hard_ware")
	private String hardWare;

	@Column(name = "card")
	private String card;

	@Column(name = "bus")
	private String bus;

	public Technology() {
		// TODO Auto-generated constructor stub
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

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
