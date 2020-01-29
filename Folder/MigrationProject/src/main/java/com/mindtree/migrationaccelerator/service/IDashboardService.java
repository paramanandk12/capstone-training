package com.mindtree.migrationaccelerator.service;

import java.util.List;

import com.mindtree.migrationaccelerator.dto.MasterWorkbookDTO;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IDashboardService {
	List<MasterWorkbookDTO> getAllMastersheetDataRecords(int projectId) throws ServiceException;

	List<UserSlotSelection> getScheduleReportForUser(int projectId, int countryId, int siteId)  throws ServiceException;
}