package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IUserSkypeDao;
import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserSkypeDaoImpl implements IUserSkypeDao {
	final static Logger logger = Logger.getLogger(UserSkypeDaoImpl.class);

	@Autowired
	private IBaseDao<UserSkypeDetails, Integer> baseDao;

	@Override
	public Boolean storeUserSkypeFromMastersheet(Session session, UserSkypeDetails userSkypeDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting UserSkypeDetails started !!!");
			baseDao.insertEntity(session, userSkypeDetails);
			isProcessed = true;
			logger.info("Inserting UserSkypeDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error? while creating the User Skype Details ", e);
			throw new DaoException("Error while creating User Skype Details !!!");
		}
		return isProcessed;
	}

	@Override
	public Boolean updateUserSkypeFromMastersheet(Session session, UserSkypeDetails skypeDetsils) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating UserSkypeDetails started !!!");
			baseDao.mergeEntity(session, skypeDetsils);
			isProcessed = true;
			logger.info("Updating UserSkypeDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error? while Updating the User Skype Details ", e);
			throw new DaoException("Error while Updating User Skype Details !!!");
		}
		return isProcessed;
		
	}
}