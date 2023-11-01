package com.example.demo.dto.user;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.ViewedProduct;

public class ViewedProductDto extends AbstractDTO<ViewedProductDto> {
	private String username;
	private Long productId;
	private Long view_count;

	public ViewedProductDto() {
		// TODO Auto-generated constructor stub
	}

	public ViewedProductDto(String username, Long productId, Long view_count) {
		super();
		this.username = username;
		this.productId = productId;
		this.view_count = view_count;
	}

	public ViewedProductDto(Long productId, Long view_count) {
		super();
		this.productId = productId;
		this.view_count = view_count;
	}

	public ViewedProductDto(ViewedProduct entity) {
		// TODO Auto-generated constructor stub
		this.setId(entity.getId());
		this.username = entity.getUser().getUsername();
		this.productId = entity.getProduct().getId();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getView_count() {
		return view_count;
	}

	public void setView_count(Long view_count) {
		this.view_count = view_count;
	}

}
