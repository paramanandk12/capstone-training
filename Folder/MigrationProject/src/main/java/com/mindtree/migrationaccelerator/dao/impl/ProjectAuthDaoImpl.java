package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.ProjectAuthDao;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class ProjectAuthDaoImpl implements ProjectAuthDao {

	final static Logger logger = Logger.getLogger(ProjectAuthDaoImpl.class);

	@Autowired
	private IBaseDao<Project, Integer> baseDao;

	@Override
	public boolean getProjectRecords(Session session, String projNameColName, String projName) throws DaoException {
		boolean isExists = false;
		try {
			logger.info("Fetching Project records from the DB !!!");
			isExists = baseDao.ifExists(session, Project.class, projNameColName, projName);
			logger.info("Project Record retrieved Successfully !!!");
		} catch (Exception e) {
			logger.error("Error retrieving data from the DB !!!" + e);
			throw new DaoException("Error while fetching the project");
		}
		return isExists;
	}
}