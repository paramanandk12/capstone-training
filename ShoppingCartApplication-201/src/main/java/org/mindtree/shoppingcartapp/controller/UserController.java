/**
 * 
 */
package org.mindtree.shoppingcartapp.controller;

import org.mindtree.shoppingcartapp.entity.CartEntity;
import org.mindtree.shoppingcartapp.entity.UserEntity;
import org.mindtree.shoppingcartapp.repository.UserDetailRepository;
import org.mindtree.shoppingcartapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author M1048697
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/login/{userEmail}")
	public ResponseEntity<UserEntity> login(@PathVariable("userEmail") String userEmail) {

		try {
			logger.debug("in login method calling service ");
			UserEntity user = userService.findOneUser(userEmail);
			logger.debug(" login successfully..  ");
			return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<UserEntity>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
		CartEntity cart = new CartEntity();
		logger.debug("in save method calling service ");
		cart.getUserCartId();
		userDetailRepository.save(cart);
		logger.debug(" added user successfully..  ");
		user.setCart(cart);
		try {
			return ResponseEntity.ok(userService.saveUser(user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/profile/{userEmail}")
	public ResponseEntity<UserEntity> getProfile(@PathVariable("userEmail") String userEmail) {
		logger.debug("in getProfile method calling service ");
		UserEntity user = userService.findOneUser(userEmail);
		if (user.getUserEmail().equals(userEmail)) {
			logger.debug(" founded user profile successfully..  ");
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}


}
