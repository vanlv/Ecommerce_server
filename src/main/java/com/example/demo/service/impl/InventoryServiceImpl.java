package com.example.demo.service.impl;

import java.sql.Timestamp;
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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.inventory.InventoryDetailDto;
import com.example.demo.dto.inventory.InventoryDto;
import com.example.demo.dto.inventory.InventoryDtoRes;
import com.example.demo.dto.product.ColorDto;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.inventory.InventoryDetail;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Product;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.InventoryDetailRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Autowired
	private InventoryDetailRepository inventoryDetailRepos;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private ColorRepository colorRepos;

	@Override
	public Page<InventoryDtoRes> getList(SearchDto dto) {

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

//		SELECT p.name as product_name, c.name as category_name, sum(i.total_import) as total_import, sum(i.total_item) as total_item, total_import - total_item as total_sell
//		FROM tbl_inventory as i
//		inner join tbl_product as p on p.id = 20 and p.id = i.product_id
//		inner join tbl_category as c on c.id = p.category_id
//		group by product_id

		String orderBy = " ORDER BY p.id DESC";
		String groupBy = " group by i.product.id ";
		String sqlCount = "select new com.example.demo.dto.inventory.InventoryDtoRes(p.id as productId, "
				+ "p.name as product_name, " + "p.mainIamge as productMainImage, " + "c.name as category_name, "
				+ "sum(i.total_import_item) as total_import_item, " + "sum(i.quantity_item) as quantity_item ) "
				+ "from Inventory as i inner join Product as p on p.id = i.product.id inner join Category as c on c.id = p.category.id ";
		String sql = "select new com.example.demo.dto.inventory.InventoryDtoRes(p.id as productId, "
				+ "p.name as product_name, " + "p.mainIamge as productMainImage, " + "c.name as category_name, "
				+ "sum(i.total_import_item) as total_import_item, " + "sum(i.quantity_item) as quantity_item ) "
				+ "from Inventory as i inner join Product as p on p.id = i.product.id inner join Category as c on c.id = p.category.id ";
		if (dto.getCategory() != null) {
			sql += " and c.code = '" + dto.getCategory() + "'";
			sqlCount += " and c.code = '" + dto.getCategory() + "'";
		} else {
			sql += "";
			sqlCount += "";
		};
		
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			sql += " where i.display = 1 AND ( p.name LIKE :text OR p.description LIKE :text )" + groupBy + orderBy;
			sqlCount += " where i.display = 1 AND ( p.name LIKE :text OR p.description LIKE :text )" + groupBy;
		} else {
			sql += " where i.display = 1" + groupBy + orderBy;
			sqlCount += " where i.display = 1" + groupBy;
		}

		

		Query q = manager.createQuery(sql, InventoryDtoRes.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<InventoryDtoRes> entities = q.getResultList();

		for (InventoryDtoRes item : entities) {
			item.setSold(item.getTotal_import_item() - item.getQuantity_item());
		}

		long count = (long) qCount.getResultList().size();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<InventoryDtoRes> result = new PageImpl<InventoryDtoRes>(entities, pageable, count);
		return result;
	}

	@Override
	public InventoryDto importOrUpdate(InventoryDto dto) {

		if (dto != null) {
			Inventory inventory = null;
			InventoryDetail inventoryDetail = null;

			Product product = productRepos.getById(dto.getProductId());
			Color color = colorRepos.findOneByName(dto.getColor());

			if (inventoryRepos.existsByProductAndColor(product, color)) {
				inventory = inventoryRepos.getById(dto.getId());
				inventory.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
				List<InventoryDetailDto> inventoryDetailDtos = dto.getInventory_details();
				List<InventoryDetail> inventoryDetails = new ArrayList<>();
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					inventoryDetail = new InventoryDetail();
					inventoryDetail.setImportDate(new Timestamp(new Date().getTime()).toString());
					inventoryDetail.setImport_quantity(detailDto.getImport_quantity());
					inventoryDetail.setNote(detailDto.getNote());
					inventoryDetail.setOriginal_price(detailDto.getOriginal_price());
					inventoryDetails.add(inventoryDetail);
				}

				Integer totalImport = 0;
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					totalImport += detailDto.getImport_quantity();
				}

				inventory.setTotal_import_item(totalImport + inventory.getTotal_import_item());
				inventory.setQuantity_item(inventory.getQuantity_item() + totalImport);
				inventory.setDisplay(1);
				inventory.setColor(color);
				inventory.setInventory_details(inventoryDetails);
				for (InventoryDetail detail : inventoryDetails) {
					detail.setInventory(inventory);
				}
				inventoryDetail = inventoryDetailRepos.save(inventoryDetail);
			} else {
				inventory = new Inventory();
				inventory.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				inventory.setProduct(product);
				List<InventoryDetailDto> inventoryDetailDtos = dto.getInventory_details();
				List<InventoryDetail> inventoryDetails = new ArrayList<>();
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					inventoryDetail = new InventoryDetail();
					inventoryDetail.setImportDate(new Timestamp(new Date().getTime()).toString());
					inventoryDetail.setImport_quantity(detailDto.getImport_quantity());
					inventoryDetail.setNote(detailDto.getNote());
					inventoryDetail.setOriginal_price(detailDto.getOriginal_price());
					inventoryDetails.add(inventoryDetail);
				}

				Integer totalImport = 0;
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					totalImport += detailDto.getImport_quantity();
				}

				inventory.setTotal_import_item(totalImport);
				inventory.setQuantity_item(totalImport);
				inventory.setDisplay(1);
				inventory.setColor(color);
				inventory.setInventory_details(inventoryDetails);
				for (InventoryDetail detail : inventoryDetails) {
					detail.setInventory(inventory);
				}
				inventoryDetail = inventoryDetailRepos.save(inventoryDetail);
			}
			inventory = inventoryRepos.save(inventory);

			return new InventoryDto(inventory);

		}

		return null;
	}

	@Override
	public List<InventoryDetailDto> getDetailInventoryById(Long id) {

		List<InventoryDetail> details = inventoryDetailRepos.getAllByInventoryId(id);

		List<InventoryDetailDto> dtos = new ArrayList<>();

		for (InventoryDetail detail : details) {
			InventoryDetailDto dto = new InventoryDetailDto(detail);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public List<InventoryDtoRes> getListByProduct(Long productId) {
		// TODO Auto-generated method stub
		List<Inventory> list = inventoryRepos.getAllByProductId(productId);
		List<InventoryDtoRes> dtos = new ArrayList<InventoryDtoRes>();
		for (Inventory item : list) {
			InventoryDtoRes dto = new InventoryDtoRes(item);
			dto.setCategory_name(item.getProduct().getCategory().getName());
			dto.setId(item.getId());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Boolean calcelSellProduct(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Inventory i = inventoryRepos.getById(id);
			if (i.getDisplay() == 1) {
				i.setDisplay(0);
			} else {
				i.setDisplay(1);
			}
			inventoryRepos.save(i);
			return true;
		}

		return false;
	}

	@Override
	public List<ColorDto> getAllColorNotExsistProduct(Long productId) {

//		SELECT T1.name
//		FROM
//		   tbl_product_color T1
//		   LEFT OUTER JOIN
//		   tbl_inventory T2 ON T1.id = T2.color_id and T2.product_id = 47
//		where T2.product_id is null

		// TODO Auto-generated method stub

		String sql = "select new com.example.demo.dto.product.ColorDto(c.name) "
				+ "from Color as c LEFT OUTER JOIN Inventory as i on c.id = i.color.id and i.product.id = " + productId
				+ " where i.product.id is null";

		Query q = manager.createQuery(sql, ColorDto.class);

		@SuppressWarnings("unchecked")
		List<ColorDto> entities = q.getResultList();
		return entities;
	}

}
