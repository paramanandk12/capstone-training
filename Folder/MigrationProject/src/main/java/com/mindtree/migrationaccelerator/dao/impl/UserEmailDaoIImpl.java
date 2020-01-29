package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IUserEmailDao;
import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserEmailDaoIImpl implements IUserEmailDao {
	final static Logger logger = Logger.getLogger(UserEmailDaoIImpl.class);

	@Autowired
	private IBaseDao<UserEmailDetails, Integer> baseDao;

	@Override
	public Boolean storeUserEmailDetailsFromMastersheet(Session session, UserEmailDetails userEmailDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting UserEmailDetails started !!!");
			baseDao.insertEntity(session, userEmailDetails);
			isProcessed = true;
			logger.info("Inserting UserEmailDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the User Email Details ", e);
			throw new DaoException("Error while creating User email Details !!!");
		}
		return isProcessed;
	}

	@Override
	public Boolean updateUserEmailDetailsFromMastersheet(Session session, UserEmailDetails userEmailDetails) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating UserEmailDetails data started !!!");
			baseDao.mergeEntity(session, userEmailDetails);
			isProcessed = true;
			logger.info("Updating UserEmailDetails data completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the UserEmailDetails Details !!! ", e);
			throw new DaoException("Error while Updating UserEmailDetails Details !!!");
		}
		return isProcessed;
	}
}