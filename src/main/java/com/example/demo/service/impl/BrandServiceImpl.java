package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.product.BrandDto;
import com.example.demo.entity.product.Brand;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository repos;

	@Autowired
	private EntityManager manager;

	@Override
	public Page<BrandDto> getList(Integer page, Integer limit, String sortBy) {
		Page<Brand> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<BrandDto> dtos = list.map(tag -> new BrandDto(tag));

		return dtos;
	}

	@Override
	public Page<BrandDto> getListHide(Integer page, Integer limit, String sortBy) {
		Page<Brand> list = repos.getListHide(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<BrandDto> dtos = list.map(tag -> new BrandDto(tag));

		return dtos;
	}

	@Override
	public Page<BrandDto> getAll(Integer page, Integer limit, String sortBy) {
		Page<Brand> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<BrandDto> dtos = list.map(tag -> new BrandDto(tag));

		return dtos;
	}

	@Override
	public BrandDto saveOrUpdate(BrandDto dto) {
		if (dto != null) {
			Brand entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new Brand();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setMadeIn(dto.getMadeIn());
			entity.setDisplay(1);

			entity = repos.save(entity);

			if (entity != null) {
				return new BrandDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Brand entity = repos.getById(id);
			if (entity.getDisplay() == 1) {
				entity.setDisplay(0);
			} else {
				entity.setDisplay(1);
			}
			entity = repos.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkCode(Long id, String code) {
		if (code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public BrandDto getOne(Long id) {
		Brand brand = repos.getById(id);
		BrandDto dto = new BrandDto(brand);
		return dto;
	}

	@Override
	public BrandDto getOneByCode(String code) {
		Brand brand = repos.findOneByCode(code);
		BrandDto dto = new BrandDto(brand);
		return dto;
	}

	@Override
	public List<BrandDto> getListByCategory(String category) {
//		select * from tbl_brand as b
//		inner join tbl_product as p on p.brand_id = b.id
//		inner join tbl_category as c on c.id = p.category_id and c.code = "laptop"
//		group by p.brand_id
		String sql = "select new com.example.demo.dto.product.BrandDto(b.name, b.code)" + " from Brand as b "
				+ " INNER JOIN Product as p ON p.brand.id = b.id "
				+ " INNER JOIN Category as c ON c.id = p.category.id ";
		String whereClause = " where (1=1)";
		if (category != null && StringUtils.hasText(category)) {
			whereClause += " AND ( c.code = :category )";
		}
		sql += whereClause + " group by p.brand.id";

		Query q = manager.createQuery(sql, BrandDto.class);
		if (category != null && StringUtils.hasText(category)) {
			q.setParameter("category", category);
		}

		@SuppressWarnings("unchecked")
		List<BrandDto> entities = q.getResultList();
		return entities;
	}

}
