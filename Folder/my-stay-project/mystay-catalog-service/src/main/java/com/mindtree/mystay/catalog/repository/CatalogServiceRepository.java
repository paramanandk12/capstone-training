package com.mindtree.mystay.catalog.repository;

import java.util.List;

import com.mindtree.mystay.catalog.entity.HotelEntity;
import com.mindtree.mystay.catalog.model.SearchRequest;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
public interface CatalogServiceRepository {

	List<HotelEntity> getHotels(SearchRequest searchRequest);

	List<HotelEntity> addHotels(List<HotelEntity> hotelList);

	HotelEntity updateHotel(HotelEntity hotel);

	HotelEntity getHotelById(String hotelId);

	String deleteHotel(String hotelId);

}
