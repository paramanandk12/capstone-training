package com.mindtree.mystay.booking.service;

import org.springframework.stereotype.Service;

import com.mindtree.mystay.booking.entity.BookingEntity;
import com.mindtree.mystay.booking.exception.RoomNotAvailableException;
import com.mindtree.mystay.booking.model.Booking;
import com.mindtree.mystay.booking.model.Status;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@Service
public interface BookingService {
	
	Booking bookRoom(BookingEntity bookingDetails, String hotelId, String roomType) throws RoomNotAvailableException;

//	Payment makePayment(Payment payment);

	Status cancelBooking(String bookingId);

}
