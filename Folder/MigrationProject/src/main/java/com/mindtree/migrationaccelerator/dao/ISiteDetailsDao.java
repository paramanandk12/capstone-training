package com.mindtree.migrationaccelerator.dao;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface ISiteDetailsDao {
	Boolean storeSiteFromMastersheet(Session session, Site siteDetails) throws DaoException;

	Boolean updateSiteFromMastersheet(Session session, Site list) throws DaoException;

	List<Site> searchSiteAvailable(Session session, Integer projectId, String siteFromDB) throws DaoException;
	
	List<Site> getSiteByProjectCountry(Session session, Integer projectId, Integer countryId) throws DaoException;

	Site getSiteEntityById(Session session, Integer id) throws DaoException;

	boolean isSiteExists(Session session, String siteName, Integer countryID, Integer projectID) throws DaoException;
}
