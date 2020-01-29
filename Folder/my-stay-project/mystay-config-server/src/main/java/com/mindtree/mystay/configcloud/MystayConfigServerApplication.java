package com.mindtree.mystay.configcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@SpringBootApplication
@EnableConfigServer
public class MystayConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystayConfigServerApplication.class, args);
	}

}
