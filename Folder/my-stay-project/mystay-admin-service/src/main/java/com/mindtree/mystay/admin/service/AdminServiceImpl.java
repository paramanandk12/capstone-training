package com.mindtree.mystay.admin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mindtree.mystay.admin.feignclientproxy.CatalogServiceProxy;
import com.mindtree.mystay.admin.model.Hotel;
import com.mindtree.mystay.admin.model.Offer;
import com.mindtree.mystay.admin.model.Room;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CatalogServiceProxy catalogServiceProxy;

	@Override
	public ResponseEntity<List<Hotel>> addHotels(List<Hotel> hotelList) {
		return catalogServiceProxy.addHotels(hotelList);
	}

	@Override
	public ResponseEntity<Hotel> updateHotel(String hotelId, Hotel hotel) {
		return catalogServiceProxy.updateHotel(hotelId, hotel);
	}

	@Override
	public ResponseEntity<String> deleteHotel(String hotelId) {
		return catalogServiceProxy.deleteHotel(hotelId);
	}

	@Override
	public ResponseEntity<Hotel> addRooms(String hotelId, List<Room> rooms) {
		return catalogServiceProxy.addRooms(hotelId, rooms);
	}

	@Override
	public ResponseEntity<Hotel> updateRoom(Room room, String hotelId, String roomId) {
		return catalogServiceProxy.updateRoom(room, hotelId, roomId);
	}

	@Override
	public ResponseEntity<Hotel> deleteRoom(String hotelId, String roomId) {
		return catalogServiceProxy.deleteRoom(hotelId, roomId);
	}

	@Override
	public ResponseEntity<String> upgradeOffer(Offer offer, String hotelId) {
		return catalogServiceProxy.upgradeOffer(offer, hotelId);
	}
}
