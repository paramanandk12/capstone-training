package com.mindtree.migrationaccelerator.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.ICountryDetailsDao;
import com.mindtree.migrationaccelerator.dao.IMasterSheetDetailsDao;
import com.mindtree.migrationaccelerator.dao.IProjectManagementDao;
import com.mindtree.migrationaccelerator.dao.ISiteDetailsDao;
import com.mindtree.migrationaccelerator.dao.ISlotDao;
import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.MasterWorkbookDTO;
import com.mindtree.migrationaccelerator.dto.SlotDTO;
import com.mindtree.migrationaccelerator.dto.SlotDetailsDTO;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.enums.SlotStatus;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.ISlotService;
import com.mindtree.migrationaccelerator.util.CollectionBeanToCollectionDTOConverter;
import com.mindtree.migrationaccelerator.util.DTOToBeanConverter;
import com.mindtree.migrationaccelerator.util.TimeZoneUility;

@Service
public class SlotServiceImpl implements ISlotService {

	final static Logger logger = Logger.getLogger(SlotServiceImpl.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	@Autowired
	private ISlotDao iSlotDao;
	
	@Autowired
	private ICountryDetailsDao iCountryDetailsDao;
	
	@Autowired
	private ISiteDetailsDao iSiteDetailsDao;
	
	@Autowired
	private IProjectManagementDao iProjectManagementDao;

	@Autowired
	private CollectionBeanToCollectionDTOConverter collectionBeanToCollectionDTOConverter;

	@Autowired
	private DTOToBeanConverter dtoToBeanConverter;
	
	@Autowired
	private IMasterSheetDetailsDao iMasterSheetDetailsDao;

	@Override
	@Transactional
	public List<MasterWorkbookDTO> getAllMastersheetDataRecords(int projectId) throws ServiceException {

		List<MasterWorkbookDTO> listWorkbookDTO = null;
		List<MasterWorkbook> listWorkbook = null;
		Transaction tx = null;
		try{
			
			logger.info("Fetching MasterWorkbookDTO data started !!!");
			tx = iBaseDao.getSession().beginTransaction();

			 listWorkbook = iSlotDao.getWorkBooksByProjectId(iBaseDao.getSession(), projectId);

			for (MasterWorkbook masterWorkbook : listWorkbook) {
				List<MasterSheetData> masterSheetDatas = iSlotDao.getMastersheetDataByID(iBaseDao.getSession(),
						masterWorkbook.getMasterWorkbookId());
				masterWorkbook.setMasterSheetData(masterSheetDatas);
			}

			listWorkbookDTO = collectionBeanToCollectionDTOConverter.getMasterWorkbookDTOList(listWorkbook);
			tx.commit();
			logger.info("Fetching MasterWorkbookDTO data completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Fetching MasterWorkbookDTO data  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return listWorkbookDTO;
	}
	
	@Transactional
	@Override
	public boolean insertSlotSDetails(SlotDetailsDTO slotDetailsDTO, AdminDTO adminDTO)  throws ServiceException {
		boolean isInserted = true;
		Transaction tx = null;
		try{
			Session session = iBaseDao.getSession();
			logger.info("Inserting records into Slot tables started !!!");
			tx = session.beginTransaction();

			Project project = iProjectManagementDao.getProjectEntityById(session, slotDetailsDTO.getProjectId());
			Country country = iCountryDetailsDao.getCountryEntityById(session, slotDetailsDTO.getCountryId());
			Site site = iSiteDetailsDao.getSiteEntityById(session, slotDetailsDTO.getSiteId());
			String timezone = slotDetailsDTO.getTimezone();
			
			for (SlotDTO slotDTO : slotDetailsDTO.getSlotDetails()) {
				
				slotDTO.setStartDate(slotDetailsDTO.getStartDate());
				slotDTO.setEndDate(slotDetailsDTO.getEndDate());
				
				Slot slot = dtoToBeanConverter.slotDTOToSlotEntity(slotDTO);
				slot.setSlotStatus(SlotStatus.AVAILABLE);
				slot.setCountry(country);
				slot.setProject(project);
				slot.setSite(site);
				slot.setCreatedOn(TimeZoneUility.getCurrentDateTime());
				slot.setCreatedBy(adminDTO.getEmail());
				slot.setTimeZone(timezone);
				slot.setUserCountOccupied(0);
				slot.setIsDeleted(false);
				iSlotDao.insertSlot(session, slot);
			}

			tx.commit();
			logger.info("Inserting records into Slot tables completed !!!");
		} catch (DaoException e) {
			isInserted = false;
			if (tx != null)
				tx.rollback();
			logger.error("Error While Inserting records into Slot tables !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return isInserted;
	}

	@Override
	@Transactional
	public List<Slot> getSlotsByProject(int projectId) throws ServiceException {
		List<Slot> slots = null;
		Transaction tx = null;
		try{
			logger.info("Fetching Slots details started !!!");
			tx = iBaseDao.getSession().beginTransaction();
			slots = iSlotDao.getSlotsByProjectId(iBaseDao.getSession(), projectId);
			tx.commit();
			logger.info("Fetching Slots details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Fetching Slots details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return slots;
	}

	@Override
	@Transactional
	public boolean updateSlotDetails(SlotDTO slotDTO, AdminDTO adminDTO) throws ServiceException {
		boolean isUpdated = false;
		
		Transaction tx = null;
		try{
			logger.info("Updating Slot details started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			
			Slot slot = dtoToBeanConverter.slotDTOToSlotEntity(slotDTO);
			slot.setUpdatedBy(adminDTO.getEmail());
			slot.setLastUpdated(TimeZoneUility.getCurrentDateTime());
			
			isUpdated = iSlotDao.updateSlot(iBaseDao.getSession(), slot);
			tx.commit();
			logger.info("Updating Slot Details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Updating Slot Details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return isUpdated;
	}

	@Override
	@Transactional
	public boolean deleteSlot(int SlotID, AdminDTO adminDTO) throws ServiceException {
		boolean isDeleted = false;
		
		Transaction tx = null;
		try{
			logger.info("Deleting Slot details started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			
			Slot slot = iSlotDao.getSlotBySlotID(session, SlotID);
			slot.setUpdatedBy(adminDTO.getEmail());
			slot.setLastUpdated(TimeZoneUility.getCurrentDateTime());

			isDeleted = iSlotDao.deleteSlot(iBaseDao.getSession(), slot);
			tx.commit();
			logger.info("Deleting Slot Details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Deleting Slot Details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return isDeleted;
	}
	
	@Override
	@Transactional
	public Slot getSlotBySlotID(int slotID) throws ServiceException {
		
		Transaction tx = null;
		Slot slot = null;
		try{
			logger.info("Fetching Slot details started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			slot = iSlotDao.getSlotBySlotID(session, slotID);
			tx.commit();
			logger.info("Fetching Slot Details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Fetching Slot Details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return slot;
	}

	@Override
	@Transactional
	public List<Slot> getSlotsByDate(Date date, String email) throws ServiceException {
		List<Slot> slots = null;
		Transaction tx = null;
		try{
			logger.info("Fetching Slots details started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			
			MasterSheetData masterSheetDataUser = iMasterSheetDetailsDao.getUserRecord(session, email);
			slots = iSlotDao.getSlotsByDate(session, date, masterSheetDataUser);
			tx.commit();
			logger.info("Fetching Slots details completed !!!");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error While Fetching Slots details  !!!");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return slots;
	}
	
}