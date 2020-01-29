package com.mindtree.mystay.booking.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mindtree.mystay.booking.model.Hotels;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@FeignClient(name = "mystay-catalog-service")
@RibbonClient(name = "mystay-catalog-service")
public interface CatalogServiceProxy {
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<Hotels> hotelById(@PathVariable String hotelId);

}
