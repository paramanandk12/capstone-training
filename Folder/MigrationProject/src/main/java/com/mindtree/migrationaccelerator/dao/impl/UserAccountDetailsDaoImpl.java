package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IUserAccountDetailsDao;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserAccountDetailsDaoImpl implements IUserAccountDetailsDao {
	final static Logger logger = Logger.getLogger(UserLocationDaoImpl.class);

	@Autowired
	private IBaseDao<UserAccountDetails, Integer> baseDao;

	@Override
	public Boolean insertUserAccountDetails(Session session, UserAccountDetails userAccountDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting UserAccountDetails started !!!");
			baseDao.insertEntity(session, userAccountDetails);
			isProcessed = true;
			logger.info("Inserting UserAccountDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the User Account Details !!! ", e);
			throw new DaoException("Error while creating User Account Details !!!");
		}
		return isProcessed;
	}

	@Override
	public Boolean updateUserAccountDetailsFromMastersheet(Session session, UserAccountDetails userAccountDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating UserAccountDetails data started !!!");
			baseDao.mergeEntity(session, userAccountDetails);
			isProcessed = true;
			logger.info("Updating UserAccountDetails data completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the UserAccountDetails Details !!! ", e);
			throw new DaoException("Error while Updating UserAccountDetails Details !!!");
		}
		return isProcessed;
	}
}