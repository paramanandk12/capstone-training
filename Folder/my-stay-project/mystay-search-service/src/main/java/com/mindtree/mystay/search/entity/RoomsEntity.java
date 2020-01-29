package com.mindtree.mystay.search.entity;

import java.util.List;

/**
 * @author Rajanigandha Khot M1052150
 *
 */
public class RoomsEntity {

	private String roomNo;
	private String roomType;
	private String availability;
	private Integer price;
	private List<String> services;
	private String bookingURL;

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public String getBookingURL() {
		return bookingURL;
	}

	public void setBookingURL(String bookingURL) {
		this.bookingURL = bookingURL;
	}

	@Override
	public String toString() {
		return "RoomsEntity [roomNo=" + roomNo + ", roomType=" + roomType + ", availability=" + availability
				+ ", price=" + price + ", services=" + services + ", bookingURL=" + bookingURL + "]";
	}

	
}
