package org.mindtree.shoppingcartapp.repository;

import org.mindtree.shoppingcartapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * @author M1048697
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	 @Query("SELECT u FROM User u where u.userEmail = ?1")
	  UserEntity findByEmail(String userEmail);
	 
  
}
