package com.mindtree.mystay.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RoomNotAvailableException(String message) {
		super(message);
	}

}
