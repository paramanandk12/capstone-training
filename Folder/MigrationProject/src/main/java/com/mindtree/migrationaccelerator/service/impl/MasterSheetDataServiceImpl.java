package com.mindtree.migrationaccelerator.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IMasterSheetDetailsDao;
import com.mindtree.migrationaccelerator.dao.IMasterWorkbookDao;
import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.AllEntitiesDTO;
import com.mindtree.migrationaccelerator.dto.FileUploadResponeDTO;
import com.mindtree.migrationaccelerator.dto.ProcessedFileDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.dto.ResponseDTO;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;
import com.mindtree.migrationaccelerator.enums.MigrationStatus;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.ICountryService;
import com.mindtree.migrationaccelerator.service.IMasterSheetDataService;
import com.mindtree.migrationaccelerator.service.IProjectManagementService;
import com.mindtree.migrationaccelerator.service.ISiteService;
import com.mindtree.migrationaccelerator.service.IUserAccountDetailsService;
import com.mindtree.migrationaccelerator.service.IUserEmailDetailsService;
import com.mindtree.migrationaccelerator.service.IUserLocationDetailsService;
import com.mindtree.migrationaccelerator.service.IUserOtherDetailsService;
import com.mindtree.migrationaccelerator.service.IUserSkypeDetailsService;
import com.mindtree.migrationaccelerator.service.IUserWorkstationDetailsService;
import com.mindtree.migrationaccelerator.util.DataRecordsToEntityConverter;
import com.mindtree.migrationaccelerator.util.TimeZoneUility;

@Service
public class MasterSheetDataServiceImpl implements IMasterSheetDataService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;
	
	@Autowired
	private ISiteService iSiteService;
	
	@Autowired
	private IProjectManagementService iProjectManagementService;
	
	@Autowired
	private IUserLocationDetailsService iUserLocationDetailsService;

	@Autowired
	private IUserAccountDetailsService iAccountDetailsService;

	@Autowired
	private IUserEmailDetailsService iEmailDetailsService;

	@Autowired
	private IUserSkypeDetailsService iSkypeDetailsService;

	@Autowired
	private IUserWorkstationDetailsService iWorkstationDetailsService;

	@Autowired
	private IUserOtherDetailsService iOtherDetailsService;

	@Autowired
	private IMasterWorkbookDao iMasterWorkbookDao;
	
	@Autowired
	private IMasterSheetDetailsDao iMasterSheetDetailsDao;
	
	@Autowired
	private ICountryService iCountryService;
	
	final static Logger logger = Logger.getLogger(MasterSheetDataServiceImpl.class);
	
	@Override
	@Transactional
	public ResponseDTO insertAllEntitiesFromSheet(MultipartFile file, ProjectDTO projectDTO,
			ProcessedFileDTO processedFileDTO, FileUploadResponeDTO fileUploadResponeDTO, AdminDTO adminDTO) throws ServiceException {

		Transaction tx = null;
		Session session = iBaseDao.getSession();
		tx = session.beginTransaction();
		ResponseDTO response = new ResponseDTO();
		try {
			MasterWorkbook masterWorkbook = createMasterWorkBookRecord(file, projectDTO, adminDTO);
			iMasterWorkbookDao.insertMasterWorkbookRecord(session, masterWorkbook);
			processedFileDTO.setMasterWorkBook(masterWorkbook);
			
			AllEntitiesDTO allEntitiesDTO = createALLEntitiesFromSheetToInsert(processedFileDTO, fileUploadResponeDTO, adminDTO);
			boolean isProcessed = insertMasterSheetChildEntitiesIntoDB(session, allEntitiesDTO);
			
			tx.commit();
			response.setAllEntities(allEntitiesDTO);
			response.setProcessed(isProcessed);
		}catch(ServiceException e) {
			if (tx != null)
				tx.rollback();
			logger.error(
					"Unable to store user location entity in the DB and insertUserLocationDetails()  method is excecuting!!!"
							+ e);
			response.setProcessed(false);
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error(
					"Unable to store user location entity in the DB and insertUserLocationDetails()  method is excecuting!!!"
							+ e);
			response.setProcessed(false);
		}catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error(
					"Unable to store user location entity in the DB and insertUserLocationDetails()  method is excecuting!!!"
							+ e);
			response.setProcessed(false);
		}
		return response;
	}
	
	@Override
	public AllEntitiesDTO insertUniqueCountriesFromSheet(AllEntitiesDTO allEntitiesDTO) throws ServiceException {
		logger.info("Inserting Unique Coutnries from MasterSheet Started..!");
		
		try {
			List<Country> countries = allEntitiesDTO.getCountryDetailsList();
			List<Country> tempCountries = new ArrayList<Country>();
			
			for(Country country : countries) {
				boolean isCountryPresent = iCountryService.isCountryExists(iBaseDao.getSession(), country.getCountryName(), allEntitiesDTO.getProject().getProjectId());

				if(!isCountryPresent) {
					iCountryService.saveCountry(iBaseDao.getSession(), country);
					tempCountries.add(country);
				}
			}
			allEntitiesDTO.getCountryDetailsList().clear();
			allEntitiesDTO.setCountryDetailsList(tempCountries);
		}catch(Exception e){
			logger.error("Unable to store country entity in the DB and insertUniqueCountriesFromSheet()  method is excecuting!!!"+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		logger.info("Inserting Unique Coutnries from MasterSheet Completed..!");
		return allEntitiesDTO;
	}
	
	@Override
	public AllEntitiesDTO insertUniqueSitesFromSheet(AllEntitiesDTO allEntitiesDTO) throws ServiceException {
		logger.info("Inserting Unique Sites from MasterSheet Started..!");
		try {
			List<Site> sites = allEntitiesDTO.getSiteDetailsList();
			List<Site> tempSites = new ArrayList<Site>();
			
			for(Site site : sites) {
				Country country = iCountryService.getCountryByNameAndProject(site.getCountry().getCountryName(),allEntitiesDTO.getProject().getProjectId());
				boolean isSitePresent = iSiteService.isSiteExists(iBaseDao.getSession(), site.getSiteName(), country.getCountryId(), allEntitiesDTO.getProject().getProjectId());
				
				if(!isSitePresent) {
					site.setCountry(country);
					iSiteService.saveSite(iBaseDao.getSession(), site);
					tempSites.add(site);
				}
			}
			allEntitiesDTO.getSiteDetailsList().clear();
			allEntitiesDTO.setSiteDetailsList(tempSites);
		}catch(Exception e){
			logger.error("Unable to store site entity in the DB and insertUniqueSitesFromSheet()  method is excecuting!!!"+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		logger.info("Inserting Unique Sites from MasterSheet Completed..!");
		return allEntitiesDTO;
	}
	
	public MasterWorkbook createMasterWorkBookRecord(MultipartFile file, ProjectDTO projectDTO, AdminDTO adminDTO) throws ServiceException {
		MasterWorkbook masterWorkbook = new MasterWorkbook();
		masterWorkbook.setName(file.getOriginalFilename());
		masterWorkbook.setCreatedOn(TimeZoneUility.getCurrentDateTime());
		masterWorkbook.setCreatedBy(adminDTO.getEmail());
		masterWorkbook.setSize(file.getSize());
		Project project = iProjectManagementService.getProjectEntityById(projectDTO.getId());
		masterWorkbook.setProject(project);
		masterWorkbook.setFormat("EXCEL");
		return masterWorkbook;
	}
	
	private AllEntitiesDTO createALLEntitiesFromSheetToInsert(ProcessedFileDTO processedFileDTO, FileUploadResponeDTO fileUploadResponeDTO, AdminDTO adminDTO) throws ServiceException, DaoException {
		String dataRecords2D[][] = processedFileDTO.getDataRecords2D();
		Map<String, Integer> mapColumnIndexName = processedFileDTO.getMapColumnAndIndex();
		AllEntitiesDTO allEntitiesDTO = null;
		
		List<UserLocationDetails> userLocDetails = new ArrayList<>();
		List<UserAccountDetails> userAccountDetails = new ArrayList<>();
		List<UserEmailDetails> userEmailDetails = new ArrayList<>();
		List<UserSkypeDetails> userSkypelDetails = new ArrayList<>();
		List<UserWorkstationDetails> userWorkstationDetails = new ArrayList<>();
		List<UserOtherDetails> userOtherDetails = new ArrayList<>();
		List<MasterSheetData> masterSheet = new ArrayList<>();
		List<Country> countryDetails = new ArrayList<>();
		List<Site> siteDetails = new ArrayList<>();
		
		Collection<String> existingEmailIDs = fileUploadResponeDTO.getSimilarRecordsList();

		for (String[] rowData : dataRecords2D) {

			MasterSheetData masterSheetData = DataRecordsToEntityConverter.createMasterSheetDetails(rowData,
					mapColumnIndexName);
			
			//Skip duplicate records
			if(fileUploadResponeDTO != null && fileUploadResponeDTO.getSimilarRecordsList() != null && !fileUploadResponeDTO.getSimilarRecordsList().isEmpty()) {
				if(existingEmailIDs.contains(masterSheetData.getLoginIdUPNasIs())) {
					continue;
				}
			}
			
			if(masterSheetData != null) {
				masterSheetData.setMigrationStatus(MigrationStatus.STARTED);
				masterSheetData.setMasterWorkbook(processedFileDTO.getMasterWorkBook());
				masterSheetData.setCreatedOn(TimeZoneUility.getCurrentDateTime());
				masterSheetData.setCreatedBy(adminDTO.getEmail());
				masterSheetData.setProject(processedFileDTO.getProject());
				iMasterSheetDetailsDao.insertMastersheetData(iBaseDao.getSession(), masterSheetData);
			}
			
			//Set UserLocationDetails attribute
			UserLocationDetails locDetails = DataRecordsToEntityConverter.createUserLocationDetails(rowData,
					mapColumnIndexName);
			locDetails.setMasterSheetData(masterSheetData);
			locDetails.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			locDetails.setCreatedBy(adminDTO.getEmail());
			
			//Set UserAccountDetails attribute
			UserAccountDetails accountDetails = DataRecordsToEntityConverter.createUserAccountDetails(rowData,
					mapColumnIndexName);
			accountDetails.setMasterSheetData(masterSheetData);
			accountDetails.setProject(processedFileDTO.getProject());
			accountDetails.setMigrationStatus(MigrationStatus.STARTED);
			accountDetails.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			accountDetails.setCreatedBy(adminDTO.getEmail());
			
			//Set UserEmailDetails attribute
			UserEmailDetails emailDetails = DataRecordsToEntityConverter.createUserEmailDetails(rowData,
					mapColumnIndexName);
			emailDetails.setMasterSheetData(masterSheetData);
			emailDetails.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			emailDetails.setCreatedBy(adminDTO.getEmail());
			
			//Set UserSkypeDetails attribute
			UserSkypeDetails skypeDetails = DataRecordsToEntityConverter.createUserSkypeDetails(rowData,
					mapColumnIndexName);
			skypeDetails.setMasterSheetData(masterSheetData);
			skypeDetails.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			skypeDetails.setCreatedBy(adminDTO.getEmail());
			
			//Set UserWorkstationDetails attribute
			UserWorkstationDetails workstationDetails = DataRecordsToEntityConverter
					.createUserWorkstationDetails(rowData, mapColumnIndexName);
			workstationDetails.setMasterSheetData(masterSheetData);
			workstationDetails.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			workstationDetails.setCreatedBy(adminDTO.getEmail());
			
			//Set UserOtherDetails attribute
			UserOtherDetails otherDetails = DataRecordsToEntityConverter.createUserOtherDetails(rowData,
					mapColumnIndexName);
			otherDetails.setMasterSheetData(masterSheetData);
			otherDetails.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			otherDetails.setCreatedBy(adminDTO.getEmail());
			
			
			Country country = DataRecordsToEntityConverter.createCountryDetails(rowData, mapColumnIndexName);
			country.setProject(processedFileDTO.getProject());
			country.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			country.setCreatedBy(adminDTO.getEmail());
			
			Site site = DataRecordsToEntityConverter.createSiteDetails(rowData, mapColumnIndexName);
			site.setProject(processedFileDTO.getProject());
			site.setCreatedOn(TimeZoneUility.getCurrentDateTime());
			site.setCreatedBy(adminDTO.getEmail());
			site.setCountry(country);
			
			userLocDetails.add(locDetails);
			userAccountDetails.add(accountDetails);
			userEmailDetails.add(emailDetails);
			userSkypelDetails.add(skypeDetails);
			userWorkstationDetails.add(workstationDetails);
			userOtherDetails.add(otherDetails);
			masterSheet.add(masterSheetData);
			countryDetails.add(country);
			siteDetails.add(site);
		}

		allEntitiesDTO = new AllEntitiesDTO();
		
		allEntitiesDTO.setProject(processedFileDTO.getProject());
		allEntitiesDTO.setUserLocationDetailsList(userLocDetails);
		allEntitiesDTO.setUserAccountDetailsList(userAccountDetails);
		allEntitiesDTO.setUserEmailDetailsList(userEmailDetails);
		allEntitiesDTO.setUserSkypeDetailsList(userSkypelDetails);
		allEntitiesDTO.setUserWorkstationDetailsList(userWorkstationDetails);
		allEntitiesDTO.setUserOtherDetailsList(userOtherDetails);
		allEntitiesDTO.setMasterSheetDetailsList(masterSheet);
		allEntitiesDTO.setCountryDetailsList(countryDetails);
		allEntitiesDTO.setSiteDetailsList(siteDetails);
		return allEntitiesDTO;
	}
	
	private Boolean insertMasterSheetChildEntitiesIntoDB(Session session, AllEntitiesDTO allEntitiesDTO) throws ServiceException {
		Boolean status = false;
		try {
			
			if(allEntitiesDTO.getUserLocationDetailsList() != null)
				iUserLocationDetailsService.insertUserLocationDetails(session, allEntitiesDTO.getUserLocationDetailsList());
			
			if(allEntitiesDTO.getUserAccountDetailsList() != null)
				iAccountDetailsService.insertUserAccountDetails(session, allEntitiesDTO.getUserAccountDetailsList());
			
			if(allEntitiesDTO.getUserEmailDetailsList() != null)
				iEmailDetailsService.insertUserEmailDetails(session, allEntitiesDTO.getUserEmailDetailsList());
			
			if(allEntitiesDTO.getUserSkypeDetailsList() != null)
				iSkypeDetailsService.insertUserSkypeDetails(session, allEntitiesDTO.getUserSkypeDetailsList());
			
			if(allEntitiesDTO.getUserWorkstationDetailsList() != null)
				iWorkstationDetailsService.insertUserWorkStationDetails(session, allEntitiesDTO.getUserWorkstationDetailsList());
			
			if(allEntitiesDTO.getUserOtherDetailsList() != null)
				iOtherDetailsService.insertUserOtherDetails(session, allEntitiesDTO.getUserOtherDetailsList());
			
			status = true;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return status;
	}
	
	@Override
	@Transactional
	public ResponseDTO updateAllEntitiesFromSheet(MultipartFile file, ProjectDTO projectDTO,
			ProcessedFileDTO processedFileDTO, FileUploadResponeDTO fileResponseUploadDTO, AdminDTO adminDTO) {
		Transaction tx = null;
		Session session = iBaseDao.getSession();
		tx = session.beginTransaction();
		ResponseDTO response = new ResponseDTO();
		try {
			MasterWorkbook masterWorkbook = createMasterWorkBookRecord(file, projectDTO, adminDTO);
			iMasterWorkbookDao.insertMasterWorkbookRecord(session, masterWorkbook);
			processedFileDTO.setMasterWorkBook(masterWorkbook);
			AllEntitiesDTO allEntitiesDTO = createALLEntitiesFromSheetToUpdate(processedFileDTO, fileResponseUploadDTO, adminDTO);
			allEntitiesDTO.setProject(masterWorkbook.getProject());
			
			boolean isProcessed = updateMasterSheetChildEntitiesIntoDB(session, allEntitiesDTO);
			
			logger.info("Updating enetities returned , " + isProcessed);
			tx.commit();
			response.setAllEntities(allEntitiesDTO);
			response.setProcessed(isProcessed);
		}catch(ServiceException e) {
			if (tx != null)
				tx.rollback();
			logger.error(
					"Unable to store user location entity in the DB and insertUserLocationDetails()  method is excecuting!!!"
							+ e);
			response.setProcessed(false);
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error(
					"Unable to store user location entity in the DB and insertUserLocationDetails()  method is excecuting!!!"
							+ e);
			response.setProcessed(false);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	private AllEntitiesDTO createALLEntitiesFromSheetToUpdate(ProcessedFileDTO processedFileDTO,
			FileUploadResponeDTO fileUploadResponeDTO, AdminDTO adminDTO) throws ServiceException, DaoException {
		String dataRecords2D[][] = processedFileDTO.getDataRecords2D();
		Map<String, Integer> mapColumnIndexName = processedFileDTO.getMapColumnAndIndex();
		AllEntitiesDTO allEntitiesDTO = null;
		
		List<UserLocationDetails> userLocDetails = new ArrayList<>();
		List<UserAccountDetails> userAccountDetails = new ArrayList<>();
		List<UserEmailDetails> userEmailDetails = new ArrayList<>();
		List<UserSkypeDetails> userSkypelDetails = new ArrayList<>();
		List<UserWorkstationDetails> userWorkstationDetails = new ArrayList<>();
		List<UserOtherDetails> userOtherDetails = new ArrayList<>();
		List<MasterSheetData> masterSheet = new ArrayList<>();
		List<Country> countryDetails = new ArrayList<>();
		List<Site> siteDetails = new ArrayList<>();
		Collection<String> existingEmailIDs = fileUploadResponeDTO.getSimilarRecordsList();
		int i = 0;
		
		for (String[] rowData : dataRecords2D) {

			MasterSheetData masterSheetDataFromSheet = DataRecordsToEntityConverter.createMasterSheetDetails(rowData,
					mapColumnIndexName);
			
			if(fileUploadResponeDTO != null && fileUploadResponeDTO.getSimilarRecordsList() != null && !fileUploadResponeDTO.getSimilarRecordsList().isEmpty()) {
				if(existingEmailIDs.contains(masterSheetDataFromSheet.getLoginIdUPNasIs())) {
					//Update MasterSheet Details
					MasterSheetData masterSheetDataFromDB = iMasterSheetDetailsDao.getUserRecord(iBaseDao.getSession(), masterSheetDataFromSheet.getLoginIdUPNasIs());

					if(masterSheetDataFromDB != null) {
						i = i + 1;
						masterSheetDataFromDB = DataRecordsToEntityConverter.updateMasterSheetDetails(rowData,
								mapColumnIndexName, masterSheetDataFromDB);
						masterSheetDataFromDB.setMasterWorkbook(processedFileDTO.getMasterWorkBook());
						masterSheetDataFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
						masterSheetDataFromDB.setUpdatedBy(adminDTO.getEmail());
						masterSheet.add(masterSheetDataFromDB);
						
						Integer masterSheetID = masterSheetDataFromDB.getMasterSheetRecordsId();
						//Update User Location Details
						UserLocationDetails userLocationFromDB = (UserLocationDetails) iBaseDao.getUserEntityByMasterSheetID(iBaseDao.getSession(), UserLocationDetails.class, "masterSheetData", masterSheetID);
						if(userLocationFromDB != null) {
							userLocationFromDB = DataRecordsToEntityConverter.updateUserLocationDetails(rowData,
									mapColumnIndexName, userLocationFromDB);
							userLocationFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
							userLocationFromDB.setUpdatedBy(adminDTO.getEmail());
							userLocDetails.add(userLocationFromDB);
						}
						
						//Update UserAccountDetails attribute
						UserAccountDetails userAccountDetailsFromDB = (UserAccountDetails) iBaseDao.getUserEntityByMasterSheetID(iBaseDao.getSession(), UserAccountDetails.class, "masterSheetData", masterSheetID);
						if(userAccountDetailsFromDB != null) {
							userAccountDetailsFromDB = DataRecordsToEntityConverter.updateUserAccountDetails(rowData,
									mapColumnIndexName, userAccountDetailsFromDB);
							userAccountDetailsFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
							userAccountDetailsFromDB.setUpdatedBy(adminDTO.getEmail());
							userAccountDetails.add(userAccountDetailsFromDB);
						}
						
						//Set UserEmailDetails attribute
						UserEmailDetails userEmailFromDB = (UserEmailDetails) iBaseDao.getUserEntityByMasterSheetID(iBaseDao.getSession(), UserEmailDetails.class, "masterSheetData", masterSheetID);
						if(userEmailFromDB != null) {
							userEmailFromDB = DataRecordsToEntityConverter.updateUserEmailDetails(rowData,
									mapColumnIndexName, userEmailFromDB);
							userEmailFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
							userEmailFromDB.setUpdatedBy(adminDTO.getEmail());
							userEmailDetails.add(userEmailFromDB);
						}
						
						//Update UserSkypeDetails attribute
						UserSkypeDetails userSkypeDetailsFromDB = (UserSkypeDetails) iBaseDao.getUserEntityByMasterSheetID(iBaseDao.getSession(), UserSkypeDetails.class, "masterSheetData", masterSheetID);
						if(userSkypeDetailsFromDB != null) {
							userSkypeDetailsFromDB = DataRecordsToEntityConverter.updateUserSkypeDetails(rowData,
									mapColumnIndexName, userSkypeDetailsFromDB);
							userSkypeDetailsFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
							userSkypeDetailsFromDB.setUpdatedBy(adminDTO.getEmail());
							userSkypelDetails.add(userSkypeDetailsFromDB);
						}
						
						//Update UserWorkstationDetails attribute
						UserWorkstationDetails userWorkstationDetailsFromDB = (UserWorkstationDetails) iBaseDao.getUserEntityByMasterSheetID(iBaseDao.getSession(), UserWorkstationDetails.class, "masterSheetData", masterSheetID);
						if(userWorkstationDetailsFromDB != null) {
							userWorkstationDetailsFromDB = DataRecordsToEntityConverter
									.updateUserWorkstationDetails(rowData, mapColumnIndexName, userWorkstationDetailsFromDB);
							userWorkstationDetailsFromDB.setUpdatedBy(adminDTO.getEmail());
							userWorkstationDetailsFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
							userWorkstationDetails.add(userWorkstationDetailsFromDB);
						}
						
						//Update UserOtherDetails attribute
						UserOtherDetails userOtherDetailsFromDB = (UserOtherDetails) iBaseDao.getUserEntityByMasterSheetID(iBaseDao.getSession(), UserOtherDetails.class, "masterSheetData", masterSheetID);
						if(userOtherDetailsFromDB != null) {
							userOtherDetailsFromDB = DataRecordsToEntityConverter.updateUserOtherDetails(rowData,
									mapColumnIndexName, userOtherDetailsFromDB);
							userOtherDetailsFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
							userOtherDetailsFromDB.setUpdatedBy(adminDTO.getEmail());
							userOtherDetails.add(userOtherDetailsFromDB);
						}
						
						//Update UserOtherDetails attribute
						if(userOtherDetailsFromDB != null) {
							userOtherDetailsFromDB = DataRecordsToEntityConverter.updateUserOtherDetails(rowData,
									mapColumnIndexName, userOtherDetailsFromDB);
							userOtherDetailsFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
							userOtherDetailsFromDB.setUpdatedBy(adminDTO.getEmail());
							userOtherDetails.add(userOtherDetailsFromDB);
						}
						
						//Insert Country/Site if not present
						Country country = iCountryService.getCountryByNameAndProject(iBaseDao.getSession(), masterSheetDataFromDB.getCountry(), processedFileDTO.getProject().getProjectId());
						if(country == null) {
							//If country is new then it must be associated with new site
							country = DataRecordsToEntityConverter.createCountryDetails(rowData, mapColumnIndexName);
							country.setProject(processedFileDTO.getProject());
							country.setCreatedOn(TimeZoneUility.getCurrentDateTime());
							country.setCreatedBy(adminDTO.getEmail());
							countryDetails.add(country);
							
							Site site = DataRecordsToEntityConverter.createSiteDetails(rowData, mapColumnIndexName);
							site.setProject(processedFileDTO.getProject());
							site.setCountry(country);
							site.setCreatedOn(TimeZoneUility.getCurrentDateTime());
							site.setCreatedBy(adminDTO.getEmail());
							siteDetails.add(site);
						} else {
							//Country is old but site is new
							String siteFromDB = masterSheetDataFromDB.getSite();
							boolean isSitePresent = iSiteService.isSiteExists(siteFromDB, country.getCountryId(), processedFileDTO.getProject().getProjectId());
							if(!isSitePresent) {
								Site site = DataRecordsToEntityConverter.createSiteDetails(rowData, mapColumnIndexName);
								site.setProject(processedFileDTO.getProject());
								site.setCountry(country);
								site.setCreatedOn(TimeZoneUility.getCurrentDateTime());
								site.setCreatedBy(adminDTO.getEmail());
								siteDetails.add(site);
							}
						}
					}
					
				}//similar records ends
			}
		}// for loop ends
		
		fileUploadResponeDTO.setNewRecordsCount(i);
		allEntitiesDTO = new AllEntitiesDTO();
		
		allEntitiesDTO.setProject(processedFileDTO.getProject());
		allEntitiesDTO.setUserLocationDetailsList(userLocDetails);
		allEntitiesDTO.setUserAccountDetailsList(userAccountDetails);
		allEntitiesDTO.setUserEmailDetailsList(userEmailDetails);
		allEntitiesDTO.setUserSkypeDetailsList(userSkypelDetails);
		allEntitiesDTO.setUserWorkstationDetailsList(userWorkstationDetails);
		allEntitiesDTO.setUserOtherDetailsList(userOtherDetails);
		allEntitiesDTO.setMasterSheetDetailsList(masterSheet);
		allEntitiesDTO.setCountryDetailsList(countryDetails);
		allEntitiesDTO.setSiteDetailsList(siteDetails);
		
		return allEntitiesDTO;
	}

	private boolean updateMasterSheetChildEntitiesIntoDB(Session session, AllEntitiesDTO allEntitiesDTO) throws ServiceException {
		Boolean status = false;
		try {
			
			if(allEntitiesDTO.getUserLocationDetailsList() != null)
				iUserLocationDetailsService.updateUserLocationDetails(session, allEntitiesDTO.getUserLocationDetailsList());
			
			if(allEntitiesDTO.getUserAccountDetailsList() != null)
				iAccountDetailsService.updateUserAccountDetails(session, allEntitiesDTO.getUserAccountDetailsList());
			
			if(allEntitiesDTO.getUserEmailDetailsList() != null)
				iEmailDetailsService.updateUserEmailDetails(session, allEntitiesDTO.getUserEmailDetailsList());
			
			if(allEntitiesDTO.getUserSkypeDetailsList() != null)
				iSkypeDetailsService.updateUserSkypeDetails(session, allEntitiesDTO.getUserSkypeDetailsList());
			
			if(allEntitiesDTO.getUserWorkstationDetailsList() != null)
				iWorkstationDetailsService.updateUserWorkStationDetails(session, allEntitiesDTO.getUserWorkstationDetailsList());
			
			if(allEntitiesDTO.getUserOtherDetailsList() != null)
				iOtherDetailsService.updateUserOtherDetails(session, allEntitiesDTO.getUserOtherDetailsList());
			
			status = true;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return status;
	}


}