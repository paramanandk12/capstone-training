package com.mindtree.mystay.search.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.mystay.search.entity.HotelEntity;
import com.mindtree.mystay.search.entity.RoomsEntity;
import com.mindtree.mystay.search.model.Booking;
import com.mindtree.mystay.search.model.BookingModel;
import com.mindtree.mystay.search.model.Hotels;
import com.mindtree.mystay.search.model.Rooms;
import com.mindtree.mystay.search.model.SearchRequest;
import com.mindtree.mystay.search.proxy.BookingServiceProxy;
import com.mindtree.mystay.search.proxy.CatalogServiceProxy;

/**
 * @author Rajanigandha Khot M1052150
 *
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CatalogServiceProxy catalogServiceProxy;
	@Autowired
	private BookingServiceProxy bookingServiceProxy;

	private static Logger logger = LogManager.getLogger();
	static final String ROOM_BUDGET = "budget";
	static final String ROOM_LUXURY = "luxury";

	// Getting hotels based on user input like distance, priceRange, location
	@Override
	public List<Hotels> findHotels(SearchRequest searchRequest) {

		List<Hotels> hotelList = new ArrayList<>();
		Set<Hotels> hotelSet = new HashSet<>();
		List<HotelEntity> hotels = catalogServiceProxy.getHotels(searchRequest);

		if (searchRequest.getLocation() != null && searchRequest.getDistance() != null
				&& searchRequest.getMinPrice() != null && searchRequest.getMaxPrice() != null) {
			hotels.stream().forEach(hotel -> {
				if (hotel.getDistance() <= searchRequest.getDistance()) {
					Integer minPrice = 0;
					Integer maxPrice = 0;
					Hotels hotelModel = new Hotels();
					for (RoomsEntity roomEntity : hotel.getRooms()) {
						if (searchRequest.getMinPrice() <= roomEntity.getPrice()
								&& searchRequest.getMaxPrice() >= roomEntity.getPrice()) {
							if (ROOM_BUDGET.equalsIgnoreCase(roomEntity.getRoomType())) {
								minPrice = roomEntity.getPrice();
							} else {
								if (ROOM_LUXURY.equalsIgnoreCase(roomEntity.getRoomType()))
									maxPrice = roomEntity.getPrice();
								else if (maxPrice == 0)
									maxPrice = roomEntity.getPrice();
							}
							hotelModel.setHotelId(hotel.getHotelId());
							hotelModel.setHotelName(hotel.getHotelName());
							hotelModel.setAddress(hotel.getAddress());
							hotelModel.setDistance(hotel.getDistance());
							hotelModel.setMinPrice(minPrice);
							hotelModel.setMaxPrice(maxPrice);
							hotelSet.add(hotelModel);
						}
					}

				}

			});

		} else if (searchRequest.getLocation() != null && searchRequest.getDistance() != null
				&& searchRequest.getMinPrice() == null && searchRequest.getMaxPrice() == null) {
			hotels.stream().forEach(hotel -> {
				if (hotel.getDistance() <= searchRequest.getDistance()) {
					Integer minPrice = 0;
					Integer maxPrice = 0;
					Hotels hotelModel = new Hotels();
					for (RoomsEntity roomEntity : hotel.getRooms()) {
						if (ROOM_BUDGET.equalsIgnoreCase(roomEntity.getRoomType())) {
							minPrice = roomEntity.getPrice();
						} else {
							if (ROOM_LUXURY.equalsIgnoreCase(roomEntity.getRoomType()))
								maxPrice = roomEntity.getPrice();
							else if (maxPrice == 0)
								maxPrice = roomEntity.getPrice();
						}
						hotelModel.setHotelId(hotel.getHotelId());
						hotelModel.setHotelName(hotel.getHotelName());
						hotelModel.setAddress(hotel.getAddress());
						hotelModel.setDistance(hotel.getDistance());
						hotelModel.setMinPrice(minPrice);
						hotelModel.setMaxPrice(maxPrice);
						hotelSet.add(hotelModel);
					}

				}
			});
		} else if (searchRequest.getLocation() != null && searchRequest.getDistance() == null
				&& searchRequest.getMinPrice() == null && searchRequest.getMaxPrice() == null) {
			hotels.stream().forEach(hotel -> {
				Integer minPrice = 0;
				Integer maxPrice = 0;
				Hotels hotelModel = new Hotels();
				for (RoomsEntity roomEntity : hotel.getRooms()) {
					if (ROOM_BUDGET.equalsIgnoreCase(roomEntity.getRoomType())) {
						minPrice = roomEntity.getPrice();
					} else {
						if (ROOM_LUXURY.equalsIgnoreCase(roomEntity.getRoomType()))
							maxPrice = roomEntity.getPrice();
						else if (maxPrice == 0)
							maxPrice = roomEntity.getPrice();
					}
					hotelModel.setHotelId(hotel.getHotelId());
					hotelModel.setHotelName(hotel.getHotelName());
					hotelModel.setAddress(hotel.getAddress());
					hotelModel.setDistance(hotel.getDistance());
					hotelModel.setMinPrice(minPrice);
					hotelModel.setMaxPrice(maxPrice);
					hotelSet.add(hotelModel);
				}

			});
		}
		hotelList.addAll(hotelSet);
		return hotelList;
	}


	// Getting all available rooms based on hotelId
	@Override
	public List<Rooms> findRooms(String hotelId) {

		HotelEntity findRooms = catalogServiceProxy.hotelById(hotelId);
		List<Rooms> hotelList = new ArrayList<>();
		findRooms.getRooms().forEach(room -> {
			if (room.getAvailability().equals("yes")) {
				Rooms roomsModel = new Rooms();
				roomsModel.setHotelId(findRooms.getHotelId());
				roomsModel.setHotelName(findRooms.getHotelName());
				roomsModel.setAddress(findRooms.getAddress());
				roomsModel.setDistance(findRooms.getDistance());
				roomsModel.setOffers(findRooms.getOffers());
				roomsModel.setRoomNo(room.getRoomNo());
				roomsModel.setRoomType(room.getRoomType());
				roomsModel.setServices(room.getServices());
				roomsModel.setAvailability(room.getAvailability());
				roomsModel.setPrice(room.getPrice());
				hotelList.add(roomsModel);
			}
		});
		return hotelList;
	}

	@Override
	public Booking callBookService(BookingModel bookingDetails, String hotelId, String roomType) {
		Booking bookRoom = null;
		try {
			ResponseEntity<Booking> response = bookingServiceProxy.bookRoom(bookingDetails, hotelId, roomType);
			if (HttpStatus.OK.equals(response.getStatusCode())) {
				bookRoom = response.getBody();
			}
		} catch (Exception ex) {
			logger.error("Exception occur when calling Booking Service: " + ex);
		}
		return bookRoom;
	}
}
