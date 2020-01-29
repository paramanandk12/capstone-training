package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IUserWorStationDao;
import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IUserWorkstationDetailsService;

@Service
public class UserWorkstationDetailsServiceImpl implements IUserWorkstationDetailsService {
	final static Logger logger = Logger.getLogger(UserLocationDetailsServiceImpl.class);

	@Autowired
	private IUserWorStationDao iUserWorkStationDao;

	@Override
	public void insertUserWorkStationDetails(Session session, List<UserWorkstationDetails> userWorkstationDetails)
			throws ServiceException {
		try {
			logger.info("Insert UserWorkstationDetails started !!!");

			for (UserWorkstationDetails list : userWorkstationDetails) {
				iUserWorkStationDao.storeUserWorkStationFromMastersheet(session, list);
			}
			logger.info("Insert UserWorkstationDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to store user workstation entity in the DB and insertUserLocationDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}

	@Override
	public void updateUserWorkStationDetails(Session session, List<UserWorkstationDetails> userWorkstationDetails) throws ServiceException{
		try {
			logger.info("Updating UserWorkstationDetails started !!!");
			for (UserWorkstationDetails userWorkstation : userWorkstationDetails) {
				iUserWorkStationDao.updateUserWorkStationFromMastersheet(session, userWorkstation);
			}
			logger.info("Updating UserWorkstationDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to update user workstation entity in the DB and updateUserWorkStationDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}
}