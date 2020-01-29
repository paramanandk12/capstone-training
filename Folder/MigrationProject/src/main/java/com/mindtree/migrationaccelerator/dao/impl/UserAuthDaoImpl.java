package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.UserAuthDao;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {

	final static Logger logger = Logger.getLogger(UserAuthDaoImpl.class);

	@Autowired
	private IBaseDao<UserAccountDetails, Integer> baseDao;

	@Override
	public UserAccountDetails getUserRecords(Session session, String email) throws DaoException {
		UserAccountDetails user = null;
		try {
			logger.info("Fetching user records from the DB !!!");
			user = baseDao.getEntity(session, UserAccountDetails.class, "loginIdUPNasIs", email);
			logger.info("Record retrieved Successfully !!!");
		} catch (Exception e) {
			logger.error("Error retrieving data from the DB !!!" + e);
			throw new DaoException(e.getMessage());
		}
		return user;
	}
}