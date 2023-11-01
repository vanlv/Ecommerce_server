package com.example.demo.entity.other;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_slide")
public class Slide extends BaseEntity {

	@Column(name = "url")
	private String image;

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	public Slide() {
		// TODO Auto-generated constructor stub
	}

	public Slide(String image) {
		super();
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

}
