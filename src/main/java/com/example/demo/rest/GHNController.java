package com.example.demo.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ship.Item;
import com.example.demo.dto.ship.OrderCodes;
import com.example.demo.dto.ship.ShipCancelOrderResponse;
import com.example.demo.dto.ship.ShipFeeResponse;
import com.example.demo.dto.ship.ShipInfoOrderResponse;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.repository.OrderRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/services/ship/ghn")
public class GHNController {

    public static final String TOKEN = "d3d4b907-2781-11ec-b8c6-fade198b4859";
    public static final Integer SHOP_ID = 82605;
    public static final String API_ENDPOINT = "https://dev-online-gateway.ghn.vn/shiip/public-api";
    public static final String CREATE_ORDER_URL = API_ENDPOINT + "/v2/shipping-order/create";
    public static final String CALCULATE_TIME_SHIP_URL = API_ENDPOINT + "/v2/shipping-order/leadtime";
    public static final String CALCULATE_SHIP_FEE_URL = API_ENDPOINT + "/v2/shipping-order/fee";
    public static final String GET_TOKEN_URL = API_ENDPOINT + "/v2/a5/gen-token";
    public static final String PRINT_INVOICE_URL = API_ENDPOINT + "/printA5";
    public static final String GET_ORDER_DETAIL_URL = API_ENDPOINT + "/v2/shipping-order/detail";
    public static final String GET_RETURN_ORDER_INFO_URL = API_ENDPOINT + "/v2/shipping-order/preview";
    public static final String CANCEL_ORDER_URL = API_ENDPOINT + "/v2/switch-status/cancel";

    @Autowired
    private OrderRepository orderRepos;

    // lên đơn hàng
    // lên đơn hàng
    @PostMapping(value = "/create-order/{id}")
    public Map<String, Object> createOrder(@PathVariable(name = "id") Long order_id, @RequestParam Integer weight,
                                           @RequestParam Integer length, @RequestParam Integer width, @RequestParam Integer height)
            throws ClientProtocolException, IOException {

        Order o = orderRepos.getById(order_id);
        JSONObject json = new JSONObject();
        if (o.getPayment().getStatus() == 1) {
            json.put("payment_type_id", 1);
            json.put("cod_amount", 0);
        } else {
            json.put("payment_type_id", 2);
            json.put("cod_amount", o.getTotal_price());
        }
        json.put("note", "");
        json.put("required_note", "KHONGCHOXEMHANG");
        json.put("client_order_code", o.getId().toString());
        json.put("to_name", o.getShipment().getCustomer_name().toString());
        json.put("to_phone", o.getShipment().getPhone().toString());
        String address = o.getShipment().getAddress() + ", " + o.getShipment().getWard() + ", " + o.getShipment().getDistrict() + ", " + o.getShipment().getProvince();
        json.put("to_address", address);
        json.put("to_ward_code", o.getShipment().getWard_code().toString());
        json.put("to_district_id", o.getShipment().getDistrict_id().toString());
        json.put("weight", weight);
        json.put("length", length);
        json.put("width", width);
        json.put("height", height);
        json.put("service_id", 53320);
        json.put("content", "Tao don hang");
        List<Item> list = new ArrayList<>();
        for (OrderDetail detail : o.getOrderDetails()) {
            list.add(new Item(detail.getProduct().getName().toString(), detail.getProduct().getId().toString(),
                    detail.getQuantity(), detail.getPrice()));
        }
        JSONArray jsonArray = new JSONArray(list);
        json.put("items", jsonArray);

        System.out.println(json);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(CREATE_ORDER_URL);
        StringEntity stringEntity = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        post.setHeader("content-type", "application/json");
        post.setHeader("token", TOKEN);
        post.setHeader("shopid", SHOP_ID.toString());
        post.setEntity(stringEntity);
        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }
        JSONObject result = new JSONObject(resultJsonStr.toString());
        JSONObject data = (JSONObject) result.get("data");
        JSONObject fee = (JSONObject) data.get("fee");
        String order_code = data.get("order_code").toString();
        String expected_delivery_time = data.get("expected_delivery_time").toString();
        Long total_fee = Long.parseLong(data.get("total_fee").toString());
        Integer code = Integer.parseInt(result.get("code").toString());
        String message_display = result.get("message_display").toString();
        String message = result.get("message").toString();
        String code_message_value = result.get("code_message_value").toString();
        Map<String, Object> kq = new HashMap<String, Object>();
        kq.put("code", code);
        kq.put("message", message);
        kq.put("message_display", message_display);
        kq.put("code_message_value", code_message_value);
        kq.put("order_code", order_code);
        kq.put("expected_delivery_time", expected_delivery_time);
        kq.put("total_fee", total_fee);
        kq.put("fee", fee.toMap());
        return kq;
    }


    // tính phí ship
    @GetMapping(value = "/ship-fee")
    public ResponseEntity<ShipFeeResponse> calculateShipFee(@RequestParam Integer from_district_id,
                                                            @RequestParam Integer service_id, @RequestParam Integer to_district_id, @RequestParam String to_ward_code,
                                                            @RequestParam(required =false) Integer height, @RequestParam(required =false) Integer length, @RequestParam(required =false) Integer weight,
                                                            @RequestParam(required =false) Integer width, @RequestParam(required =false) Integer value) throws ClientProtocolException, IOException, URISyntaxException {
        JSONObject json = new JSONObject();
        json.put("from_district_id", from_district_id);
        json.put("service_id", service_id);
        json.put("to_district_id", to_district_id);
        json.put("to_ward_code", to_ward_code);
        json.put("height", height);
        json.put("length", length);
        json.put("weight", weight);
        json.put("width", width);
        json.put("insurance_value", value);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(CALCULATE_SHIP_FEE_URL);
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : json.toMap().entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        URI uri = new URIBuilder(get.getURI()).setParameters(params).build();
        get.setURI(uri);
        get.setHeader("token", TOKEN);
        get.setHeader("shopid", SHOP_ID.toString());
        CloseableHttpResponse res = client.execute(get);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }
        JSONObject result = new JSONObject(resultJsonStr.toString());
        JSONObject fee = (JSONObject) result.get("data");
//		System.out.println(fee);

        Long only_ship_fee = Long.parseLong(fee.get("service_fee").toString());
        Long total_ship_fee = Long.parseLong(fee.get("total").toString());
        Long insurance_fee = Long.parseLong(fee.get("insurance_fee").toString());
        String message = result.get("message").toString();
        Integer code = (Integer) result.get("code");
        Boolean success;
        if (code == 200)
            success = true;
        else
            success = false;
        ShipFeeResponse ship = new ShipFeeResponse(only_ship_fee, total_ship_fee, insurance_fee, message, success);
        return new ResponseEntity<ShipFeeResponse>(ship, HttpStatus.OK);
    }

    @GetMapping(value = "/checkorderinfo")
    public ResponseEntity<ShipInfoOrderResponse> checkOrderInfo(@RequestParam(name = "order_code") String code_order)
            throws ClientProtocolException, IOException, URISyntaxException {
        JSONObject json = new JSONObject();
        json.put("order_code", code_order);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(GET_ORDER_DETAIL_URL);
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : json.toMap().entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }
        URI uri = new URIBuilder(get.getURI()).setParameters(params).build();
        get.setURI(uri);
        get.setHeader("token", TOKEN);
        get.setHeader("shopid", SHOP_ID.toString());
        CloseableHttpResponse res = client.execute(get);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }
        JSONObject result = new JSONObject(resultJsonStr.toString());
        JSONObject data = (JSONObject) result.get("data");
        String message = result.get("message").toString();
        Integer code = (Integer) result.get("code");
        Boolean success;
        if (code == 200)
            success = true;
        else
            success = false;
        String order_code = data.get("order_code").toString();
        String partner_id = data.get("client_order_code").toString();
        Integer status = 0;
        String status_name = data.get("status").toString();
        switch (status_name) {
            case "ready_to_pick":
                status = 2;
                break;
            case "cancel":
                status = -1;
                break;
            default:
                break;
        }
        String created_date = "";
        try {
            created_date = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(
                    new Date(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'").parse(data.get("order_date").toString()).getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String updated_date = data.get("updated_date").toString();
        String pick_date = data.get("pickup_time").toString();
        String deliver_date = data.get("leadtime").toString();
        String to_name = data.get("to_name").toString();
        String to_phone = data.get("to_phone").toString();
        String to_address = data.get("to_address").toString();
        Integer weight = Integer.parseInt(data.get("converted_weight").toString());
        Integer is_freeship = 0;
        Long cod_amount = Long.parseLong(data.get("cod_amount").toString());
        Long insurance = Long.parseLong(data.get("insurance_value").toString());
        Long ship_fee = Long.parseLong(data.get("cod_amount").toString());
        Long order_value = Long.parseLong(data.get("cod_amount").toString());

        ShipInfoOrderResponse info = new ShipInfoOrderResponse(message, success, order_code, partner_id, status,
                status_name, created_date, updated_date, pick_date, deliver_date, to_name, to_phone, to_address, weight,
                is_freeship, cod_amount, insurance, ship_fee, order_value);
        return new ResponseEntity<ShipInfoOrderResponse>(info, HttpStatus.OK);
    }

    @PostMapping(value = "/cancel-order")
    public ResponseEntity<ShipCancelOrderResponse> cancelOrder(@RequestBody OrderCodes order_codes)
            throws ClientProtocolException, IOException {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray(order_codes.getOrder_codes());
        json.put("order_codes", jsonArray);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(CANCEL_ORDER_URL);
        StringEntity stringEntity = new StringEntity(json.toString());
        post.setHeader("content-type", "application/json");
        post.setHeader("token", TOKEN);
        post.setHeader("shopid", SHOP_ID.toString());
        post.setEntity(stringEntity);
        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }
        JSONObject result = new JSONObject(resultJsonStr.toString());
        String message = result.get("message").toString();
        Integer code = (Integer) result.get("code");
        Boolean success;
        if (code == 200)
            success = true;
        else
            success = false;
        ShipCancelOrderResponse ship = new ShipCancelOrderResponse(message, success);
        return new ResponseEntity<ShipCancelOrderResponse>(ship, HttpStatus.OK);
    }

}