package com.mindtree.mystay.payment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystay.payment.model.PaymentRequest;
import com.mindtree.mystay.payment.model.PaymentResponse;
import com.mindtree.mystay.payment.service.PaymentServiceImpl;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceImplTest {
	
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	
	@Test
	public void testMakePayment() {
		PaymentRequest request = new PaymentRequest();
		request.setPaymentType("card");
		request.setEmail("abc@mindtree.com");
		request.setAmmount(25000L);
		PaymentResponse response = paymentServiceImpl.makePayment(request);
		
		assertNotNull(response);
		assertEquals("success", response.getTransactionStatus());
	}

}
