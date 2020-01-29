package com.mindtree.migrationaccelerator.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IUserLocationDao;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserLocationDaoImpl implements IUserLocationDao {

	final static Logger logger = Logger.getLogger(UserLocationDaoImpl.class);

	@Autowired
	private IBaseDao<UserLocationDetails, Integer> baseDao;
	@Autowired
	private IBaseDao<UserAccountDetails, Integer> baseDaoUserAccount;

	@Override
	public Boolean storeUserLocationFromMastersheet(Session session, UserLocationDetails userLocationDetails)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting UserLocationDetails started !!!");
			baseDao.insertEntity(session, userLocationDetails);
			isProcessed = true;
			logger.info("Inserting UserLocationDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the User Location", e);
			throw new DaoException("Error while creating User Location !!!");
		}
		return isProcessed;
	}

	@Override
	public List<UserAccountDetails> getUserAccountsByProjectId(Session session, int projectId) throws DaoException {
		List<UserAccountDetails> userAccountsList=null;
		try {
			logger.info("Fetching UserLocationDetails started !!!");
			String query = "select * from user_account_details where project_id =" + projectId;
			userAccountsList=baseDaoUserAccount.getByQuery(session, query, UserAccountDetails.class);
			logger.info("Fetching UserLocationDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while Fetching the User Location", e);
			throw new DaoException("Error while Fetching User Location !!!");
		}
		return userAccountsList;
	}

	@Override
	public Boolean updateUserLocationFromMastersheet(Session session, UserLocationDetails userLocationDetails) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating UserLocationDetails data started !!!");
			baseDao.mergeEntity(session, userLocationDetails);
			isProcessed = true;
			logger.info("Updating UserLocationDetails data completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the UserLocationDetails Details !!! ", e);
			throw new DaoException("Error while Updating UserLocationDetails Details !!!");
		}
		return isProcessed;
	}
}