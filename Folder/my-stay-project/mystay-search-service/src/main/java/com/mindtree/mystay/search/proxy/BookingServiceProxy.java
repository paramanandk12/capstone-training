package com.mindtree.mystay.search.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.mystay.search.model.Booking;
import com.mindtree.mystay.search.model.BookingModel;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@FeignClient(name = "mystay-zuul-gateway")
@RibbonClient(name = "mystay-booking-service")
public interface BookingServiceProxy {

	@PostMapping("/mystay/booking/bookRoom/{hotelId}/{roomType}")
	public ResponseEntity<Booking> bookRoom(@RequestBody BookingModel bookingDetails, @PathVariable String hotelId,
			@PathVariable String roomType);

}
