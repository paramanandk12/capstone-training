package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IUserWorStationDao {
	Boolean storeUserWorkStationFromMastersheet(Session session, UserWorkstationDetails userWorkstationDetails)
			throws DaoException;

	Boolean updateUserWorkStationFromMastersheet(Session session, UserWorkstationDetails list) throws DaoException;
}