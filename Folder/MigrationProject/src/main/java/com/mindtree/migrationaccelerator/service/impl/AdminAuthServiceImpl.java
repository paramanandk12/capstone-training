package com.mindtree.migrationaccelerator.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.AdminAuthDao;
import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.entity.Admin;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IAdminAuthService;
import com.mindtree.migrationaccelerator.util.DTOToBeanConverter;

@Service
public class AdminAuthServiceImpl implements IAdminAuthService {

	final static Logger logger = Logger.getLogger(AdminAuthServiceImpl.class);

	@Autowired
	private DTOToBeanConverter dtoToBeanConverter;
	@Autowired
	private AdminAuthDao adminAuthDao;
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao baseDao;

	@Override
	public boolean authenticateAdmin(AdminDTO adminDTO) throws ServiceException {
		boolean isExists = false;
		try{
			logger.info("Admin authentication started !!! ");
			Admin adminFromUI = dtoToBeanConverter.adminDTOToAdminEntity(adminDTO);
			Admin adminFromDB = adminAuthDao.getAdminRecords(baseDao.getSession(), adminDTO.getEmail());
			isExists = isValidAdmin(adminFromUI, adminFromDB);
			logger.info("Admin authentication completed !!! ");
		} catch (DaoException e) {
			isExists = false;
			logger.error("Error retrieving the records !!! " + e);
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return isExists;
	}

	private boolean isValidAdmin(Admin adminFromUI, Admin adminFromDB) {
		boolean isValidAdmin = adminFromUI.getPassword().equals(adminFromDB.getPassword());
		return isValidAdmin;
	}
}