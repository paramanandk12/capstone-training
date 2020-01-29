package com.mindtree.mystay.zuul.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.mindtree.mystay.zuul.dao.UserRepository;
import com.mindtree.mystay.zuul.model.User;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserServiceTest {
			
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Spy
	private List<User> users = new ArrayList<>();
	
	@Test
	public void getUserByUserIdServiceTest() {
		User testUser = getUsers().get(0);
		
		try {
			
			User user = userRepository.findByUserId("5cdbc0db4e969d595c07c046");
			when(user).thenReturn(testUser);
			assertEquals("Amar", userService.findByUserId("5cdbc0db4e969d595c07c046").getFirstName());
			assertEquals(null, userService.findByUserId("12345"));
			
		} catch(Exception e) {
		}
	
	}// getUserByUserIdServiceTest()
	
	@Test
	public void getUserByUsernameServiceTest() {
		User testUser = getUsers().get(0);
		
		try {
			User user = userRepository.findByUsername("john123");
			when(user).thenReturn(testUser);
			
			assertEquals("Amar", userService.findByUsername("john123").getFirstName());
			
			assertEquals(null, userService.findByUsername("amar123").getFirstName());
		
		} catch (Exception e) {
		}
	}// getUserByUsernameServiceTest()
	
	@Test
	public void findAllUsersTest() {
		try {
			List<User> users = new ArrayList<>();
			List<User> userList = userRepository.findAll();
			when(userList).thenReturn(users);
			
			assertEquals(0, userService.findAllUsers().size());
			
			List<User> users1 = getUsers();
			when(userList).thenReturn(users1);
			assertEquals(3, userService.findAllUsers().size());
			
		} catch (Exception e) {
		}
	}//findAllUsersTest()
	
	@Test
	public void saveNewUserTest() {
		try {
			
			User user2 = getUsers().get(0);
			when(userRepository.save(user2)).thenReturn(user2);
			assertEquals("user register successfully",userService.saveNewUser(user2));
			
			User user1 = getUsers().get(0);
			user1.setUsername("john123");
			assertEquals("user registeration failed",userService.saveNewUser(user1));
			
		} catch(Exception e) {
			
		}
	}//saveNewUserTest()
	
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
}// test class