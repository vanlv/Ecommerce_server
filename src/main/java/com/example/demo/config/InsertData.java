//package com.example.demo.config;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.example.demo.common.Erole;
//import com.example.demo.entity.user.FullName;
//import com.example.demo.entity.user.Role;
//import com.example.demo.entity.user.User;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UserRepository;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InsertData implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {
//
//	private static boolean eventFired = false;
//	private static final Logger logger = LoggerFactory.getLogger(InsertData.class);
//
//	@Autowired
//	private UserRepository repos;
//
//	@Autowired
//	private RoleRepository roleRepos;
//
//	@Autowired
//	private PasswordEncoder encoder;
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		if (eventFired) {
//			return;
//		}
//		logger.info("Application started.");
//
//		eventFired = true;
//
//		try {
//			createRoles();
//			createAdminUser();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void createRoles() {
//		List<Role> roleNames = new ArrayList<>();
//		roleNames.add(new Role(Erole.ROLE_ADMIN));
//		roleNames.add(new Role(Erole.ROLE_STAFF_BUSINESS));
//		roleNames.add(new Role(Erole.ROLE_STAFF_SALE));
//		roleNames.add(new Role(Erole.ROLE_STAFF_STOCK));
//		roleNames.add(new Role(Erole.ROLE_USER));
//
//		for (Role roleName : roleNames) {
//			if (roleRepos.existsByName(roleName.getName())) {
//				return;
//			}
//			roleName.setName(roleName.getName());
//			try {
//				roleRepos.save(roleName);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//
//	private void createAdminUser() {
//		if (repos.existsByUsername("admin")) {
//			return;
//		} else if (repos.existsByEmail("admin@gmail.com")) {
//			return;
//		} else {
//			FullName fullName = new FullName("User", "Admin");
//			User admin = new User("0999999999", "admin@gmail.com", "admin", encoder.encode("admin"), "", fullName);
//
//			admin.setPhone("0999999999");
//			admin.setEmail("admin@gmail.com");
//			admin.setUsername("admin");
//			admin.setPassword(encoder.encode("admin"));
//			admin.setDateOfBirth("14/12/1999");
//			admin.setFullname(fullName);
//			
//			fullName.setUser(admin);
//			
//			Set<Role> roles = new HashSet<Role>();
//			Role role = roleRepos.findByName(Erole.ROLE_ADMIN)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
//			roles.add(role);
//			admin.setRoles(roles);
//			try {
//				repos.save(admin);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
