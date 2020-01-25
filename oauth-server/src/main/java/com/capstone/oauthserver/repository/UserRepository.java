package com.capstone.oauthserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.oauthserver.model.Users;


public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Optional<Users> findByName(String username);

}
