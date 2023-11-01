package com.example.demo.entity.paymentgateway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_vnpaypay_config")
public class VnPay {

	@Id
	@Column(name = "vnp_tmn_code")
	private String vnp_TmnCode;

	@Column(name = "vnp_hash_secret")
	private String vnp_HashSecret;

	@Column(name = "vnp_pay_url")
	private String vnp_PayUrl;

	@Column(name = "vnp_return_url")
	private String vnp_Returnurl;

	@Column(name = "vnp_api_url")
	private String vnp_apiUrl;

	public String getVnp_TmnCode() {
		return vnp_TmnCode;
	}

	public void setVnp_TmnCode(String vnp_TmnCode) {
		this.vnp_TmnCode = vnp_TmnCode;
	}

	public String getVnp_HashSecret() {
		return vnp_HashSecret;
	}

	public void setVnp_HashSecret(String vnp_HashSecret) {
		this.vnp_HashSecret = vnp_HashSecret;
	}

	public String getVnp_PayUrl() {
		return vnp_PayUrl;
	}

	public void setVnp_PayUrl(String vnp_PayUrl) {
		this.vnp_PayUrl = vnp_PayUrl;
	}

	public String getVnp_Returnurl() {
		return vnp_Returnurl;
	}

	public void setVnp_Returnurl(String vnp_Returnurl) {
		this.vnp_Returnurl = vnp_Returnurl;
	}

	public String getVnp_apiUrl() {
		return vnp_apiUrl;
	}

	public void setVnp_apiUrl(String vnp_apiUrl) {
		this.vnp_apiUrl = vnp_apiUrl;
	}

}
