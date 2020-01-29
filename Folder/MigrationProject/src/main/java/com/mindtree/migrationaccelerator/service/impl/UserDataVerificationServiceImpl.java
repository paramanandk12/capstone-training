/*package com.mindtree.migrationaccelerator.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.dto.UserDataVerificationDTO;
import com.mindtree.migrationaccelerator.entity.UserDataVerification;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.service.IBaseService;
import com.mindtree.migrationaccelerator.service.IUserDataVerificationService;
import com.mindtree.migrationaccelerator.util.DTOToBeanConverter;

@Service
public class UserDataVerificationServiceImpl implements IUserDataVerificationService {

	final static Logger logger = Logger.getLogger(UserDataVerificationServiceImpl.class);

	@Autowired
	private DTOToBeanConverter dtoToBeanConverter;
	
	@Autowired
	private IBaseService baseService;

	@Transactional
	public Boolean insertVerifiedUsersdata(UserDataVerificationDTO userDataVerificationDTO) throws ServiceException 
	{
		boolean isInserted = false;
		Transaction tx = null;
		
		try (Session session = baseService.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			logger.info("Inserting new User data !!! ");
			UserDataVerification userDataVerificationFromUI = dtoToBeanConverter.UserDataVerificationDTOToUserDataVerificationEntity(userDataVerificationDTO);
			if (!checkUserDataVerificationExistence(session, userDataVerificationFromUI))
		return isInserted;
	}

}

	private boolean checkUserDataVerificationExistence(Session session,
			UserDataVerification userDataVerificationFromUI) {
		// TODO Auto-generated method stub
		return false;
	}
}
*/	