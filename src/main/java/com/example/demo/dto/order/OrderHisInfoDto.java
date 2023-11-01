package com.example.demo.dto.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.entity.order.Order;

public class OrderHisInfoDto {
	private String createdDate;
	private String createdShipDate;
	private String expectedDate;
	private String completeDate;
	private String prepareDate;
	private Long total_price;
	private Long discount_price;
	private Integer total_item;
	private String orderInfo;
	private String address;
	private String province;
	private String district;
	private String ward;
	private Integer weight;
	private Integer length;
	private Integer width;
	private Integer height;
	private Long ship_fee;
	private Integer ship_type;
	private String ward_code;
	private Integer district_id;
	private Integer status_order;
	private String status_order_name;
	private Integer status_payment;
	private String status_payment_name;
	private String ship_order_code;
	private String payment_method;

	public OrderHisInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderHisInfoDto(Order entity) {
		// TODO Auto-generated constructor stub
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));

			this.expectedDate = new SimpleDateFormat("dd/MM/yyyy")
					.format(new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
							.parse(entity.getCreatedDate()).getTime() + 7*24 * 60 * 60 * 1000));

			if (entity.getCreatedShipDate() != null) {
				this.createdShipDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date(
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedShipDate()).getTime()));
			}
			if (entity.getCompleteDate() != null) {
				this.completeDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date(
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCompleteDate()).getTime()));
			}
			if (entity.getPrepareDate() != null) {
				this.prepareDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date(
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getPrepareDate()).getTime()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.total_price = entity.getTotal_price();
		this.discount_price = entity.getDiscount_price();
		this.ship_fee = entity.getShip_fee();
		this.ship_type = entity.getShip_type();
		this.total_item = entity.getTotal_item();
		this.orderInfo = entity.getOrderInfo();
		this.address = entity.getShipment().getAddress();
		this.province = entity.getShipment().getProvince();
		this.district = entity.getShipment().getDistrict();
		this.ward = entity.getShipment().getWard();
		this.ward_code = entity.getShipment().getWard_code();
		this.district_id = entity.getShipment().getDistrict_id();
		this.status_order = entity.getStatus();
		this.status_payment = entity.getPayment().getStatus();
		switch (this.status_order) {
		case -2:
			this.status_order_name = "Khách hàng trả lại hàng";
			break;
		case -1:
			this.status_order_name = "Khách hàng huỷ đơn";
			break;
		case 0:
			this.status_order_name = "Đang chờ xác nhận";
			break;
		case 1:
			this.status_order_name = "Đang chuẩn bị hàng";
			break;
		case 2:
			this.status_order_name = "Đang giao hàng";
			break;
		case 3:
			this.status_order_name = "Đã nhận hàng";
			break;
		default:
			this.status_order_name = "Đang chờ xác nhận";
			break;
		}

		switch (this.status_payment) {
		case -1:
			this.status_payment_name = "Đã huỷ thanh toán";
			break;
		case 0:
			this.status_payment_name = "Chưa thanh toán";
			break;
		case 1:
			this.status_payment_name = "Đã thanh toán";
			break;
		case 2:
			this.status_payment_name = "Đã hoàn tiền";
			break;
		default:
			this.status_payment_name = "Chưa thanh toán";
			break;
		}

		this.payment_method = entity.getPayment().getMethod().getName();
		this.ship_order_code = entity.getShipment().getOrder_code();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Integer getStatus_order() {
		return status_order;
	}

	public void setStatus_order(Integer status_order) {
		this.status_order = status_order;
	}

	public String getStatus_order_name() {
		return status_order_name;
	}

	public void setStatus_order_name(String status_order_name) {
		this.status_order_name = status_order_name;
	}

	public Integer getStatus_payment() {
		return status_payment;
	}

	public void setStatus_payment(Integer status_payment) {
		this.status_payment = status_payment;
	}

	public String getStatus_payment_name() {
		return status_payment_name;
	}

	public void setStatus_payment_name(String status_payment_name) {
		this.status_payment_name = status_payment_name;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getWard_code() {
		return ward_code;
	}

	public void setWard_code(String ward_code) {
		this.ward_code = ward_code;
	}

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public String getShip_order_code() {
		return ship_order_code;
	}

	public void setShip_order_code(String ship_order_code) {
		this.ship_order_code = ship_order_code;
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

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

}
