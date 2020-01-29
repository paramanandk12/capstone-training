package com.mindtree.migrationaccelerator.dao;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IUserLocationDao {
	Boolean storeUserLocationFromMastersheet(Session session, UserLocationDetails userLocationDetails)
			throws DaoException;

	List<UserAccountDetails> getUserAccountsByProjectId(Session session, int projectId) throws DaoException;

	Boolean updateUserLocationFromMastersheet(Session session, UserLocationDetails list) throws DaoException;
}