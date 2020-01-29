package com.mindtree.mystay.payment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.mindtree.mystay.payment.model.PaymentRequest;
import com.mindtree.mystay.payment.model.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

/**
 * @author Paplu Patel(M1048008)
 *
 */


@Service
@PropertySource("classpath:application.properties")
public class PaymentServiceImpl implements PaymentService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;
	@PostConstruct
	public void init() {
		Stripe.apiKey = secretKey;
	}
	
	@Override
	public PaymentResponse makePayment(PaymentRequest payment) {
		logger.info("Payment Service");
		PaymentResponse paymentRecord = new PaymentResponse();
		Map<String, Object> paymentIntentParams = new HashMap<>();
		paymentIntentParams.put("amount", payment.getAmmount()*100);
		paymentIntentParams.put("currency", "INR");
		ArrayList<String> paymentMethodType = new ArrayList<>();
		paymentMethodType.add(payment.getPaymentType());
		paymentIntentParams.put("payment_method_types", paymentMethodType);
		paymentIntentParams.put("receipt_email", payment.getEmail());
		PaymentIntent paymentIntent = null;
		try {
			paymentIntent = PaymentIntent.create(paymentIntentParams);
			if(!paymentIntent.getId().isEmpty()||null!=paymentIntent.getId()) {
				paymentRecord.setTransactionStatus("success");
			} 
			paymentRecord.setTransactionId(paymentIntent.getId());
			paymentRecord.setAmmount(paymentIntent.getAmount());
			paymentRecord.setPaymentDate(new Date());
			paymentRecord.setEmail(payment.getEmail());
			paymentRecord.setPaymentType(payment.getPaymentType());
						
		} catch (StripeException e) {
			logger.error(e.getMessage());
		}
		return paymentRecord;
	}

}
