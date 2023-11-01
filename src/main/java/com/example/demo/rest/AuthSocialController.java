package com.example.demo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.common.ETypeAccount;
import com.example.demo.common.Erole;
import com.example.demo.common.GooglePojo;
import com.example.demo.common.ZaloPojo;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.user.Address;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.FacebookUtils;
import com.example.demo.utils.GoogleUtils;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.ZaloUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
public class AuthSocialController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private GoogleUtils googleUtils;

	@Autowired
	private FacebookUtils facebookUtils;

	@Autowired
	private ZaloUtils zaloUtils;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/oauth2/google")
	public RedirectView loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			return new RedirectView("http://localhost:3000/oauth2/redirect?status=false");
		}
		ObjectMapper mapper = new ObjectMapper();
		String token = googleUtils.getToken(code);
		JsonNode node = mapper.readTree(token);
		String accessToken = node.get("access_token").textValue();

		GooglePojo googlePojo = null;
		if (accessToken != null) {
			googlePojo = googleUtils.getUserInfo(accessToken);
		}
		UserDetails userDetail = googleUtils.buildUser(googlePojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = userRepository.findOneByUsername(userDetail.getUsername());

		if (user == null) {
			user = new User();
			user.setDisplay(1);
			user.setEmail(googlePojo.getEmail());
			user.setUsername(googlePojo.getEmail());
			user.setFullname(googlePojo.getName());
			user.setPassword(encoder.encode(googlePojo.getSocial_user_id()));
			user.setType_account(ETypeAccount.GOOGLE);
			List<Role> roles = new ArrayList<Role>();
			Role userRole = roleRepository.findOneByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
			user.setRoles(roles);

			Address address = new Address(null, null, null, null);
			user.setAddress(address);
			address.setUser(user);
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			userRepository.save(user);
		}
		Authentication authen = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), googlePojo.getSocial_user_id()));
		String jwt = jwtUtils.generateJwtToken(authen);
		return new RedirectView(
				"http://localhost:3000/oauth2/redirect?status=true&token=" + jwt + "&username=" + user.getEmail());
	}

	@RequestMapping("/oauth2/facebook")
	public RedirectView loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return new RedirectView("http://localhost:3000/oauth2/redirect?status=false");
		}
		ObjectMapper mapper = new ObjectMapper();
		String token = facebookUtils.getToken(code);
		JsonNode node = mapper.readTree(token);
		String accessToken = node.get("access_token").textValue();
		com.restfb.types.User user = facebookUtils.getUserInfo(accessToken);
		UserDetails userDetail = facebookUtils.buildUser(user);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		User userEntity = userRepository.findOneByUsername(userDetail.getUsername());
		if (userEntity == null) {
			userEntity = new User();
			userEntity.setDisplay(1);
			StringBuilder str = new StringBuilder();
			str.append(user.getId());
			str.reverse();
			String randomemail = "facebook-" + str + "@yopmail.com";
			if (user.getEmail() != null) {
				userEntity.setEmail(user.getEmail());
			} else {
				userEntity.setEmail(randomemail);
			}
			userEntity.setUsername(user.getId());
			userEntity.setFullname(user.getName());
			userEntity.setPassword(encoder.encode(user.getId()));
			userEntity.setType_account(ETypeAccount.FACEBOOK);
			List<Role> roles = new ArrayList<Role>();
			Role userRole = roleRepository.findOneByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
			userEntity.setRoles(roles);

			Address address = new Address(null, null, null, null);
			userEntity.setAddress(address);
			address.setUser(userEntity);
			Cart cart = new Cart();
			cart.setUser(userEntity);
			userEntity.setCart(cart);
			userRepository.save(userEntity);
		}
		String username = userEntity.getUsername();
		Authentication authen = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, username));
		String jwt = jwtUtils.generateJwtToken(authen);
		return new RedirectView("http://localhost:3000/oauth2/redirect?status=true&token=" + jwt + "&username="
				+ userEntity.getUsername());
	}

	@RequestMapping(value = "/oauth2/zalo")
	public RedirectView loginZalo(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			return new RedirectView("http://localhost:3000/oauth2/redirect?status=false");
		}
		ObjectMapper mapper = new ObjectMapper();
		String token = zaloUtils.getToken(code);
//		System.out.println(token);
		JsonNode node = mapper.readTree(token);
		String accessToken = node.get("access_token").textValue();
		ZaloPojo zaloPojo = null;
		if (accessToken != null) {
			zaloPojo = zaloUtils.getUserInfo(accessToken);
		}
		UserDetails userDetail = zaloUtils.buildUser(zaloPojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userRepository.findOneByUsername(userDetail.getUsername());
		if (user == null) {
			user = new User();
			user.setDisplay(1);
			StringBuilder str = new StringBuilder();
			str.append(zaloPojo.getId());
			str.reverse();
			String randomemail = "zalo-" + str + "@yopmail.com";
			if (user.getEmail() != null) {
				user.setEmail(user.getEmail());
			} else {
				user.setEmail(randomemail);
			}
			user.setUsername(zaloPojo.getId());
			user.setFullname(zaloPojo.getName());
			user.setPassword(encoder.encode(zaloPojo.getId()));
			user.setType_account(ETypeAccount.ZALO);
			List<Role> roles = new ArrayList<Role>();
			Role userRole = roleRepository.findOneByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
			user.setRoles(roles);
			
			Address address = new Address(null, null, null, null);
			user.setAddress(address);
			address.setUser(user);
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			userRepository.save(user);
		}
		Authentication authen = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getUsername()));
		String jwt = jwtUtils.generateJwtToken(authen);
		return new RedirectView(
				"http://localhost:3000/oauth2/redirect?status=true&token=" + jwt + "&username=" + user.getUsername());
	}
}
