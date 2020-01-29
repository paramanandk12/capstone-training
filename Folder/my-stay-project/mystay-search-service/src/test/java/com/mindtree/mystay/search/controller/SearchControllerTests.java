package com.mindtree.mystay.search.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystay.search.controller.SearchController;
import com.mindtree.mystay.search.model.Booking;
import com.mindtree.mystay.search.model.BookingModel;
import com.mindtree.mystay.search.model.Hotels;
import com.mindtree.mystay.search.model.Links;
import com.mindtree.mystay.search.model.Rooms;
import com.mindtree.mystay.search.model.SearchRequest;
import com.mindtree.mystay.search.model.Status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTests {

    @Autowired
	private SearchController searchController;

	@Test
	public void testGetHotels()
	{
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setLocation("Bangalore");
		searchRequest.toString();
		ResponseEntity<List<Hotels>> response = searchController.getHotels(searchRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<Hotels> hotelList = response.getBody();
		hotelList.get(0).getAddress();
		hotelList.get(0).getHotelName();
		hotelList.get(0).getDistance();
		hotelList.get(0).getMinPrice();
		hotelList.get(0).getMaxPrice();
		hotelList.get(0).getUrl();
		assertEquals(4, hotelList.size());
		
		
		searchRequest.setLocation("Kolkata");
		searchRequest.setDistance(5);
		response = searchController.getHotels(searchRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());
		
		searchRequest.setLocation("Delhi");
		searchRequest.setDistance(5);
		searchRequest.setMinPrice(5);
		searchRequest.setMaxPrice(100000);
		response = searchController.getHotels(searchRequest);
		assertNotNull(response);
	}
	
	@Test
	public void testGetRooms()
	{
		String hotelId = "12";
		List<Rooms> rooms = searchController.getRooms(hotelId);
		assertNotNull(rooms);
		
		rooms.get(0).getHotelId();
		rooms.get(0).getHotelName();
		rooms.get(0).getAddress();
		rooms.get(0).getDistance();
		rooms.get(0).getOffers();
		rooms.get(0).getRoomNo();
		rooms.get(0).getPrice();
		rooms.get(0).getUrl();
		rooms.get(0).getAvailability();
		rooms.get(0).getServices();
		assertEquals(3, rooms.size());
		
		Links links = new Links();
		links.setCancelBooking("cancelBooking");
		links.setPaymentGateway("Stripe API");
		
		links.getCancelBooking();
		links.getPaymentGateway();
		
		Booking booking = new Booking();
		booking.setBookingReferenceId("123");
		booking.setBookingStatus("confirmed");
		booking.setCheckInDate(new Date());
		booking.setCheckOutDate(new Date());
		booking.setHotelName("test Hotel");
		booking.setLinks(links);
		booking.setNoOfPersons(2);
		booking.setNoOfRooms(1);
		booking.setPaymentStatus("success");
		booking.setRoomType("luxury");
		booking.setTotalAmmount(6500.0);
		
		booking.getBookingReferenceId();
		booking.getBookingStatus();
		booking.getCheckInDate();
		booking.getCheckOutDate();
		booking.getHotelName();
		booking.getLinks();
		booking.getNoOfPersons();
		booking.getNoOfRooms();
		booking.getPaymentStatus();
		booking.getRoomType();
		booking.getTotalAmmount();
		
		Status status = new Status();
		status.setMessage("success");
		
		status.getMessage();
	}
	
	@Test
	public void testBookingModel()
	{
		BookingModel bookingModel = new BookingModel();
		bookingModel.setBookingDate(new Date());
		bookingModel.getBookingDate();
		
		bookingModel.setBookingId("123");
		bookingModel.getBookingId();
		
		bookingModel.setBookingStatus("confirmed");
		bookingModel.getBookingStatus();
		
		bookingModel.setCheckInDate(new Date());
		bookingModel.getCheckInDate();
		
		bookingModel.setCheckOutDate(new Date());
		bookingModel.getCheckOutDate();
		
		bookingModel.setHotelId("12333");
		bookingModel.getHotelId();
		
		bookingModel.setHotelName("test Hotel");
		bookingModel.getHotelName();
		
		bookingModel.setMobileNo(9878987887L);
		bookingModel.getMobileNo();
		
		bookingModel.setNoOfPersons(2);
		bookingModel.getNoOfPersons();
		
		bookingModel.setNoOfRooms(1);
		bookingModel.getNoOfRooms();
		
		bookingModel.setPaymentId("23214342");
		bookingModel.getPaymentId();
		
		bookingModel.setPaymentStatus("confirmed");
		bookingModel.getPaymentStatus();
		
		bookingModel.setRoomType("luxury");
		bookingModel.getRoomType();
		
		bookingModel.setUserId("wqdas");
		bookingModel.getUserId();
	}
}
