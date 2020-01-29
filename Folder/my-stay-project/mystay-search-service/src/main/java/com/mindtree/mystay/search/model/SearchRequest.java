package com.mindtree.mystay.search.model;
/**
 * @author Rajanigandha Khot M1052150
 *
 */

public class SearchRequest {

	private String location;
	private Integer distance;
	private Integer minPrice;
	private Integer maxPrice;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	@Override
	public String toString() {
		return "SearchRequest [location=" + location + ", distance=" + distance + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + "]";
	}

}
