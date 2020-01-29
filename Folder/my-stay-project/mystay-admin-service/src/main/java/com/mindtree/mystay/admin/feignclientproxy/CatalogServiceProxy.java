package com.mindtree.mystay.admin.feignclientproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.mystay.admin.model.Hotel;
import com.mindtree.mystay.admin.model.Offer;
import com.mindtree.mystay.admin.model.Room;

/**
 * @author Dhruvam Gupta M1052242
 *
 * May 18, 2019
 */

@FeignClient(name = "mystay-catalog-service", primary = false)
@RibbonClient("mystay-catalog-service")
public interface CatalogServiceProxy {
	
	@PostMapping("/add-hotels")
	public ResponseEntity<List<Hotel>> addHotels(@RequestBody List<Hotel> hotelList);
	
	@PutMapping("/update-hotel/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel);
	
	@DeleteMapping("/delete-hotel/{hotelId}")
	public ResponseEntity<String> deleteHotel(@PathVariable String hotelId);

	@PostMapping("/upgrade-offers/{hotelId}")
	public ResponseEntity<String> upgradeOffer(@RequestBody Offer offer, @PathVariable String hotelId);
	
	@PostMapping("/add-rooms/{hotelId}")
	public ResponseEntity<Hotel> addRooms(@PathVariable String hotelId, @RequestBody List<Room> rooms);
	
	@PutMapping("/update-room/{hotelId}/{roomId}")
	public ResponseEntity<Hotel> updateRoom(@RequestBody Room room, @PathVariable String hotelId, @PathVariable String roomId);
	
	@DeleteMapping("/delete-room/{hotelId}/{roomId}")
	public ResponseEntity<Hotel> deleteRoom(@PathVariable String hotelId, @PathVariable String roomId);
}