package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface ProjectAuthDao {
	boolean getProjectRecords(Session session, String projNameColName, String projName) throws DaoException;
}