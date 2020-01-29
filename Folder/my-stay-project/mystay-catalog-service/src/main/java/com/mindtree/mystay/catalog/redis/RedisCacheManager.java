package com.mindtree.mystay.catalog.redis;

import org.springframework.stereotype.Component;

import com.mindtree.mystay.catalog.entity.HotelEntity;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@Component
public interface RedisCacheManager {

	public void cacheHotelByLocation(HotelEntity hotelEntity, String location);

	public void cacheHotelById(HotelEntity hotelEntity, String hotelId);
}
