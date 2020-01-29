package org.mindtree.shoppingcartapp.services.impl;

import org.mindtree.shoppingcartapp.entity.UserEntity;
import org.mindtree.shoppingcartapp.exception.DataNotFoundException;
import org.mindtree.shoppingcartapp.repository.UserRepository;
import org.mindtree.shoppingcartapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author M1048697
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public UserEntity saveUser(UserEntity user) throws DataNotFoundException {
		logger.info("saveUser calling..");
		return userRepository.save(user);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public UserEntity findOneUser(String userEmail) {
		logger.info("findOneUser calling..");
		return userRepository.findByEmail(userEmail);
	}
	
	

}
