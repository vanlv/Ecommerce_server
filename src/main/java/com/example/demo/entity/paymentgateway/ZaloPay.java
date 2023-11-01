package com.example.demo.entity.paymentgateway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_zalopay_config")
public class ZaloPay {
	@Id
	@Column(name = "appid")
	private String appid;

	@Column(name = "key1")
	private String key1;

	@Column(name = "key2")
	private String key2;

	@Column(name = "endpoint")
	private String endpoint;

	@Column(name = "redirecturl")
	private String redirecturl;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getRedirecturl() {
		return redirecturl;
	}

	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}

}