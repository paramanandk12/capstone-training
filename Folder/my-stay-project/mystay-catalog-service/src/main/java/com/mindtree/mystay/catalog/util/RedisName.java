package com.mindtree.mystay.catalog.util;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
public enum RedisName {

	HOTEL_BY_LOCATION("HotelLocation_"), HOTEL_BY_ID("HotelId"), HOTEL_ID("Hotel_");

	private String value;

	private RedisName(String value) {
		this.value = value;
	}
}