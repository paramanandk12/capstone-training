package com.mindtree.mystay.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@JsonIgnoreProperties
public class Status {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
