package com.mindtree.mystay.booking.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.mystay.booking.entity.BookingEntity;
import com.mindtree.mystay.booking.exception.RoomNotAvailableException;
import com.mindtree.mystay.booking.model.Booking;
import com.mindtree.mystay.booking.model.Hotels;
import com.mindtree.mystay.booking.model.PaymentRequest;
import com.mindtree.mystay.booking.model.PaymentResponse;
import com.mindtree.mystay.booking.model.Rooms;
import com.mindtree.mystay.booking.model.Status;
import com.mindtree.mystay.booking.proxy.CatalogServiceProxy;
import com.mindtree.mystay.booking.proxy.PaymentServiceProxy;
import com.mindtree.mystay.booking.repository.BookingRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@Service
public class BookingServiceImpl implements BookingService {

	public static final String STATUS_MSG_CONFIRMED = "Confirmed";
	public static final String STATUS_MSG_CANCELLED = "Cancelled";
	public static final String STATUS_MSG_FAILED = "Failed";
	private static Logger logger = LogManager.getLogger();
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CatalogServiceProxy catalogServiceProxy;
	@Autowired
	private PaymentServiceProxy paymentServiceProxy;

	@Override
	@HystrixCommand(fallbackMethod = "bookRoomFallBack", groupKey = "BookRoom", commandKey = "BookRoom")
	public Booking bookRoom(BookingEntity bookingDetails, String hotelId, String roomType)
			throws RoomNotAvailableException {

		Booking booking = null;
		
		bookingDetails.setHotelId(hotelId);
		bookingDetails.setRoomType(roomType);

		// This is to call Catalog Service
		Rooms rooms = callCatalogServiceApi(bookingDetails);
		
		if (rooms != null && "yes".equalsIgnoreCase(rooms.getAvailability())) {
			makePayment(bookingDetails, rooms);

			if (STATUS_MSG_CONFIRMED.equals(bookingDetails.getBookingStatus())) {
				BookingEntity savedDetails = bookingRepository.save(bookingDetails);
				if (savedDetails != null) {
					booking = prepareResponse(savedDetails);
				}
			} else {
				booking = prepareResponse(bookingDetails);
			}
		} else {
			throw new RoomNotAvailableException("This room is not available. Please try for other rooms.");
		}

		return booking;
	}

	private void makePayment(BookingEntity bookingEntity, Rooms rooms) {

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmmount(calculateAmmount(bookingEntity, rooms.getPrice()));
		paymentRequest.setEmail(bookingEntity.getEmail());
		paymentRequest.setPaymentType("card");

		try {
			ResponseEntity<PaymentResponse> makePayment = paymentServiceProxy.makePayment(paymentRequest);

			if (HttpStatus.OK.equals(makePayment.getStatusCode())) {
				PaymentResponse response = makePayment.getBody();
				bookingEntity.setBookingDate(new Date());
				bookingEntity.setBookingStatus(STATUS_MSG_CONFIRMED);
				bookingEntity.setPaymentStatus(response.getTransactionStatus());
				bookingEntity.setAmount(response.getAmmount() / 100);
				bookingEntity.setPaymentDate(response.getPaymentDate());
				bookingEntity.setTransactionId(response.getTransactionId());
				bookingEntity.setPaymentType(response.getPaymentType());
			}
		} catch (Exception e) {
			logger.error("Exception while calling paymentService: " + e);
			bookingEntity.setBookingStatus(STATUS_MSG_FAILED);
			bookingEntity.setPaymentStatus(STATUS_MSG_FAILED);
			bookingEntity.setAmount(paymentRequest.getAmmount());
		}
	}

	private Rooms callCatalogServiceApi(BookingEntity bookingDetails) {

		Rooms rooms = null;
		try {
			ResponseEntity<Hotels> response = catalogServiceProxy.hotelById(bookingDetails.getHotelId());
			if (response != null && HttpStatus.OK.equals((response.getStatusCode()))) {
				Hotels hotel = response.getBody();
				bookingDetails.setHotelName(hotel.getHotelName());
				List<Rooms> roomList = hotel.getRooms().stream()
						.filter(room -> bookingDetails.getRoomType().equalsIgnoreCase(room.getRoomType()))
						.collect(Collectors.toList());
				if(!roomList.isEmpty()) {
					rooms = roomList.get(0);
				}
			}
		} catch (Exception ex) {
			logger.error("Exception occur while calling Catalog Service: " + ex);
		}
		return rooms;
	}

	private Long calculateAmmount(BookingEntity bookingDetails, Double price) {
		long difference = bookingDetails.getCheckOutDate().getTime() - bookingDetails.getCheckInDate().getTime();
		float daysBetween = (difference / (1000 * 60 * 60 * 24));
		return (long) (daysBetween * price * bookingDetails.getNoOfRooms());
	}

	private Booking prepareResponse(BookingEntity savedDetails) {

		Booking booking = new Booking();
		try {
			booking.setBookingReferenceId(savedDetails.getBookingId());
			booking.setHotelName(savedDetails.getHotelName());
			booking.setRoomType(savedDetails.getRoomType());
			booking.setNoOfRooms(savedDetails.getNoOfRooms());
			booking.setNoOfPersons(savedDetails.getNoOfPersons());
			booking.setCheckInDate(savedDetails.getCheckInDate());
			booking.setCheckOutDate(savedDetails.getCheckOutDate());
			booking.setBookingStatus(savedDetails.getBookingStatus());
			booking.setTransactionId(savedDetails.getTransactionId());
			booking.setPaymentStatus(savedDetails.getPaymentStatus());
			booking.setTotalAmmount(savedDetails.getAmount());
		} catch (Exception ex) {
			logger.error("Exception in prepareResponse() ->" + ex.getMessage());
		}
		return booking;
	}

	@Override
	@HystrixCommand(fallbackMethod = "cancelBookingFallBack", groupKey = "CancelBooking", commandKey = "CancelBooking")
	public Status cancelBooking(String bookingId) {

		Status status = null;
		Optional<BookingEntity> dbData = bookingRepository.findById(bookingId);
		try {
			if (dbData.isPresent()) {
				BookingEntity bookingDetails = dbData.get();
				bookingDetails.setBookingStatus(STATUS_MSG_CANCELLED);
				BookingEntity updatedBooking = bookingRepository.save(bookingDetails);
				if (updatedBooking != null) {
					status = new Status();
					status.setMessage("Booking Id: " + bookingId + ", Successfully Cancelled.");
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in cancelBooking ->" + ex.getMessage());
		}

		return status;
	}

	private Booking bookRoomFallBack(BookingEntity bookingDetails, String hotelId, String roomType) {
		Booking booking = new Booking();
		Status status = new Status();
		status.setMessage("Failed to Book Room. Please Try once again.");
		booking.setStatus(status);
		return booking;
	}
	
	private Status cancelBookingFallBack(String bookingId) {
		Status status = new Status();
		status.setMessage("Booking Cancelation Failed. Please Try once again.");
		return status;
	}
}
