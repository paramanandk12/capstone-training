package com.mindtree.migrationaccelerator.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.migrationaccelerator.dto.MasterWorkbookDTO;
import com.mindtree.migrationaccelerator.dto.UserSlotSelectionDTO;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.response.ResponseBodyData;
import com.mindtree.migrationaccelerator.service.IDashboardService;
import com.mindtree.migrationaccelerator.util.BeanToDTOConverter;
import com.mindtree.migrationaccelerator.util.SessionManager;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController extends SessionManager{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(DashboardController.class);

	@Autowired
	private IDashboardService dashboardService;

	@Autowired
	private BeanToDTOConverter beanToDTOConverter;

	@RequestMapping(value = "/fetchAllRecords-project", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<List<MasterWorkbookDTO>>> getAllDetails(
			@RequestParam("projectId") int projectId) {
		ResponseBodyData<List<MasterWorkbookDTO>> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);

		List<MasterWorkbookDTO> masterWorkbookData = null;
		try {
			logger.info("Trying to fetch get masterdata !!!");
			masterWorkbookData = dashboardService.getAllMastersheetDataRecords(projectId);
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(masterWorkbookData);
			logger.info("Fetched masterdata successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch Masterdata records !!! " + e);
			response.setMessage("Unable to fetch Masterdata records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/fetchScheduleReportForUser.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<List<UserSlotSelectionDTO>>> fetchUserReportForSchedules(
			@RequestParam("projectId") int projectId, @RequestParam("countryId") int countryId, @RequestParam("siteId") int siteId) {
		ResponseBodyData<List<UserSlotSelectionDTO>> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);

		List<UserSlotSelectionDTO> userSlotSelectionDTOList = new ArrayList<UserSlotSelectionDTO>();
		try {
			logger.info("Trying to fetch get user schedule report !!!");
			List<UserSlotSelection> userScheduleReportList = dashboardService.getScheduleReportForUser(projectId, countryId, siteId);
			
			for(UserSlotSelection userSlotSelection : userScheduleReportList)
				userSlotSelectionDTOList.add(beanToDTOConverter.userSlotSelectionEntityToDTO(userSlotSelection));
			
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(userSlotSelectionDTOList);
			logger.info("Fetched user schedule report successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch user schedule report !!! " + e);
			response.setMessage("Unable to fetch user schedule report !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}