package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IUserOtherDetailsDao;
import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserOtherDetailsDaoImpl implements IUserOtherDetailsDao {
	final static Logger logger = Logger.getLogger(UserLocationDaoImpl.class);

	@Autowired
	private IBaseDao<UserOtherDetails, Integer> baseDao;

	@Override
	public Boolean storeUserOtherDetailsFromMastersheet(Session session, UserOtherDetails userOtherDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting UserOtherDetailsDaoImpl started !!!");
			baseDao.insertEntity(session, userOtherDetails);
			isProcessed = true;
			logger.info("Inserting UserOtherDetailsDaoImpl completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the User other details", e);
			throw new DaoException("Error while creating User other details !!!");
		}
		return isProcessed;
	}

	@Override
	public Boolean updateUserOtherDetailsFromMastersheet(Session session, UserOtherDetails userOtherDetails) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating UserOtherDetails data started !!!");
			baseDao.mergeEntity(session, userOtherDetails);
			isProcessed = true;
			logger.info("Updating UserOtherDetails data completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the UserOtherDetails Details !!! ", e);
			throw new DaoException("Error while Updating UserOtherDetails Details !!!");
		}
		return isProcessed;
	}
}