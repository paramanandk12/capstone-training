package com.mindtree.migrationaccelerator.service;

import com.mindtree.migrationaccelerator.dto.FileUploadResponeDTO;
import com.mindtree.migrationaccelerator.dto.ProcessedFileDTO;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IFileValidation {

	FileUploadResponeDTO validateFile(ProcessedFileDTO processedFileDTO) throws ServiceException ;
}