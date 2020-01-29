package com.mindtree.mystay.catalog.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@JsonInclude(Include.NON_NULL)
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 1L;
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
		return "AddressEntity [street=" + street + ", city=" + city + ", state=" + state + ", pin=" + pin + "]";
	}

}
