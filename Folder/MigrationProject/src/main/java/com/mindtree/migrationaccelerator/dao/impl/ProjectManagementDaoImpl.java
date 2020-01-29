package com.mindtree.migrationaccelerator.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IProjectManagementDao;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class ProjectManagementDaoImpl implements IProjectManagementDao {

	final static Logger logger = Logger.getLogger(ProjectManagementDaoImpl.class);

	@Autowired
	private IBaseDao<Project, Integer> baseDao;

	@Autowired
	private IBaseDao<UserAccountDetails, Integer> baseDaoUL;

	@Override
	public Boolean createNewProject(Session session, Project project) throws DaoException {
		boolean isCreated = false;
		try {
			logger.info("Creating new project in DB !!!");
			baseDao.insertEntity(session, project);
			isCreated = true;
			logger.info("Project created successfully !!!");
		} catch (Exception e) {
			logger.error("Error while creating the project", e);
			throw new DaoException("Error while creating new project !!!");
		}
		return isCreated;
	}

	@Override
	public List<Project> getAllProjects(Session session) throws DaoException {
		List<Project> projectsList = null;
		try {
			logger.info("Getting list of all projects !!!");
			projectsList = baseDao.getEntity(session, Project.class);
			logger.info("List of all projects ftched successfully !!!");

		} catch (Exception e) {
			logger.error("Unable to fetch list of projects getAllProjects method is excecuting!!!" + e);
		}
		return projectsList;
	}

	@Override
	public Boolean updateProject(Session session, Project projectFromUI) throws DaoException {
		boolean isupdated = false;
		try {
			logger.info("Updating selected project in DB !!!");
			baseDao.mergeEntity(session, projectFromUI);
			isupdated = true;
			logger.info("Project updated successfully !!!");
		} catch (Exception e) {
			logger.error("Error while updating the project", e);
			throw new DaoException("Error while updating new project !!!");
		}
		return isupdated;
	}

	@Override
	public Project getProjectEntityById(Session session, Integer id) throws DaoException {
		try {
			logger.info("Fetching project by id : "+id);
			return baseDao.loadEntity(session, Project.class, id);
		} catch (Exception e) {
			logger.error("Error while Fetching project by id : "+id);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public List<UserAccountDetails> getAllUserAccountList(Session session) throws DaoException {
		List<UserAccountDetails> userAccountDetailsList = null;
		try {
			logger.info("Getting list of all projects !!!");
			userAccountDetailsList = baseDaoUL.getEntity(session, UserAccountDetails.class);
			logger.info("List of all projects ftched successfully !!!");

		} catch (Exception e) {
			logger.error("Unable to fetch list of projects getAllProjects method is excecuting!!!" + e);
		}
		return userAccountDetailsList;
	}
}