package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IUserSkypeDao {
	Boolean storeUserSkypeFromMastersheet(Session session, UserSkypeDetails userSkypeDetails) throws DaoException;

	Boolean updateUserSkypeFromMastersheet(Session session, UserSkypeDetails list) throws DaoException;
}