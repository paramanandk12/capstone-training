package com.mindtree.mystay.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.mystay.search.entity.AddressEntity;

/**
 * @author Rajanigandha Khot (M1052150)
 *
 */
@JsonIgnoreProperties
public class Hotels {

	private String hotelId;
	private String hotelName;
	private AddressEntity address;
	private Integer distance;
	private Integer minPrice;
	private Integer maxPrice;
	private String url;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
