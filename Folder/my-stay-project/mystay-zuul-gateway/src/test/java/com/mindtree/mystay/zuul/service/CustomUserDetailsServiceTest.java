package com.mindtree.mystay.zuul.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystay.zuul.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomUserDetailsServiceTest {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Test
	public void getGrantedAuthoritiesTest() {
		User user = getUsers().get(0);
		List<GrantedAuthority> authorities = customUserDetailsService.getGrantedAuthorities(user);

		assertEquals("ROLE_ADMIN", authorities.get(0).getAuthority());
	}// getGrantedAuthoritiesTest()

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsernameTest() {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername("john123");
		assertNotNull(userDetails);

		userDetails = customUserDetailsService.loadUserByUsername("atr");

	}//loadUserByUsernameTest()

	public static List<User> getUsers() {
		List<User> users = new ArrayList<>();

		User user = new User();

		user.setUserId("1001");
		user.setFirstName("Amar");
		user.setLastName("Singh");
		user.setEmailId("amar@gk.co");
		user.setGender("male");
		user.setMobile(983726183L);
		user.setPassword("12345");
		user.setUsername("amar123");
		user.setUserType("ADMIN");
		users.add(user);

		user = new User();

		user.setUserId("1002");
		user.setFirstName("Aman");
		user.setLastName("Singh");
		user.setEmailId("aman@gk.co");
		user.setGender("male");
		user.setMobile(983726183L);
		user.setPassword("12345");
		user.setUsername("aman123");
		user.setUserType("USER");
		users.add(user);

		user = new User();

		user.setUserId("1003");
		user.setFirstName("Shreya");
		user.setLastName("Ghosal");
		user.setEmailId("shreya@gk.co");
		user.setGender("male");
		user.setMobile(983726183L);
		user.setPassword("12345");
		user.setUsername("shreya123");
		user.setUserType("USER");
		users.add(user);

		return users;
	}// getUsers()

}
