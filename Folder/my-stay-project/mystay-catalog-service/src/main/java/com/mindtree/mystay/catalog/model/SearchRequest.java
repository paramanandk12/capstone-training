package com.mindtree.mystay.catalog.model;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
public class SearchRequest {

	private String location;
	private Integer distance;
	private Integer price;

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SearchRequest [location=" + location + ", distance=" + distance + ", price=" + price + "]";
	}

}
