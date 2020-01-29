package com.mindtree.mystay.catalog.service;

import java.util.List;

import com.mindtree.mystay.catalog.entity.HotelEntity;
import com.mindtree.mystay.catalog.entity.RoomsEntity;
import com.mindtree.mystay.catalog.model.OfferRequest;
import com.mindtree.mystay.catalog.model.SearchRequest;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
public interface CatalogService {
	

	List<HotelEntity> getHotels(SearchRequest searchRequest);

	List<HotelEntity> addHotels(List<HotelEntity> hotelList);

	HotelEntity updateHotel(String hotelId, HotelEntity hotel);

	String deleteHotel(String hotelId);

	String upgradeOffer(String hotelId, OfferRequest offerRequest);

	HotelEntity addRooms(String hotelId, List<RoomsEntity> roomsEntity);

	HotelEntity updateRoom(String hotelId, String roomId, RoomsEntity room);

	HotelEntity deleteHotelRoom(String hotelId, String roomId);

	HotelEntity hotelById(String hotelId);

}
