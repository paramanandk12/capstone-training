package com.mindtree.mystay.catalog.config;

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
 * @author Abhishek Karmakar M1049319
 *
 */
@Configuration
@EnableSwagger2
public class CatalogServiceSwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Abhishek Karmakar", "http://......",
			"abhishek.karmakar@mindtree.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("My Stay Catalog Service ",
			"This API will give you list of all the hotel and its details", "API TOS", "Terms of service",
			new Contact("Abhishek Karmakar", "www.//.com", "abhishek.karmakar@mindtree.com"), "License of API",
			"API license URL", Collections.emptyList());
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

}
