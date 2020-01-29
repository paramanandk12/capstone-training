package com.mindtree.migrationaccelerator.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.FileUploadResponeDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.enums.ButtonAction;
import com.mindtree.migrationaccelerator.response.ResponseBodyData;
import com.mindtree.migrationaccelerator.service.IProjectManagementService;
import com.mindtree.migrationaccelerator.util.SessionManager;

@RestController
@RequestMapping("api/project")
public class ProjectController extends SessionManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	final static Logger logger = Logger.getLogger(ProjectController.class);

	@Autowired
	private IProjectManagementService projectManagementService;

	@RequestMapping(value = "/project-creation.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<List<ProjectDTO>>> createProjct(
			@RequestBody ProjectDTO projectDTO) {
		
		ResponseBodyData<List<ProjectDTO>> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		List<ProjectDTO> projectsList = null;
		
		Boolean status = false;
		try {
			logger.info("Project creation started !!!");
			
			HttpSession session = getSession();
			AdminDTO adminDTO = (AdminDTO) SessionManager.getLoggedInUser(session.getId());

			status = projectManagementService.createNewProject(projectDTO, adminDTO);
			
			if (status == true) {
				projectsList = projectManagementService.getAllProjects();
				response.setMessage("OK");
				response.setStatus(HttpStatus.OK);
				response.setData(projectsList);
				logger.info("Project creation completed successfully !!!");
			} else {
				response.setMessage("ERROR");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				logger.info("Project alredy exists !!!");
			}
			iBaseDao.getSession().clear();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Project creation error !!!", e);
			response.setMessage("Project creation error !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/get-all-projects.view", method = { RequestMethod.GET }, produces = "application/json")
	public ResponseEntity<ResponseBodyData<List<ProjectDTO>>> getAllProjectsList() {
		
		ResponseBodyData<List<ProjectDTO>> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		List<ProjectDTO> projectsList = null;
		try {
			logger.info("Trying to fetch project list !!!");
			projectsList = projectManagementService.getAllProjects();
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(projectsList);
			logger.info("Project list fetched successfully !!!" + " " + projectsList.size() + " Projects found");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Unable to fetch list of projects getAllProjects method is excecuting!!!" + e);
			response.setMessage("Unable to fetch list of projects getAllProjects method is excecuting!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/update-project.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<List<ProjectDTO>>> updateProjectCreationStatus(
			@RequestBody ProjectDTO projectDTO) {
		
		ResponseBodyData<List<ProjectDTO>> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		List<ProjectDTO> projectsList = null;
		try {
			logger.info("Project updation started !!!");
			
			HttpSession session = getSession();
			AdminDTO adminDTO = (AdminDTO) SessionManager.getLoggedInUser(session.getId());

			projectManagementService.updateSelectedProject(projectDTO, adminDTO);
			projectsList = projectManagementService.getAllProjects();
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(projectsList);
			iBaseDao.getSession().clear();
			logger.info("Project updation completed !!!");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Unable to fetch list of projects getAllProjects method is excecuting!!!" + e);
			response.setMessage("Unable to fetch list of projects getAllProjects method is excecuting!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/uploading-MasterSheet.view", consumes = {
			"multipart/form-data" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseBodyData<FileUploadResponeDTO>> uploadingMasterSheet(
			@RequestPart("project") ProjectDTO projectDTO, @RequestPart("file") MultipartFile file) {
		
		ResponseBodyData<FileUploadResponeDTO> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		
		FileUploadResponeDTO fileUploadResponeDTO = null;
		try {
			logger.info("Processing uploaded file started !!!");
			fileUploadResponeDTO = projectManagementService.readFileAndProcessData(file, projectDTO, getSession());
			fileUploadResponeDTO.setCurrentButtonAction(ButtonAction.UPLOAD);
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(fileUploadResponeDTO);
			
			getSession().setAttribute("fileUploadDTO", fileUploadResponeDTO);
			iBaseDao.getSession().clear();
			logger.info("Processing uploaded file completed !!!");
			return ResponseEntity.ok(response);
		} catch (Exception e)  {
			e.printStackTrace();
			logger.error("Mastersheet processing error !!! " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/appendingRecords-MasterSheet.view", consumes = {
			"multipart/form-data" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseBodyData<FileUploadResponeDTO>> appendingMasterSheet(
			@RequestPart("project") ProjectDTO projectDTO, @RequestPart("file") MultipartFile file) {
		
		ResponseBodyData<FileUploadResponeDTO> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		
		FileUploadResponeDTO fileUploadResponeDTO = null;
		try {
			logger.info("appending records started !!!");
			
			fileUploadResponeDTO = (FileUploadResponeDTO) getSession().getAttribute("fileUploadDTO");
			fileUploadResponeDTO.setCurrentButtonAction(ButtonAction.APPEND);
			fileUploadResponeDTO = projectManagementService.processAndAppendRecords(file, projectDTO, fileUploadResponeDTO, getSession());

			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(fileUploadResponeDTO);
			iBaseDao.getSession().clear();
			logger.info("appending records completed !!!");
			return ResponseEntity.ok(response);
		}  catch (Exception e){ 
			logger.error("appending records error !!! " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/overwrittingRecords-MasterSheet.view", consumes = {
			"multipart/form-data" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseBodyData<FileUploadResponeDTO>> overwriteMasterSheet(
			@RequestPart("project") ProjectDTO projectDTO, @RequestPart("file") MultipartFile file) {
		
		ResponseBodyData<FileUploadResponeDTO> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		
		FileUploadResponeDTO fileUploadResponeDTO = null;

		try {
			logger.info("overwritting records started !!!");

			fileUploadResponeDTO = (FileUploadResponeDTO) getSession().getAttribute("fileUploadDTO");
			fileUploadResponeDTO.setCurrentButtonAction(ButtonAction.OVERWRITE);
			fileUploadResponeDTO = projectManagementService.processAndOverwriteRecords(file, projectDTO,
					fileUploadResponeDTO, getSession());

			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(fileUploadResponeDTO);
			iBaseDao.getSession().clear();
			logger.info("overwritting records completed !!!");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("overwritting records error !!! " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/appendAndOverwrittingRecords-MasterSheet.view", consumes = {
			"multipart/form-data" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseBodyData<FileUploadResponeDTO>> appendAndOverwriteMasterSheet(
			@RequestPart("project") ProjectDTO projectDTO, @RequestPart("file") MultipartFile file) {

		ResponseBodyData<FileUploadResponeDTO> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);

		FileUploadResponeDTO fileUploadResponeDTO = null;

		try {
			logger.info("Append and overwritting records started !!!");
			StringBuilder eventMessage = new StringBuilder();
			
			fileUploadResponeDTO = (FileUploadResponeDTO) getSession().getAttribute("fileUploadDTO");
			fileUploadResponeDTO.setCurrentButtonAction(ButtonAction.APPEND_AND_OVERWRITE);
			
			fileUploadResponeDTO = projectManagementService.processAndAppendRecords(file, projectDTO, fileUploadResponeDTO, getSession());
			eventMessage.append(fileUploadResponeDTO.getNewRecordsCount() + " Records are appended successfully");
			
			fileUploadResponeDTO = projectManagementService.processAndOverwriteRecords(file, projectDTO,
					fileUploadResponeDTO, getSession());
			eventMessage.append(" and " + fileUploadResponeDTO.getNewRecordsCount() + " Records are overwritten successfully");
			
			fileUploadResponeDTO.setEventMessage(eventMessage.toString());
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(fileUploadResponeDTO);
			iBaseDao.getSession().clear();
			logger.info("Append and overwritting records completed !!!");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Append and overwritting records error !!! " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}