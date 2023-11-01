package com.example.demo.dto.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.other.Slide;

public class SlideDto extends AbstractDTO<SlideDto> {

	private String image;
	private Integer display;
	private String createdDate;

	public SlideDto() {
		// TODO Auto-generated constructor stub
	}

	public SlideDto(Slide entity) {
		super();
		this.setId(entity.getId());
		this.image = entity.getImage();
		this.display = entity.getDisplay();
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
