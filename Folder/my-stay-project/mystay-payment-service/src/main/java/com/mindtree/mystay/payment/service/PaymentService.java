package com.mindtree.mystay.payment.service;

import org.springframework.stereotype.Service;

import com.mindtree.mystay.payment.model.PaymentRequest;
import com.mindtree.mystay.payment.model.PaymentResponse;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@Service
public interface PaymentService {

	PaymentResponse makePayment(PaymentRequest payment);

}
