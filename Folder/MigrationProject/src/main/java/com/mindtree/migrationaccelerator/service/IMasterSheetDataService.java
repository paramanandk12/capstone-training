package com.mindtree.migrationaccelerator.service;

import org.springframework.web.multipart.MultipartFile;

import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.AllEntitiesDTO;
import com.mindtree.migrationaccelerator.dto.FileUploadResponeDTO;
import com.mindtree.migrationaccelerator.dto.ProcessedFileDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.dto.ResponseDTO;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IMasterSheetDataService {

	MasterWorkbook createMasterWorkBookRecord(MultipartFile file, ProjectDTO projectDTO, AdminDTO adminDTO) throws ServiceException;
	
	ResponseDTO insertAllEntitiesFromSheet(MultipartFile file, ProjectDTO projectDTO, ProcessedFileDTO processedFileDTO, FileUploadResponeDTO fileUploadResponeDTO, AdminDTO adminDTO) throws ServiceException;

	ResponseDTO updateAllEntitiesFromSheet(MultipartFile file, ProjectDTO projectDTO, ProcessedFileDTO processedFileDTO,
			FileUploadResponeDTO fileResponseUploadDTO, AdminDTO adminDTO);

	AllEntitiesDTO insertUniqueCountriesFromSheet(AllEntitiesDTO allEntitiesDTO) throws ServiceException;

	AllEntitiesDTO insertUniqueSitesFromSheet(AllEntitiesDTO allEntitiesDTO) throws ServiceException;
}