package com.mindtree.mystay.zuul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Gaurav Kumar(M1052233)
 *
 */

@Configuration
@EnableAuthorizationServer
@PropertySource("classpath:bootstrap.properties")
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	private static final String REALM = "MY_OAUTH_REALM";

	@Value("${client.client_id}")
	String clientId;

	@Value("${client.client_secret}")
	String clientSecret;

	@Value("${client.grant_type}")
	String grantType;

	@Value("${client.authorization_code}")
	String authorizationCode;

	@Value("${client.refresh_token}")
	String refreshToken;

	@Value("${client.implicit}")
	String implicit;

	@Value("${client.scope_read}")
	String scopeRead;

	@Value("${client.scope_write}")
	String scopeWrite;

	@Value("${client.scope_trust}")
	String scopeTrust;

	@Value("${client.role_client}")
	String roleClient;

	@Value("${client.role_trusted_client}")
	String roleTrustedClient;

	@Value("${client.access_token_validity_seconds}")
	String accessTokenValidityInSeconds;

	@Value("${client.refresh_token_validity_seconds}")
	String refreshTokenValidityInSeconds;

	@Autowired
	Environment environment;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		try {
			endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
					.authenticationManager(authenticationManager).tokenEnhancer(tokenEnhancer());
		} catch (Exception e) {

		}
	}// configure(-)

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) {
		try {
			clients.inMemory().withClient(clientId).secret(clientSecret)
					.authorizedGrantTypes(grantType, authorizationCode, refreshToken, implicit)
					.authorities(roleClient, roleTrustedClient).scopes(scopeRead, scopeWrite, scopeTrust)
					.accessTokenValiditySeconds(Integer.parseInt(accessTokenValidityInSeconds))
					.refreshTokenValiditySeconds(Integer.parseInt(refreshTokenValidityInSeconds));
		} catch (Exception e) {

		}
	}// configure(-)

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		try {
			oauthServer.realm(REALM + "/client");
		} catch (Exception e) {

		}

	}// configure(-)

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}// tokenEnhancer()
}