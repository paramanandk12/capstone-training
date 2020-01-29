package com.mindtree.mystay.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystay.admin.feignclientproxy.CatalogServiceProxy;
import com.mindtree.mystay.admin.model.Hotel;
import com.mindtree.mystay.admin.model.Offer;
import com.mindtree.mystay.admin.model.Room;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Dhruvam Gupta M1052242
 *
 *         May 18, 2019
 */

@RestController
public class AdminController {

	private static final String MESSAGE = "Message";

	@Autowired
	private CatalogServiceProxy catalogServiceProxy;

	/*
	 * adding hotels
	 *
	 */

	@PostMapping("/add-hotels")
	@HystrixCommand(fallbackMethod = "addHotelsFallback", commandKey = "addHotelsCommandKey", groupKey = "addHotelsGroupKey")
	public ResponseEntity<List<Hotel>> addHotels(@RequestBody List<Hotel> hotelList) {
		return catalogServiceProxy.addHotels(hotelList);
	}

	/*
	 * adding hotels fallback method
	 *
	 */

	public ResponseEntity<List<Hotel>> addHotelsFallback(List<Hotel> hotelList) {
		return ResponseEntity.badRequest().body(hotelList);
	}

	/*
	 * updating hotel
	 *
	 */

	@PutMapping("/upd-hotel/{hotelId}")
	@HystrixCommand(fallbackMethod = "updateHotelFallback", commandKey = "updateHotelCommandKey", groupKey = "updateHotelGroupKey")
	public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel) {
		return catalogServiceProxy.updateHotel(hotelId, hotel);
	}

	/*
	 * updating hotel fallback method
	 *
	 */

	public ResponseEntity<Hotel> updateHotelFallback(String hotelId, Hotel hotel) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.header(MESSAGE, "Could not update hotel with id:" + hotelId).body(hotel);
	}

	/*
	 * deleting hotel
	 *
	 */

	@DeleteMapping("/del-hotel/{hotelId}")
	@HystrixCommand(fallbackMethod = "deleteHotelFallback", commandKey = "deleteHotelCommandKey", groupKey = "deleteHotelGroupKey")
	public ResponseEntity<String> deleteHotel(@PathVariable String hotelId) {
		return catalogServiceProxy.deleteHotel(hotelId);
	}

	/*
	 * deleting hotel fallback method
	 *
	 */

	public ResponseEntity<String> deleteHotelFallback(String hotelId) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not delete hotel:" + hotelId);
	}

	/*
	 * adding rooms
	 *
	 */

	@PostMapping("/add-rooms/{hotelId}")
	@HystrixCommand(fallbackMethod = "addRoomsFallback", commandKey = "addRoomsCommandKey", groupKey = "addRoomsGroupKey")
	public ResponseEntity<Hotel> addRooms(@PathVariable String hotelId, @RequestBody List<Room> rooms) {
		return catalogServiceProxy.addRooms(hotelId, rooms);
	}

	/*
	 * adding rooms fallback method
	 *
	 */

	public ResponseEntity<Hotel> addRoomsFallback(String hotelId, List<Room> rooms) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).header(MESSAGE, "Could not add rooms"+rooms.toString()+" to hotel:" + hotelId)
				.build();
	}

	/*
	 * updating room
	 *
	 */

	@PutMapping("/upd-room/{hotelId}/{roomId}")
	@HystrixCommand(fallbackMethod = "updateRoomFallback", commandKey = "updateRoomCommandKey", groupKey = "updateRoomGroupKey")
	public ResponseEntity<Hotel> updateRoom(@RequestBody Room room, @PathVariable String hotelId,
			@PathVariable String roomId) {
		return catalogServiceProxy.updateRoom(room, hotelId, roomId);
	}

	/*
	 * updating room fallback method
	 *
	 */

	public ResponseEntity<Hotel> updateRoomFallback(Room room, String hotelId, String roomId) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).header(MESSAGE, "Could not update room:" + roomId
				+ " with room details " + room.toString() + " from hotel with hotel id:" + hotelId).build();
	}

	/*
	 * deleting room
	 *
	 */

	@DeleteMapping("/del-room/{hotelId}/{roomId}")
	@HystrixCommand(fallbackMethod = "deleteRoomFallback", commandKey = "deleteRoomCommandKey", groupKey = "deleteRoomGroupKey")
	public ResponseEntity<Hotel> deleteRoom(@PathVariable String hotelId, @PathVariable String roomId) {
		return catalogServiceProxy.deleteRoom(hotelId, roomId);
	}

	/*
	 * deleting room fallback method
	 *
	 */

	public ResponseEntity<Hotel> deleteRoomFallback(String hotelId, String roomId) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.header(MESSAGE, "Could not delete room:" + roomId + " from hotel with hotel id:" + hotelId).build();
	}

	/*
	 * upgrading offers
	 *
	 */

	@PostMapping("/upgrade-offer/{hotelId}")
	@HystrixCommand(fallbackMethod = "upgradeOfferFallback", commandKey = "upgradeOfferCommandKey", groupKey = "upgradeOfferGroupKey")
	public ResponseEntity<String> upgradeOffer(@RequestBody Offer offer, @PathVariable String hotelId) {
		return catalogServiceProxy.upgradeOffer(offer, hotelId);
	}

	/*
	 * upgrading offer fallback method
	 *
	 */

	public ResponseEntity<String> upgradeOfferFallback(Offer offer, String hotelId) {
		return ResponseEntity.badRequest().body(
				"Could not upgrade offer:" + offer.getOffers().toString() + " for hotel with hotel id:" + hotelId);
	}

}
