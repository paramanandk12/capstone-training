package com.mindtree.mystay.zuul.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.mystay.zuul.dao.UserRepository;
import com.mindtree.mystay.zuul.model.User;

/**
 * @author Gaurav Kumar(M1052233)
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService {

	static Logger logger = LogManager.getLogger();

	@Autowired
	UserRepository userRepository;

	/*
	 * This service register the new user and save user info into database
	 * 
	 * @param User Object
	 * 
	 * @return String Object
	 */
	@Override
	public String saveNewUser(User newUser) {
		User user = null;
		try {
			User matchUser = userRepository.findByUsername(newUser.getUsername());
			if (matchUser != null) {
				logger.error("User Detail have name : " + newUser.getFirstName()
						+ " not been saved due to username is already in use");
				return "Username is already in use";
			}
			ObjectId obId = new ObjectId();
			newUser.setUserId(obId.toHexString());
			user = userRepository.save(newUser);
			if (user != null) {
				logger.info("user have name " + newUser.getFirstName() + " register successfully");
				return "user register successfully";
			}
		} catch (Exception e) {
		}
		logger.error("user registeration failed have name : " + newUser.getFirstName());
		return "user registeration failed";
	}// saveNewUser(-)

	/*
	 * This service find user by given username from database
	 * 
	 * @param String Object
	 * 
	 * @return User Object
	 */
	@Override
	public User findByUsername(String username) {
		User user = null;
		try {
			user = userRepository.findByUsername(username);
			if (user != null) {
				logger.info("user found by username : " + username);
				return user;
			}
		} catch (Exception e) {
		}
		logger.error("user not found by username :" + username);
		return user;
	}// findByUsername(-)
	
	/*
	 * This service find user by given user id from database
	 * 
	 * @param String Object
	 * 
	 * @return User Object
	 */

	@Override
	public User findByUserId(String userId) {
		User user = null;
		try {
			user = userRepository.findByUserId(userId);
			if (user != null) {
				logger.info("user found by userId : " + userId);
				return user;
			}
		} catch (Exception e) {
		}

		logger.error("user not found by userId : " + userId);
		return user;
	}// findByUserId(-)
	
	/*
	 * This service provide all the users details from database
	 * 
	 * @return List Object
	 */

	@Override
	public List<User> findAllUsers() {
		List<User> userList = new ArrayList<>();
		try {
			userList = userRepository.findAll();
			if (!userList.isEmpty()) {
				logger.info("list of users found and returned");
				return userList;
			}
		} catch (Exception e) {
		}
		logger.error("no user found");
		return userList;
	}// findAllUsers()

}// service
