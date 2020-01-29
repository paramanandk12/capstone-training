package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IMasterWorkbookDao;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
@Repository
public class MasterWorbookDaoImpl implements IMasterWorkbookDao {
	final static Logger logger = Logger.getLogger(UserLocationDaoImpl.class);

	@Autowired
	private IBaseDao<MasterWorkbook, Integer> baseDao;

	@Override
	public Boolean insertMasterWorkbookRecord(Session session, MasterWorkbook masterWorkbook) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting MasterWorkbook data started !!!");
			baseDao.insertEntity(session, masterWorkbook);
			isProcessed = true;
			logger.info("Inserting MasterWorkbook data completed !!!");
		} catch (Exception e) {
			logger.error("Error while inserting the MasterWorkbook record", e);
			throw new DaoException("Error while inserting the MasterWorkbook record !!!");
		}
		return isProcessed;
	}
}