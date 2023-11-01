package com.example.demo.service.impl;

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
import com.example.demo.dto.promotion.DiscountProductDto;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.promotion.ProductDiscount;
import com.example.demo.repository.ProductDiscountRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.DiscountProductService;

@Service
public class DiscountProductServiceImpl implements DiscountProductService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private ProductDiscountRepository discountRepos;

	@Autowired
	private ProductRepository productRepos;

	@Override
	public DiscountProductDto updateDiscountProduct(DiscountProductDto dto) {
		Product p = productRepos.getById(dto.getId());
		ProductDiscount discount = discountRepos.findOneByProduct(p);
		discount.setType(dto.getType());
		discount.setValue(dto.getValue());
		discountRepos.save(discount);
		return new DiscountProductDto(discount);
	}

	@Override
	public Page<DiscountProductDto> getList(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String orderBy = " ORDER BY p.id DESC";
		String groupBy = " group by i.product.id ";
		String sqlCount = "select new com.example.demo.dto.promotion.DiscountProductDto(p.id, p.name, p.mainIamge, p.price, "
				+ "sum(i.quantity_item) as quantity_item, " + " d.status, d.type, d.value)"
				+ "from Inventory as i inner join Product as p on p.id = i.product.id inner join ProductDiscount as d on d.product.id = p.id ";
		String sql = "select new com.example.demo.dto.promotion.DiscountProductDto(p.id, p.name, p.mainIamge, p.price, "
				+ "sum(i.quantity_item) as quantity_item, " + " d.status, d.type, d.value)"
				+ "from Inventory as i inner join Product as p on p.id = i.product.id inner join ProductDiscount as d on d.product.id = p.id ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			sql += " where i.display = 1 AND ( p.name LIKE :text OR p.description LIKE :text )" + groupBy + orderBy;
			sqlCount += " where i.display = 1 AND ( p.name LIKE :text OR p.description LIKE :text )" + groupBy;
		} else {
			sql += " where i.display = 1" + groupBy + orderBy;
			sqlCount += " where i.display = 1" + groupBy;
		}

		Query q = manager.createQuery(sql, DiscountProductDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<DiscountProductDto> entities = q.getResultList();
		
		for(DiscountProductDto item : entities) {
			if (item.getStatus() == 1) {
				if(item.getType() != null && item.getValue() != null) {
					if (item.getType() == 1) {
						item.setReal_price(item.getPrice() * (100 - item.getValue()) /100);
					} else {
						item.setReal_price(item.getPrice() - item.getValue());
					}
				} else {
					item.setReal_price(item.getPrice());
				}
			} else {
				item.setReal_price(item.getPrice());
			}
		}
		
		long count = (long) qCount.getResultList().size();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<DiscountProductDto> result = new PageImpl<DiscountProductDto>(entities, pageable, count);
		return result;
	}

	@Override
	public DiscountProductDto getOneByProductId(Long product_id) {
		// TODO Auto-generated method stub
		Product p = productRepos.getById(product_id);
		DiscountProductDto dto = new DiscountProductDto(p.getDiscount());
		return dto;
	}

	@Override
	public Boolean deleteDiscountProduct(Long product_id) {
		if (product_id != null) {
			Product p = productRepos.getById(product_id);
			ProductDiscount discount = discountRepos.findOneByProduct(p);
			if (discount.getStatus() == 1) {
				discount.setStatus(0);
			} else {
				discount.setStatus(1);
			}
			discountRepos.save(discount);
			return true;
		}
		return false;
	}

}
