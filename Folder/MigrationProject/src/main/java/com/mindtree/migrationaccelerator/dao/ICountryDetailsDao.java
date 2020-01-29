package com.mindtree.migrationaccelerator.dao;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface ICountryDetailsDao {
	Boolean insertCountry(Session session, Country countryDetails)
			throws DaoException;

	List<Country> getAllCountries(Session session)  throws DaoException;

	List<Country> searchCountryAvailable(Session session, Integer projectID, String name) throws DaoException;

	List<Country> getCountryByProject(Session session, Integer projectID) throws DaoException;

	Country getCountryEntityById(Session session, Integer id) throws DaoException;

	boolean isCountryExists(Session session, String countryName, Integer projectID) throws DaoException;

	Country getCountryByName(Session session, String countryName) throws DaoException;

	Country getCountryByNameAndProject(Session session, String countryName, Integer projectID) throws DaoException;


}
