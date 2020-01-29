package com.mindtree.migrationaccelerator.dao.impl;

import java.util.Date;
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
import com.mindtree.migrationaccelerator.dao.ISlotDao;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.exceptions.DaoException;


@Repository
public class SlotDaoImpl implements ISlotDao {
	final static Logger logger = Logger.getLogger(SlotDaoImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	@Autowired
	private IBaseDao<MasterSheetData, Integer> dataBaseDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MasterWorkbook> getWorkBooksByProjectId(Session session, int projectId) throws DaoException {
		String query = "select * from master_workbook where project_id =" + projectId;
		List<MasterWorkbook> listWb = iBaseDao.getByQuery(session, query, MasterWorkbook.class);
		return listWb;
	}

	@Override
	public List<MasterSheetData> getMastersheetDataByID(Session session, Integer masterWorkbookId) throws DaoException {
		String query = "select * from mastersheet_sheet_data where master_workbook_id =" + masterWorkbookId;
		List<MasterSheetData> listMastersheetData = dataBaseDao.getByQuery(session, query, MasterSheetData.class);
		return listMastersheetData;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertSlot(Session session, Slot slotDetails) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Inserting slot details started !!!");
			iBaseDao.save(session, slotDetails);
			isProcessed = true;
			logger.info("Inserting slot details completed !!!");
		} catch (Exception e) {
			logger.error("Error while creating the slot Details !!! ", e);
			throw new DaoException("Error while creating slot Details !!!");
		}
		return isProcessed;
	}

	@Override
	public List<Slot> getSlotsByProjectId(Session session, int projectId) throws DaoException {
		logger.info("Searching Slot started !!!");
		List<Slot> slotListDB = null;
		
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Slot> criteria = (CriteriaQuery<Slot>) builder.createQuery(Slot.class);
			Root<Slot> root = (Root<Slot>) criteria.from(Slot.class);
			
			Predicate valRestriction = builder.and(builder.equal(root.get("project"), projectId),
					builder.equal(root.get("isDeleted"), 0));
			
			criteria.where(valRestriction);
			
			slotListDB = session.createQuery(criteria).list();
			
			logger.info("Searching Slot completed !!!");
		} catch (Exception e) {
			logger.error("Error while Searching the Slot Details !!! ", e);
			throw new DaoException("Error while Searching Slot Details !!!");
		}
		return slotListDB;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSlot(Session session, Slot slot) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Updating slot details started !!!");
			iBaseDao.update(session, slot);
			isProcessed = true;
			logger.info("Updating slot details completed !!!");
		} catch (Exception e) {
			logger.error("Error while Updating the slot Details !!! ", e);
			throw new DaoException("Error while Updating slot Details !!!");
		}
		return isProcessed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteSlot(Session session, Slot slot) throws DaoException {
		boolean isProcessed = false;
		try {
			logger.info("Deleting slot details started !!!");
			slot.setIsDeleted(true);
			iBaseDao.update(session, slot);
			isProcessed = true;
			logger.info("Deleting slot details completed !!!");
		} catch (Exception e) {
			logger.error("Error while Deleting the slot Details !!! ", e);
			throw new DaoException("Error while Deleting slot Details !!!");
		}
		return isProcessed;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Slot getSlotBySlotID(Session session, int SlotID) throws DaoException {
		Slot slot = null;
		try {
			logger.info("Fetching slot details started !!!");
			slot = (Slot) iBaseDao.getEntity(session, Slot.class, SlotID);
			logger.info("Fetching slot details completed !!!");
		} catch (Exception e) {
			logger.error("Error while Fetching the slot Details !!! ", e);
			throw new DaoException("Error while Fetching slot Details !!!");
		}
		return slot;
	}

	@Override
	public List<Slot> getSlotsByDate(Session session, Date date, MasterSheetData masterSheetDataUser) throws DaoException {
		logger.info("Searching Slot started !!!");
		List<Slot> slotListDB = null;
		
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Slot> criteria = (CriteriaQuery<Slot>) builder.createQuery(Slot.class);
			Root<Slot> root = (Root<Slot>) criteria.from(Slot.class);
			
			Predicate valRestriction = builder.and(builder.lessThanOrEqualTo(root.get("startDate"), date), 
					builder.equal(root.get("project"), masterSheetDataUser.getProject()),
					builder.equal(root.get("country").get("countryName"), masterSheetDataUser.getCountry()),
					builder.equal(root.get("site").get("siteName"), masterSheetDataUser.getSite()),
					builder.greaterThanOrEqualTo(root.get("endDate"), date),
					builder.lessThan(root.get("userCountOccupied"), root.get("userCountCapacity")),
					builder.equal(root.get("isDeleted"), 0));
			
			criteria.where(valRestriction);
			
			slotListDB = session.createQuery(criteria).list();
			
			logger.info("Searching Slot completed !!!");
		} catch (Exception e) {
			logger.error("Error while Searching the Slot Details !!! ", e);
			throw new DaoException("Error while Searching Slot Details !!!");
		}
		return slotListDB;
	}
	
}
