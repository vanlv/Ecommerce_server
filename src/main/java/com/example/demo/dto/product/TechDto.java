package com.example.demo.dto.product;

import com.example.demo.entity.product.Technology;

public class TechDto {

	private String screen;
	private String screen_size;
	private String operatorSystem;
	private String ram;
	private String pin;
	private String chip;
	private String design;
	private String releaseTime;
	private String display_resolution;
	private String camera;

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

	public TechDto() {
		// TODO Auto-generated constructor stub
	}

	public TechDto(Technology entity) {
		// electric
		this.screen = entity.getScreen();
		this.screen_size = entity.getScreen_size();
		this.operatorSystem = entity.getOperatorSystem();
		this.ram = entity.getRam();
		this.pin = entity.getPin();
		this.design = entity.getDesign();
		this.releaseTime = entity.getReleaseTime();
		this.display_resolution = entity.getDisplay_resolution();
		this.chip = entity.getChip();

		// phone, tablet
		this.frontCamera = entity.getFrontCamera();
		this.behindCamera = entity.getBehindCamera();
		this.internalMemory = entity.getInternalMemory();
		this.sim = entity.getSim();
		this.number_sim = entity.getNumber_sim();

		// laptop
		this.cpu = entity.getCpu();
		this.hardWare = entity.getHardWare();
		this.card = entity.getCard();
		this.bus = entity.getBus();
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

}
