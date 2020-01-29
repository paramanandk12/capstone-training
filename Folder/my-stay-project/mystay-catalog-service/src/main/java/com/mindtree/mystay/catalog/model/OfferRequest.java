package com.mindtree.mystay.catalog.model;

import java.util.List;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
public class OfferRequest {

	private String operation;
	private List<String> offers;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List<String> getOffers() {
		return offers;
	}

	public void setOffers(List<String> offers) {
		this.offers = offers;
	}

	@Override
	public String toString() {
		return "OfferRequest [operation=" + operation + ", offers=" + offers + "]";
	}

}
