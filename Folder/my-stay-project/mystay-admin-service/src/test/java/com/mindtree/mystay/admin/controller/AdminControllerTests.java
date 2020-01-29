package com.mindtree.mystay.admin.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystay.admin.model.Address;
import com.mindtree.mystay.admin.model.Hotel;
import com.mindtree.mystay.admin.model.Offer;
import com.mindtree.mystay.admin.model.Room;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTests {

	@Autowired
	private AdminController adminController;
	
	@Test
	public void testAddHotels() {

		Address address = new Address();
		address.setCity("Bangalore");
		address.setPin(560037);
		address.setStreet("JP Nagar");
		address.setState("Karnataka");

		Room room = new Room();

		List<String> services = new ArrayList<String>();
		services.add("wi-fi");

		room.setAvailability("yes");
		room.setPrice(4500.00);
		room.setRoomNo("101");
		room.setRoomType("semi-luxury");
		room.setServices(services);
		room.setUrl(null);

		List<Room> rooms = new ArrayList<Room>();
		rooms.add(room);

		List<String> offers = new ArrayList<String>();
		offers.add("Flat Rs.100 OFF using LazyPay");

		Hotel hotel = new Hotel();
		hotel.setAddress(address);
		hotel.setDistance(3);
		hotel.setHotelId("1011");
		hotel.setHotelName("Stay ByHill");
		hotel.setOffers(offers);
		hotel.setRooms(rooms);

		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels.add(hotel);

		ResponseEntity<List<Hotel>> responseOk = adminController.addHotels(hotels);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());

	}

	@Test
	public void testupdateHotel() {
		Hotel hotel = new Hotel();
		hotel.setDistance(5);
		hotel.setHotelId("1011");

		ResponseEntity<Hotel> responseOk = adminController.updateHotel(hotel.getHotelId(), hotel);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());
		assertEquals(new Integer(5), responseOk.getBody().getDistance());
	}

	@Test
	public void testDeleteHotel() {

		String hotelId = "1011";
		
		ResponseEntity<String> responseOk = adminController.deleteHotel(hotelId);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());
		
	}
	
	@Test
	public void testUpgradeOffer() {

		Offer offer = new Offer();
		
		List<String> offers = new ArrayList<>();
		offers.add("10% OFF on UPI Payment");
		offers.add("Upto Rs.500 cashback on LazyPay");
		
		offer.setOffers(offers);
		offer.setOperation("add");
		
		String hotelId = "12";
		
		ResponseEntity<String> responseOk = adminController.upgradeOffer(offer, hotelId);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());
		
		offers = new ArrayList<>();
		offers.add("flat Rs.100 cachback using PayTm");
		
		offer.setOffers(offers);
		offer.setOperation("delete");
		
	    responseOk = adminController.upgradeOffer(offer, hotelId);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());
	}
	
	@Test
	public void testAddRooms() {


		List<String> services = new ArrayList<String>();
		services.add("wi-fi");
		
		List<Room> rooms = new ArrayList<>();
		
		Room room = new Room();
		room.setAvailability("yes");
		room.setPrice(25000.00);
		room.setRoomNo("309");
		room.setRoomType("luxury");
		room.setServices(services);
		
		rooms.add(room);
		
		String hotelId = "12";
		
		ResponseEntity<Hotel> responseOk = adminController.addRooms(hotelId, rooms);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());
		assertEquals(3, responseOk.getBody().getRooms().size());
	}
	
	@Test
	public void testUpdateRoom() {

		String hotelId = "12";
		String roomId = "309";
		
		Room room = new Room();
		room.setAvailability("no");
		
		ResponseEntity<Hotel> responseOk = adminController.updateRoom(room, hotelId, roomId);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());
	}
	
	@Test
	public void testDeleteRoom() {

		String hotelId = "12";
		String roomId = "309";
		
		ResponseEntity<Hotel> responseOk = adminController.deleteRoom(hotelId, roomId);
		assertEquals(HttpStatus.OK, responseOk.getStatusCode());
		assertEquals(2, responseOk.getBody().getRooms().size());
	}
}
