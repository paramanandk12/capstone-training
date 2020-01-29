package com.mindtree.migrationaccelerator.dao;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IProjectManagementDao {

	Boolean createNewProject(Session session, Project project) throws DaoException;

	List<Project> getAllProjects(Session session) throws DaoException;

	Boolean updateProject(Session session, Project projectFromUI) throws DaoException;

	Project getProjectEntityById(Session session, Integer id) throws DaoException;

	List<UserAccountDetails> getAllUserAccountList(Session session) throws DaoException;

}