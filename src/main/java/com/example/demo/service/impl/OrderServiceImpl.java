package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.order.OrderDetailDto;
import com.example.demo.dto.order.OrderDetailHisDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.order.OrderHisFullDto;
import com.example.demo.dto.order.OrderHisInfoDto;
import com.example.demo.dto.order.PaymentDto;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.PaymentMethod;
import com.example.demo.entity.order.Shipment;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.Seller;
import com.example.demo.entity.user.User;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentMethodRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SellerRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentRepository paymentRepos;

	@Autowired
	private ShipmentRepository shipmentRepos;

	@Autowired
	private PaymentMethodRepository paymentMethodRepos;

	@Autowired
	private ColorRepository colorRepos;

	@Autowired
	private SellerRepository sellerRepos;

	@Override
	public Page<OrderHisDto> getAllOrder(AdvanceSearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.createdDate DESC";
		String sqlCount = "select count(entity.id) from  Order as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.order.OrderHisDto(entity) from  Order as entity where (1=1) ";

		if (dto.getStatus() == -1 || dto.getStatus() == 0 || dto.getStatus() == 1 || dto.getStatus() == 2 || dto.getStatus() == -2 || dto.getStatus() == 3) {
			whereClause += " AND ( entity.status = " + dto.getStatus() + ")";
		} else {
			whereClause += "";
		}

		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, entity.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

//		System.out.println(sql);

		Query q = manager.createQuery(sql, OrderHisDto.class);
		Query qCount = manager.createQuery(sqlCount);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<OrderHisDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (OrderHisDto o : entities) {
			Order order = orderRepository.getById(o.getId());
			Integer quantity = order.getOrderDetails().size() - 1;
			if (quantity > 0) {
				o.setDescription(
						order.getOrderDetails().get(0).getProduct().getName() + " và " + quantity + " sản phẩm khác");
			} else {
				o.setDescription(order.getOrderDetails().get(0).getProduct().getName());
			}
			if (dto != null) {
				orderDtos.add(o);
			}
		}

		Page<OrderHisDto> result = new PageImpl<OrderHisDto>(entities, pageable, count);
		return result;

	}

	@Override
	public List<OrderHisDto> getAllOrderByUser(String username) {
		User user = userRepository.findOneByUsername(username);
		List<Order> result = orderRepository.getAllByUser(user, Sort.by(Sort.Direction.DESC, "createdDate"));

		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (Order o : result) {
			OrderHisDto dto = new OrderHisDto(o);
			Integer quantity = o.getOrderDetails().size() - 1;
			if (quantity > 0) {
				dto.setDescription(o.getOrderDetails().get(0).getProduct().getName() + " - Màu "
						+ o.getOrderDetails().get(0).getColor() + " và " + quantity + " sản phẩm khác");
			} else {
				dto.setDescription(o.getOrderDetails().get(0).getProduct().getName() + " - Màu "
						+ o.getOrderDetails().get(0).getColor());
			}
			orderDtos.add(dto);
		}
		return orderDtos;
	}

	@Override
	public OrderDto createOrder(OrderDto dto) {
		if (dto != null) {
			User user = userRepository.findOneByUsername(dto.getUsername());
			Payment payment = new Payment();
			Shipment shipment = new Shipment();
//			Seller seller = new Seller();
			PaymentDto paymentDto = dto.getPayment();
			PaymentMethod payMethod = paymentMethodRepos.findOneByCode(paymentDto.getMethod_code());

			Order order = new Order();
			order.setOrderInfo(dto.getOrderInfo());
			order.setStatus(0);

			order.setSend_status(0);
			order.setTotal_price(dto.getTotal_price() - dto.getShip_fee());
			order.setShip_fee(dto.getShip_fee());
			order.setShip_type(dto.getShip_type());
			order.setTotal_item(dto.getTotal_item());
			order.setUser(user);
			if(dto.getTotal_price() > 0 && dto.getTotal_price() <= 1000000) {
				order.setDiscount_price(dto.getTotal_price() * 5 /100);
			}  else {
				order.setDiscount_price(0L);
			}

			shipment.setOrder_code(dto.getOrder_code());
			shipment.setProvince(dto.getProvince());
			shipment.setDistrict(dto.getDistrict());
			shipment.setWard(dto.getWard());
			shipment.setAddress(dto.getAddress());
			shipment.setDistrict_id(dto.getDistrict_id());
			shipment.setWard_code(dto.getWard_code());
			shipment.setPhone(dto.getPhone());
			shipment.setEmail(dto.getEmail());
			shipment.setCustomer_name(dto.getCustomer_name());

			payment.setBankName(paymentDto.getBankName());
			payment.setDatePayment(paymentDto.getDatePayment());
			payment.setMethod(payMethod);
			payment.setTradingCode(paymentDto.getTradingCode());
			switch (paymentDto.getStatus()) {
			case 0:
				payment.setStatus(0);
				break;
			case 1:
				payment.setStatus(1);
				break;
			default:
				payment.setStatus(-1);
				break;
			}

			order.setPayment(payment);
			order.setShipment(shipment);
			payment.setOrder(order);
			shipment.setOrder(order);

			order = orderRepository.save(order);
			payment = paymentRepos.save(payment);
			shipment = shipmentRepos.save(shipment);

			List<OrderDetailDto> orderDetailDtos = dto.getOrder_details();
			for (OrderDetailDto i : orderDetailDtos) {
				Product product = productRepository.getById(i.getProduct_id());
				Color color = colorRepos.findOneByName(i.getColor());
//				if (inventoryRepos.existsByProductAndColor(product, color)) {
//					Inventory inventory = inventoryRepos.getOneByProductAndColor(product, color);
//					inventory.setQuantity_item(inventory.getQuantity_item() - i.getQuantity());
//					inventoryRepos.save(inventory);
//				}
//				OrderDetail orderDetail = i.toEntity(order, product, color.getName());
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct(product);
				orderDetail.setQuantity(i.getQuantity());
				orderDetail.setColor(color.getName());
				orderDetail.setPrice(i.getPrice());
				orderDetail.setTotal_price(i.getTotal_price());
				orderDetail.setOrder(order);
				orderDetailRepository.save(orderDetail);
			}
			return new OrderDto(order);
		}
		return null;
	}

	@Override
	public Boolean checkTradingCode(String tradingCode) {
		Long count = 0L;
		if (tradingCode != null && StringUtils.hasText(tradingCode)) {
			count = paymentRepos.checkCode(tradingCode);
		}

		Boolean result = (count != 0L) ? true : false;
		return result;
	}

	@Override
	public List<OrderDetailHisDto> getDetailOrderById(Long id) {
		List<OrderDetail> details = orderDetailRepository.getAllByOrderId(id);
		List<OrderDetailHisDto> dtos = new ArrayList<>();
		for (OrderDetail detail : details) {
			OrderDetailHisDto dto = new OrderDetailHisDto(detail);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public OrderHisFullDto getDetailOrder(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Order order = orderRepository.getById(id);
			OrderHisFullDto dto = new OrderHisFullDto(order);
			OrderHisInfoDto orderInfoDto = dto.getOrder_info();
			List<OrderDetailHisDto> list = dto.getOrder_details();
			Integer total_weight = 0, total_length = 0, total_width = 0, total_height = 0;
			for (OrderDetailHisDto item : list) {
				Product p = productRepository.getById(item.getProduct_id());
				total_weight += p.getWeight();
				total_length += p.getLength();
				total_width += p.getWidth();
				total_height += p.getHeight();
			}
			orderInfoDto.setWeight(total_weight);
			orderInfoDto.setLength(total_length);
			orderInfoDto.setWidth(total_width);
			orderInfoDto.setHeight(total_height);
			return dto;
		}
		return null;
	}

	@Override
	public Integer getQuantityProductSeller(Long product_id) {
		// TODO Auto-generated method stub
		List<OrderDetail> orders = orderDetailRepository.getAllByProductId(product_id);
		Integer count_seller = 0;
		for (OrderDetail order : orders) {
			if (order.getOrder().getStatus() == 2) {
				count_seller += order.getQuantity();
			}
		}
		return count_seller;
	}

	@Override
	public Page<OrderHisDto> getAllOrderBySeller(AdvanceSearchDto dto, Long shipper_id) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.createdDate DESC";
		String sqlCount = "select count(entity.id) from  Order as entity where (1=1) and entity.seller.id = "
				+ shipper_id;
		String sql = "select new com.example.demo.dto.order.OrderHisDto(entity) from  Order as entity where (1=1) and entity.seller.id = "
				+ shipper_id;

		if (dto.getStatus() == -1 || dto.getStatus() == 0 || dto.getStatus() == 1 || dto.getStatus() == 2) {
			whereClause += " AND ( entity.status = " + dto.getStatus() + ")";
		} else {
			whereClause += "";
		}

		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, entity.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		System.out.println(sql);

		Query q = manager.createQuery(sql, OrderHisDto.class);
		Query qCount = manager.createQuery(sqlCount);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<OrderHisDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (OrderHisDto o : entities) {
			Order order = orderRepository.getById(o.getId());
			Integer quantity = order.getOrderDetails().size() - 1;
			if (quantity > 0) {
				o.setDescription(
						order.getOrderDetails().get(0).getProduct().getName() + " và " + quantity + " sản phẩm khác");
			} else {
				o.setDescription(order.getOrderDetails().get(0).getProduct().getName());
			}
			if (dto != null) {
				orderDtos.add(o);
			}
		}

		Page<OrderHisDto> result = new PageImpl<OrderHisDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<OrderHisDto> getAllOrderBySellerUsername(AdvanceSearchDto dto, String seller_username) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		Seller seller = sellerRepos.findOneByUser(userRepository.findOneByUsername(seller_username));
		String whereClause = "";
		String orderBy = " ORDER BY entity.createdDate DESC";
		String sqlCount = "select count(entity.id) from  Order as entity where (1=1) and entity.seller.id = "
				+ seller.getId();
		String sql = "select new com.example.demo.dto.order.OrderHisDto(entity) from  Order as entity where (1=1) and entity.seller.id = "
				+ seller.getId();

		if (dto.getStatus() == -1 || dto.getStatus() == 0 || dto.getStatus() == 1 || dto.getStatus() == 2) {
			whereClause += " AND ( entity.status = " + dto.getStatus() + ")";
		} else {
			whereClause += "";
		}

		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, entity.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

//		System.out.println(sql);

		Query q = manager.createQuery(sql, OrderHisDto.class);
		Query qCount = manager.createQuery(sqlCount);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<OrderHisDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (OrderHisDto o : entities) {
			Order order = orderRepository.getById(o.getId());
			Integer quantity = order.getOrderDetails().size() - 1;
			if (quantity > 0) {
				o.setDescription(
						order.getOrderDetails().get(0).getProduct().getName() + " và " + quantity + " sản phẩm khác");
			} else {
				o.setDescription(order.getOrderDetails().get(0).getProduct().getName());
			}
			if (dto != null) {
				orderDtos.add(o);
			}
		}

		Page<OrderHisDto> result = new PageImpl<OrderHisDto>(entities, pageable, count);
		return result;
	}

	@Override
	public OrderDto getOneOrderAfterPayment(Long id) {
		if (id != null) {
			Order order = orderRepository.getById(id);
			return new OrderDto(order);
		}
		return null;
	}

}
