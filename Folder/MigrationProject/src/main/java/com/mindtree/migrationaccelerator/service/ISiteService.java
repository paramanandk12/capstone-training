package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface ISiteService {
	void insertSiteDetails(Session session, List<Site> siteList) throws ServiceException;

	void updateSiteDetails(Session session, List<Site> siteDetailsList) throws ServiceException;

	List<Site> searchSite(Integer projectId, String siteFromDB) throws ServiceException;
	
	List<Site> getSiteByProjectCountry(Integer projectId, Integer countryId) throws ServiceException;

	boolean isSiteExists(Session session, String siteName, Integer countryID, Integer projectID) throws ServiceException;

	void saveSite(Session session, Site site) throws ServiceException;

	boolean isSiteExists(String siteName, Integer countryID, Integer projectID) throws ServiceException;
}