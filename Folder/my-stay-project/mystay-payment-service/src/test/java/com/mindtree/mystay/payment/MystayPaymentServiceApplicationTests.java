package com.mindtree.mystay.payment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RunWith(Suite.class)
@SuiteClasses({PaymentControllerTest.class, PaymentServiceImplTest.class})
@SpringBootTest
public class MystayPaymentServiceApplicationTests {
	

}
