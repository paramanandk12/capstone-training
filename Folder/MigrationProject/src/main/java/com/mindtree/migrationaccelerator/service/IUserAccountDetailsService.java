package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserAccountDetailsService {
	void insertUserAccountDetails(Session session, List<UserAccountDetails> userAccountDetailslist) throws ServiceException;

	void updateUserAccountDetails(Session session, List<UserAccountDetails> userAccountDetailsList) throws ServiceException;
}