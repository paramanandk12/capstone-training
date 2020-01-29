package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.ICountryDetailsDao;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IBaseService;
import com.mindtree.migrationaccelerator.service.ICountryService;

@Service
public class CountryDetailsServiceImpl implements ICountryService {
	final static Logger logger = Logger.getLogger(CountryDetailsServiceImpl.class);
	@Autowired
	private IBaseService baseService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDAO;

	@Autowired
	private ICountryDetailsDao userCountryDetailsDao;

	@Override
	public void insertCountryDetailsFromMastersheet(Session session, List<Country> countryList) throws ServiceException {
		try{
			logger.info("Inserting countryList started !!!");
			for (Country list : countryList) {
				userCountryDetailsDao.insertCountry(session, list);
			}
			logger.info("Inserting userCountryDetails completed !!!");
		} catch (DaoException e) {
			logger.error(
					"Unable to store country entity entities in the DB and insertCountryDetails()  method is excecuting!!!"
							+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}

	@Override
	public List<Country> getAllCountries() throws ServiceException {
		List<Country> countryListDB = null;
		Transaction tx = null;
		try (Session session = baseService.getSessionFactory().openSession()) {
			logger.info("Fetching countryList started !!!");
			tx = session.beginTransaction();
			countryListDB = userCountryDetailsDao.getAllCountries(session);
			tx.commit();
			logger.info("Fetching CountryDetails completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch country  entities from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return countryListDB;
	}
	
	@Override
	public List<Country> searchCountry(Integer projectID, String name) throws ServiceException {
		List<Country> countryListDB = null;
		Transaction tx = null;
		try {
			logger.info("Fetching countryList started !!!");
			Session session = iBaseDAO.getSession();
			tx = session.beginTransaction();
			countryListDB = userCountryDetailsDao.searchCountryAvailable(session, projectID, name);
			tx.commit();
			logger.info("Fetching CountryDetails completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch country  entities from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return countryListDB;
	}

	@Transactional
	@Override
	public List<Country> getCountryByProject(Integer projectID) throws ServiceException {
		List<Country> countryListDB = null;
		Transaction tx = null;
		try {
			logger.info("Fetching countryList started in getCountryByProject() !!!");
			Session session = iBaseDAO.getSession();
			tx = session.beginTransaction();
			countryListDB = userCountryDetailsDao.getCountryByProject(session, projectID);
			tx.commit();
			logger.info("Fetching CountryDetails completed in getCountryByProject() !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch country  entities from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return countryListDB;
	}
	
	@Override
	@Transactional
	public boolean isCountryExists(Session session,String countryName, Integer projectID) throws ServiceException {
		boolean isExists = false;
		Transaction tx = null;
		try {
			logger.info("Fetching country started in isCountryExists() !!!");
			tx = session.beginTransaction();
			isExists = userCountryDetailsDao.isCountryExists(session, countryName, projectID);
			tx.commit();
			logger.info("Fetching CountryDetails completed in isCountryExists() !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch country  entity from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return isExists;
	}

	//for embedded Transaction no need to begin new transaction
	@Override
	public Country getCountryByNameAndProject(Session session, String countryName, Integer projectID) throws ServiceException {
		Country country = null;
		try {
			logger.info("Fetching country started in getCountryByName() !!!");
			country = userCountryDetailsDao.getCountryByNameAndProject(session, countryName, projectID);
			logger.info("Fetching CountryDetails completed in getCountryByName() !!!");
		} catch (DaoException e) {
			logger.error("Unable to fetch country  entity from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return country;
	}

	//for Fresh Transaction
	@Override
	@Transactional
	public Country getCountryByNameAndProject(String countryName, Integer projectID) throws ServiceException {
		Country country = null;
		Transaction tx = null;
		try {
			logger.info("Fetching country started in getCountryByNameAndProject() !!!");
			Session session = iBaseDAO.getSession();
			tx = session.beginTransaction();
			country = userCountryDetailsDao.getCountryByNameAndProject(session, countryName, projectID);
			tx.commit();
			logger.info("Fetching CountryDetails completed in getCountryByNameAndProject() !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch country  entity from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return country;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void saveCountry(Session session, Country country) throws ServiceException {
		Transaction tx = null;
		try {
			logger.info("Fetching country started in isCountryExists() !!!");
			tx = session.beginTransaction();
			iBaseDAO.save(session, country);
			tx.commit();
			logger.info("Fetching CountryDetails completed in isCountryExists() !!!");
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch country  entity from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
	}

}
