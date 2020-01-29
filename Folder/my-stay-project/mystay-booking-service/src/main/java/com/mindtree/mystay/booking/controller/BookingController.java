package com.mindtree.mystay.booking.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystay.booking.entity.BookingEntity;
import com.mindtree.mystay.booking.exception.RoomNotAvailableException;
import com.mindtree.mystay.booking.model.Booking;
import com.mindtree.mystay.booking.model.Links;
import com.mindtree.mystay.booking.model.Status;
import com.mindtree.mystay.booking.service.BookingService;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * This method expecting a request body to book a room by processing the
	 * request.
	 * 
	 * @param bookingDetails
	 * @param hotelId
	 * @param roomType
	 * @return
	 * @throws RoomNotAvailableException
	 */
	@PostMapping("/bookRoom/{hotelId}/{roomType}")
	public ResponseEntity<Booking> bookRoom(@Valid @RequestBody BookingEntity bookingDetails, @PathVariable String hotelId,
			@PathVariable String roomType) throws RoomNotAvailableException {

		logger.info("Inside bookRoom method.");
		Booking bookRoom = bookingService.bookRoom(bookingDetails, hotelId, roomType);

		if (bookRoom != null && bookRoom.getBookingReferenceId() != null) {
			ControllerLinkBuilder linkTo = linkTo(
					methodOn(this.getClass()).cancelBooking(bookRoom.getBookingReferenceId()));
			Links links = new Links();
			links.setCancelBooking(linkTo.toString());
			bookRoom.setLinks(links);

			return ResponseEntity.status(HttpStatus.OK).body(bookRoom);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bookRoom);
	}
	
	/**
	 * Below method is used to cancel the booking by passing booking Id in it.
	 * 
	 * @param bookingId
	 * @return
	 */
	@GetMapping("/cancelBooking/{bookingId}")
	public Status cancelBooking(@PathVariable String bookingId) {
		return bookingService.cancelBooking(bookingId);
	}
}
