package com.mindtree.migrationaccelerator.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.LoggedInUserDetails;
import com.mindtree.migrationaccelerator.enums.LoggedInUserType;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IAdminAuthService;
import com.mindtree.migrationaccelerator.util.SessionManager;

@RestController
@RequestMapping("api/admin")
public class AdminController extends SessionManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private IAdminAuthService adminAuthService;

	@RequestMapping(value = "/auth-admin.view", method = { RequestMethod.POST })
	public Boolean getAdminAuthenticationStatus(@RequestBody AdminDTO adminDTO) {
		Boolean status = false;
		try {
			logger.info("Admin authentication started !!!");
			status = adminAuthService.authenticateAdmin(adminDTO);
			if(status) {
				HttpSession session = getSession();
				LoggedInUserDetails<AdminDTO> loggedInUser = new LoggedInUserDetails<>(LoggedInUserType.ADMIN,adminDTO);
				loggedInUserDetails.put(session.getId(), loggedInUser);
			}
			logger.info("Admin authentication completed !!! " + status);
		} catch (ServiceException e) {
			logger.error("Admin authentication error !!!", e);
		}
		return status;
	}
}