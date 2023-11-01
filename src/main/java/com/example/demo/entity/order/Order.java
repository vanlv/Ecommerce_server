package com.example.demo.entity.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.user.Seller;
import com.example.demo.entity.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_order")
public class Order extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller")
	private Seller seller;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Shipment shipment;

	@Column(name = "total_price")
	private Long total_price;

	@Column(name = "discount_price")
	private Long discount_price;

	@Column(name = "ship_fee")
	private Long ship_fee;

	@Column(name = "ship_type") // 1 giao hàng nhanh, 2 giao hàng tiết kiệm
	private Integer ship_type;

	@Column(name = "total_item")
	private Integer total_item;

	@Column(name = "order_info")
	private String orderInfo;

	@Column(name = "status")
	private Integer status;

	@Column(name = "create_ship_date")
	private String createdShipDate;

	@Column(name = "complete_date")
	private String completeDate;
	
	@Column(name = "prepare_date")
	private String prepareDate;
	
	@Column(name = "is_send_email")
	private Integer send_status; // 0: chưa send, 1: đã send

	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Order() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public Long getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(Long discount_price) {
		this.discount_price = discount_price;
	}

	public Integer getTotal_item() {
		return total_item;
	}

	public void setTotal_item(Integer total_item) {
		this.total_item = total_item;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Long getShip_fee() {
		return ship_fee;
	}

	public void setShip_fee(Long ship_fee) {
		this.ship_fee = ship_fee;
	}

	public Integer getShip_type() {
		return ship_type;
	}

	public void setShip_type(Integer ship_type) {
		this.ship_type = ship_type;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Integer getSend_status() {
		return send_status;
	}

	public void setSend_status(Integer send_status) {
		this.send_status = send_status;
	}

	public String getCreatedShipDate() {
		return createdShipDate;
	}

	public void setCreatedShipDate(String createdShipDate) {
		this.createdShipDate = createdShipDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public String getPrepareDate() {
		return prepareDate;
	}

	public void setPrepareDate(String prepareDate) {
		this.prepareDate = prepareDate;
	}
	
	

}
