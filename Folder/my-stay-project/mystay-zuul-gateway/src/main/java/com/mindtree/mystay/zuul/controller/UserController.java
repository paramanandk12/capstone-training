package com.mindtree.mystay.zuul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystay.zuul.model.User;
import com.mindtree.mystay.zuul.service.UserService;

/**
 * @author Gaurav Kumar(M1052233)
 *
 */

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	TokenStore tokenStore;

	/*
	 * This request call a service to provide all the users details from database
	 * 
	 *  @return ResponseEntity List Object
	 */
	@GetMapping("/admin/users")
	public ResponseEntity<List<User>> allUsers() {
		List<User> userList = userService.findAllUsers();

		if (userList.isEmpty()) {
			return new ResponseEntity<>(userList, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(userList, HttpStatus.OK);

	}// allUsers()

	/*
	 * This request will call a service to find user by given user id
	 * 
	 * @param Integer Object
	 * 
	 * @return ResponseEntity User Object
	 */
	@GetMapping("/admin/user/{userId}")
	public ResponseEntity<User> userById(@PathVariable("userId") String userId) {
		User user = userService.findByUserId(userId);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}

		return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

	}// userById(-)

	/*
	 *  The request call a service to register the new user
	 *  
	 *  @param User Object
	 *  
	 *  @return ResponseEntity String Object
	 */
	@PostMapping("/registeration")
	public ResponseEntity<String> register(@RequestBody User newUser) {
		String message = userService.saveNewUser(newUser);

		if (message.contains("success")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		}

		return new ResponseEntity<>(message, HttpStatus.NOT_IMPLEMENTED);

	}// register(-)

	/*
	 * This request logout the user from the application
	 * 
	 * @param access token
	 * 
	 * @return ResponseEntity String Object	
	 */
	@PostMapping("/user/logout")
	public ResponseEntity<String> userLogout(@RequestParam("access_token") String accessToken) {

		try {
			OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
			if (oAuth2AccessToken == null) {
				return new ResponseEntity<>("No such token exists", HttpStatus.NOT_FOUND);
			}

			tokenStore.removeAccessToken(oAuth2AccessToken);
			return new ResponseEntity<>("You have been logout", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error in token store", HttpStatus.NOT_ACCEPTABLE);
		}

	}// userLogout(-)

}// controller