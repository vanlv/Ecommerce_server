package com.example.demo.config;

public class MomoConfig {
	public static String PARTNER_CODE = "MOMOYTHI20210518";
	public static String ACCESS_KEY = "pgmjAretwF7LguLT";
	public static String SECRET_KEY = "MvQRof5M7xqZ7r2WgWJNEo616ElPsktv";
	public static String PAY_QUERY_STATUS_URL = "https://test-payment.momo.vn/pay/query-status";
	public static String PAY_CONFIRM_URL = "https://test-payment.momo.vn/pay/confirm";
	public static String RETURN_URL = "http://localhost:8080/api/momo/test";
	public static String NOTIFY_URL = "http://localhost:3000/success/payment";
	public static String IPN_URL = "https://fcf6-123-24-233-164.ngrok.io";
	public static String CREATE_ORDER_URL = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
	public static String REDIRECT_URL = "http://localhost:3000/success/payment";
}
