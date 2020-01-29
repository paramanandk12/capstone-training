package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.ISiteDetailsDao;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.ISiteService;

@Service
public class SiteDetailsServiceImpl implements ISiteService {
	final static Logger logger = Logger.getLogger(CountryDetailsServiceImpl.class);

	@Autowired
	private ISiteDetailsDao userSiteDetailsDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;
	
	
	@Override
	public void insertSiteDetails(Session session, List<Site> siteList) throws ServiceException {
		try{
			logger.info("Inserting siteList started !!!");

			for (Site list : siteList) {
				userSiteDetailsDao.storeSiteFromMastersheet(session, list);
			}
			logger.info("Inserting user Site Details completed !!!");
		} catch (DaoException e) {
			logger.error("Unable to store Site entity in the DB and insertSiteDetails()  method is excecuting!!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}

	}
	@Override
	public void updateSiteDetails(Session session, List<Site> siteDetailsList)throws ServiceException {
		try {
			logger.info("Updating siteList started !!!");

			for (Site site : siteDetailsList) {
				userSiteDetailsDao.updateSiteFromMastersheet(session, site);
			}
			logger.info("Updating user Site Details completed !!!");
		} catch (DaoException e) {
			logger.error("Unable to update Site entity in the DB and updateSiteDetails()  method is excecuting!!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}
	
	@Override
	public List<Site> searchSite(Integer projectId, String siteFromDB) throws ServiceException {
		List<Site> SiteListDB = null;
		Transaction tx = null;
		try {
			logger.info("Searching SiteList started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			SiteListDB = userSiteDetailsDao.searchSiteAvailable(session, projectId, siteFromDB);
			tx.commit();
			logger.info("Searching SiteDetails completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to Searching Site  entities from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return SiteListDB;
	}
	
	@Transactional
	@Override
	public List<Site> getSiteByProjectCountry(Integer projectId, Integer countryId) throws ServiceException {
		List<Site> SiteListDB = null;
		Transaction tx = null;
		try {
			logger.info("Searching SiteList started in getSiteByProjectCountry() !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			SiteListDB = userSiteDetailsDao.getSiteByProjectCountry(session, projectId, countryId);
			tx.commit();
			logger.info("Searching SiteDetails completed in getSiteByProjectCountry()!!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to Searching Site  entities from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return SiteListDB;
		
	}
	
	//for Fresh Transaction
	@Override
	@Transactional
	public boolean isSiteExists(Session session, String siteName, Integer countryID, Integer projectID) throws ServiceException {
		Transaction tx = null;
		boolean isExists = false;
		try {
			logger.info("Fetching site started in isSiteExists() !!!");
			tx = session.beginTransaction();
			isExists = userSiteDetailsDao.isSiteExists(session, siteName, countryID, projectID);
			tx.commit();
			logger.info("Fetching SiteDetails completed in isSiteExists() !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch site  entity from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return isExists;
	}
	
	//for embedded Transaction no need to begin new transaction
	@Override
	public boolean isSiteExists(String siteName, Integer countryID, Integer projectID) throws ServiceException {
		boolean isExists = false;
		try {
			logger.info("Fetching site started in isSiteExists() !!!");
			isExists = userSiteDetailsDao.isSiteExists(iBaseDao.getSession(), siteName, countryID, projectID);
			logger.info("Fetching SiteDetails completed in isSiteExists() !!!");
		} catch (DaoException e) {
			logger.error("Unable to fetch site  entity from the DB !!!" + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return isExists;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void saveSite(Session session, Site site) throws ServiceException {
		Transaction tx = null;
		try {
			logger.info("Fetching country started in isCountryExists() !!!");
			tx = session.beginTransaction();
			iBaseDao.save(session, site);
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