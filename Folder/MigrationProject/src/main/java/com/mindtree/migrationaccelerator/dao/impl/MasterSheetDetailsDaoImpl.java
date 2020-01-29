package com.mindtree.migrationaccelerator.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IMasterSheetDetailsDao;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class MasterSheetDetailsDaoImpl implements IMasterSheetDetailsDao {
	final static Logger logger = Logger.getLogger(MasterSheetDetailsDaoImpl.class);

	@Autowired
	private IBaseDao<MasterSheetData, Integer> baseDao;

	@Override
	public Boolean insertMastersheetData(Session session, MasterSheetData mastersheet)
			throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting MasterSheetData data started !!!");
			baseDao.insertEntity(session, mastersheet);
			isProcessed = true;
			logger.info("Inserting mastersheet data completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the Mastersheet Details !!! ", e);
			throw new DaoException("Error while creating Mastersheet Details !!!");
		}
		return isProcessed;
	}

	@Override
	public MasterSheetData getUserRecord(Session session, String email) throws DaoException {

		MasterSheetData masterSheetData = null;
		try {
			logger.info("Fetching MasterSheetData data started !!!");
			masterSheetData = baseDao.getEntity(session, MasterSheetData.class, "loginIdUPNasIs", email);
			logger.info("Fetching mastersheet data completed !!!");
		} catch (Exception e) {
			logger.error("Error while fetching the Mastersheet Details !!! ", e);
			throw new DaoException("Error while fetching Mastersheet Details !!!");
		}
		return masterSheetData;
	}

	@Override
	public Boolean updateMasterSheetDetailsFromMastersheet(Session session, MasterSheetData mastersheet) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating MasterSheetData data started !!!");
			baseDao.mergeEntity(session, mastersheet);
			isProcessed = true;
			logger.info("Updating mastersheet data completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the Mastersheet Details !!! ", e);
			throw new DaoException("Error while Updating Mastersheet Details !!!");
		}
		return isProcessed;
	}
}