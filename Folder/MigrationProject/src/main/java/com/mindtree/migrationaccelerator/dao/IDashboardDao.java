package com.mindtree.migrationaccelerator.dao;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IDashboardDao {

	List<MasterWorkbook> getWorkBooksByProjectId(Session session, int projectId) throws DaoException;

	List<MasterSheetData> getMastersheetDataByID(Session session, Integer masterWorkbookId) throws DaoException;
}