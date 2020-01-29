package com.mindtree.mystay.catalog.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.mindtree.mystay.catalog.config.RedisUtil;
import com.mindtree.mystay.catalog.entity.HotelEntity;
import com.mindtree.mystay.catalog.util.RedisName;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@Configuration
public class RedisCacheManagerImpl implements RedisCacheManager {

	@Autowired
	private RedisUtil<HotelEntity> redisUtilHotel;

	@Override
	public void cacheHotelByLocation(HotelEntity hotelEntity, String location) {
		redisUtilHotel.putMap(RedisName.HOTEL_BY_LOCATION + location, RedisName.HOTEL_ID + hotelEntity.getHotelId(),
				hotelEntity);
		redisUtilHotel.setExpire(RedisName.HOTEL_BY_LOCATION + location, 1, TimeUnit.HOURS);
	}

	@Override
	public void cacheHotelById(HotelEntity hotelEntity, String hotelId) {
		redisUtilHotel.putMap(RedisName.HOTEL_BY_ID.toString(), RedisName.HOTEL_ID + hotelEntity.getHotelId(),
				hotelEntity);
		redisUtilHotel.setExpire(RedisName.HOTEL_BY_ID.toString(), 1, TimeUnit.HOURS);
	}

}
