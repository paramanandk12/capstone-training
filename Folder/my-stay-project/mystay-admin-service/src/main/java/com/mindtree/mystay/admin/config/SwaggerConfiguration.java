package com.mindtree.mystay.admin.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Dhruvam Gupta M1052242
 *
 * May 18, 2019
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public static final Contact DEFAULT_CONTACT = new Contact("Dhruvam Gupta", "http://www.mystay.com", "dhruvam.gupta@mindtree.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("My Stay Admin Service ",
			"This API lets admin add/delete/update hotels/rooms/offers", "API TOS", "Terms of service",
			DEFAULT_CONTACT, "License of API",
			"API license URL", Collections.emptyList());
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json", "application/xml"));
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
}
