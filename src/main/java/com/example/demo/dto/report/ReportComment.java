package com.example.demo.dto.report;

public class ReportComment {
	private Integer rating;
	private Double percent;
	private String message;

	public ReportComment() {
		super();
	}

	public ReportComment(Integer rating, Double percent, String message) {
		super();
		this.rating = rating;
		this.percent = percent;
		this.message = message;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
