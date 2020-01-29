package com.mindtree.migrationaccelerator.service;

import java.util.List;

import com.mindtree.migrationaccelerator.dto.MastersheetDataDTO;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserDetailsService {
	List<MastersheetDataDTO> getAllMastersheetDataRecords(String LoginUPN_ID) throws ServiceException;
}
