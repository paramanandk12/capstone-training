package com.mindtree.mystay.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystay.catalog.entity.HotelEntity;
import com.mindtree.mystay.catalog.entity.RoomsEntity;
import com.mindtree.mystay.catalog.exceptions.HotelNotFound;
import com.mindtree.mystay.catalog.model.OfferRequest;
import com.mindtree.mystay.catalog.model.SearchRequest;
import com.mindtree.mystay.catalog.service.CatalogService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@RestController
public class CatalogServiceController {

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.status(HttpStatus.OK).header("Custom-Header-Welcome", "List of hotels")
				.body("Welcome to Mystay App");
	}

	// *********************
	// fetching hotels based on location
	// *********************
	@HystrixCommand(fallbackMethod = "getHotelsfallBack", groupKey = "getHotels()", commandKey = "getHotels()")
	@PostMapping("/all-hotels")
	public ResponseEntity<List<HotelEntity>> getHotels(@RequestBody SearchRequest searchRequest) {
		List<HotelEntity> hotels = catalogService.getHotels(searchRequest);
		if (!hotels.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).header("Custom-Header-getHotels", "List of hotels")
					.body(hotels);
		else
			throw new HotelNotFound("No Hotels found in this location please try with different location");
	}

	// *********************
	// fetching hotels based on location fallback
	// *********************
	public ResponseEntity<List<HotelEntity>> getHotelsfallBack(@RequestBody SearchRequest searchRequest) {
		return ResponseEntity.notFound().header("Custom-Header-getHotels",
				"No Hotels found in" + searchRequest.getLocation() + "location please try with different location")
				.build();
	}

	// *********************
	// adding hotels
	// *********************

	@HystrixCommand(fallbackMethod = "addHotelsfallBack", groupKey = "addHotels()", commandKey = "addHotels()")
	@PostMapping("/add-hotels")
	public ResponseEntity<List<HotelEntity>> addHotels(@RequestBody List<HotelEntity> hotelList) {
		List<HotelEntity> hotels = catalogService.addHotels(hotelList);
		if (!hotels.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).header("Custom-Header-addHotels", "Hotels added succesfully.")
					.body(hotels);
		else
			throw new HotelNotFound("No Hotel Added.");
	}

	// *****************************
	// adding hotels fallback method
	// *****************************
	public ResponseEntity<List<HotelEntity>> addHotelsfallBack(@RequestBody List<HotelEntity> hotelList) {
		return ResponseEntity.notFound().header("Custom-Header-addHotels", "Hotels " + hotelList + " Not Added.")
				.build();
	}

	// ************************
	// update hotels
	// ************************
	@HystrixCommand(fallbackMethod = "updateHotelfallBack", groupKey = "updateHotel()", commandKey = "updateHotels()")
	@PutMapping("/update-hotel/{hotelId}")
	public ResponseEntity<HotelEntity> updateHotel(@PathVariable String hotelId, @RequestBody HotelEntity hotel) {

		HotelEntity hotelEntity = catalogService.updateHotel(hotelId, hotel);
		if (hotelEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.header("Custom-Header-updateHotel", "Hotels updated succesfully.").body(hotelEntity);
		else
			throw new HotelNotFound("Hotel is not Updated.");

	}

	// ************************
	// update hotels fallback
	// ************************
	public ResponseEntity<HotelEntity> updateHotelfallBack(@PathVariable String hotelId,
			@RequestBody HotelEntity hotel) {
		return ResponseEntity.notFound().header("Custom-Header-updateHotel",
				"Hotel " + hotelId + " " + hotel.getHotelName() + " is not Updated.").build();
	}

	// *********************
	// delete hotels
	// **********************
	@HystrixCommand(fallbackMethod = "deleteHotelsfallBack", groupKey = "deleteHotels()", commandKey = "deleteHotels()")
	@DeleteMapping("/delete-hotel/{hotelId}")
	public ResponseEntity<String> deleteHotel(@PathVariable String hotelId) {
		String msg = catalogService.deleteHotel(hotelId);
		if ("Hotel deleted Succesfully.".equals(msg))
			return ResponseEntity.status(HttpStatus.OK)
					.header("Custom-Header-deleteHotel", "Hotel deleted Succesfully.").body(msg);
		else
			throw new HotelNotFound(msg);
	}

	// ***********************
	// delete hotels fallback
	// ***********************
	public ResponseEntity<String> deleteHotelsfallBack(@PathVariable String hotelId) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("Custom-Header-deleteHotel", "Hotel with " + hotelId + " not deleted.").build();
	}

	// *********************
	// upgrade offer
	// **********************
	@HystrixCommand(fallbackMethod = "upgradeOfferfallBack", groupKey = "upgradeOffer()", commandKey = "upgradeOffer()")
	@PostMapping("/upgrade-offers/{hotelId}")
	public ResponseEntity<String> upgradeOffer(@RequestBody OfferRequest offerRequest, @PathVariable String hotelId) {

		String msg = catalogService.upgradeOffer(hotelId, offerRequest);
		if ("Offers Updated".equals(msg))
			return ResponseEntity.status(HttpStatus.OK).header("Custom-Header-upgradeOffer", "Offers Updated")
					.body(msg);
		else
			throw new HotelNotFound("Offers not Added");

	}

	// *********************
	// upgrade offer fallback
	// **********************
	public ResponseEntity<String> upgradeOfferfallBack(@RequestBody OfferRequest offerRequest,
			@PathVariable String hotelId) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("Custom-Header-upgradeOffer", offerRequest.getOffers() + " not Added for hotel id: " + hotelId)
				.build();
	}

	// *********************
	// Add Room
	// **********************
	@HystrixCommand(fallbackMethod = "addRoomsfallBack", groupKey = "addRooms()", commandKey = "addRooms()")
	@PostMapping("/add-rooms/{hotelId}")
	public ResponseEntity<HotelEntity> addRooms(@PathVariable String hotelId, @RequestBody List<RoomsEntity> rooms) {

		HotelEntity addRooms = catalogService.addRooms(hotelId, rooms);
		if (addRooms != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.header("Custom-Header-addRooms", "Hotel Room added Successfully").body(addRooms);
		} else {
			throw new HotelNotFound("Hotel Room Cannot be added");
		}
	}

	// *********************
	// Add Room fallback
	// **********************
	public ResponseEntity<HotelEntity> addRoomsfallBack(@PathVariable String hotelId,
			@RequestBody List<RoomsEntity> rooms) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Custom-Header-addRoooms",
				"Hotel Room in hotel " + hotelId + " Cannot be added with details :" + rooms).build();
	}

	// *********************
	// Update Room
	// **********************
	@HystrixCommand(fallbackMethod = "updateRoomfallBack", groupKey = "updateRoom()", commandKey = "updateRoom()")
	@PutMapping("/update-room/{hotelId}/{roomId}")
	public ResponseEntity<HotelEntity> updateRoom(@RequestBody RoomsEntity room, @PathVariable String hotelId,
			@PathVariable String roomId) {

		HotelEntity updateRoom = catalogService.updateRoom(hotelId, roomId, room);
		if (updateRoom != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.header("Custom-Header-updateRoom", "Hotel Room added Successfully").body(updateRoom);
		} else {
			throw new HotelNotFound("Hotel Room Cannot be updated");
		}
	}

	// *********************
	// Update rooms fallback
	// **********************
	public ResponseEntity<HotelEntity> updateRoomfallBack(@RequestBody RoomsEntity room, @PathVariable String hotelId,
			@PathVariable String roomId) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Custom-Header-updateRoom", "Hotel Room for "
				+ hotelId + " Cannot be updated for room having Id as:" + roomId + "and details as : " + room).build();
	}

	// *********************
	// Delete Room
	// **********************
	@HystrixCommand(fallbackMethod = "deleteHotelRoomfallBack", groupKey = "deleteHotelRoom()", commandKey = "deleteHotelRoom()")
	@DeleteMapping("/delete-room/{hotelId}/{roomId}")
	public ResponseEntity<HotelEntity> deleteHotelRoom(@PathVariable String hotelId, @PathVariable String roomId) {

		HotelEntity deleteHotelRoom = catalogService.deleteHotelRoom(hotelId, roomId);
		if (deleteHotelRoom != null)
			return ResponseEntity.status(HttpStatus.OK)
					.header("Custom-Header-deleteRoom", "Hotel Rooms Successfully deleted.").body(deleteHotelRoom);
		else {
			throw new HotelNotFound("Hotel Rooms Not deleted");
		}
	}

	// *********************
	// Delete Room fallback
	// **********************
	public ResponseEntity<HotelEntity> deleteHotelRoomfallBack(@PathVariable String hotelId,
			@PathVariable String roomId) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("Custom-Header-deleteRoom", "Room no: " + roomId + " in Hotel: " + hotelId + " Not deleted")
				.build();
	}

	// *********************
	// Get Hotel by Id
	// **********************
	@HystrixCommand(fallbackMethod = "hotelByIdfallBack", groupKey = "hotelById()", commandKey = "hotelById()")
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<HotelEntity> hotelById(@PathVariable String hotelId) {
		HotelEntity hotelById = catalogService.hotelById(hotelId);
		if (hotelById != null) {
			return ResponseEntity.status(HttpStatus.OK).header("Custom-Header-getHotelbyId", "Hotel by Id")
					.body(hotelById);
		} else
			throw new HotelNotFound("Hotel not found..!!");
	}

	// ************************
	// Get Hotel by Id fallback
	// ************************
	public ResponseEntity<HotelEntity> hotelByIdfallBack(@PathVariable String hotelId) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("Custom-Header-getHotelId", "Hotel having" + hotelId + " not found..!!").build();
	}
}
