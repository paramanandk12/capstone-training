package com.mindtree.mystay.zuul.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.mindtree.mystay.zuul.model.User;
import com.mindtree.mystay.zuul.service.UserService;

/**
 * @author Gaurav Kumar(M1052233)
 *
 */

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	UserService userService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final Map<String, Object> additionalInfo = new HashMap<>();

		String authorities = "";
		for (GrantedAuthority role : authentication.getAuthorities()) {
			authorities = "" + role ;
		}

		User user = userService.findByUsername(authentication.getName());
		
		String userName = user.getFirstName()+ " "+user.getLastName();
		
		
		additionalInfo.put("user", userName);
		additionalInfo.put("role", authorities.substring(5));
		additionalInfo.put("userId", user.getUserId());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

		return accessToken;
	} // enhance(-,-)

}