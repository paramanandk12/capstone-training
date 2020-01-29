package com.mindtree.mystay.turbine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author Rajanigandha Khot M1052150
 *
 */

@EnableHystrixDashboard
@EnableTurbine
@SpringBootApplication
public class MystayTurbineDashbordApplication {

	private static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		SpringApplication.run(MystayTurbineDashbordApplication.class, args);
		logger.info("Starting turbine dashboard application");
	}

}
