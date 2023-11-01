package com.example.demo.dto.user;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.Comment;

public class CommentDto extends AbstractDTO<CommentDto> {

	private String content;
	private Float rating;
	private String username;
	private Long productId;
	private String displayName;
	private String createdDate;
	private String date_comment;
	private Integer display;

	public CommentDto() {
		super();
	}

	public CommentDto(Comment entity) {
		this.setId(entity.getId());
		this.content = entity.getContent();
		this.rating = entity.getRating();
		this.displayName = entity.getDisplayName();
		this.username = entity.getUser().getUsername();
		this.productId = entity.getProduct().getId();
		this.createdDate = entity.getCreatedDate();
		this.date_comment = entity.getDate_comment();
		this.display = entity.getDisplay();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDate_comment() {
		return date_comment;
	}

	public void setDate_comment(String date_comment) {
		this.date_comment = date_comment;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

}
