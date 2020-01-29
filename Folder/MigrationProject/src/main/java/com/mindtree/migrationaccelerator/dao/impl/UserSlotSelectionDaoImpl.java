package com.mindtree.migrationaccelerator.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IUserSlotSelectionDao;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

@Repository
public class UserSlotSelectionDaoImpl implements IUserSlotSelectionDao {

	final static Logger logger = Logger.getLogger(UserSlotSelectionDaoImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean bookSlot(Session session, UserSlotSelection userSlotSelection) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting UserSlotSelection details started !!!");
			iBaseDao.save(session, userSlotSelection);
			isProcessed = true;
			logger.info("Inserting UserSlotSelection details completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the UserSlotSelection Details !!! ", e);
			throw new DaoException("Error while creating UserSlotSelection Details !!!");
		}
		return isProcessed;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteSlotSelection(Session session, UserSlotSelection userSlotSelection) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Deleting UserSlotSelection details started !!!");
			userSlotSelection.setIsDeleted(true);
			iBaseDao.update(session, userSlotSelection);
			isProcessed = true;
			logger.info("Deleting UserSlotSelection details completed !!!");
		} catch (Exception e) {
			logger.error("Error while Deleting the UserSlotSelection Details !!! ", e);
			throw new DaoException("Error while Deleting UserSlotSelection Details !!!");
		}
		return isProcessed;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSlotSelection(Session session, UserSlotSelection userSlotSelection) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating UserSlotSelection details started !!!");
			iBaseDao.update(session, userSlotSelection);
			isProcessed = true;
			logger.info("Updating UserSlotSelection details completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the UserSlotSelection Details !!! ", e);
			throw new DaoException("Error while Updating UserSlotSelection Details !!!");
		}
		return isProcessed;
	}


	@Override
	public List<UserSlotSelection> getUserSlotDetailsByMasterSheetRecord(Session session, MasterSheetData masterSheetData) throws DaoException {
		logger.info("Searching UserSlotSelection started !!!");
		List<UserSlotSelection> userSlotSelectionListDB = null;
		
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserSlotSelection> criteria = (CriteriaQuery<UserSlotSelection>) builder.createQuery(UserSlotSelection.class);
			Root<UserSlotSelection> root = (Root<UserSlotSelection>) criteria.from(UserSlotSelection.class);
			
			Predicate valRestriction = builder.and(builder.equal(root.get("masterSheetData"), masterSheetData),
					builder.equal(root.get("masterSheetData").get("project"), masterSheetData.getProject()),
					builder.equal(root.get("isDeleted"), 0));
			
			criteria.where(valRestriction);
			
			userSlotSelectionListDB = session.createQuery(criteria).list();
			
			logger.info("Searching UserSlotSelection completed !!!");
		} catch (Exception e) {
			logger.error("Error while Searching the UserSlotSelection Details !!! ", e);
			throw new DaoException("Error while Searching UserSlotSelection Details !!!");
		}
		return userSlotSelectionListDB;
	}

}
