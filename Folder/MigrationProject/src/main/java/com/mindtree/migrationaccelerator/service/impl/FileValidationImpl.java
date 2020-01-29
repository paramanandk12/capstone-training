package com.mindtree.migrationaccelerator.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.migrationaccelerator.constants.MigrationAcceleratorConstants;
import com.mindtree.migrationaccelerator.dao.IUserLocationDao;
import com.mindtree.migrationaccelerator.dto.FileUploadResponeDTO;
import com.mindtree.migrationaccelerator.dto.ProcessedFileDTO;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.enums.RecordsStatus;
import com.mindtree.migrationaccelerator.exceptions.DaoException;
import com.mindtree.migrationaccelerator.service.IBaseService;
import com.mindtree.migrationaccelerator.service.IFileValidation;
import com.mindtree.migrationaccelerator.util.PropertiesReader;

@Service
public class FileValidationImpl implements IFileValidation {
	final static Logger logger = Logger.getLogger(FileValidationImpl.class);

	static Properties prop;
	static {
		try {
			prop = PropertiesReader.getPropertiesReaderInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Autowired
	private IBaseService baseService;

	@Autowired
	private IUserLocationDao iUserLocationDao;
	FileUploadResponeDTO fileUploadResponeDTO = new FileUploadResponeDTO();
	@Override
	public FileUploadResponeDTO validateFile(ProcessedFileDTO processedFileDTO) {
		String[][] dataRecords2d = processedFileDTO.getDataRecords2D();
		Map<String, Integer> columnIndexmap = processedFileDTO.getMapColumnAndIndex();

		int projectId = processedFileDTO.getProject().getProjectId();
		List<UserAccountDetails> userAccountsListDB = null;
		List<String> userListDB = null;
		List<String> userAccountsListSheet = new ArrayList<>();
		userAccountsListDB = getUserAccountListFromDB(projectId);

		userListDB = createLoginIdListFromEntity(userAccountsListDB);

		for (int i = 0; i < dataRecords2d.length; i++) {
			boolean isEmailInvalid = verifyLoginIdUpnAsIs(i, dataRecords2d, columnIndexmap);
			boolean isCountryInvalid = verifyCountry(i, dataRecords2d, columnIndexmap);
			boolean isSiteInvalid = verifySite(i, dataRecords2d, columnIndexmap);
			boolean isSamAccountAsIsInvalid = verifySamAsIs(i, dataRecords2d, columnIndexmap);
			userAccountsListSheet.add(dataRecords2d[i][columnIndexmap
					.get(prop.getProperty(MigrationAcceleratorConstants.LOGIN_ID_AS_IS))]);
			if (isEmailInvalid == false) {
				fileUploadResponeDTO.setEventStatus(false);
				fileUploadResponeDTO.setRecordsStatus(RecordsStatus.INVALID);
				fileUploadResponeDTO.setEventMessage("Login ID UPN column's data is null! Upload a valid sheet.");
				break;
			} else if (isCountryInvalid == false) {
				fileUploadResponeDTO.setEventStatus(false);
				fileUploadResponeDTO.setRecordsStatus(RecordsStatus.INVALID);
				fileUploadResponeDTO.setEventMessage("Country column's data is null! Upload a valid sheet.");
				break;
			} else if (isSiteInvalid == false) {
				fileUploadResponeDTO.setEventStatus(false);
				fileUploadResponeDTO.setRecordsStatus(RecordsStatus.INVALID);
				fileUploadResponeDTO.setEventMessage("Site column's data is null! Upload a valid sheet.");
				break;
			} else if (isSamAccountAsIsInvalid == false) {
				fileUploadResponeDTO.setEventStatus(false);
				fileUploadResponeDTO.setRecordsStatus(RecordsStatus.INVALID);
				fileUploadResponeDTO
						.setEventMessage("sAMAccountName AS-IS column's data is null! Upload a valid sheet.");
				break;
			} else {
				fileUploadResponeDTO.setEventStatus(true);
				fileUploadResponeDTO.setRecordsStatus(RecordsStatus.NEW);
			}
		}
		if (fileUploadResponeDTO.isEventStatus() == true
				&& fileUploadResponeDTO.getRecordsStatus() == RecordsStatus.NEW)
			logger.info("checking duplicate");
			fileUploadResponeDTO = validateIfUsersExists(userListDB, userAccountsListSheet);

		return fileUploadResponeDTO;
	}

	private FileUploadResponeDTO validateIfUsersExists(List<String> userListDB, List<String> userAccountsListSheet) {

		Collection<String> collectionUserfromDB = new ArrayList<>(userListDB);
		Collection<String> collectionUserfromSheet = new ArrayList<>(userAccountsListSheet);

		Collection<String> similar = new HashSet<>(collectionUserfromDB);
		Collection<String> different = new HashSet<>(collectionUserfromSheet);
		//different.addAll(collectionUserfromSheet);
		//different.addAll(collectionUserfromDB);

		similar.retainAll(different);
		different.removeAll(similar);

		int sheetRecordsCount = userAccountsListSheet.size();
		int similarRecordsCount = similar.size();
		int newRecordsCount = sheetRecordsCount - similarRecordsCount;

		fileUploadResponeDTO.setNewRecordsCount(newRecordsCount);
		fileUploadResponeDTO.setExistingRecordsCount(similarRecordsCount);
		fileUploadResponeDTO.setTotalRecordsCount(sheetRecordsCount);
		//set list for overwrite and append
		fileUploadResponeDTO.setSimilarRecordsList(similar);
		fileUploadResponeDTO.setDifferentRecordsList(different);
		return fileUploadResponeDTO;
	}

	private List<String> createLoginIdListFromEntity(List<UserAccountDetails> userAccountsListDB) {
		List<String> loginIdUPNList = new ArrayList<>();
		for (UserAccountDetails userAccountDetails : userAccountsListDB) {
			loginIdUPNList.add(userAccountDetails.getLoginIdUPNasIs());
		}
		return loginIdUPNList;
	}

	boolean validateUserExistence(List<UserAccountDetails> userAccountsListDB, List<String> userAccountsListSheet) {
		boolean isExist = false;

		return isExist;

	}

	private List<UserAccountDetails> getUserAccountListFromDB(int projectId) {
		List<UserAccountDetails> userAccountsListDB = null;
		Transaction tx = null;
		try (Session session = baseService.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			logger.info("Attempting to fetch existing user accounts list !!! ");
			userAccountsListDB = iUserLocationDao.getUserAccountsByProjectId(session, projectId);
			tx.commit();
		} catch (DaoException e) {
			logger.error("Unable to fetch existing useraccount list !!!" + e);
			tx.rollback();
		}
		return userAccountsListDB;
	}

	private boolean verifyCountry(int i, String[][] dataRecords2d, Map<String, Integer> columnIndexMap) {
		String country = dataRecords2d[i][columnIndexMap.get(prop.getProperty(MigrationAcceleratorConstants.COUNTRY))];
		boolean isEmpty = isEmptyCell(country);
		return isEmpty;
	}

	private boolean verifyLoginIdUpnAsIs(int i, String[][] dataRecords2d, Map<String, Integer> columnIndexMap) {
		String logInIdAsIs = dataRecords2d[i][columnIndexMap
				.get(prop.getProperty(MigrationAcceleratorConstants.LOGIN_ID_AS_IS))];
		boolean isEmpty = isEmptyCell(logInIdAsIs);
		return isEmpty;
	}

	private boolean verifySite(int i, String[][] dataRecords2d, Map<String, Integer> columnIndexMap) {
		String site = dataRecords2d[i][columnIndexMap.get(prop.getProperty(MigrationAcceleratorConstants.SITE))];
		boolean isEmpty = isEmptyCell(site);
		return isEmpty;
	}

	private boolean verifySamAsIs(int i, String[][] dataRecords2d, Map<String, Integer> columnIndexMap) {
		String samAsIs = dataRecords2d[i][columnIndexMap
				.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_AS_IS))];
		boolean isEmpty = isEmptyCell(samAsIs);
		return isEmpty;
	}

	private boolean isEmptyCell(String value) {
		boolean status = false;
		if (value != null) {
			if (value.equals("")) {
				status = false;
			} else {
				status = true;
			}
		}
		return status;
	}
}