package com.example.demo.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.product.Product;

@Entity
@Table(name = "tbl_comment")
public class Comment extends BaseEntity {

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "content")
	private String content;

	@Column(name = "rating")
	private Float rating;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "date_comment")
	private String date_comment;

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
