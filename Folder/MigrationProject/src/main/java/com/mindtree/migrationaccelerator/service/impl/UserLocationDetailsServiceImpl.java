package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IUserLocationDao;
import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IUserLocationDetailsService;

@Service
public class UserLocationDetailsServiceImpl implements IUserLocationDetailsService {

	final static Logger logger = Logger.getLogger(UserLocationDetailsServiceImpl.class);

	@Autowired
	private IUserLocationDao iUserLocationDao;

	@Override
	public void insertUserLocationDetails(Session session, List<UserLocationDetails> userLocationDetailsList) throws ServiceException {

		try {
			logger.info("Inserting UserLocationDetails started !!!");
			for (UserLocationDetails list : userLocationDetailsList) {
				iUserLocationDao.storeUserLocationFromMastersheet(session, list);
			}
			logger.info("Inserting UserLocationDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to store user location entity in the DB and insertUserLocationDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}

	@Override
	public void updateUserLocationDetails(Session session, List<UserLocationDetails> userLocationDetailsList) throws ServiceException{
		try{
			logger.info("Updating UserLocationDetails started !!!");
			for (UserLocationDetails userLocationDetails : userLocationDetailsList) {
				if(userLocationDetails != null)
					iUserLocationDao.updateUserLocationFromMastersheet(session, userLocationDetails);
			}
			logger.info("Updating UserLocationDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to update user location entity in the DB and updateUserLocationDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
	}
}