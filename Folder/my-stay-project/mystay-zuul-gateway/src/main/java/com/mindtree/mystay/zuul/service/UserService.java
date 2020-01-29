package com.mindtree.mystay.zuul.service;

import java.util.List;

import com.mindtree.mystay.zuul.model.User;

/**
 * @author Gaurav Kumar(M1052233)
 *
 */

public interface UserService {

	String saveNewUser(User newUser);
	
	User findByUsername(String username);
	
	User findByUserId(String userId);
	
	List<User> findAllUsers();
	
}
