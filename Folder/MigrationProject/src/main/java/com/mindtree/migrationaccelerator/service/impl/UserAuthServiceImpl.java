package com.mindtree.migrationaccelerator.service.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dao.IMasterSheetDetailsDao;
import com.mindtree.migrationaccelerator.dao.UserAuthDao;
import com.mindtree.migrationaccelerator.dto.MastersheetDataDTO;
import com.mindtree.migrationaccelerator.dto.UserDTO;
import com.mindtree.migrationaccelerator.dto.UserDetailsUploadDTO;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IBaseService;
import com.mindtree.migrationaccelerator.service.IUserAuthService;
import com.mindtree.migrationaccelerator.util.BeanToDTOConverter;
import com.mindtree.migrationaccelerator.util.DTOToBeanConverter;

@Service
public class UserAuthServiceImpl implements IUserAuthService {

	final static Logger logger = Logger.getLogger(UserAuthServiceImpl.class);

	@Autowired
	private DTOToBeanConverter dtoToBeanConverter;
	@Autowired
	private BeanToDTOConverter beanToDTOConverter;
	@Autowired
	private UserAuthDao userAuthDao;

	@Autowired
	private IMasterSheetDetailsDao masterSheetDetailsDao;
	@Autowired
	private IBaseService baseService;

	@Override
	@Transactional
	public UserDetailsUploadDTO authenticateUser(UserDTO userDTO) throws ServiceException {
		UserDetailsUploadDTO userDetailsUploadDTO = new UserDetailsUploadDTO();
		Boolean isExists = false;

		try (Session session = baseService.getSessionFactory().openSession()) {
			logger.info("User Authentication started !!! ");
			UserAccountDetails userFromUI = dtoToBeanConverter.userDTOToUserEntity(userDTO);
			UserAccountDetails userFromDB = userAuthDao.getUserRecords(session, userDTO.getEmail());
			isExists = isValidUser(userFromUI, userFromDB);

			if (isExists == true) {
				MasterSheetData masterSheetDataUser = masterSheetDetailsDao.getUserRecord(session, userDTO.getEmail());
				MastersheetDataDTO mastersheetDataDTO = beanToDTOConverter
						.masterSheetDataEntityToMasterSheetDataDTO(masterSheetDataUser);
				userDetailsUploadDTO.setCountry(mastersheetDataDTO.getCountry());
				userDetailsUploadDTO.setFirstName(mastersheetDataDTO.getFirstName());
				userDetailsUploadDTO.setLastName(mastersheetDataDTO.getLastName());
				userDetailsUploadDTO.setSamAccountNameAsIs(mastersheetDataDTO.getSamAccountNameAsIs());
				userDetailsUploadDTO.setSite(mastersheetDataDTO.getSite());
				userDetailsUploadDTO.setUserStatus(isExists);
				userDetailsUploadDTO.setEmail(userDTO.getEmail());
				
				if(mastersheetDataDTO.getProjectDTO() != null)
					userDetailsUploadDTO.setProjectDTO(mastersheetDataDTO.getProjectDTO());
			}
			logger.info("User Authentication completed !!! ");
		} catch (DaoException e) {
			userDetailsUploadDTO.setUserStatus(isExists);
			logger.error("Error retrieving user records !!! ");
			throw new ServiceException(e.getMessage(), e, true, true);
		}
		return userDetailsUploadDTO;
	}

	private boolean isValidUser(UserAccountDetails userFromUI, UserAccountDetails userFromDB) {
		boolean isValidUser = userFromUI.getLoginIdUPNasIs().equals(userFromDB.getLoginIdUPNasIs());
		return isValidUser;
	}
}
