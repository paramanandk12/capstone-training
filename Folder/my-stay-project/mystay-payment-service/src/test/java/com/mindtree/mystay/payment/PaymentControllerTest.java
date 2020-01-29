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

import com.mindtree.mystay.payment.controller.PaymentController;
import com.mindtree.mystay.payment.model.PaymentRequest;
import com.mindtree.mystay.payment.model.PaymentResponse;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTest {
	
	@Autowired
	private PaymentController paymentCotroller;
	
	@Test
	public void testMakePayment() {
		PaymentRequest request = new PaymentRequest();
		request.setPaymentType("card");
		request.setEmail("abc@mindtree.com");
		request.setAmmount(25000L);
		ResponseEntity<PaymentResponse> makePayment = paymentCotroller.makePayment(request);
		
		assertNotNull(makePayment);
		assertEquals(HttpStatus.OK, makePayment.getStatusCode());
		PaymentResponse response = makePayment.getBody();
		assertNotNull(response);
		assertEquals("success", response.getTransactionStatus());
	}
	
	@Test
	public void testMakePaymentFailed() {
		ResponseEntity<PaymentResponse> makePayment = paymentCotroller.makePayment(null);
		
		assertNotNull(makePayment);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, makePayment.getStatusCode());
	}
	
}
