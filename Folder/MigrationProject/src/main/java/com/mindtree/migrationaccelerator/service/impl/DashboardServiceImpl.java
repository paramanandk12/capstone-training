package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IDashboardDao;
import com.mindtree.migrationaccelerator.dto.MasterWorkbookDTO;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IBaseService;
import com.mindtree.migrationaccelerator.service.IDashboardService;
import com.mindtree.migrationaccelerator.service.IProjectManagementService;
import com.mindtree.migrationaccelerator.util.CollectionBeanToCollectionDTOConverter;

@Service
public class DashboardServiceImpl implements IDashboardService {

	final static Logger logger = Logger.getLogger(DashboardServiceImpl.class);

	@Autowired
	private IBaseService iBaseService;
	
	@Autowired
	private IProjectManagementService iProjectManagementService;
	
	@Autowired
	private IDashboardDao dashboardDao;

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	@Autowired
	private CollectionBeanToCollectionDTOConverter collectionBeanToCollectionDTOConverter;

	@Override
	@Transactional
	public List<MasterWorkbookDTO> getAllMastersheetDataRecords(int projectId) throws ServiceException {

		List<MasterWorkbookDTO> listWorkbookDTO = null;
		List<MasterWorkbook> listWorkbook = null;
		Transaction tx = null;
		try (Session session = iBaseService.getSessionFactory().openSession()) {
			logger.info("Fetching MasterWorkbookDTO data started !!!");
			tx = session.beginTransaction();

			listWorkbook = dashboardDao.getWorkBooksByProjectId(session, projectId);

			for (MasterWorkbook masterWorkbook : listWorkbook) {
				List<MasterSheetData> masterSheetDatas = dashboardDao.getMastersheetDataByID(session,
						masterWorkbook.getMasterWorkbookId());
				masterWorkbook.setMasterSheetData(masterSheetDatas);
			}

			listWorkbookDTO = collectionBeanToCollectionDTOConverter.getMasterWorkbookDTOList(listWorkbook);
			tx.commit();
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return listWorkbookDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserSlotSelection> getScheduleReportForUser(int projectId, int countryId, int siteId)
			throws ServiceException {
		Transaction tx = null;
		List<UserSlotSelection> slotListDB = null;
		try {
			logger.info("Fetching getScheduleReportForUser data started !!!");
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			Project project = iProjectManagementService.getProjectEntityById(session, projectId);
			Country country = (Country) iBaseDao.getEntity(session, Country.class, countryId);
			Site site = (Site) iBaseDao.getEntity(session, Site.class, siteId);
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserSlotSelection> criteria = (CriteriaQuery<UserSlotSelection>) builder.createQuery(UserSlotSelection.class);
			Root<UserSlotSelection> root = (Root<UserSlotSelection>) criteria.from(UserSlotSelection.class);
			
			Predicate valRestriction = builder.and(builder.equal(root.get("slot").get("project"), project), 
					builder.equal(root.get("slot").get("country"), country),
					builder.equal(root.get("slot").get("site"), site),
					builder.equal(root.get("slot").get("isDeleted"), 0),
					builder.equal(root.get("isDeleted"), 0));		
					
			criteria.where(valRestriction);
			slotListDB = session.createQuery(criteria).list();
			tx.commit();
			logger.info("Fetching getScheduleReportForUser data completed !!!");
		}catch(Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch getScheduleReportForUser report !!!", e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		
		return slotListDB;
	}
}