package com.mindtree.mystay.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystay.booking.controller.BookingController;
import com.mindtree.mystay.booking.entity.BookingEntity;
import com.mindtree.mystay.booking.exception.RoomNotAvailableException;
import com.mindtree.mystay.booking.model.Booking;
import com.mindtree.mystay.booking.model.Status;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingControllerTest {

	@Autowired
	private BookingController bookingController;

	@Test
	public void testBookRoomFailed() throws ParseException {

		BookingEntity bookingDetails = new BookingEntity();
		bookingDetails.setUserId("200112");
		bookingDetails.setNoOfPersons(2);
		bookingDetails.setNoOfRooms(1);
		bookingDetails.setEmail("abc@gmail.com");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		bookingDetails.setCheckInDate(formatter.parse("2019-05-29"));
		bookingDetails.setCheckOutDate(formatter.parse("2019-06-05"));

		try {
			ResponseEntity<Booking> response = bookingController.bookRoom(bookingDetails, "1001", "budget");
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			Booking bookRoom = response.getBody();
			assertNotNull(bookRoom);
			assertNotNull(bookRoom.getStatus());
			assertEquals("Failed to Book Room. Please Try once again.", bookRoom.getStatus().getMessage());

		} catch (RoomNotAvailableException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testBookRoom() throws ParseException {

		BookingEntity bookingDetails = new BookingEntity();
		bookingDetails.setUserId("200112");
		bookingDetails.setNoOfPersons(2);
		bookingDetails.setNoOfRooms(1);
		bookingDetails.setEmail("abc@gmail.com");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		bookingDetails.setCheckInDate(formatter.parse("2019-05-29"));
		bookingDetails.setCheckOutDate(formatter.parse("2019-06-05"));

		try {
			ResponseEntity<Booking> response = bookingController.bookRoom(bookingDetails, "1001", "semi-luxury");
			assertEquals(HttpStatus.OK, response.getStatusCode());
			Booking bookRoom = response.getBody();
			assertNotNull(bookRoom);
			assertEquals("Confirmed", bookRoom.getBookingStatus());
		} catch (RoomNotAvailableException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testBookRoomException() throws ParseException {

		BookingEntity bookingDetails = new BookingEntity();
		bookingDetails.setUserId("200112");
		bookingDetails.setNoOfPersons(2);
		bookingDetails.setNoOfRooms(1);
		bookingDetails.setEmail("abc@gmail.com");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		bookingDetails.setCheckInDate(formatter.parse("2019-06-10"));
		bookingDetails.setCheckOutDate(formatter.parse("2019-06-15"));
		ResponseEntity<Booking> response = null;
		try {
			response = bookingController.bookRoom(bookingDetails, "1004", "semi-luxury");

		} catch (RoomNotAvailableException e) {
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
			assertEquals("This room is not available. Please try for other rooms.", e.getMessage());
		}
	}

	@Test
	public void testCancelBookingFailed() {
		String expectedMessage = "Booking Cancelation Failed. Please Try once again.";
		Status cancelBooking = bookingController.cancelBooking(null);
		assertNotNull(cancelBooking);
		assertEquals(expectedMessage, cancelBooking.getMessage());

	}

}
