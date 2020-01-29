package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserOtherDetailsService {
	void insertUserOtherDetails(Session session, List<UserOtherDetails> userOtherDetails) throws ServiceException;

	void updateUserOtherDetails(Session session, List<UserOtherDetails> userOtherDetailsList) throws ServiceException;
}