package com.mindtree.mystay.booking.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.mystay.booking.model.PaymentRequest;
import com.mindtree.mystay.booking.model.PaymentResponse;


/**
 * @author Paplu Patel(M1048008)
 *
 */
@FeignClient(name = "mystay-payment-service")
@RibbonClient(name = "mystay-payment-service")
public interface PaymentServiceProxy {

	@PostMapping("/makePayment")
	public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest payment);

}
