package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.AdminAuthDao;
import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.entity.Admin;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class AdminAuthDaoImpl implements AdminAuthDao {

	final static Logger logger = Logger.getLogger(AdminAuthDaoImpl.class);

	@Autowired
	private IBaseDao<Admin, Integer> baseDao;

	@Override
	public Admin getAdminRecords(Session session, String email) throws DaoException {
		Admin admin = null;
		try {
			logger.info("Fetching Admin records from the DB !!!");
			admin = baseDao.getEntity(session, Admin.class, "emailId", email);
			logger.info("Admin Record retrieved Successfully !!!");
		} catch (Exception e) {
			logger.error("Error retrieving data from the DB !!!" + e);
			throw new DaoException("Error while fetching the admin");
		}
		return admin;
	}
}