package com.capstone.oauthserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.oauthserver.model.CustomUserDetails;
import com.capstone.oauthserver.model.Users;
import com.capstone.oauthserver.repository.UserRepository;


public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		Optional<Users> usersOptional = userRepository.findByName(username);

		usersOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return usersOptional.map(CustomUserDetails::new).get();
		
		
		
	}

}
