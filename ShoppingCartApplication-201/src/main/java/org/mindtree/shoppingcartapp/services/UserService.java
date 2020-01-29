package org.mindtree.shoppingcartapp.services;

import org.mindtree.shoppingcartapp.entity.UserEntity;


/**
 * @author M1048697
 *
 */
public interface UserService {
	
	
	UserEntity saveUser(UserEntity user) throws Exception;
	
	UserEntity findOneUser(String userEmail);
	

}
