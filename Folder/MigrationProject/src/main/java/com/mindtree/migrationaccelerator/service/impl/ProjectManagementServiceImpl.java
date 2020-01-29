package com.mindtree.migrationaccelerator.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dao.IProjectManagementDao;
import com.mindtree.migrationaccelerator.dao.ProjectAuthDao;
import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.AllEntitiesDTO;
import com.mindtree.migrationaccelerator.dto.FileUploadResponeDTO;
import com.mindtree.migrationaccelerator.dto.ProcessedFileDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.dto.ResponseDTO;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.enums.ProjectStatus;
import com.mindtree.migrationaccelerator.enums.RecordsStatus;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IFileValidation;
import com.mindtree.migrationaccelerator.service.IMasterSheetDataService;
import com.mindtree.migrationaccelerator.service.IProjectManagementService;
import com.mindtree.migrationaccelerator.util.CollectionBeanToCollectionDTOConverter;
import com.mindtree.migrationaccelerator.util.DTOToBeanConverter;
import com.mindtree.migrationaccelerator.util.ExcelReader;
import com.mindtree.migrationaccelerator.util.SessionManager;
import com.mindtree.migrationaccelerator.util.TimeZoneUility;

@Service
public class ProjectManagementServiceImpl implements IProjectManagementService {

	final static Logger logger = Logger
			.getLogger(ProjectManagementServiceImpl.class);

	@Autowired
	private DTOToBeanConverter dtoToBeanConverter;

	@Autowired
	private CollectionBeanToCollectionDTOConverter collectionBeanToDTOConverter;

	@Autowired
	private ProjectAuthDao projectAuthDao;

	@Autowired
	private IProjectManagementDao iProjectManagementDao;

	@Autowired
	private ExcelReader excelReader;

	@Autowired
	private IFileValidation iFileValidation;

	@Autowired
	private IProjectManagementService iProjectManagementService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	@Autowired
	private IMasterSheetDataService iMasterDataService;

	public String fileLocation = "";

	@Override
	@Transactional
	public Boolean createNewProject(ProjectDTO projectDTO, AdminDTO adminDTO)
			throws ServiceException {
		boolean isCreated = false;
		Transaction tx = null;
		try {
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			logger.info("Creation of new project started  !!! ");
			Project project = dtoToBeanConverter
					.projectDTOToProjectEntity(projectDTO);
			if (!checkProjectExistence(session, project)) {
				project.setProjName(projectDTO.getName());
				project.setDescription(projectDTO.getDescription());
				project.setProjectManagerName(projectDTO.getManager());
				project.setProjectManagerEmail(projectDTO.getManagerEmail());
				project.setProjectStatus(ProjectStatus.ACTIVE);
				project.setCreatedOn(TimeZoneUility.getCurrentDateTime());
				project.setCreatedBy(adminDTO.getEmail());

				isCreated = iProjectManagementDao.createNewProject(session,
						project);
				tx.commit();
				logger.info("Creation of new project completed  !!! ");
			}
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to create project inside service !!!", e);
			isCreated = false;
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return isCreated;
	}

	private boolean checkProjectExistence(Session session, Project projectFromUI) {
		boolean isExist = false;
		String projNameColName = "projName";
		try {
			isExist = projectAuthDao.getProjectRecords(session,
					projNameColName, projectFromUI.getProjName());

			logger.info("Project is Avaialble in DB !!! : " + isExist);
		} catch (DaoException e) {
			isExist = false;
		}
		return isExist;
	}

	@Transactional
	@Override
	public List<ProjectDTO> getAllProjects() throws ServiceException {
		Transaction tx = null;

		List<ProjectDTO> projectDTOList = null;
		logger.info("Fetching ProjectDTO data started !!! ");
		try {
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			List<Project> projectsList = iProjectManagementDao
					.getAllProjects(session);
			projectDTOList = collectionBeanToDTOConverter
					.getProjectDTOList(projectsList);
			tx.commit();
			logger.info("Fetching ProjectDTO data completed !!! ");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to fetch list of projects getAllProjects method is excecuting!!!"
					+ e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return projectDTOList;
	}

	@Override
	@Transactional
	public Boolean updateSelectedProject(ProjectDTO projectDTO,
			AdminDTO adminDTO) throws ServiceException {
		boolean isUpdated = false;
		Transaction tx = null;
		try {
			Session session = iBaseDao.getSession();
			tx = session.beginTransaction();
			logger.info("Updating ProjectDTO data started !!! ");
			Project projectFromUI = dtoToBeanConverter
					.projectDTOToProjectEntity(projectDTO);
			Project projectFromDB = iProjectManagementDao.getProjectEntityById(
					session, projectDTO.getId());

			projectFromDB.setProjName(projectFromUI.getProjName());
			projectFromDB.setProjectManagerName(projectFromUI
					.getProjectManagerName());
			projectFromDB.setProjectManagerEmail(projectFromUI
					.getProjectManagerEmail());
			projectFromDB.setDescription(projectFromUI.getDescription());
			projectFromDB.setLastUpdated(TimeZoneUility.getCurrentDateTime());
			projectFromDB.setUpdatedBy(adminDTO.getEmail());
			isUpdated = iProjectManagementDao.updateProject(session,
					projectFromDB);
			tx.commit();
			logger.info("Updating ProjectDTO data started !!! ");
		} catch (DaoException e) {
			if (tx != null)
				tx.rollback();
			logger.error("Unable to update project inside service !!!", e);
			isUpdated = false;
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return isUpdated;
	}

	@Override
	public FileUploadResponeDTO readFileAndProcessData(MultipartFile file,
			ProjectDTO projectDTO, HttpSession session) throws ServiceException {
		FileUploadResponeDTO fileUploadResponeDTO = null;

		try {
			logger.info("Action reading File And Processing Data started !!!");
			getMultipartFileAndCopyLocal(file);
		} catch (IOException e) {
			logger.error("Unable to process uploaded file !!! " + e);
		}

		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
				try {
					ProcessedFileDTO processedFileDTO = excelReader
							.processExcel(fileLocation);

					Project project = iProjectManagementService
							.getProjectEntityById(projectDTO.getId());

					// VALIDATION
					logger.info("All types of file validation started !!!");
					processedFileDTO.setProject(project);
					fileUploadResponeDTO = iFileValidation
							.validateFile(processedFileDTO);
					session.setAttribute("processedFileDTO", processedFileDTO);

					logger.info("All types of file validation completed !!!"
							+ fileUploadResponeDTO.getEventMessage());
					// CREATE ENTITY AND INSERT AS PER ACTION
					// (NEW,EXISTING,COMBINATION)

					// This condition check and adds new records.
					if (fileUploadResponeDTO.isEventStatus() == true
							&& fileUploadResponeDTO.getRecordsStatus() == RecordsStatus.NEW
							&& fileUploadResponeDTO.getExistingRecordsCount() == 0) {
						logger.info("Creating all new entity DTO and insert into DB process started !!!");

						AdminDTO adminDTO = (AdminDTO) SessionManager
								.getLoggedInUser(session.getId());

						ResponseDTO response = iMasterDataService
								.insertAllEntitiesFromSheet(file, projectDTO,
										processedFileDTO, fileUploadResponeDTO,
										adminDTO);

						AllEntitiesDTO allEntitiesDTO = response
								.getAllEntities();
						allEntitiesDTO.setProject(project);
						allEntitiesDTO = iMasterDataService
								.insertUniqueCountriesFromSheet(allEntitiesDTO);
						allEntitiesDTO = iMasterDataService
								.insertUniqueSitesFromSheet(allEntitiesDTO);

						response.setAllEntities(allEntitiesDTO);

						if (response.isProcessed() == true) {
							fileUploadResponeDTO.setEventMessage(response
									.getAllEntities()
									.getMasterSheetDetailsList().size()
									+ " New records inserted successfully!");
							logger.info("All Data inserted successfully from the uploaded sheet !!!");
						}
						logger.info("Creating all new entity DTO and insert into DB process completed !!!");
					}

					else if (fileUploadResponeDTO.isEventStatus() == true
							&& fileUploadResponeDTO.getRecordsStatus() == RecordsStatus.NEW
							&& fileUploadResponeDTO.getExistingRecordsCount() > 0) {
						logger.info("Requesting confirmation from the user to overwrite or append !!!");
						int existingRecordsCount = fileUploadResponeDTO
								.getExistingRecordsCount();
						int newRecordsCount = fileUploadResponeDTO
								.getNewRecordsCount();

						// This will append
						if (existingRecordsCount == 0 && newRecordsCount > 0)
							fileUploadResponeDTO
									.setRecordsStatus(RecordsStatus.NEW);

						// This will overwrite
						if (existingRecordsCount > 0 && newRecordsCount == 0)
							fileUploadResponeDTO
									.setRecordsStatus(RecordsStatus.EXIST);

						// This will overwrite and append
						if (existingRecordsCount > 0 && newRecordsCount > 0)
							fileUploadResponeDTO
									.setRecordsStatus(RecordsStatus.COMBINATION);

						StringBuilder eventMsg = new StringBuilder();

						eventMsg.append(existingRecordsCount
								+ " similar records exists in the DB!");
						eventMsg.append(" and " + newRecordsCount
								+ " new records are available!");
						eventMsg.append("Below are possible operations you can do with new Mastersheet data?");

						fileUploadResponeDTO.setEventMessage(eventMsg
								.toString());
					}

				} catch (InvalidFormatException | IOException e) {
					logger.error("Reading File And Processing Data Failed !!!"
							+ e.getMessage());
				}
				logger.info("Action reading File And Processing Data completed successfully !!!");
			} else if (fileLocation.endsWith(".csv")) { // CSV reader util } } }
														// }
			}
		}
		return fileUploadResponeDTO;
	}

	// This method receives a MultipartFile and saves it in the current
	// location:
	public String getMultipartFileAndCopyLocal(MultipartFile file)
			throws IOException {
		InputStream in = file.getInputStream();
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1)
				+ file.getOriginalFilename();
		FileOutputStream f = new FileOutputStream(fileLocation);
		int ch = 0;
		while ((ch = in.read()) != -1) {
			f.write(ch);
		}
		f.flush();
		f.close();
		return fileLocation;
	}

	@Override
	public Project getProjectEntityById(Integer id) throws ServiceException {
		try {
			return iProjectManagementDao.getProjectEntityById(
					iBaseDao.getSession(), id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}

	@Override
	public Project getProjectEntityById(Session session, int projectId)
			throws ServiceException {
		try {
			return iProjectManagementDao.getProjectEntityById(session,
					projectId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e, true, true);
		}
	}

	@Override
	public FileUploadResponeDTO processAndAppendRecords(MultipartFile file,
			ProjectDTO projectDTO, FileUploadResponeDTO fileResponseUploadDTO,
			HttpSession session) throws ServiceException {

		ProcessedFileDTO processedFileDTO = (ProcessedFileDTO) session
				.getAttribute("processedFileDTO");
		AdminDTO adminDTO = (AdminDTO) SessionManager.getLoggedInUser(session
				.getId());
		ResponseDTO response = iMasterDataService.insertAllEntitiesFromSheet(
				file, projectDTO, processedFileDTO, fileResponseUploadDTO,
				adminDTO);

		AllEntitiesDTO allEntitiesDTO = response.getAllEntities();
		allEntitiesDTO = iMasterDataService
				.insertUniqueCountriesFromSheet(allEntitiesDTO);
		allEntitiesDTO = iMasterDataService
				.insertUniqueSitesFromSheet(allEntitiesDTO);

		if (response.isProcessed() == true) {
			fileResponseUploadDTO.setEventMessage(fileResponseUploadDTO
					.getNewRecordsCount()
					+ " New records Appended successfully!");
			logger.info("All Data inserted successfully from the uploaded sheet !!!");
		}

		return fileResponseUploadDTO;
	}

	@Override
	public FileUploadResponeDTO processAndOverwriteRecords(MultipartFile file,
			ProjectDTO projectDTO, FileUploadResponeDTO fileResponseUploadDTO,
			HttpSession session) throws ServiceException {

		ProcessedFileDTO processedFileDTO = (ProcessedFileDTO) session
				.getAttribute("processedFileDTO");
		AdminDTO adminDTO = (AdminDTO) SessionManager.getLoggedInUser(session
				.getId());
		ResponseDTO response = null;
		if (fileResponseUploadDTO.getSimilarRecordsList() != null
				&& fileResponseUploadDTO.getSimilarRecordsList().size() > 0) {
			response = iMasterDataService.updateAllEntitiesFromSheet(file,
					projectDTO, processedFileDTO, fileResponseUploadDTO,
					adminDTO);

			AllEntitiesDTO allEntitiesDTO = response.getAllEntities();
			allEntitiesDTO = iMasterDataService
					.insertUniqueCountriesFromSheet(allEntitiesDTO);
			allEntitiesDTO = iMasterDataService
					.insertUniqueSitesFromSheet(allEntitiesDTO);

			if (response.isProcessed() == true) {
				fileResponseUploadDTO.setEventMessage(fileResponseUploadDTO
						.getNewRecordsCount()
						+ " Records Overwritten successfully!");
				logger.info("Records Overwritten successfully from the uploaded sheet !!!");
			}
		}

		return fileResponseUploadDTO;
	}

}