package com.mindtree.mystay.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class MystayCatalogServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(MystayCatalogServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MystayCatalogServiceApplication.class, args);
		logger.debug("**************Catalog-Service is now UP and Running**************");
	}

}
