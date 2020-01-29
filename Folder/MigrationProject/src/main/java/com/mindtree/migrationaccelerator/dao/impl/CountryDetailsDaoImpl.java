package com.mindtree.migrationaccelerator.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
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
import com.mindtree.migrationaccelerator.dao.ICountryDetailsDao;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class CountryDetailsDaoImpl implements ICountryDetailsDao {
	final static Logger logger = Logger.getLogger(CountryDetailsDaoImpl.class);

	@Autowired
	private IBaseDao<Country, Integer> baseDao;

	@Override
	public Boolean insertCountry(Session session, Country countryDetails) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting countryDetails started !!!");
			baseDao.save(session, countryDetails);
			isProcessed = true;
			logger.info("Inserting countryDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the country Details !!! ", e);
			throw new DaoException("Error while creating country Details !!!");
		}
		return isProcessed;
	}

	@Override
	public List<Country> getAllCountries(Session session) throws DaoException {
		List<Country> countryListDB = null;
		try {
			logger.info("Fetching countryDetails started !!!");
			String query = "select * from country ";
			countryListDB = baseDao.getByQuery(session, query, Country.class);
			logger.info("Fetching countryDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while fetching the country Details !!! ", e);
			throw new DaoException("Error while Fetching country Details !!!");
		}
		return countryListDB;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> searchCountryAvailable(Session session, Integer projectID, String name) throws DaoException {
		List<Country> countryListDB = null;
		try {
			logger.info("Searching countryDetails started !!!");
			String sql = "select * from country where name = :name and project_id = : id";
			 TypedQuery<Country> query = session.createQuery(sql);
			 query.setParameter("name", name);
			 query.setParameter("id", projectID);
			 countryListDB = query.getResultList();
			
			logger.info("Searching countryDetails completed !!!");
		} catch (Exception e) {
			logger.error("Error while Searching the country Details !!! ", e);
			throw new DaoException("Error while Searching country Details !!!");
		}
		return countryListDB;

	}
	
	@Override
	public List<Country> getCountryByProject(Session session, Integer projectID) throws DaoException {
		List<Country> countryListDB = null;
		try {
			logger.info("Searching countryDetails started in getCountryByProject() !!!");
			 countryListDB = baseDao.getEntityList(session, Country.class, "project", projectID);
			
			logger.info("Searching countryDetails completed getCountryByProject() !!!");
		} catch (Exception e) {
			logger.error("Error while Searching the country Details !!! ", e);
			throw new DaoException("Error while Searching country Details !!!");
		}
		return countryListDB;

	}
	
	@Override
	public Country getCountryEntityById(Session session, Integer id) throws DaoException {
		try {
			logger.info("Fetching country by id started: "+id);
			return baseDao.loadEntity(session, Country.class, id);
		} catch (Exception e) {
			logger.error("Error while Fetching country by id : "+id);
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean isCountryExists(Session session, String countryName, Integer projectID) throws DaoException {
		try {
			logger.info("Fetching country by countryName started: "+countryName);
				
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Country> criteria = (CriteriaQuery<Country>) builder.createQuery(Country.class);
			Root<Country> root = (Root<Country>) criteria.from(Country.class);
			
			Predicate valRestriction = builder.and(builder.equal(root.get("countryName"), countryName),
					builder.equal(root.get("project"), projectID));
			
			criteria.where(valRestriction);
			
			if(session.createQuery(criteria).list().size() > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			logger.error("Error while Fetching country by countryName : "+countryName);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public Country getCountryByName(Session session, String countryName) throws DaoException {
		try {
			logger.info("Fetching country by countryName started: "+countryName);
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Country> criteria = (CriteriaQuery<Country>) builder.createQuery(Country.class);
			Root<Country> root = (Root<Country>) criteria.from(Country.class);
			criteria.select(root).where(builder.equal(root.get("countryName"), countryName));
			return session.createQuery(criteria).getSingleResult();
		} catch (NoResultException  e) {
			logger.error("No Country found by countryName : "+ countryName + " returning null");
			return null;
		} catch (Exception e) {
			logger.error("Error while Fetching country by countryName : "+countryName);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public Country getCountryByNameAndProject(Session session, String countryName, Integer projectID) throws DaoException {
		try {
			logger.info("Fetching country by countryName started: "+countryName);
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Country> criteria = (CriteriaQuery<Country>) builder.createQuery(Country.class);
			Root<Country> root = (Root<Country>) criteria.from(Country.class);
			
			Predicate valRestriction = builder.and(builder.equal(root.get("countryName"), countryName),
					builder.equal(root.get("project"), projectID));
			
			criteria.where(valRestriction);
				return session.createQuery(criteria).getSingleResult();
		} catch (NoResultException  e) {
			logger.error("No Country found by countryName : "+ countryName + " returning null");
			return null;
		} catch (Exception e) {
			logger.error("Error while Fetching country by countryName : "+countryName);
			throw new DaoException(e.getMessage(), e);
		}
	}
}