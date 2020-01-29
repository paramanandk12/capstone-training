package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IUserOtherDetailsDao {
	Boolean storeUserOtherDetailsFromMastersheet(Session session, UserOtherDetails userOtherDetails)
			throws DaoException;

	Boolean updateUserOtherDetailsFromMastersheet(Session session, UserOtherDetails list) throws DaoException;
}