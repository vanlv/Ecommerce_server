package com.example.demo.dto.product;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Image;

public class ImageDto extends AbstractDTO<ImageDto> {

	private String url;

	public ImageDto() {
		// TODO Auto-generated constructor stub
	}

	public ImageDto(Image image) {
		super();
		this.setId(image.getId());
		this.url = image.getUrl();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
