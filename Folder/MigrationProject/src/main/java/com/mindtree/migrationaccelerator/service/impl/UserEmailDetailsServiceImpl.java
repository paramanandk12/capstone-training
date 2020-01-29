package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IUserEmailDao;
import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IUserEmailDetailsService;

@Service
public class UserEmailDetailsServiceImpl implements IUserEmailDetailsService {

	final static Logger logger = Logger.getLogger(UserEmailDetailsServiceImpl.class);

	@Autowired
	private IUserEmailDao iUserEmailDao;

	@Override
	public void insertUserEmailDetails(Session session, List<UserEmailDetails> userEmailDetailsList) throws ServiceException {
		try{
			logger.info("Insrting UserEmailDetails started !!!");
			for (UserEmailDetails list : userEmailDetailsList) {
				iUserEmailDao.storeUserEmailDetailsFromMastersheet(session, list);
			}
			logger.info("Insrting UserEmailDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to store user email details entity entities in the DB and insertUserEmailDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}

	@Override
	public void updateUserEmailDetails(Session session, List<UserEmailDetails> userEmailDetailsList) throws ServiceException {
		try {
			logger.info("Updating UserEmailDetails started !!!");
			for (UserEmailDetails userEmailDetails : userEmailDetailsList) {
				iUserEmailDao.updateUserEmailDetailsFromMastersheet(session, userEmailDetails);
			}
			logger.info("Updating UserEmailDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to update user email details entity entities in the DB and updateUserEmailDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
	}
}