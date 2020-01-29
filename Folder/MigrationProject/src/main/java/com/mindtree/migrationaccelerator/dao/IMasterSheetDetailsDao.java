package com.mindtree.migrationaccelerator.dao;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IMasterSheetDetailsDao {
	Boolean insertMastersheetData(Session session, MasterSheetData mastersheet) throws DaoException;

	MasterSheetData getUserRecord(Session session, String email) throws DaoException;
	
	Boolean updateMasterSheetDetailsFromMastersheet(Session session, MasterSheetData list) throws DaoException;

}