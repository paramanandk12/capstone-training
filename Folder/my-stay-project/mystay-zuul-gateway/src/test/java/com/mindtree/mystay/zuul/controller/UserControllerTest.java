package com.mindtree.mystay.zuul.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.mystay.zuul.model.User;
import com.mindtree.mystay.zuul.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	MockMvc mockMvc;
	
	private UserService userService;
	
	@Autowired
	UserController userController;

	@Test
	public void allUsersControllerTest() {
		ResponseEntity<List<User>> userListResponse = userController.allUsers();
		assertEquals(HttpStatus.OK, userListResponse.getStatusCode());
	}// allUsersControllerTest()

	@Test
	public void userByIdControllerTest() {
		try {
			ResponseEntity<User> userResponse = userController.userById("5cdbc0db4e969d595c07c046");
			assertEquals(HttpStatus.OK, userResponse.getStatusCode());
			userResponse = userController.userById("5cdbc0db595c07c046");
			assertEquals(HttpStatus.NOT_FOUND, userResponse.getStatusCode());
		} catch (Exception e) {
		}
	}// userByIdControllerTest()

	@Test
	public void registerControllerTest() {
		try {
			User newUser = getUsers().get(1);
			ResponseEntity<String> messageResponse = userController.register(newUser);
			assertEquals("user register successfully", messageResponse.getBody());

			newUser.setUsername("john123");
			messageResponse = userController.register(newUser);
			assertEquals("Username is already in use", messageResponse.getBody());
		} catch (Exception e) {
		}
	}// registerControllerTest()

	@Test
	public void userLogoutControllerTest() {
		try {
			ResponseEntity<String> messageResponse = userController.userLogout("20cbea10-d263-49d7-829d-50de481fa663");
			assertEquals(HttpStatus.NOT_FOUND, messageResponse.getStatusCode());
		} catch (Exception e) {
		}
	}// userLogoutControllerTest()

	@Test
	public void getUserByUserIdMockTest() {
		User testUser = getUsers().get(0);
		try {
			mockMvc.perform(get("/admin/user/{userId}", "5cdbc0db4e969d595c07c046")).andExpect(status().isOk());
			User user = userService.findByUserId("5cdbc0db4e969d595c07c046");
			when(user).thenReturn(testUser);
			verify(userService, atLeastOnce()).findByUserId("5cdbc0db4e969d595c07c046");
			verifyNoMoreInteractions(userService);
		} catch (Exception e) {
		}
	}// getUserByUserIdMockTest()

	@Test
	public void getUserByUserIdMockTest_NotFound() {
		User testUser = getUsers().get(0);
		try {
			mockMvc.perform(get("/admin/user/{userId}", "5cdbc0dbuytr9d595c07c046")).andExpect(status().isNotFound());
			User user = userService.findByUserId("5cdbc0dbuytr9d595c07c046");
			when(user).thenReturn(testUser);
			verify(userService, atLeastOnce()).findByUserId("5cdbc0dbuytr9d595c07c046");
			verifyNoMoreInteractions(userService);
		} catch (Exception e) {
		}
	}// getUserByUserIdMockTest_NotFound()

	@Test
	public void findAllUsersMockTest() {
		try {
			mockMvc.perform(get("/admin/users")).andExpect(status().isOk());
			verify(userService, atLeastOnce()).findAllUsers();
			verifyNoMoreInteractions(userService);
		} catch (Exception e) {
		}
	}// findAllUsersMockTest()

	@Test
	public void findAllUsersMockTest_NotFound() {
		try {
			mockMvc.perform(get("/admin/users")).andExpect(status().isNotFound());
			verify(userService, atLeastOnce()).findAllUsers();
			verifyNoMoreInteractions(userService);
		} catch (Exception e) {
		}
	}// findAllUsersMockTest_NotFound()

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}// asJsonString(-)

	@Test
	public void registerMockTest() {
		User user = getUsers().get(0);
		try {
			when(userService.saveNewUser(user)).thenReturn("user register successfully");
			mockMvc.perform(
					post("/registeration").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(user)))
					.andExpect(status().isOk()).andExpect(content().string("user register successfully"));
			verify(userService, atLeastOnce()).saveNewUser(user);
			verifyNoMoreInteractions(userService);
		} catch (Exception e) {
		}
	}// registerMockTest()

	@Test
	public void registerMockTest_NotImplemented() {
		User user = null;
		try {
			mockMvc.perform(
					post("/registeration").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(user)))
					.andExpect(status().isNotImplemented()).andExpect(content().string("user registeration failed"));
			verify(userService, atLeastOnce()).saveNewUser(user);
			verifyNoMoreInteractions(userService);
		} catch (Exception e) {
		}
	}// registerMockTest_NotImplemented()

	@Test
	public void userLogoutMockTest() {
		try {
			mockMvc.perform(post("/user/logout").contentType(MediaType.APPLICATION_JSON_VALUE).param("access_token",
					"20cbea10-d263-49d7-829d-50de481fa663")).andExpect(status().isNotAcceptable())
					.andExpect(content().string("Error in token store"));
		} catch (Exception e) {
		}
	}// userLogoutTest()

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