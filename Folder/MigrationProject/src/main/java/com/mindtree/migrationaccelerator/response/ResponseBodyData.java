package com.mindtree.migrationaccelerator.response;

import org.springframework.http.HttpStatus;

public class ResponseBodyData<T> {

	private String message;
	private HttpStatus status;
	private T data;

	public ResponseBodyData() {

	}

	public ResponseBodyData(String message, HttpStatus status, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setData(T data) {
		this.data = data;
	}

}
