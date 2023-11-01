package com.example.demo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.mail.MailRequest;
import com.example.demo.dto.mail.MailResponse;
import com.example.demo.dto.order.OrderDetailDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.service.impl.MailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/send-email")
public class SendMailController {

	@Autowired
	private MailService service;

	@PostMapping("")
	public MailResponse sendEmail(@RequestBody MailRequest request) {

		OrderDto order = request.getOrder();
		List<OrderDetailDto> order_details = order.getOrder_details();
		Map<String, Object> model = new HashMap<>();
		model.put("order_id", request.getOrder().getId());
		model.put("customer_name", order.getCustomer_name());
		model.put("customer_email", request.getTo());
		model.put("customer_phone", order.getPhone());
		model.put("customer_address", order.getAddress());
		String ship_type = "";
		switch (order.getPayment().getMethod_code()) {
		case "cod":
			ship_type = "Thanh toán tiền mặt khi nhận hàng";
			break;
		case "vnpay":
			ship_type = "Thanh toán bằng ví VnPay";
			break;
		case "momo":
			ship_type = "Thanh toán bằng ví Momo";
			break;
		case "zalopay":
			ship_type = "Thanh toán bằng ví ZaloPay";
			break;
		default:
			break;
		}
		model.put("date_order", order.getCreatedDate());
		model.put("orders", order_details);
		model.put("ship_fee", order.getShip_fee());
		model.put("ship_type", ship_type);
		model.put("total", order.getTotal_price() + order.getShip_fee());
		model.put("total_noship", order.getTotal_price());
		return service.sendEmail(request, model);
	}
	
}
