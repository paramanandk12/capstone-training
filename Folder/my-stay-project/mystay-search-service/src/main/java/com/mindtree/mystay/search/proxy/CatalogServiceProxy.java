package com.mindtree.mystay.search.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.mystay.search.entity.HotelEntity;
import com.mindtree.mystay.search.model.SearchRequest;
/**
 * @author Rajanigandha Khot M1052150
 *
 */

@FeignClient(name = "mystay-catalog-service")
@RibbonClient(name = "mystay-catalog-service")
public interface CatalogServiceProxy {

	@PostMapping("/all-hotels")
	public List<HotelEntity> getHotels(@RequestBody SearchRequest searchRequest);
	
	@GetMapping("/hotel/{hotelId}")
	public HotelEntity hotelById(@PathVariable String hotelId);
}
