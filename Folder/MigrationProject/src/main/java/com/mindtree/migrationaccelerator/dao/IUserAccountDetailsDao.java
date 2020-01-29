package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IUserAccountDetailsDao {
	Boolean insertUserAccountDetails(Session session, UserAccountDetails userAccountDetails)
			throws DaoException;

	Boolean updateUserAccountDetailsFromMastersheet(Session session, UserAccountDetails list) throws DaoException;
}