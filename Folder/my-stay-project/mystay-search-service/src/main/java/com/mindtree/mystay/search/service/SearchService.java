package com.mindtree.mystay.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.mystay.search.model.Booking;
import com.mindtree.mystay.search.model.BookingModel;
import com.mindtree.mystay.search.model.Hotels;
import com.mindtree.mystay.search.model.Rooms;
import com.mindtree.mystay.search.model.SearchRequest;



/**
 * @author Rajanigandha Khot M1052150
 *
 */

@Service
public interface SearchService  {

	//Getting hotels based on user input like distance, priceRange, location
	public List<Hotels> findHotels(SearchRequest searchRequest);

	//Getting all available rooms based on hotelId
	public List<Rooms> findRooms(String hotelId);

	Booking callBookService(BookingModel bookingDetails, String hotelId, String roomType);
	
}
