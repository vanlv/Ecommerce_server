package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
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

@Service
public interface ReportService {
public List<OrderHisDto> getAllOrderByUser(String username);
	
	// thống kê sản phẩm bản chạy theo khoang thời gian
	public Page<ReportProductOrder> reportProduct(AdvanceSearchDto dto);
	
	// thông ke khách hàng đã mua sản phẩm theo khoảng thời gian
	public Page<ReportCustomer> reportCustomer(AdvanceSearchDto dto);
	
	// thống kê sản phẩn đang nằm trong đơn đặt hàng nào đó
	public Page<ReportProduct> reportProductByHistoryOrder(Long product_id, AdvanceSearchDto dto);

	// thống kê sản phẩm hết hàng
	public Page<ReportProductInventory> reportProductOutOfStock(AdvanceSearchDto dto);
	
	// thống kê sản phẩm theo nhà cung cấp
	public Page<ReportSupplier> reportSupplier(AdvanceSearchDto dto);
	
	// thống kê chi tiết các sản phẩm theo nhà cung cấp
	public Page<ReportProductOrder> reportDetailProductBySupplier(Long supplier_id, AdvanceSearchDto dto);
	
	// thống kê sản phẩm theo danh mục
	public Page<ReportCategory> reportCategory(AdvanceSearchDto dto);
	
	// thống kê chi tiết các sản phẩm theo danh mục
	public Page<ReportProductOrder> reportDetailProductByCategory(Long category_id, AdvanceSearchDto dto);
	
	// thống kê sản phẩm theo thuong hieu
	public Page<ReportBrand> reportBrand(AdvanceSearchDto dto);
	
	// thống kê chi tiết các sản phẩm theo thương hiệu
	public Page<ReportProductOrder> reportDetailProductByBrand(Long brand_id, AdvanceSearchDto dto);
	
	// Thống kê sản phẩm theo nhân viên bán hàng
	public Page<ReportCustomer> reportSellerEmployee(AdvanceSearchDto dto);
	
	
}
