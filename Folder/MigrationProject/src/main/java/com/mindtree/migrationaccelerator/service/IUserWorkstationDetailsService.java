package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserWorkstationDetailsService {
	void insertUserWorkStationDetails(Session session, List<UserWorkstationDetails> userWorkstationDetails) throws ServiceException;

	void updateUserWorkStationDetails(Session session, List<UserWorkstationDetails> userWorkstationDetailsList) throws ServiceException;
}