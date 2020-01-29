package com.mindtree.mystay.catalog.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@Document(collection = "Hotels")
@JsonInclude(Include.NON_NULL)
public class HotelEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String hotelId;
	private String hotelName;
	private AddressEntity address;
	private Integer distance;
	private List<String> offers;
	private List<RoomsEntity> rooms;

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

	public List<String> getOffers() {
		return offers;
	}

	public void setOffers(List<String> offers) {
		this.offers = offers;
	}

	public List<RoomsEntity> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomsEntity> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "HotelEntity [hotelId=" + hotelId + ", hotelName=" + hotelName + ", address=" + address + ", distance="
				+ distance + ", offers=" + offers + ", rooms=" + rooms + "]";
	}

}
