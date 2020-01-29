package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserSkypeDetailsService {
	void insertUserSkypeDetails(Session session, List<UserSkypeDetails> userSkypeDetailsList) throws ServiceException;

	void updateUserSkypeDetails(Session session, List<UserSkypeDetails> userSkypeDetailsList) throws ServiceException;
}