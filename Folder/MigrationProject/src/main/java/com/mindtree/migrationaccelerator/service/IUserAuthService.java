package com.mindtree.migrationaccelerator.service;

import com.mindtree.migrationaccelerator.dto.UserDTO;
import com.mindtree.migrationaccelerator.dto.UserDetailsUploadDTO;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserAuthService {
	UserDetailsUploadDTO authenticateUser(UserDTO userDTO) throws ServiceException;
}