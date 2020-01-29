package com.mindtree.mystay.catalog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystay.catalog.entity.AddressEntity;
import com.mindtree.mystay.catalog.entity.HotelEntity;
import com.mindtree.mystay.catalog.entity.RoomsEntity;
import com.mindtree.mystay.catalog.model.OfferRequest;
import com.mindtree.mystay.catalog.model.SearchRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogControllerTest {

	@Autowired
	private CatalogServiceController catalogServiceController;
	
	@Test
	public void testWelcome() {
	
		ResponseEntity<String> response = catalogServiceController.welcome();
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetHotels()
	{
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setLocation("Bangalore");
		
		ResponseEntity<List<HotelEntity>> response = catalogServiceController.getHotels(searchRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		searchRequest.setLocation("Kolkata");
		response = catalogServiceController.getHotels(searchRequest);
		assertEquals(3, response.getBody().size());
		
		searchRequest.setLocation("Ahmedabad");
		response = catalogServiceController.getHotels(searchRequest);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
	}
	
	@Test
	public void testAddHotels() {

		AddressEntity address = new AddressEntity();
		address.setCity("Delhi");
		address.setPin(560037);
		address.setStreet("Rohini");
		address.setState("Delhi");

		RoomsEntity room = new RoomsEntity();

		List<String> services = new ArrayList<String>();
		services.add("wi-fi");

		room.setAvailability("yes");
		room.setPrice(4500.00);
		room.setRoomNo("101");
		room.setRoomType("semi-luxury");
		room.setServices(services);
		room.setUrl(null);

		List<RoomsEntity> rooms = new ArrayList<RoomsEntity>();
		rooms.add(room);

		List<String> offers = new ArrayList<String>();
		offers.add("Flat Rs.100 OFF using LazyPay");

		HotelEntity hotel = new HotelEntity();
		hotel.setAddress(address);
		hotel.setDistance(3);
		hotel.setHotelId("1025");
		hotel.setHotelName("Hotel Southern");
		hotel.setOffers(offers);
		hotel.setRooms(rooms);

		List<HotelEntity> hotels = new ArrayList<HotelEntity>();
		hotels.add(hotel);

		ResponseEntity<List<HotelEntity>> response = catalogServiceController.addHotels(hotels);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		hotels = null;
		response = catalogServiceController.addHotels(hotels);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testAddRooms() {


		List<String> services = new ArrayList<String>();
		services.add("wi-fi");
		
		List<RoomsEntity> rooms = new ArrayList<>();
		
		RoomsEntity room = new RoomsEntity();
		room.setAvailability("yes");
		room.setPrice(25000.00);
		room.setRoomNo("313");
		room.setRoomType("luxury");
		room.setServices(services);
		
		rooms.add(room);
		
		String hotelId = "1003";
		
		ResponseEntity<HotelEntity> response = catalogServiceController.addRooms(hotelId, rooms);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(4,response.getBody().getRooms().size());
		
		hotelId = null;
		response = catalogServiceController.addRooms(hotelId, rooms);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testUpdateHotel() {
		HotelEntity hotel = new HotelEntity();
		hotel.setDistance(5);
		hotel.setHotelId("1003");

		ResponseEntity<HotelEntity> response = catalogServiceController.updateHotel(hotel.getHotelId(), hotel);
		assertEquals(new Integer(5), response.getBody().getDistance());
		assertEquals("Homestay", response.getBody().getHotelName());
	}
	
	
	@Test
	public void testUpdateRoom() {

		String hotelId = "12";
		String roomId = "305";
		
		RoomsEntity room = new RoomsEntity();
		room.setAvailability("yes");
		
		ResponseEntity<HotelEntity> response = catalogServiceController.updateRoom(room, hotelId, roomId);
		assertNotNull(response.getBody());
		
		roomId = null;
		room = null;
		hotelId = null;
		response = catalogServiceController.updateRoom(room, hotelId, roomId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testDeleteRoom() {

		String hotelId = "12";
		String roomId = "310";
		
		ResponseEntity<HotelEntity> response = catalogServiceController.deleteHotelRoom(hotelId, roomId);
		assertNotNull(response.getBody());
		assertEquals(3, response.getBody().getRooms().size());
		
		hotelId = null;
		response = catalogServiceController.deleteHotelRoom(hotelId, roomId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testUpgradeOffer() {

		OfferRequest offer = new OfferRequest();
		
		List<String> offers = new ArrayList<>();
		offers.add("Upto Rs.500 cashback on LazyPay");
		
		offer.setOffers(offers);
		offer.setOperation("add");
		
		String hotelId = "1003";
		
		ResponseEntity<String> response = catalogServiceController.upgradeOffer(offer, hotelId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Offers Updated", response.getBody());
		
		offers = new ArrayList<>();
		offers.add("10% OFF on UPI Payment");
		offer.setOffers(offers);
		offer.setOperation("delete");
		
	    response = catalogServiceController.upgradeOffer(offer, hotelId);
	    assertNotNull(response);
		assertEquals("Offers Updated", response.getBody());
		
		hotelId = "1010";
		response = catalogServiceController.upgradeOffer(offer, hotelId);
		assertEquals(HttpStatus.NOT_FOUND,response. getStatusCode());
	}
	
	@Test
	public void testDeleteHotel() {

		String hotelId = "1010";
		
		ResponseEntity<String> response = catalogServiceController.deleteHotel(hotelId);
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND,response. getStatusCode());
		
		hotelId = "12345";
		response = catalogServiceController.deleteHotel(hotelId);
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND,response. getStatusCode());
		
	}
}
