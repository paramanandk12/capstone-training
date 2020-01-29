package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserEmailDetailsService {
	void insertUserEmailDetails(Session session, List<UserEmailDetails> userEmailDetailsList) throws ServiceException;

	void updateUserEmailDetails(Session session, List<UserEmailDetails> userEmailDetailsList) throws ServiceException;
}