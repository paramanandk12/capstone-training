package com.mindtree.migrationaccelerator.service;

import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IAdminAuthService {
	boolean authenticateAdmin(AdminDTO adminDTO) throws ServiceException;
}