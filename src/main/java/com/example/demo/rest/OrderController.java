package com.example.demo.rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.order.OrderDetailHisDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.order.OrderHisFullDto;
import com.example.demo.dto.order.PaymentDto;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.Shipment;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Product;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ShipmentRepository shipmentRepos;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private ColorRepository colorRepos;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<OrderHisDto>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "4") Integer status) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Page<OrderHisDto> result = service.getAllOrder(dto);
		return new ResponseEntity<Page<OrderHisDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/seller/{id}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<Page<OrderHisDto>> getAllByShipper(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "3") Integer status, @PathVariable Long id) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Page<OrderHisDto> result = service.getAllOrderBySeller(dto, id);
		return new ResponseEntity<Page<OrderHisDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/seller")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<Page<OrderHisDto>> getAllShipperByUsername(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "3") Integer status) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Page<OrderHisDto> result = service.getAllOrderBySellerUsername(dto, username);
		return new ResponseEntity<Page<OrderHisDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<List<OrderDetailHisDto>> getDetail(@PathVariable Long id) {
		List<OrderDetailHisDto> result = service.getDetailOrderById(id);
		return new ResponseEntity<List<OrderDetailHisDto>>(result, HttpStatus.OK);
	}

	// get sau khi đặt
	@GetMapping("/chi-tiet/{id}")
//	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<OrderDto> getDetailAfterPayment(@PathVariable Long id) {
		OrderDto result = service.getOneOrderAfterPayment(id);
		String ship_type = "";
		switch (result.getPayment().getMethod_code()) {
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
		result.setStatus_payment_name(ship_type);
		return new ResponseEntity<OrderDto>(result, HttpStatus.OK);
	}

	@GetMapping("/detail-full/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<OrderHisFullDto> getDetailFull(@PathVariable Long id) {
		OrderHisFullDto result = service.getDetailOrder(id);
		return new ResponseEntity<OrderHisFullDto>(result, HttpStatus.OK);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<OrderHisDto>> getAllByUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<OrderHisDto> result = service.getAllOrderByUser(username);

		return new ResponseEntity<List<OrderHisDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<OrderDto> create(@Validated @RequestBody OrderDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		OrderDto result = service.createOrder(dto);
		return new ResponseEntity<OrderDto>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/checkCode")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> check(@RequestParam("tradingCode") String tradingCode) {
		Boolean result = service.checkTradingCode(tradingCode);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	// xác nhận đơn hàng => status = 3
	@PutMapping("/confirm/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<MessageResponse> confirmOrder(@PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		if (order.getStatus() == 3) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng đã được hoàn thành!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -2) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Đơn hàng không thành công, khách trả lại hàng!"), HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã bị huỷ!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == 1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đang chờ nhập hàng từ nhà cung cấp!"),
					HttpStatus.BAD_REQUEST);
		} else {
			List<OrderDetail> orderDetailDtos = order.getOrderDetails();
			for (OrderDetail i : orderDetailDtos) {
				Product product = productRepos.getById(i.getProduct().getId());
				Color color = colorRepos.findOneByName(i.getColor());
				if (inventoryRepos.existsByProductAndColor(product, color)) {
					Inventory inventory = inventoryRepos.getOneByProductAndColor(product, color);
					inventory.setQuantity_item(inventory.getQuantity_item() - i.getQuantity());
					inventoryRepos.save(inventory);
				}
			}
			order.setStatus(3);
			order.setCompleteDate(new Timestamp(new Date().getTime()).toString());
		}

		if (order.getStatus() == -1) {
			payment.setStatus(-1);
		} else if (order.getStatus() == 3) {
			payment.setStatus(1);
		} else if (order.getStatus() == -2) {
			payment.setStatus(-1);
		}
		paymentRepository.save(payment);

		orderRepository.save(order);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Xác nhận đơn hàng thành công!"), HttpStatus.OK);
	}

	// đang giao hàng => status = 2
	@PutMapping("/is-shipping/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
	public ResponseEntity<MessageResponse> shipping(@PathVariable Long id, @RequestParam String order_code) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		Shipment shipment = shipmentRepos.findOneByOrderId(order.getId());
		if (order.getStatus() == 2) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Đơn hàng đang giao, không cần xác nhận lại!"), HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã bị huỷ!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -2) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Đơn hàng không thành công, khách trả lại hàng!"), HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == 3) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã được xác nhận!"),
					HttpStatus.BAD_REQUEST);
		} else {
			List<OrderDetail> orderDetailDtos = order.getOrderDetails();
			for (OrderDetail i : orderDetailDtos) {
				Product product = productRepos.getById(i.getProduct().getId());
				Color color = colorRepos.findOneByName(i.getColor());
				Inventory inv = inventoryRepos.getOneByProductAndColor(product, color);
				Integer quantity_in_stock = inv.getQuantity_item();
				if (quantity_in_stock == 0) {
					order.setStatus(1);
					order.setPrepareDate(new Timestamp(new Date().getTime()).toString());
					orderRepository.save(order);
					return new ResponseEntity<MessageResponse>(
							new MessageResponse(
									"Sản phẩm " + i.getProduct().getName() + " đã hết hàng, vui lòng nhập thêm hàng."),
							HttpStatus.BAD_REQUEST);
				} else if (i.getQuantity() > quantity_in_stock) {
					order.setStatus(1);
					order.setPrepareDate(new Timestamp(new Date().getTime()).toString());
					orderRepository.save(order);
					return new ResponseEntity<MessageResponse>(
							new MessageResponse("Sản phẩm " + i.getProduct().getName() + " chỉ còn lại "
									+ quantity_in_stock + " sản phẩm, Vui lòng nhập thêm hàng."),
							HttpStatus.BAD_REQUEST);
				}
			}
			order.setStatus(2);
			shipment.setOrder_code(order_code);
			order.setCreatedShipDate(new Timestamp(new Date().getTime()).toString());
		}

		if (order.getStatus() == -1) {
			payment.setStatus(-1);
		} else if (order.getStatus() == 3) {
			payment.setStatus(1);
		} else if (order.getStatus() == -2) {
			payment.setStatus(-1);
		}
		paymentRepository.save(payment);
		orderRepository.save(order);
		shipmentRepos.save(shipment);
		return ResponseEntity.ok(new MessageResponse("Cập nhật trạng thái đơn hàng thành công!"));
	}

	// chuẩn bị hàng => status = 1
	@PutMapping("/prepare/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
	public ResponseEntity<MessageResponse> prepareOrder(@PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		Shipment shipment = shipmentRepos.findOneByOrderId(order.getId());
		if (order.getStatus() == 2) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Đơn hàng đang giao, không cần chuẩn bị lại!"), HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã bị huỷ!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -2) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Đơn hàng không thành công, khách trả lại hàng!"), HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == 3) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã được giao thành công!"),
					HttpStatus.BAD_REQUEST);
		} else {
			List<OrderDetail> orderDetailDtos = order.getOrderDetails();
			for (OrderDetail i : orderDetailDtos) {
				Product product = productRepos.getById(i.getProduct().getId());
				Color color = colorRepos.findOneByName(i.getColor());
				Inventory inv = inventoryRepos.getOneByProductAndColor(product, color);
				Integer quantity_in_stock = inv.getQuantity_item();
				if (quantity_in_stock == 0) {
					order.setStatus(0);
					orderRepository.save(order);
					return new ResponseEntity<MessageResponse>(
							new MessageResponse(
									"Sản phẩm " + i.getProduct().getName() + " đã hết hàng, vui lòng nhập thêm hàng."),
							HttpStatus.BAD_REQUEST);
				} else if (i.getQuantity() > quantity_in_stock) {
					order.setStatus(0);
					orderRepository.save(order);
					return new ResponseEntity<MessageResponse>(
							new MessageResponse("Sản phẩm " + i.getProduct().getName() + " chỉ còn lại "
									+ quantity_in_stock + " sản phẩm, Vui lòng nhập thêm hàng."),
							HttpStatus.BAD_REQUEST);
				}
			}
			order.setStatus(1);
			order.setPrepareDate(new Timestamp(new Date().getTime()).toString());
		}

		if (order.getStatus() == -1) {
			payment.setStatus(-1);
		} else if (order.getStatus() == 3) {
			payment.setStatus(1);
		} else if (order.getStatus() == -2) {
			payment.setStatus(-1);
		}
		paymentRepository.save(payment);
		orderRepository.save(order);
		shipmentRepos.save(shipment);
		return ResponseEntity.ok(new MessageResponse("Cập nhật trạng thái đơn hàng thành công!"));
	}

	// khách trả lại hàng => status = -2
	@PutMapping("/return/{id}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<MessageResponse> returnOrder(@PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		if (order.getStatus() == 3) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng đã được giao thành công!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã bị huỷ!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == 0 || order.getStatus() == 1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng chưa được giao!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == 2) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng đang được giao!"),
					HttpStatus.BAD_REQUEST);
		} else {
			order.setStatus(-2);
			List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrderId(order.getId());
			for (OrderDetail i : orderDetails) {
				Product p = productRepos.getById(i.getProduct().getId());
				Color c = colorRepos.findOneByName(i.getColor());
				if (inventoryRepos.existsByProductAndColor(p, c)) {
					Inventory inventory = inventoryRepos.getOneByProductAndColor(p, c);
					inventory.setQuantity_item(inventory.getQuantity_item() + i.getQuantity());
					inventoryRepos.save(inventory);
				}
			}
//			order.setCompleteDate(new Timestamp(new Date().getTime()).toString());
		}

		payment.setStatus(-1);
		paymentRepository.save(payment);

		orderRepository.save(order);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Xác nhận đơn hàng thành công!"), HttpStatus.OK);
	}

	// huỷ đơn hàng
	@PutMapping("/cancel/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<MessageResponse> cancel(@PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			if (order.getStatus() == -1) {
				return new ResponseEntity<MessageResponse>(new MessageResponse("Bạn đã huỷ đơn hàng này rồi!"),
						HttpStatus.BAD_REQUEST);
			} else if (order.getStatus() == -2) {
				return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã được hoàn lại!"),
						HttpStatus.BAD_REQUEST);
			} else if (order.getStatus() == 3) {
				return new ResponseEntity<MessageResponse>(
						new MessageResponse("Đơn hàng này đã giao thành công, không thể huỷ!"), HttpStatus.BAD_REQUEST);
			} else if (order.getStatus() == 2) {
				return new ResponseEntity<MessageResponse>(
						new MessageResponse("Đơn hàng này đang được giao, không thể huỷ!"), HttpStatus.BAD_REQUEST);
			} else {
				order.setStatus(-1);
				List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrderId(order.getId());
				for (OrderDetail i : orderDetails) {
					Product p = productRepos.getById(i.getProduct().getId());
					Color c = colorRepos.findOneByName(i.getColor());
					if (inventoryRepos.existsByProductAndColor(p, c)) {
						Inventory inventory = inventoryRepos.getOneByProductAndColor(p, c);
						inventory.setQuantity_item(inventory.getQuantity_item() + i.getQuantity());
						inventoryRepos.save(inventory);
					}
				}
			}
		} else {
			if (order.getStatus() == -1) {
				return ResponseEntity.ok(new MessageResponse("Bạn đã huỷ đơn hàng này rồi!"));
			} else if (order.getStatus() == 3) {
				return ResponseEntity.ok(new MessageResponse("Đơn hàng này đã được giao thành công, không thể huỷ!"));
			} else if (order.getStatus() == 2) {
				return ResponseEntity.ok(new MessageResponse("Đơn hàng này đang được giao, không thể huỷ!"));
			} else {
				order.setStatus(-1);
				List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrderId(order.getId());
				for (OrderDetail i : orderDetails) {
					Product p = productRepos.getById(i.getProduct().getId());
					Color c = colorRepos.findOneByName(i.getColor());
					if (inventoryRepos.existsByProductAndColor(p, c)) {
						Inventory inventory = inventoryRepos.getOneByProductAndColor(p, c);
						inventory.setQuantity_item(inventory.getQuantity_item() + i.getQuantity());
						inventoryRepos.save(inventory);
					}
				}
			}
		}
		payment.setStatus(-1);
		paymentRepository.save(payment);

		orderRepository.save(order);
		return ResponseEntity.ok(new MessageResponse("Huỷ đơn hàng thành công!"));
	}

	// Xác nhận thanh toán thành công
	@PutMapping("/pay-success/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> paySuccess(@RequestBody PaymentDto dto, @PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		payment.setStatus(1);
		payment.setBankName(dto.getBankName());
		payment.setTradingCode(dto.getTradingCode());
		paymentRepository.save(payment);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Đặt hàng thành công!"), HttpStatus.OK);
	}

	// đang giao hàng
	@PutMapping("/update-order-code-ghn/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> updateOrderCodeGHN(@PathVariable Long id, @RequestBody String order_code) {
		Order order = orderRepository.getById(id);
		Shipment shipment = shipmentRepos.findOneByOrderId(id);
		shipment.setOrder_code(order_code);
		shipmentRepos.save(shipment);
		orderRepository.save(order);
		return ResponseEntity.ok(new MessageResponse("SUCCESS"));
	}

	// đang giao hàng
	@PutMapping("/update-order-code/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> updateOrderCode(@PathVariable Long id) {
//		Order order = orderRepository.getById(id);
		Shipment shipment = shipmentRepos.findOneByOrderId(id);
		String code = String.valueOf(new Date().getTime());
		shipment.setOrder_code(code);
		shipmentRepos.save(shipment);
		return ResponseEntity.ok(new MessageResponse("SUCCESS"));
	}

	// update trạng thái send email
	@PutMapping("/send-email/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> updateSendEmail(@PathVariable Long id) {
		Order order = orderRepository.getById(id);
		order.setSend_status(1);
		orderRepository.save(order);
		return ResponseEntity.ok("SUCCESS");
	}

}
