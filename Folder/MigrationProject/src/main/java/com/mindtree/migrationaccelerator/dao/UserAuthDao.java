package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface UserAuthDao {
	UserAccountDetails getUserRecords(Session session, String email) throws DaoException;
}