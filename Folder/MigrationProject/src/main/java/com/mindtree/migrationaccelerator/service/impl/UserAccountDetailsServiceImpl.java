package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IUserAccountDetailsDao;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IUserAccountDetailsService;

@Service
public class UserAccountDetailsServiceImpl implements IUserAccountDetailsService {

	final static Logger logger = Logger.getLogger(UserAccountDetailsServiceImpl.class);

	@Autowired
	private IUserAccountDetailsDao userAccountDetailsDao;

	@Override
	public void insertUserAccountDetails(Session session,List<UserAccountDetails> userAccountDetailslist) throws ServiceException {
		try{
			logger.info("Inserting UserAccountDetails started !!!");
			for (UserAccountDetails list : userAccountDetailslist) {
				userAccountDetailsDao.insertUserAccountDetails(session, list);
			}
			logger.info("Inserting UserAccountDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to store user Account Details entity entities in the DB and insertUserAccountDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}

	@Override
	public void updateUserAccountDetails(Session session, List<UserAccountDetails> userAccountDetailsList) throws ServiceException{
		try {
			logger.info("Updating UserAccountDetails started !!!");
			for (UserAccountDetails userAccountDetails : userAccountDetailsList) {
				userAccountDetailsDao.updateUserAccountDetailsFromMastersheet(session, userAccountDetails);
			}
			logger.info("Updating UserAccountDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to update user location entity entities in the DB and updateUserAccountDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}
}