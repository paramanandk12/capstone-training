package com.mindtree.migrationaccelerator.service;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface ICountryService {
	List<Country> getAllCountries() throws ServiceException;

	List<Country> searchCountry(Integer projectID, String name) throws ServiceException;

	void insertCountryDetailsFromMastersheet(Session session, List<Country> countryList) throws ServiceException;

	public List<Country> getCountryByProject(Integer projectID) throws ServiceException;

	boolean isCountryExists(Session session, String countryName, Integer integer) throws ServiceException;

	void saveCountry(Session session, Country country) throws ServiceException;

	Country getCountryByNameAndProject(Session session, String countryFromDB, Integer projectId) throws ServiceException;

	Country getCountryByNameAndProject(String countryName, Integer projectID) throws ServiceException;
		
}
