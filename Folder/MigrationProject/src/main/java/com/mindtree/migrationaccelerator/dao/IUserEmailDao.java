package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IUserEmailDao {
	Boolean storeUserEmailDetailsFromMastersheet(Session session, UserEmailDetails userEmailDetails)
			throws DaoException;

	Boolean updateUserEmailDetailsFromMastersheet(Session session, UserEmailDetails list) throws DaoException;
}