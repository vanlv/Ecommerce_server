package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.Date;

public class AbstractDTO<T> {
	private Long id;
//	private String createdBy;
	private String createdDate = new Timestamp(new Date().getTime()).toString();
//	private String modifiedBy;
//	private Date modifiedDate;
//	private List<T> listResult = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}

	public String getCreatedDate() {
		return createdDate;
	}
//
	public void setCreatedDate(String createdDate) {
		this.createdDate = new Timestamp(new Date().getTime()).toString();
	}
//
//	public String getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(String modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	public Date getModifiedDate() {
//		return modifiedDate;
//	}
//
//	public void setModifiedDate(Date modifiedDate) {
//		this.modifiedDate = modifiedDate;
//	}

//	public List<T> getListResult() {
//		return listResult;
//	}
//
//	public void setListResult(List<T> listResult) {
//		this.listResult = listResult;
//	}
}
