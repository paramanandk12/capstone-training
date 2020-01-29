package com.mindtree.migrationaccelerator.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.ISiteDetailsDao;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class SiteDetailsDaoImpl implements ISiteDetailsDao {

	final static Logger logger = Logger.getLogger(CountryDetailsDaoImpl.class);

	@Autowired
	private IBaseDao<Site, Integer> baseDao;

	@Override
	public Boolean storeSiteFromMastersheet(Session session, Site siteDetails) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting Site Details started !!!");
			baseDao.save(session, siteDetails);
			isProcessed = true;
			logger.info("Inserting  Site Details completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the Site Details !!! ", e);
			throw new DaoException("Error while creating Site Details !!!");
		}
		return isProcessed;
	}

	@Override
	public Boolean updateSiteFromMastersheet(Session session, Site siteDetails) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating Site data started !!!");
			baseDao.mergeEntity(session, siteDetails);
			isProcessed = true;
			logger.info("Updating Site data completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the Site Details !!! ", e);
			throw new DaoException("Error while Updating Site Details !!!");
		}
		return isProcessed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Site> searchSiteAvailable(Session session, Integer projectId, String siteFromDB) throws DaoException{
		List<Site> siteListDB = null;
		try {
			logger.info("Searching Site started !!!");
			String sql = "select * from site where site_name = :name and project_id = :pid ";
			 TypedQuery<Site> query = session.createQuery(sql);
			 query.setParameter("name", siteFromDB);
			 query.setParameter("pid", projectId);
			 siteListDB = query.getResultList();
			
			logger.info("Searching Site completed !!!");
		} catch (Exception e) {
			logger.error("Error while Searching the Site Details !!! ", e);
			throw new DaoException("Error while Searching Site Details !!!");
		}
		return siteListDB;
	}

	@Override
	public List<Site> getSiteByProjectCountry(Session session, Integer projectId, Integer countryId)
			throws DaoException {
		List<Site> siteListDB = null;
		try {
			logger.info("Searching Site started !!!");
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Site> criteria = (CriteriaQuery<Site>) builder.createQuery(Site.class);
			Root<Site> root = (Root<Site>) criteria.from(Site.class);
			criteria.select(root).where(builder.and(builder.equal(root.get("project"), projectId), builder.equal(root.get("country"), countryId)));
			
			siteListDB = session.createQuery(criteria).list();		
			
			logger.info("Searching Site completed !!!");
		} catch (Exception e) {
			logger.error("Error while Searching the Site Details !!! ", e);
			throw new DaoException("Error while Searching Site Details !!!");
		}
		return siteListDB;
	}
	
	@Override
	public Site getSiteEntityById(Session session, Integer id) throws DaoException {
		try {
			logger.info("Fetching site by id : "+id);
			return baseDao.loadEntity(session, Site.class, id);
		} catch (Exception e) {
			logger.error("Error while Fetching site by id : "+id);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public boolean isSiteExists(Session session, String siteName, Integer countryID, Integer projectID) throws DaoException {
		try {
			logger.info("Fetching site by siteName started: "+siteName);
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Site> criteria = (CriteriaQuery<Site>) builder.createQuery(Site.class);
			Root<Site> root = (Root<Site>) criteria.from(Site.class);
			
			Predicate valRestriction = builder.and(builder.equal(root.get("siteName"), siteName),
					builder.equal(root.get("country"), countryID), builder.equal(root.get("project"), projectID));
			
			criteria.where(valRestriction);
			
			if(session.createQuery(criteria).list().size() > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			logger.error("Error while Fetching site by siteName : "+siteName);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
