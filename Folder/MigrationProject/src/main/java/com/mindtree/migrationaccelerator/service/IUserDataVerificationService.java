package com.mindtree.migrationaccelerator.service;

import com.mindtree.migrationaccelerator.dto.UserDataVerificationDTO;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserDataVerificationService  {
	Boolean insertVerifiedUsersdata(UserDataVerificationDTO userDataVerificationDTO) throws ServiceException;
}


