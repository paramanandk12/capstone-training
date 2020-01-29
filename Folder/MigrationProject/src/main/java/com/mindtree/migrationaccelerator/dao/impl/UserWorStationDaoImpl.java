package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IUserWorStationDao;
import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserWorStationDaoImpl implements IUserWorStationDao {

	final static Logger logger = Logger.getLogger(UserWorStationDaoImpl.class);

	@Autowired
	private IBaseDao<UserWorkstationDetails, Integer> baseDao;

	@Override
	public Boolean storeUserWorkStationFromMastersheet(Session session, UserWorkstationDetails userWorkstationDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting UserWorkstationDetails started !!!");
			baseDao.insertEntity(session, userWorkstationDetails);
			isProcessed = true;
			logger.info("Inserting UserWorkstationDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the User workstation ", e);
			throw new DaoException("Error while creating User workstation !!!");
		}
		return isProcessed;
	}

	@Override
	public Boolean updateUserWorkStationFromMastersheet(Session session, UserWorkstationDetails userWorkstationDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating UserWorkstationDetails data started !!!");
			baseDao.mergeEntity(session, userWorkstationDetails);
			isProcessed = true;
			logger.info("Updating UserWorkstationDetails data completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the UserWorkstationDetails Details !!! ", e);
			throw new DaoException("Error while Updating UserWorkstationDetails Details !!!");
		}
		return isProcessed;
	}
}