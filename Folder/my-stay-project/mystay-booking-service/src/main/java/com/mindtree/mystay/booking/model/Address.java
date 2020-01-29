package com.mindtree.mystay.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@JsonIgnoreProperties
public class Address {

	private String street;
	private String city;
	private String state;
	private Integer pin;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "AddressEnity [street=" + street + ", city=" + city + ", state=" + state + ", pin=" + pin + "]";
	}

	
}
