package com.example.demo.rest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class TestClient {

	@RequestMapping(value = "/testclient/get")
	public List<Test> testclientGET() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
//	    headers.add("token", "Basic " + encodedAuth);
		ResponseEntity<List<Test>> res = restTemplate.exchange("https://5f85c82fc8a16a0016e6a484.mockapi.io/test",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Test>>() {

				});
		return res.getBody();
	}

	@RequestMapping(value = "/testclient/post")
	public ResponseEntity<Test> testclientPost(@RequestHeader(value = "token") String token, @RequestBody Test test) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("token", token);
		Test result = restTemplate.postForObject("https://5f85c82fc8a16a0016e6a484.mockapi.io/test", test, Test.class);
		return new ResponseEntity<Test>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ship/post")
	public ResponseEntity<OrderInfo> testShipPost(@RequestHeader(value = "token") String token,
			@RequestHeader(value = "shopid") Integer shopid, @RequestBody OrderInfo order) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Content-type", "application/json");
		headers.add("token", token);
		headers.add("shopid", shopid.toString());
//		OrderInfo result = restTemplate.postForObject(
//				"https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create", order, OrderInfo.class);
		HttpEntity<OrderInfo> entity = new HttpEntity<OrderInfo>(order, headers);
		restTemplate.exchange(
			       "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create", HttpMethod.POST, entity, OrderInfo.class);
		return new ResponseEntity<OrderInfo>(entity.getBody(), HttpStatus.OK);
	}
}

class Test {
	String name;
	String code;

	public Test() {
		super();
	}

	public Test(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

class Category {
	private String level1;
	private String level2;

	public Category() {
		super();
	}

	public Category(String level1, String level2) {
		super();
		this.level1 = level1;
		this.level2 = level2;
	}

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

}

class OrderInfo {
	private Integer payment_type_id; // Mã người thanh toán phí dịch vụ. 1: Người bán/Người gửi. 2: Người mua/Người
										// nhận.
	private String note; // Người gửi ghi chú cho tài xế.
	private String required_note; // Ghi chú bắt buộc, Bao gồm: CHOTHUHANG, CHOXEMHANGKHONGTHU, KHONGCHOXEMHANG --
									// Bắt buộc
	private String return_phone; // Số điện thoại trả hàng khi không giao được.
	private String return_address; // Địa chỉ trả hàng khi không giao được.
	private Integer return_district_id; // Quận của người nhận hàng trả.
	private String return_ward_code; // Phường của người nhận hàng trả.
	private String client_order_code; // Mã đơn hàng riêng của khách hàng. Giá trị mặc định: null
	private String to_name; // Tên người nhận hàng. -- Bắt buộc
	private String to_phone; // Số điện thoại người nhận hàng. -- Bắt buộc
	private String to_address; // Địa chỉ Shiper tới giao hàng. -- Bắt buộc
	private String to_ward_code; // Phường của người nhận hàng. -- Bắt buộc
	private Integer to_district_id; // Quận của người nhận hàng. -- Bắt buộc
	private Integer cod_amount; // Tiền thu hộ cho người gửi. Maximum :10.000.000 Giá trị mặc định: 0
	private String content; // Nội dung của đơn hàng.
	private Integer weight; // -- Bắt buộc
	private Integer length; // -- Bắt buộc
	private Integer width; // -- Bắt buộc
	private Integer height; // -- Bắt buộc
	private Integer pick_station_id; // Mã bưu cục để gửi hàng tại điểm. Giá trị mặc định : null
	private Integer insurance_value; // Giá trị của đơn hàng ( Trường hợp mất hàng , bể hàng sẽ đền theo giá trị của
										// đơn hàng).
	private Integer service_id; // -- Bắt buộc
	private Integer service_type_id; // -- Bắt buộc
	private Integer order_value; // Giá trị đơn hàng
	private String coupon; // Mã giảm giá.
//	private pick_shift		
	private List<Item> items; // -- Bắt buộc

	public OrderInfo() {
		super();
	}

	public OrderInfo(Integer payment_type_id, String note, String required_note, String return_phone,
			String return_address, Integer return_district_id, String return_ward_code, String client_order_code,
			String to_name, String to_phone, String to_address, String to_ward_code, Integer to_district_id,
			Integer cod_amount, String content, Integer weight, Integer length, Integer width, Integer height,
			Integer pick_station_id, Integer insurance_value, Integer service_id, Integer service_type_id,
			Integer order_value, String coupon, List<Item> items) {
		super();
		this.payment_type_id = payment_type_id;
		this.note = note;
		this.required_note = required_note;
		this.return_phone = return_phone;
		this.return_address = return_address;
		this.return_district_id = return_district_id;
		this.return_ward_code = return_ward_code;
		this.client_order_code = client_order_code;
		this.to_name = to_name;
		this.to_phone = to_phone;
		this.to_address = to_address;
		this.to_ward_code = to_ward_code;
		this.to_district_id = to_district_id;
		this.cod_amount = cod_amount;
		this.content = content;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.pick_station_id = pick_station_id;
		this.insurance_value = insurance_value;
		this.service_id = service_id;
		this.service_type_id = service_type_id;
		this.order_value = order_value;
		this.coupon = coupon;
		this.items = items;
	}

	public Integer getPayment_type_id() {
		return payment_type_id;
	}

	public void setPayment_type_id(Integer payment_type_id) {
		this.payment_type_id = payment_type_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRequired_note() {
		return required_note;
	}

	public void setRequired_note(String required_note) {
		this.required_note = required_note;
	}

	public String getReturn_phone() {
		return return_phone;
	}

	public void setReturn_phone(String return_phone) {
		this.return_phone = return_phone;
	}

	public String getReturn_address() {
		return return_address;
	}

	public void setReturn_address(String return_address) {
		this.return_address = return_address;
	}

	public Integer getReturn_district_id() {
		return return_district_id;
	}

	public void setReturn_district_id(Integer return_district_id) {
		this.return_district_id = return_district_id;
	}

	public String getReturn_ward_code() {
		return return_ward_code;
	}

	public void setReturn_ward_code(String return_ward_code) {
		this.return_ward_code = return_ward_code;
	}

	public String getClient_order_code() {
		return client_order_code;
	}

	public void setClient_order_code(String client_order_code) {
		this.client_order_code = client_order_code;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public String getTo_phone() {
		return to_phone;
	}

	public void setTo_phone(String to_phone) {
		this.to_phone = to_phone;
	}

	public String getTo_address() {
		return to_address;
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

	public String getTo_ward_code() {
		return to_ward_code;
	}

	public void setTo_ward_code(String to_ward_code) {
		this.to_ward_code = to_ward_code;
	}

	public Integer getTo_district_id() {
		return to_district_id;
	}

	public void setTo_district_id(Integer to_district_id) {
		this.to_district_id = to_district_id;
	}

	public Integer getCod_amount() {
		return cod_amount;
	}

	public void setCod_amount(Integer cod_amount) {
		this.cod_amount = cod_amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getPick_station_id() {
		return pick_station_id;
	}

	public void setPick_station_id(Integer pick_station_id) {
		this.pick_station_id = pick_station_id;
	}

	public Integer getInsurance_value() {
		return insurance_value;
	}

	public void setInsurance_value(Integer insurance_value) {
		this.insurance_value = insurance_value;
	}

	public Integer getService_id() {
		return service_id;
	}

	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}

	public Integer getService_type_id() {
		return service_type_id;
	}

	public void setService_type_id(Integer service_type_id) {
		this.service_type_id = service_type_id;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}

class Item {
	private String name; // -- Bắt buộc
	private String code;
	private Integer quantity; // -- Bắt buộc
	private Long price;
	private Category category;

	public Item() {
		super();
	}

	public Item(String name, String code, Integer quantity, Long price, Category category) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}

//"payment_type_id": 2,
//"note": "Huong dep trai",
//"required_note": "KHONGCHOXEMHANG",
//"return_phone": "0332190158",
//"return_address": "39 NTT",
//"return_district_id": null,
//"return_ward_code": "",
//"client_order_code": "",
//"to_name": "Huong dep trai",
//"to_phone": "0987654321",
//"to_address": "Xã Xã Nam Tiến, Huyện Phú Xuyên, Thành phố Hà Nội",
//"to_ward_code": "800214",
//"to_district_id": 3255,
//"cod_amount": 200000,
//"content": "Theo New York Times",
//"weight": 200,
//"length": 1,
//"width": 19,
//"height": 10,
//"pick_station_id": 1444,
//"insurance_value": 10000000,
//"service_id": 0,
//"service_type_id":2,
//"order_value":130000,
//"coupon":null,
//"pick_shift":[2],
//"items": [
//     {
//         "name":"Áo Polo",
//         "code":"Polo123",
//         "quantity": 1,
//         "price": 200000,
//         "length": 12,
//         "width": 12,
//         "height": 12,
//         "category": 
//         {
//             "level1":"Áo"
//         }
//     }
//     
// ]
