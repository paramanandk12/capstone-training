package com.mindtree.mystay.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystay.payment.model.PaymentRequest;
import com.mindtree.mystay.payment.model.PaymentResponse;
import com.mindtree.mystay.payment.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RestController
public class PaymentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PaymentService paymentService;

	@HystrixCommand(fallbackMethod = "makePaymentfallBack", groupKey = "makePayment", commandKey = "makePayment")
	@PostMapping("/makePayment")
	public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest payment) {
		logger.info("Payment Controller");
		PaymentResponse makePayment = paymentService.makePayment(payment);
		return ResponseEntity.status(HttpStatus.OK).body(makePayment);
	}

	private ResponseEntity<PaymentResponse> makePaymentfallBack(@RequestBody PaymentRequest payment) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
