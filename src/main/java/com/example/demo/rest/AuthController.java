package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ETypeAccount;
import com.example.demo.common.Erole;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.auth.JwtResponse;
import com.example.demo.dto.auth.LoginDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.auth.RegisterDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.user.Address;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.Seller;
import com.example.demo.entity.user.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SellerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserDetailsImpl;
import com.example.demo.utils.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SellerRepository sellerRepos;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody LoginDto dto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

	}

	@PostMapping("/admin/login")
	public ResponseEntity<?> loginAdmin(@Validated @RequestBody LoginDto dto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		if (userDetails.getRoles().contains(new String("ROLE_ADMIN"))
				|| userDetails.getRoles().contains(new String("ROLE_SELLER"))) {
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles));
		} else {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Truy cập bị từ chối! Tài khoản hoặc mật khẩu không chính xác!"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterDto dto) {

		if (userRepository.existsByUsername(dto.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Tài khoản đã được đăng ký!"));
		}
		if (userRepository.existsByEmail(dto.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email đã được đăng ký!"));
		}
		if (userRepository.existsByPhone(dto.getPhone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Số điện thoại đã được đăng ký!"));
		}

		Address address = new Address(dto.getCity(), dto.getDistrict(), dto.getWard(), dto.getHouse());

		User user = new User(dto.getPhone(), dto.getEmail(), dto.getUsername(), encoder.encode(dto.getPassword()),
				dto.getDateOfBirth(), dto.getFullName(), address);
		user.setDisplay(1);
		user.setType_account(ETypeAccount.LOCAL);
		if (dto.getCccd() != null && dto.getExp() != null) {
			Seller seller = new Seller();
			seller.setCccd(dto.getCccd());
			seller.setExp(dto.getExp());
			seller.setUser(user);
			user.setSeller(seller);
		}

		address.setUser(user);

		List<String> strRoles = dto.getRole();
		List<Role> roles = new ArrayList<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findOneByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findOneByName(Erole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
					break;
				case "seller":
					Role shipRole = roleRepository.findOneByName(Erole.ROLE_SELLER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(shipRole);
					break;
				default:
					Role userRole = roleRepository.findOneByName(Erole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole);
					Cart cart = new Cart();
					cart.setUser(user);
					user.setCart(cart);
					break;
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
	}

	@PutMapping("/update-info")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_SELLER')")
	public ResponseEntity<?> update(@Validated @RequestBody RegisterDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userRepository.findOneByUsername(username);
		if (userRepository.existsByEmail(dto.getEmail()) && user.getEmail().equals(dto.getEmail()) == false) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email đã được đăng ký!"));
		}
		user.setFullname(dto.getFullName());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setDateOfBirth(dto.getDateOfBirth());
		if (dto.getCccd() != null && dto.getExp() != null) {
			Seller seller = sellerRepos.findOneByUser(user);
			seller.setCccd(dto.getCccd());
			seller.setExp(dto.getExp());
		}
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Cập nhật thông tin tài khoản thành công!"));
	}

	@PutMapping("/update-user/{username}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_SELLER')")
	public ResponseEntity<?> updateUserByAdmin(@Validated @RequestBody RegisterDto dto, @PathVariable String username) {
		User user = userRepository.findOneByUsername(username);
		if (userRepository.existsByEmail(dto.getEmail()) && user.getEmail().equals(dto.getEmail()) == false) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email đã được đăng ký!"));
		}
		user.setFullname(dto.getFullName());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setDateOfBirth(dto.getDateOfBirth());
		if (dto.getCccd() != null && dto.getExp() != null) {
			Seller seller = sellerRepos.findOneByUser(user);
			seller.setCccd(dto.getCccd());
			seller.setExp(dto.getExp());
		}
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Cập nhật thông tin tài khoản thành công!"));
	}

	@DeleteMapping("/hide-user/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> hideUserByAdmin(@PathVariable Long id) {
		User user = userRepository.getById(id);
		if (user.getDisplay() == 1) {
			user.setDisplay(0);
		} else {
			user.setDisplay(1);
		}
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Cập nhật thông tin tài khoản thành công!"));
	}

	@PutMapping("/update-password")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_SELLER')")
	public ResponseEntity<?> updatePassword(@Validated @RequestBody RegisterDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userRepository.findOneByUsername(username);
		String oldPassword = dto.getPassword();
		String newPassword = dto.getPasswordNew();
		if (encoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(encoder.encode(newPassword));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu cũ không chính xác!"));
		}
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Cập nhật mật khẩu thành công!"));
	}

	@GetMapping("/info")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_SELLER')")
	public ResponseEntity<UserDto> getNewById() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		UserDto result = userService.getCurrentUser(userDetails.getId());
		User user = userRepository.getById(userDetails.getId());
		if (user.getSeller() != null) {
			result.setCccd(user.getSeller().getCccd());
			result.setExp(user.getSeller().getExp());
		}
		return new ResponseEntity<UserDto>(result, HttpStatus.OK);
	}

	@GetMapping("/all/{role}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<UserDto>> getList(@PathVariable String role,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		SearchDto dto = new SearchDto(page, limit, keyword);
		String s = "";
		switch (role) {
		case "admin":
			s += "ROLE_ADMIN";
			break;
		case "seller":
			s += "ROLE_SELLER";
			break;
		case "user":
			s += "ROLE_USER";
			break;
		default:
			break;
		}
		Page<UserDto> result = userService.getListByRole(s, dto);
		return new ResponseEntity<Page<UserDto>>(result, HttpStatus.OK);
	}

//	@GetMapping("/all/shipper")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ResponseEntity<Page<UserDto>> getListUserByRole(@RequestParam(name = "page", defaultValue = "0") int page,
//			@RequestParam(name = "limit", defaultValue = "10") int limit) {
//		SearchDto dto = new SearchDto();
//		dto.setPageIndex(page);
//		dto.setPageSize(limit);
//		String role = "ROLE_ADMIN";
//		Page<UserDto> result = userService.getListByRole(role, dto);
//		return new ResponseEntity<Page<UserDto>>(result, HttpStatus.OK);
//	}

	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_SELLER')")
	public ResponseEntity<UserDto> getCustomerById(@PathVariable Long id) {
		User user = userRepository.getById(id);
		UserDto result = new UserDto(user);
		if (user.getSeller() != null) {
			result.setCccd(user.getSeller().getCccd());
			result.setExp(user.getSeller().getExp());
		}
		return new ResponseEntity<UserDto>(result, HttpStatus.OK);
	}

}