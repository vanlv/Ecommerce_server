package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CalculateDiscount;
import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.order.OrderResponse;
import com.example.demo.dto.product.ProductListDto;
import com.example.demo.dto.report.ReportBrand;
import com.example.demo.dto.report.ReportCategory;
import com.example.demo.dto.report.ReportComment;
import com.example.demo.dto.report.ReportCustomer;
import com.example.demo.dto.report.ReportProduct;
import com.example.demo.dto.report.ReportProductInventory;
import com.example.demo.dto.report.ReportProductOrder;
import com.example.demo.dto.report.ReportSupplier;
import com.example.demo.dto.user.CommentDto;
import com.example.demo.entity.user.User;
import com.example.demo.repository.InventoryDetailRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReportService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private OrderService orderService;

	@Qualifier("productServiceImpl")
	@Autowired
	private ProductService productService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private OrderRepository orderRepos;

	@Autowired
	private InventoryDetailRepository inventoryDetailRepos;
	
	@Autowired
	private InventoryRepository inventoryRepos;

	// đếm số lượng đơn hàng theo trạng thái đơn hàng
	@GetMapping("/order/count")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<OrderResponse>> getQuantityByStatus(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "1000") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "4") Integer status) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Page<OrderHisDto> result = orderService.getAllOrder(dto);

		List<OrderResponse> list = new ArrayList<OrderResponse>();
		Integer count_complete = 0, count_shiping = 0, count_wait = 0, count_cancel = 0;
		for (OrderHisDto item : result.toList()) {
			if (item.getStatus_order() == 3) {
				count_complete += 1;
			} else if (item.getStatus_order() == 2) {
				count_shiping += 1;
			} else if (item.getStatus_order() == 0) {
				count_wait += 1;
			} else {
				count_cancel += 1;
			}
		}
		list.add(new OrderResponse("Đã hoàn thành", count_complete > 0 ? count_complete : 0));
		list.add(new OrderResponse("Đang giao hàng", count_shiping > 0 ? count_shiping : 0));
		list.add(new OrderResponse("Đang chờ xác nhận", count_wait > 0 ? count_wait : 0));
		list.add(new OrderResponse("Đã huỷ đơn", count_cancel > 0 ? count_cancel : 0));
		list.add(new OrderResponse("Tỉ lệ huỷ đơn (%)",
				(int) Math.round(CalculateDiscount.calPercent(count_cancel, result.getTotalElements()))));
		list.add(new OrderResponse("Tỉ lệ thành công (%)",
				(int) Math.round(CalculateDiscount.calPercent(count_complete, result.getTotalElements()))));
		return new ResponseEntity<List<OrderResponse>>(list, HttpStatus.OK);
	}

	// đếm số lượng đơn hàng theo trạng thái đơn hàng
	@GetMapping("/seller/count/{username}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
	public ResponseEntity<List<OrderResponse>> getQuantityByStatusSellerByAdmin(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "1000") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "4") Integer status, @PathVariable String username) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Page<OrderHisDto> result = orderService.getAllOrderBySellerUsername(dto, username);

		List<OrderResponse> list = new ArrayList<OrderResponse>();
		Integer count_complete = 0, count_shiping = 0, count_wait = 0, count_cancel = 0;
		for (OrderHisDto item : result.toList()) {
			if (item.getStatus_order() == 3) {
				count_complete += 1;
			} else if (item.getStatus_order() == 2) {
				count_shiping += 1;
			} else if (item.getStatus_order() == 0) {
				count_wait += 1;
			} else {
				count_cancel += 1;
			}
		}
		list.add(new OrderResponse("Đã hoàn thành", count_complete > 0 ? count_complete : 0));
		list.add(new OrderResponse("Đang giao hàng", count_shiping > 0 ? count_shiping : 0));
		list.add(new OrderResponse("Đang chờ xác nhận", count_wait > 0 ? count_wait : 0));
		list.add(new OrderResponse("Đã huỷ đơn", count_cancel > 0 ? count_cancel : 0));
		list.add(new OrderResponse("Tỉ lệ huỷ đơn (%)",
				(int) Math.round(CalculateDiscount.calPercent(count_cancel, result.getTotalElements()))));
		list.add(new OrderResponse("Tỉ lệ thành công (%)",
				(int) Math.round(CalculateDiscount.calPercent(count_complete, result.getTotalElements()))));
		return new ResponseEntity<List<OrderResponse>>(list, HttpStatus.OK);
	}

	@GetMapping("/seller_list/{username}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
	public ResponseEntity<Page<OrderHisDto>> getAllSellerByUsernameByAdmin(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "4") Integer status, @PathVariable String username) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Page<OrderHisDto> result = orderService.getAllOrderBySellerUsername(dto, username);
		return new ResponseEntity<Page<OrderHisDto>>(result, HttpStatus.OK);
	}

	// đếm số lượng đơn hàng theo trạng thái đơn hàng
	@GetMapping("/seller/count")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
	public ResponseEntity<List<OrderResponse>> getQuantityByStatusAndShipper(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "1000") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "4") Integer status) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Page<OrderHisDto> result = orderService.getAllOrderBySellerUsername(dto, username);

		List<OrderResponse> list = new ArrayList<OrderResponse>();
		Integer count_complete = 0, count_shiping = 0, count_wait = 0, count_cancel = 0;
		for (OrderHisDto item : result.toList()) {
			if (item.getStatus_order() == 3) {
				count_complete += 1;
			} else if (item.getStatus_order() == 2) {
				count_shiping += 1;
			} else if (item.getStatus_order() == 0) {
				count_wait += 1;
			} else {
				count_cancel += 1;
			}
		}
		list.add(new OrderResponse("Đã hoàn thành", count_complete > 0 ? count_complete : 0));
		list.add(new OrderResponse("Đang giao hàng", count_shiping > 0 ? count_shiping : 0));
		list.add(new OrderResponse("Đang chờ xác nhận", count_wait > 0 ? count_wait : 0));
		list.add(new OrderResponse("Đã huỷ đơn", count_cancel > 0 ? count_cancel : 0));
		list.add(new OrderResponse("Tỉ lệ huỷ đơn (%)",
				(int) Math.round(CalculateDiscount.calPercent(count_cancel, result.getTotalElements()))));
		list.add(new OrderResponse("Tỉ lệ thành công (%)",
				(int) Math.round(CalculateDiscount.calPercent(count_complete, result.getTotalElements()))));
		return new ResponseEntity<List<OrderResponse>>(list, HttpStatus.OK);
	}

	// thống kê doanh thu theo ngày
	@GetMapping("/revenue")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<OrderResponse>> reportRevenue(
			@RequestParam(name = "last_date", defaultValue = "1000000000") Integer last_date) {
		List<OrderResponse> list = new ArrayList<>();
		Long totalRevenue = orderRepos.totalRevenueFromOrder(last_date);
		Long totalPriceImport = inventoryDetailRepos.getTotalPriceImport(last_date);
		if (totalRevenue != null) {
			totalRevenue = orderRepos.totalRevenueFromOrder(last_date);
		} else {
			totalRevenue = 0L;
		}
		if (totalPriceImport != null) {
			totalPriceImport = inventoryDetailRepos.getTotalPriceImport(last_date);
		} else {
			totalPriceImport = 0L;
		}
		list.add(new OrderResponse("Doanh thu", null, totalRevenue));
		list.add(new OrderResponse("Tổng tiền nhập hàng", null, totalPriceImport));
		return new ResponseEntity<List<OrderResponse>>(list, HttpStatus.OK);
	}

	// lấy danh sách đặt hàng của khách theo user id
	@GetMapping("/customer_all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<OrderHisDto>> getAllByUserToReport(@RequestParam(name = "user") Long user_id) {
		User u = userRepos.getById(user_id);
		List<OrderHisDto> result = reportService.getAllOrderByUser(u.getUsername());
		return new ResponseEntity<List<OrderHisDto>>(result, HttpStatus.OK);
	}

	// đếm số lượng các loại sản phẩm (đang bán, đã ẩn, đã bán, đã hết hàng)
	@GetMapping(value = "/product/count")
	public ResponseEntity<List<OrderResponse>> countByType(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "1000") int limit,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "sku", defaultValue = "") String sku,
			@RequestParam(name = "category", defaultValue = "") String category,
			@RequestParam(name = "brand", defaultValue = "") String brand,
			@RequestParam(name = "supplier", defaultValue = "") String supplier,
			@RequestParam(name = "display", defaultValue = "2") Integer display) {
		AdvanceSearchDto dto = new AdvanceSearchDto(page, limit, name, sku, display, brand, supplier, category);
		Page<ProductListDto> result = productService.getAllProduct(dto);
		List<OrderResponse> list = new ArrayList<OrderResponse>();
		Integer count_onsale = 0, count_hide = 0, count_inventory = inventoryRepos.getTotalItemOutOfStock();
		Integer sum_sold = 0;
		for (ProductListDto item : result.toList()) {
			sum_sold += item.getSeller_count();
		}
		for (ProductListDto item : result.toList()) {
			if (item.getDisplay() == 1) {
				count_onsale += 1;
			} else if (item.getDisplay() == 0) {
				count_hide += 1;
			}
		}
		list.add(new OrderResponse("Đang bán", count_onsale));
		list.add(new OrderResponse("Đã ẩn", count_hide));
		list.add(new OrderResponse("Hết hàng", count_inventory));
		list.add(new OrderResponse("Đã bán", sum_sold));
		return new ResponseEntity<List<OrderResponse>>(list, HttpStatus.OK);
	}

	// thống kê sản phẩm theo đơn hàng (sản phẩm bán chạy)
	@GetMapping("/product")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProductOrder>> reportByProduct(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) { // thống kê theo sản phẩm bán
																						// chạy nhất
		// & doanh thu
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportProductOrder> result = reportService.reportProduct(dto);
		return new ResponseEntity<Page<ReportProductOrder>>(result, HttpStatus.OK);
	}

	// lấy danh sách sản phẩm ở trong các đơn hàng
	@GetMapping("/product-in-order")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProduct>> reportProductInHistoryOrder(
			@RequestParam(name = "product") Long product_id,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) { // thống kê các lần bán hàng của
																						// sản
		// phẩm
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportProduct> result = reportService.reportProductByHistoryOrder(product_id, dto);
		return new ResponseEntity<Page<ReportProduct>>(result, HttpStatus.OK);
	}

	// lấy top danh sách khách hàng mua nhiều
	@GetMapping("/customer")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportCustomer>> reportByCustomer(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) { // thống kê theo khách hàng mua
																						// nhiều
		// nhất & doanh thu
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportCustomer> result = reportService.reportCustomer(dto);
		return new ResponseEntity<Page<ReportCustomer>>(result, HttpStatus.OK);
	}

	// thống kê danh sách sản phẩm tồn kho
	@GetMapping("/inventory/out-stock")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProductInventory>> reportProductOutOfStock(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		Page<ReportProductInventory> result = reportService.reportProductOutOfStock(dto);
		return new ResponseEntity<Page<ReportProductInventory>>(result, HttpStatus.OK);
	}

	// thống kê bình luận
	@GetMapping("/comment")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportComment>> reportComment(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "1000") Integer limit) {
		SearchDto dto = new SearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		Page<CommentDto> result = commentService.getAllComments(dto);
		List<CommentDto> resultList = result.toList();
		List<ReportComment> list = new ArrayList<>();
		Integer count_5star = 0, count_4star = 0, count_3star = 0, count_2star = 0, count_1star = 0;
		for (CommentDto item : resultList) {
			if (item.getRating() == 5) {
				count_5star += 1;
			} else if (item.getRating() == 4) {
				count_4star += 1;
			} else if (item.getRating() == 3) {
				count_3star += 1;
			} else if (item.getRating() == 2) {
				count_2star += 1;
			} else {
				count_1star += 1;
			}
		}
		Long total = result.getTotalElements();

		list.add(new ReportComment(count_5star, CalculateDiscount.calPercent(count_5star, total), "5"));
		list.add(new ReportComment(count_4star, CalculateDiscount.calPercent(count_4star, total), "4"));
		list.add(new ReportComment(count_3star, CalculateDiscount.calPercent(count_3star, total), "3"));
		list.add(new ReportComment(count_2star, CalculateDiscount.calPercent(count_2star, total), "2"));
		list.add(new ReportComment(count_1star, CalculateDiscount.calPercent(count_1star, total), "1"));

		return new ResponseEntity<Page<ReportComment>>(new PageImpl<>(list), HttpStatus.OK);
	}

	// thống kê theo danh mục
	@GetMapping("/category")
//		@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportCategory>> reportByCategory(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportCategory> result = reportService.reportCategory(dto);
		return new ResponseEntity<Page<ReportCategory>>(result, HttpStatus.OK);
	}

	// thống kê chi tiết theo danh mục
	@GetMapping("/category/detail/{category_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProductOrder>> reportDetailProductByCategory(
			@PathVariable(name = "category_id") Long category_id,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportProductOrder> result = reportService.reportDetailProductByCategory(category_id, dto);
		return new ResponseEntity<Page<ReportProductOrder>>(result, HttpStatus.OK);
	}

	// thống kê theo thương thiệu
	@GetMapping("/brand")
//		@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportBrand>> reportByBrand(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportBrand> result = reportService.reportBrand(dto);
		return new ResponseEntity<Page<ReportBrand>>(result, HttpStatus.OK);
	}

	// thống kê chi tiết theo thương hiệu
	@GetMapping("/brand/detail/{brand_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProductOrder>> reportDetailProductByBrand(
			@PathVariable(name = "brand_id") Long brand_id,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportProductOrder> result = reportService.reportDetailProductByBrand(brand_id, dto);
		return new ResponseEntity<Page<ReportProductOrder>>(result, HttpStatus.OK);
	}

	// thống kê theo nhà cung câps
	@GetMapping("/supplier")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportSupplier>> reportBySupplier(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportSupplier> result = reportService.reportSupplier(dto);
		return new ResponseEntity<Page<ReportSupplier>>(result, HttpStatus.OK);
	}

	// thống kê chi tiết theo ncc
	@GetMapping("/supplier/detail/{supplier_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProductOrder>> reportDetailProductBySupplier(
			@PathVariable(name = "supplier_id") Long supplier_id,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportProductOrder> result = reportService.reportDetailProductBySupplier(supplier_id, dto);
		return new ResponseEntity<Page<ReportProductOrder>>(result, HttpStatus.OK);
	}

}
