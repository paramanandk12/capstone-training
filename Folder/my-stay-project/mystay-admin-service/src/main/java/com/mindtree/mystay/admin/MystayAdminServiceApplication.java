package com.mindtree.mystay.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author Dhruvam Gupta M1052242
 *
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@EnableHystrixDashboard
public class MystayAdminServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MystayAdminServiceApplication.class, args);
	}

}
