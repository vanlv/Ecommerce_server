package com.example.demo.entity.other;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_promotion_home")
public class Promotion extends BaseEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

}
