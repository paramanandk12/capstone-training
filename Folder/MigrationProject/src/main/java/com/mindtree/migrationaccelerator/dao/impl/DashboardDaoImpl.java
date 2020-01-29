package com.mindtree.migrationaccelerator.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IDashboardDao;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class DashboardDaoImpl implements IDashboardDao {
	final static Logger logger = Logger.getLogger(DashboardDaoImpl.class);

	@Autowired
	private IBaseDao<MasterWorkbook, Integer> wbBaseDao;

	@Autowired
	private IBaseDao<MasterSheetData, Integer> dataBaseDao;

	@Override
	public List<MasterWorkbook> getWorkBooksByProjectId(Session session, int projectId) throws DaoException {
		String query = "select * from master_workbook where project_id =" + projectId;
		List<MasterWorkbook> listWb = wbBaseDao.getByQuery(session, query, MasterWorkbook.class);
		return listWb;
	}

	@Override
	public List<MasterSheetData> getMastersheetDataByID(Session session, Integer masterWorkbookId) throws DaoException {
		String query = "select * from mastersheet_sheet_data where master_workbook_id =" + masterWorkbookId;
		List<MasterSheetData> listMastersheetData = dataBaseDao.getByQuery(session, query, MasterSheetData.class);
		return listMastersheetData;
	}
}