package com.mindtree.mystay.catalog.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@JsonInclude(Include.NON_NULL) 
public class RoomsEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String roomNo;
	private String roomType;
	private String availability;
	private Double price;
	private List<String> services;
	private String url;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "RoomsEntity [roomNo=" + roomNo + ", roomType=" + roomType + ", availability=" + availability
				+ ", price=" + price + ", services=" + services + ", url=" + url + "]";
	}

}
