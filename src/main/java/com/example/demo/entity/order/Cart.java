package com.example.demo.entity.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.user.User;

@Entity
@Table(name = "tbl_cart")
public class Cart extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartDetail> cart_details;

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartDetail> getCart_details() {
		return cart_details;
	}

	public void setCart_details(List<CartDetail> cart_details) {
		this.cart_details = cart_details;
	}

}
