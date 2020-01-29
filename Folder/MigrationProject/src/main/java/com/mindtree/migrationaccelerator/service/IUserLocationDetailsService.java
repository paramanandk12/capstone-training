package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserLocationDetailsService {
	void insertUserLocationDetails(Session session, List<UserLocationDetails> userLocationDetailsList) throws ServiceException;

	void updateUserLocationDetails(Session session, List<UserLocationDetails> userLocationDetailsList) throws ServiceException;
}