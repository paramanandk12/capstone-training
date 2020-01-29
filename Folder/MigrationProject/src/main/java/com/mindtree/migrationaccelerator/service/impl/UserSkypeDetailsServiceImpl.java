package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IUserSkypeDao;
import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IUserSkypeDetailsService;

@Service
public class UserSkypeDetailsServiceImpl implements IUserSkypeDetailsService {
	final static Logger logger = Logger.getLogger(UserSkypeDetailsServiceImpl.class);

	@Autowired
	private IUserSkypeDao userSkypeDao;

	@Override
	public void insertUserSkypeDetails(Session session, List<UserSkypeDetails> userSkypeDetailsList) throws ServiceException {
		try{
			logger.info("Insert UserSkypeDetails started !!!");

			for (UserSkypeDetails list : userSkypeDetailsList) {
				userSkypeDao.storeUserSkypeFromMastersheet(session, list);
			}
			logger.info("Insert UserSkypeDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to store user Skype details entity in the DB and insertUserSkypeDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}
	@Override
	public void updateUserSkypeDetails(Session session, List<UserSkypeDetails> userSkypeDetailsList) throws ServiceException {
		try{
			logger.info("Updating UserSkypeDetails started !!!");
			for (UserSkypeDetails userSkypeDetails : userSkypeDetailsList) {
				userSkypeDao.updateUserSkypeFromMastersheet(session, userSkypeDetails);
			}
			logger.info("Updating UserSkypeDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to update user Skype details entity in the DB and updateUserSkypeDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}
}