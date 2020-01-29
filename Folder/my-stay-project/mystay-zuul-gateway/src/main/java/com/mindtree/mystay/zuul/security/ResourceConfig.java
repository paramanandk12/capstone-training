package com.mindtree.mystay.zuul.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * @author Gaurav Kumar(M1052233)
 *
 */

@Configuration
@EnableResourceServer
@PropertySource("classpath:bootstrap.properties")
public class ResourceConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";

	@Value("${catalog.resource.api}")
	String cataloResourceApi;

	@Value("${search.resource.api}")
	String searchResourceApi;

	@Value("${booking.resource.api}")
	String bookingResourceApi;

	@Value("${admin.resource.api}")
	String adminResourceApi;

	@Value("${admin_user.resource.api}")
	String adminUserResourceApi;

	@Value("${payment.resource.api}")
	String paymentResourceApi;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}// configure(-)

	@Override
	public void configure(HttpSecurity http) {
		try {
			http.anonymous().disable().authorizeRequests()
					.antMatchers(
							cataloResourceApi, 
							searchResourceApi, 
							bookingResourceApi, 
							paymentResourceApi
							).permitAll()
					.antMatchers(
							adminResourceApi, 
							adminUserResourceApi
							).access("hasRole('ADMIN')")
					.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		} catch (Exception e) {

		}
	}// configure(-)
}