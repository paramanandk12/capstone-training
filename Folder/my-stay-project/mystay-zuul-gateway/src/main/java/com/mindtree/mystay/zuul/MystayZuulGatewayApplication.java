package com.mindtree.mystay.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * @author Dhruvam Gupta M1052242
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class MystayZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystayZuulGatewayApplication.class, args);
	}

}
