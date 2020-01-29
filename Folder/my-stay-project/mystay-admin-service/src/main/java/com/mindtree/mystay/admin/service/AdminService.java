package com.mindtree.mystay.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindtree.mystay.admin.model.Hotel;
import com.mindtree.mystay.admin.model.Offer;
import com.mindtree.mystay.admin.model.Room;

public interface AdminService {

	public ResponseEntity<List<Hotel>> addHotels(List<Hotel> hotelList);
	public ResponseEntity<Hotel> updateHotel(String hotelId, Hotel hotel);
	public ResponseEntity<String> deleteHotel(String hotelId);
	public ResponseEntity<Hotel> addRooms(String hotelId, List<Room> rooms);
	public ResponseEntity<Hotel> updateRoom(Room room, String hotelId, String roomId);
	public ResponseEntity<Hotel> deleteRoom(String hotelId, String roomId);
	public ResponseEntity<String> upgradeOffer(Offer offer, String hotelId);
}
