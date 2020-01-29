package com.mindtree.mystay.search.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystay.search.model.Booking;
import com.mindtree.mystay.search.model.BookingModel;
import com.mindtree.mystay.search.model.Hotels;
import com.mindtree.mystay.search.model.Rooms;
import com.mindtree.mystay.search.model.SearchRequest;
import com.mindtree.mystay.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Rajanigandha Khot M1052150
 *
 */

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;

	/**
	 * Below method for getting all Hotels without rooms details based on Location
	 * 
	 * @param searchRequest : which contains location, distance, and price
	 * @return list of hotels without rooms details
	 */

	@HystrixCommand(fallbackMethod = "fallBackgetHotels", commandKey = "getHotelsByDisatncePrice()", groupKey = "getHotelsByDisatncePrice()")
	@PostMapping("/hotels")
	public ResponseEntity<List<Hotels>> getHotels(@RequestBody SearchRequest searchRequest)  {

		List<Hotels> hotels = searchService.findHotels(searchRequest);
		hotels.forEach(hotel -> {
			ControllerLinkBuilder roomLinkTo = linkTo(methodOn(this.getClass()).getRooms(hotel.getHotelId()));
			hotel.setUrl(roomLinkTo.toString());
		});
		return ResponseEntity.status(HttpStatus.OK)
				.header("Custom Header", "Hotels List for " + searchRequest.getLocation() + " location").body(hotels);
	}

	// fallback method for getHotels() method

	public ResponseEntity<List<Hotels>> fallBackgetHotels(@RequestBody SearchRequest searchRequest) {

		return ResponseEntity.notFound().header("Custom-Header", "Please provide the location").build();
	}

	// Getting all available rooms for particular hotelId

	@GetMapping("/rooms/{hotelId}")
	public List<Rooms> getRooms(@PathVariable String hotelId) {

		List<Rooms> findRooms = searchService.findRooms(hotelId);
		findRooms.forEach(rooms -> {
			ControllerLinkBuilder linkTo = linkTo(
					methodOn(this.getClass()).bookRoom(new BookingModel(), hotelId, rooms.getRoomType()));
			rooms.setUrl(linkTo.toString());
		});

		return findRooms;

	}

	/**
	 * This method is used to call Booking service to process booking.
	 * 
	 * @param bookingDetails
	 * @param hotelId
	 * @param roomType
	 * @return
	 */
	@PostMapping("/bookRoom/{hotelId}/{roomType}")
	public ResponseEntity<Booking> bookRoom(@RequestBody BookingModel bookingDetails, @PathVariable String hotelId,
			@PathVariable String roomType) {
		 Booking booking = searchService.callBookService(bookingDetails, hotelId, roomType);
		 if(booking != null) {
			 return ResponseEntity.status(HttpStatus.OK).body(booking);
		 }
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
