package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;

import com.mindtree.migrationaccelerator.enums.LoggedInUserType;

public class LoggedInUserDetails<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoggedInUserType loggedInUserType;
	T user;
	
	public LoggedInUserDetails() {}
	
	public LoggedInUserDetails(LoggedInUserType loggedInUserType, T user) {
		super();
		this.loggedInUserType = loggedInUserType;
		this.user = user;
	}

	public LoggedInUserType getLoggedInUserType() {
		return loggedInUserType;
	}

	public void setLoggedInUserType(LoggedInUserType loggedInUserType) {
		this.loggedInUserType = loggedInUserType;
	}

	public T getUser() {
		return user;
	}

	public void setUser(T user) {
		this.user = user;
	}
}
