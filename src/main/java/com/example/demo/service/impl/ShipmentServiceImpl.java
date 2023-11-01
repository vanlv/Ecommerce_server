//package com.example.demo.service.impl;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.example.demo.dto.order.ShipmentDto;
//import com.example.demo.entity.order.Shipment;
//import com.example.demo.repository.ShipmentRepository;
//import com.example.demo.service.ShipmentService;
//
//@Service
//public class ShipmentServiceImpl implements ShipmentService {
//
//	@Autowired
//	private ShipmentRepository repos;
//
//	@Override
//	public Page<ShipmentDto> getList(Integer page, Integer limit, String sortBy) {
//		Page<Shipment> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));
//
//		Page<ShipmentDto> dtos = list.map(tag -> new ShipmentDto(tag));
//
//		return dtos;
//	}
//	
//	@Override
//	public Page<ShipmentDto> getListHide(Integer page, Integer limit, String sortBy) {
//		Page<Shipment> list = repos.getListHide(PageRequest.of(page, limit, Sort.by(sortBy).descending()));
//
//		Page<ShipmentDto> dtos = list.map(tag -> new ShipmentDto(tag));
//
//		return dtos;
//	}
//	
//	@Override
//	public Page<ShipmentDto> getAll(Integer page, Integer limit, String sortBy) {
//		Page<Shipment> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));
//
//		Page<ShipmentDto> dtos = list.map(tag -> new ShipmentDto(tag));
//
//		return dtos;
//	}
//
//	@Override
//	public ShipmentDto saveOrUpdate(ShipmentDto dto) {
//		if (dto != null) {
//			Shipment entity = null;
//			if (dto.getId() != null) {
//				entity = repos.getById(dto.getId());
//				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
//			}
//			if (entity == null) {
//				entity = new Shipment();
//				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
//			}
//
//			entity.setName(dto.getName());
//			entity.setCode(dto.getCode());
//			entity.setFee(dto.getFee());
//			entity.setType(dto.getType());
//			entity.setDisplay(1);
//
//			entity = repos.save(entity);
//
//			if (entity != null) {
//				return new ShipmentDto(entity);
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Boolean delete(Long id) {
//		if (id != null) {
//			Shipment entity = repos.getById(id);
//			if(entity.getDisplay() == 1) {
//				entity.setDisplay(0);
//			} else {
//				entity.setDisplay(1);
//			}
//			entity = repos.save(entity);
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public Boolean checkCode(Long id, String code) {
//		if (code != null && StringUtils.hasText(code)) {
//			Long count = repos.checkCode(code, id);
//			return count != 0l;
//		}
//		return null;
//	}
//
//	@Override
//	public ShipmentDto getOne(Long id) {
//		// TODO Auto-generated method stub
//		Shipment shipment = repos.getById(id);
//		ShipmentDto dto = new ShipmentDto(shipment);
//		return dto;
//	}
//
//}
