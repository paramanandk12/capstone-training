package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IMasterWorkbookDao {
	Boolean insertMasterWorkbookRecord(Session session, MasterWorkbook masterWorkbook) throws DaoException;

}