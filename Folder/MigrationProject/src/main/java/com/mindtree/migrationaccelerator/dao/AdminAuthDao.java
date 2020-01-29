package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.Admin;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface AdminAuthDao {
	Admin getAdminRecords(Session session, String email) throws DaoException;
}