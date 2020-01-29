package com.mindtree.mystay.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Rajanigandha Khot M1052150
 *
 */

@EnableHystrixDashboard
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients
public class MystaySearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystaySearchServiceApplication.class, args);
	}

}
