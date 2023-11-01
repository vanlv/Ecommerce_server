package com.example.demo.dto.mail;

public class MailResponse {
	private String message;
	private Boolean status;

	public MailResponse() {
		super();
	}

	public MailResponse(String message, Boolean status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
