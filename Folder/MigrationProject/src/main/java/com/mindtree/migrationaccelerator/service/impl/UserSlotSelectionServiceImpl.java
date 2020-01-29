package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IMasterSheetDetailsDao;
import com.mindtree.migrationaccelerator.dao.IUserSlotSelectionDao;
import com.mindtree.migrationaccelerator.dto.UserDTO;
import com.mindtree.migrationaccelerator.dto.UserSlotSelectionDTO;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.enums.MigrationStatus;
import com.mindtree.migrationaccelerator.enums.SlotStatus;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IUserSlotSelection;
import com.mindtree.migrationaccelerator.util.DTOToBeanConverter;
import com.mindtree.migrationaccelerator.util.TimeZoneUility;

@Service
public class UserSlotSelectionServiceImpl implements IUserSlotSelection{

	final static Logger logger = Logger.getLogger(UserSlotSelectionServiceImpl.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	@Autowired
	private IUserSlotSelectionDao iUserSlotSelectionDao;
	
	@Autowired
	private DTOToBeanConverter dtoToBeanConverter;

	@Autowired
	private IMasterSheetDetailsDao iMasterSheetDetailsDao;

	@Transactional
	@Override
	public boolean bookSlot(UserDTO userDTO, UserSlotSelectionDTO userSlotSelectionDTO) throws ServiceException {
		boolean isSlotBooked = true;
		
		Transaction tx = null;
		try{
			logger.info("Inserting records into UserSlotSelection tables started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			
			MasterSheetData masterSheetDataUser = iMasterSheetDetailsDao.getUserRecord(session, userDTO.getEmail());
			
			UserSlotSelection userSlotSelection = dtoToBeanConverter.userSlotSelectionDTOToUserSlotSelectionEntity(userSlotSelectionDTO);
			userSlotSelection.setCreatedBy(userDTO.getEmail());
			userSlotSelection.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			userSlotSelection.setMasterSheetData(masterSheetDataUser);
			userSlotSelection.setIsDeleted(false);
			userSlotSelection.setMigrationStatus(MigrationStatus.NOT_STARTED);
			userSlotSelection.setSlotStatus(SlotStatus.BOOKED);
			userSlotSelection.setBookingCounter(0);
			
			//Update User Count Occupied by +1 
			Slot slot = userSlotSelection.getSlot();
			slot.setUserCountOccupied(slot.getUserCountOccupied() + 1);
			
			// if slot is full display its status as full
			if(slot.getUserCountCapacity() == slot.getUserCountOccupied()) {
				slot.setSlotStatus(SlotStatus.NOT_AVAILABLE);
			}
			
			userSlotSelection.setSlot(slot);
			
			isSlotBooked = iUserSlotSelectionDao.bookSlot(session, userSlotSelection);

			tx.commit();
			logger.info("Inserting records into UserSlotSelection tables completed !!!");
		} catch (DaoException e) {
			isSlotBooked = false;
			if (tx != null)
				tx.rollback();
			logger.error("Error While Inserting records into UserSlotSelection tables !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return isSlotBooked;
	}

	@Override
	@Transactional
	public boolean updateSlotSelection(UserSlotSelectionDTO userSlotSelectionDTO, UserDTO userDTO) throws ServiceException {
		boolean isUpdated = false;
		
		Transaction tx = null;
		try{
			logger.info("Updating UserSlotSelection details started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			
			UserSlotSelection userSlotSelection = dtoToBeanConverter.userSlotSelectionDTOToUserSlotSelectionEntity(userSlotSelectionDTO);
			userSlotSelection.setUpdatedBy(userDTO.getEmail());
			userSlotSelection.setLastUpdated(TimeZoneUility.getCurrentDateTime());
			
			int currentCounter = userSlotSelection.getBookingCounter();
			userSlotSelection.setBookingCounter(currentCounter + 1);
			
			isUpdated = iUserSlotSelectionDao.updateSlotSelection(iBaseDao.getSession(), userSlotSelection);
			tx.commit();
			logger.info("Updating UserSlotSelection Details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Updating UserSlotSelection Details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return isUpdated;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean deleteSlotSelection(int userSlotSelectionID, UserDTO userDTO) throws ServiceException {
		boolean isDeleted = false;
		
		Transaction tx = null;
		try{
			logger.info("Deleting UserSlotSelection details started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			
			UserSlotSelection userSlotSelection = (UserSlotSelection) iBaseDao.getEntity(session, UserSlotSelection.class, userSlotSelectionID);
			userSlotSelection.setUpdatedBy(userDTO.getEmail());
			userSlotSelection.setLastUpdated(TimeZoneUility.getCurrentDateTime());

			isDeleted = iUserSlotSelectionDao.deleteSlotSelection(iBaseDao.getSession(), userSlotSelection);
			tx.commit();
			logger.info("Deleting UserSlotSelection Details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Deleting UserSlotSelection Details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return isDeleted;
	}
	
	@Override
	@Transactional
	public List<UserSlotSelection> getUserSlotDetailsByUserEmailID(String emailID) throws ServiceException {
		
		Transaction tx = null;
		List<UserSlotSelection> userSlotSelectionList = null;
		try{
			logger.info("Fetching UserSlotSelection details started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			
			MasterSheetData masterSheetDataUser = iMasterSheetDetailsDao.getUserRecord(session, emailID);
			
			userSlotSelectionList = iUserSlotSelectionDao.getUserSlotDetailsByMasterSheetRecord(session, masterSheetDataUser);
			tx.commit();
			logger.info("Fetching UserSlotSelection Details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Fetching UserSlotSelection Details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return userSlotSelectionList;
	}

}
