package com.mindtree.mystay.zuul.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.mystay.zuul.model.User;

/**
 * @author Gaurav Kumar(M1052233)
 *
 */

@Repository("userRepository")
@Transactional
public interface UserRepository extends MongoRepository<User, Integer> {

	/*
	 * this method search User by provided username
	 */
	User findByUsername(String username);
	
	/*
	 * this method search User by provided user id
	 */
	User findByUserId(String userId);
	
}
