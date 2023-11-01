package com.example.demo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.common.ZaloPojo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ZaloUtils {

	private final String APP_ID = "2004406068292141065";
	private final String LINK_GET_ACCESS_TOKEN = "https://oauth.zaloapp.com/v4/access_token";
	private final String LINK_GET_USER_INFO = "https://graph.zalo.me/v2.0/me?fields=id,name,phone";

	public String getToken(final String code) throws ClientProtocolException, IOException {
		String response = Request
				.Post(LINK_GET_ACCESS_TOKEN).addHeader("secret_key", "HLmK1uKmdOWY0PI4Qf27").bodyForm(Form.form()
						.add("code", code).add("app_id", APP_ID).add("grant_type", "authorization_code").build())
				.execute().returnContent().asString();
		return response;
	}

	public ZaloPojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = LINK_GET_USER_INFO;
		String response = Request.Get(link).addHeader("access_token", accessToken).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		ZaloPojo zaloPojo = mapper.readValue(response, ZaloPojo.class);
		return zaloPojo;
	}

	public UserDetails buildUser(ZaloPojo googlePojo) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails userDetail = new User(googlePojo.getId(), "", enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		return userDetail;
	}
}