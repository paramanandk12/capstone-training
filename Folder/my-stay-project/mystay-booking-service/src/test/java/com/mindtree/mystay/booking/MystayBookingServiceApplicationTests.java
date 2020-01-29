package com.mindtree.mystay.booking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@SuiteClasses({ BookingControllerTest.class, BookingServiceTest.class,
		CustomizedResponseEntityExceptionHandlerTest.class })
@SpringBootTest
public class MystayBookingServiceApplicationTests {

}
