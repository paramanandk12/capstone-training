package com.mindtree.migrationaccelerator.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.FileUploadResponeDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IProjectManagementService {

	Boolean createNewProject(ProjectDTO projectDTO, AdminDTO adminDTO) throws ServiceException;

	List<ProjectDTO> getAllProjects() throws ServiceException;

	Boolean updateSelectedProject(ProjectDTO projectDTO, AdminDTO adminDTO) throws ServiceException;

	FileUploadResponeDTO readFileAndProcessData(MultipartFile file, ProjectDTO projectDTO, HttpSession session) throws ServiceException;

	Project getProjectEntityById(Integer id) throws ServiceException;

	Project getProjectEntityById(Session session, int projectId) throws ServiceException;

	FileUploadResponeDTO processAndAppendRecords(MultipartFile file, ProjectDTO projectDTO, FileUploadResponeDTO fileUploadResponeDTO, HttpSession session) throws ServiceException;

	FileUploadResponeDTO processAndOverwriteRecords(MultipartFile file, ProjectDTO projectDTO, FileUploadResponeDTO fileUploadResponseDTO, HttpSession session) throws ServiceException;;
}