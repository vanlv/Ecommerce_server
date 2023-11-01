package com.example.demo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.report.ReportBrand;
import com.example.demo.dto.report.ReportCategory;
import com.example.demo.dto.report.ReportCustomer;
import com.example.demo.dto.report.ReportProduct;
import com.example.demo.dto.report.ReportProductInventory;
import com.example.demo.dto.report.ReportProductOrder;
import com.example.demo.dto.report.ReportSupplier;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.user.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<OrderHisDto> getAllOrderByUser(String username) {
		User user = userRepository.findOneByUsername(username);
		List<Order> result = orderRepository.getAllByUser(user, Sort.by(Sort.Direction.DESC, "createdDate"));

		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (Order o : result) {
			OrderHisDto dto = new OrderHisDto(o);
			Integer quantity = o.getOrderDetails().size() - 1;
			if (quantity > 0) {
				dto.setDescription(
						o.getOrderDetails().get(0).getProduct().getName() + " và " + quantity + " sản phẩm khác");
			} else {
				dto.setDescription(o.getOrderDetails().get(0).getProduct().getName());
			}
			orderDtos.add(dto);
		}
		return orderDtos;
	}

	@Override
	public Page<ReportCustomer> reportCustomer(AdvanceSearchDto dto) {

//		SELECT u.fullname, u.phone, o.id as order_id, SUM(o.total_item) as Quantity, sum(o.total_price) as total_price 
//		FROM tbl_order as o
//		inner join tbl_user as u on  o.user_id = u.id
//		and o.status = 2
//		group by o.user_id

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY o.user.id ORDER BY quantity_buy DESC";
		String sqlCount = "select count(*) from Order as o " + "INNER JOIN User u ON u.id = o.user.id and o.status = 3 "
				+ "GROUP BY o.user.id";
		String sql = "select new com.example.demo.dto.report.ReportCustomer(u.id as id, u.fullname as customer_name, "
				+ "u.phone as customer_phone," + " o.id as order_id, " + " SUM(o.total_item) as quantity_buy, "
				+ " SUM(o.total_price) as total_price)" + " from Order as o "
				+ " INNER JOIN User u ON u.id = o.user.id " + " and o.status = 3 ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportCustomer.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ReportCustomer> entities = q.getResultList();
		long count = (long) qCount.getResultList().size();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportCustomer> result = new PageImpl<ReportCustomer>(entities, pageable, count);
		return result;
	}

	// thống kê sản phẩm bán chạy nhất
	@Override
	public Page<ReportProductOrder> reportProduct(AdvanceSearchDto dto) {
//			SELECT product_id, sum(amount) As MostSold 
//			FROM tbl_order_detail, tbl_order
//			where tbl_order.status=2
//			AND tbl_order_detail.order_id = tbl_order.id
//			Group By product_id
//			ORDER BY MostSold DESC limit 5

//			SELECT p.name as product_name, o.id as order_id, SUM(s.amount) as Quantity 
//			FROM tbl_order_detail s
//			INNER JOIN tbl_product p ON s.product_id = p.id 
//			INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//			GROUP BY s.product_id 
//			ORDER BY Quantity DESC limit 5

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY s.product.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id " + "GROUP BY s.product.id ";
		String sql = "select new com.example.demo.dto.report.ReportProductOrder(p.id as id, p.name as product_name, "
				+ "p.sku as product_sku, p.category.name as product_category, p.brand.name as product_brand, "
				+ " o.id as order_id, " + " SUM(s.quantity) as quantity_sold, "
				+ " SUM(s.quantity * s.price) as total_price)" + " from OrderDetail as s "
				+ " INNER JOIN Product p ON s.product.id = p.id"
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportProductOrder.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportProductOrder> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportProductOrder> result = new PageImpl<ReportProductOrder>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportProduct> reportProductByHistoryOrder(Long product_id, AdvanceSearchDto dto) {
//		SELECT p.name as product_name, o.id as order_id, o.create_time,
//		s.amount, s.total_price
//		FROM tbl_order_detail s
//		INNER JOIN tbl_product p ON s.product_id = p.id  and p.id=1
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = "";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Order o ON o.id = s.order.id "
				+ "INNER JOIN Product p ON s.product.id = p.id";
		String sql = "select new com.example.demo.dto.report.ReportProduct(o.id as id, o.status as status, s.quantity as quantity, s.total_price as total_price,"
				+ "o.createdDate as create_time) " + " from OrderDetail as s "
				+ " INNER JOIN Order o ON o.id = s.order.id" + " INNER JOIN Product p ON s.product.id = p.id";
		whereClause += " where p.id = " + product_id;
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause;

		Query q = manager.createQuery(sql, ReportProduct.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ReportProduct> entities = q.getResultList();
		long count = (long) qCount.getResultList().size();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		for (ReportProduct item : entities) {
			if (item.getStatus() == -1) {
				item.setStatus_order_name("Đã huỷ đơn");
			} else if (item.getStatus() == -2) {
				item.setStatus_order_name("Khách trả lại hàng");
			} else if (item.getStatus() == 0) {
				item.setStatus_order_name("Đang chờ xác nhận");
			} else if (item.getStatus() == 1) {
				item.setStatus_order_name("Đang chuẩn bị hàng");
			} else if (item.getStatus() == 2) {
				item.setStatus_order_name("Đang giao hàng");
			} else if (item.getStatus() == 3) {
				item.setStatus_order_name("Đã hoàn thành");
			}
			try {
				item.setCreate_time(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date(
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(item.getCreate_time()).getTime())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Page<ReportProduct> result = new PageImpl<ReportProduct>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportProductInventory> reportProductOutOfStock(AdvanceSearchDto dto) {
//		select p.name, i.total_item as total_item, (i.total_import - i.total_item) as total_sell 
//		from tbl_product as p
//		inner join tbl_inventory as i on i.product_id = p.id
//		where i.total_item = 0
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = "";
		String orderBy = " ORDER BY total_sell DESC ";
		String sqlCount = "select count(*) from Product as p "
				+ "INNER JOIN Inventory as i ON i.product.id = p.id and i.quantity_item =0 ";
		String sql = "select new com.example.demo.dto.report.ReportProductInventory(p.id as id, p.name as product_name, p.sku as product_sku, p.category.name as product_category, p.brand.name as product_brand, i.quantity_item as quantity,"
				+ "(i.total_import_item - i.quantity_item) as total_sell ) " + " from Product as p "
				+ " INNER JOIN Inventory as i ON i.product.id = p.id and i.quantity_item =0 ";
		sql += whereClause + orderBy;

		Query q = manager.createQuery(sql, ReportProductInventory.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ReportProductInventory> entities = q.getResultList();
		long count = (long) qCount.getResultList().size();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportProductInventory> result = new PageImpl<ReportProductInventory>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportSupplier> reportSupplier(AdvanceSearchDto dto) {
//		SELECT c.name, SUM(o.total_item) as Quantity, sum(o.total_price) as total_price 
//		FROM tbl_order_detail s
//		INNER JOIN tbl_product p ON s.product_id = p.id
//		inner join tbl_supplier c on c.id = p.supplier_id
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//		GROUP BY p.supplier_id
//		ORDER BY Quantity DESC limit 5
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY p.supplier.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id " + "GROUP BY p.supplier.id ";

		String sql = "select new com.example.demo.dto.report.ReportSupplier(c.id, c.name, c.code, "
				+ " SUM(o.total_item) as quantity_sold, " + " SUM(o.total_price) as total_price)"
				+ " FROM OrderDetail as s " + " INNER JOIN Product p ON s.product.id = p.id"
				+ " inner join Supplier c on c.id = p.supplier.id"
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportSupplier.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportSupplier> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportSupplier> result = new PageImpl<ReportSupplier>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportCategory> reportCategory(AdvanceSearchDto dto) {
//		SELECT c.name, SUM(o.total_item) as Quantity, sum(o.total_price) as total_price 
//		FROM tbl_order_detail s
//		INNER JOIN tbl_product p ON s.product_id = p.id
//		inner join tbl_category c on c.id = p.category_id
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//		GROUP BY p.category_id
//		ORDER BY Quantity DESC limit 5
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY p.category.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id " + "GROUP BY p.category.id ";

		String sql = "select new com.example.demo.dto.report.ReportCategory(c.id, c.name, c.code, "
				+ " SUM(o.total_item) as quantity_sold, " + " SUM(o.total_price) as total_price)"
				+ " FROM OrderDetail as s " + " INNER JOIN Product p ON s.product.id = p.id"
				+ " INNER JOIN Category c on c.id = p.category.id"
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportCategory.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportCategory> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportCategory> result = new PageImpl<ReportCategory>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportBrand> reportBrand(AdvanceSearchDto dto) {
//		SELECT b.name, SUM(o.total_item) as Quantity, sum(o.total_price) as total_price 
//		FROM tbl_order_detail s
//		INNER JOIN tbl_product p ON s.product_id = p.id
//		inner join tbl_brand b on b.id = p.brand_id
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//		GROUP BY p.brand_id
//		ORDER BY Quantity DESC limit 5
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY p.brand.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id " + "GROUP BY p.brand.id ";

		String sql = "select new com.example.demo.dto.report.ReportBrand(b.id, b.name, b.code, "
				+ " SUM(o.total_item) as quantity_sold, " + " SUM(o.total_price) as total_price)"
				+ " FROM OrderDetail as s " + " INNER JOIN Product p ON s.product.id = p.id"
				+ " inner join Brand b on b.id = p.brand.id"
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportBrand.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportBrand> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportBrand> result = new PageImpl<ReportBrand>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportCustomer> reportSellerEmployee(AdvanceSearchDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ReportProductOrder> reportDetailProductBySupplier(Long supplier_id, AdvanceSearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY s.product.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id " + "GROUP BY s.product.id ";
		String sql = "select new com.example.demo.dto.report.ReportProductOrder(p.id, p.name, p.sku, p.category.name, p.brand.name, "
				+ " SUM(s.quantity) as quantity_sold, "
				+ " SUM(s.quantity * s.price) as total_price)" + " from OrderDetail as s "
				+ " INNER JOIN Product p ON s.product.id = p.id"
				+ " inner join Supplier b on b.id = p.supplier.id and p.supplier.id = " + supplier_id
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportProductOrder.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportProductOrder> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportProductOrder> result = new PageImpl<ReportProductOrder>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportProductOrder> reportDetailProductByCategory(Long category_id, AdvanceSearchDto dto) {
//		SELECT p.name as product_name, o.id as order_id, sum(s.amount) as amount, sum(s.total_price) as total_price
//		FROM tbl_order_detail s
//		INNER JOIN tbl_product p ON s.product_id = p.id  and p.category_id = 5
//		inner join tbl_category c on c.id = p.category_id
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//		group by s.product_id

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY s.product.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id " + "GROUP BY s.product.id ";
		String sql = "select new com.example.demo.dto.report.ReportProductOrder(p.id, p.name, p.sku, p.category.name, p.brand.name, "
				+ " SUM(s.quantity) as quantity_sold, "
				+ " SUM(s.quantity * s.price) as total_price)" + " from OrderDetail as s "
				+ " INNER JOIN Product p ON s.product.id = p.id"
				+ " inner join Category c on c.id = p.category.id and p.category.id = " + category_id
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportProductOrder.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportProductOrder> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportProductOrder> result = new PageImpl<ReportProductOrder>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportProductOrder> reportDetailProductByBrand(Long brand_id, AdvanceSearchDto dto) {
//		SELECT p.name as product_name, o.id as order_id, sum(s.amount) as amount, sum(s.total_price) as total_price
//		FROM tbl_order_detail s
//		INNER JOIN tbl_product p ON s.product_id = p.id  and p.brand_id = 21
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//		group by s.product_id
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = " where (1=1) ";
		String groupOrderClause = " GROUP BY s.product.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id " + "GROUP BY s.product.id ";
		String sql = "select new com.example.demo.dto.report.ReportProductOrder(p.id, p.name, p.sku, p.category.name, p.brand.name, "
				+ " SUM(s.quantity) as quantity_sold, "
				+ " SUM(s.quantity * s.price) as total_price)" + " from OrderDetail as s "
				+ " INNER JOIN Product p ON s.product.id = p.id"
				+ " inner join Brand b on b.id = p.brand.id and p.brand.id = " + brand_id
				+ " INNER JOIN Order o ON o.status = 3 and o.id = s.order.id ";
		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}
		sql += whereClause + groupOrderClause;

		Query q = manager.createQuery(sql, ReportProductOrder.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportProductOrder> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportProductOrder> result = new PageImpl<ReportProductOrder>(entities, pageable, count);
		return result;
	}

}
