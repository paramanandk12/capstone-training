package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IUserOtherDetailsDao;
import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IUserOtherDetailsService;

@Service
public class UserOtherDetailsServiceImpl implements IUserOtherDetailsService {

	final static Logger logger = Logger.getLogger(UserLocationDetailsServiceImpl.class);

	@Autowired
	private IUserOtherDetailsDao userOtherDetailsDao;
	
	@Override
	public void insertUserOtherDetails(Session session, List<UserOtherDetails> userOtherDetails) throws ServiceException {
		try{
			logger.info("Inserting UserOtherDetails started !!!");
			for (UserOtherDetails list : userOtherDetails) {
				userOtherDetailsDao.storeUserOtherDetailsFromMastersheet(session, list);
			}
			logger.info("Inserting UserOtherDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to store user other details entity in the DB and insertUserOtherDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}
	@Override
	public void updateUserOtherDetails(Session session, List<UserOtherDetails> userOtherDetailsList) throws ServiceException {
		try {
			logger.info("Updating UserOtherDetails started !!!");
			for (UserOtherDetails userOtherDetails : userOtherDetailsList) {
				userOtherDetailsDao.updateUserOtherDetailsFromMastersheet(session, userOtherDetails);
			}
			logger.info("Updating UserOtherDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to update user other details entity in the DB and updateUserOtherDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}
}