package com.mindtree.mystay.booking.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class Booking {

	private String bookingReferenceId;
	private String hotelName;
	private Integer noOfRooms;
	private Integer noOfPersons;
	private String roomType;
	private Date checkInDate;
	private Date checkOutDate;
	private String bookingStatus;
	private String transactionId;
	private String paymentStatus;
	private Long totalAmmount;
	private Links links;
	private Status status;
	

	public String getBookingReferenceId() {
		return bookingReferenceId;
	}

	public void setBookingReferenceId(String bookingReferenceId) {
		this.bookingReferenceId = bookingReferenceId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(Integer noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Long getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(Long totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
