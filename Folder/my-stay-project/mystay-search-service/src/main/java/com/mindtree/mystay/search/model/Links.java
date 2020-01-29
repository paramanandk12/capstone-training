package com.mindtree.mystay.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@JsonIgnoreProperties
public class Links {
	
	private String paymentGateway;
	private String cancelBooking;
	
	public String getPaymentGateway() {
		return paymentGateway;
	}
	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	public String getCancelBooking() {
		return cancelBooking;
	}
	public void setCancelBooking(String cancelBooking) {
		this.cancelBooking = cancelBooking;
	}
	
	
}
