package com.mindtree.mystay.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystay.booking.entity.BookingEntity;
import com.mindtree.mystay.booking.exception.RoomNotAvailableException;
import com.mindtree.mystay.booking.model.Booking;
import com.mindtree.mystay.booking.model.Status;
import com.mindtree.mystay.booking.service.BookingServiceImpl;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

	@Autowired
	private BookingServiceImpl bookingService;

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
			Booking response = bookingService.bookRoom(bookingDetails, "1001", "semi-luxury");
			assertNotNull(response);
			assertEquals("Confirmed", response.getBookingStatus());

		} catch (RoomNotAvailableException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testCancelBookingNull() {
		String bookingId = "5cdc00251217bf594c9f5feb";
		Status cancelBooking = bookingService.cancelBooking(bookingId);
		assertNull(cancelBooking);
	}

//	@Test
//	public void testCancelBooking() {
//		String bookingId = "5cdd13981217bf721c109c11";
//		String expectedMessage = "Booking Id: " + bookingId + ", Successfully Cancelled.";
//		Status cancelBooking = bookingService.cancelBooking(bookingId);
//		assertNotNull(cancelBooking);
//		assertEquals(expectedMessage,cancelBooking.getMessage());
//		
//	}

}
