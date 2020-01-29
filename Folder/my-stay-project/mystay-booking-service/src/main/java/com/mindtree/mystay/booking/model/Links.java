package com.mindtree.mystay.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class Links {

	private String cancelBooking;

	public String getCancelBooking() {
		return cancelBooking;
	}

	public void setCancelBooking(String cancelBooking) {
		this.cancelBooking = cancelBooking;
	}
}
