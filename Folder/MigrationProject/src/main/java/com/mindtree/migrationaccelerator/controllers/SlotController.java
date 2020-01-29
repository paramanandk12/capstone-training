package com.mindtree.migrationaccelerator.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.CountryDTO;
import com.mindtree.migrationaccelerator.dto.SiteDTO;
import com.mindtree.migrationaccelerator.dto.SlotDTO;
import com.mindtree.migrationaccelerator.dto.SlotDetailsDTO;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.response.ResponseBodyData;
import com.mindtree.migrationaccelerator.service.ICountryService;
import com.mindtree.migrationaccelerator.service.ISiteService;
import com.mindtree.migrationaccelerator.service.ISlotService;
import com.mindtree.migrationaccelerator.util.BeanToDTOConverter;
import com.mindtree.migrationaccelerator.util.SessionManager;
import com.mindtree.migrationaccelerator.util.TimeZoneUility;


@RestController
@RequestMapping("api/slot")
public class SlotController extends SessionManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(SlotController.class);
	
	@Autowired
	private ISlotService iSlotService;
	
	@Autowired
	private ISiteService iSiteService;
	
	@Autowired
	private ICountryService iCountryService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	@Autowired
	private BeanToDTOConverter beanToDTOConverter;
	
	@RequestMapping(value = "/fetchCountriesByProject.view", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<List<CountryDTO>>> getCountriesByProject(@RequestParam("projectId") int projectId) {
		
		ResponseBodyData<List<CountryDTO>> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		List<CountryDTO> countriesDTOs = new ArrayList<CountryDTO>();
		try {
			logger.info("Trying to fetch get countries !!!");
			
			List<Country> countries = iCountryService.getCountryByProject(projectId);
			
			if(countries != null && !countries.isEmpty()) {
				for(Country country : countries) {
					countriesDTOs.add(beanToDTOConverter.countryEntityToDTO(country));
				}
			}else {
				logger.error("No Contries are foud in database for projectid for projectid- " + projectId);
			}
			
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(countriesDTOs);
			logger.info("Fetched CountriesByProject successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch countries records !!! " + e);
			response.setMessage("Unable to fetch countries records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/fetchCountrySitesByProject.view", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<List<CountryDTO>>> getCountrySitesByProject(@RequestParam("projectId") int projectId) {
		
		ResponseBodyData<List<CountryDTO>> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		List<CountryDTO> countriesDTOs = new ArrayList<CountryDTO>();
		try {
			logger.info("Trying to fetch get countries !!!");
			
			List<Country> countries = iCountryService.getCountryByProject(projectId);
			
			if(countries != null && !countries.isEmpty()) {
				for(Country country : countries) {
					countriesDTOs.add(beanToDTOConverter.countryEntityToDTO(country));
				}
			}else {
				logger.error("No Contries are foud in database for projectid for projectid- " + projectId);
			}
			
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(countriesDTOs);
			logger.info("Fetched CountriesByProject successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch countries records !!! " + e);
			response.setMessage("Unable to fetch countries records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/fetchSitesByProjectCountry.view", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<List<SiteDTO>>> getSitesByProjectCountry(@RequestParam("projectId") int projectId, @RequestParam("countryId") int countryId) {
		
		ResponseBodyData<List<SiteDTO>> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		logger.info("Trying to fetch getSitesByProjectCountry!!!");
		List<SiteDTO> siteDTOs = new ArrayList<SiteDTO>();
		try {
			
			List<Site> sites = iSiteService.getSiteByProjectCountry(projectId, countryId);
			
			if(sites != null && !sites.isEmpty()) {
				for(Site site : sites)
					siteDTOs.add(beanToDTOConverter.siteEntityToDTO(site));
			}else
				logger.error("No Sites are foud in database for projectId - " + projectId + " and countryId - " + countryId);
			
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(siteDTOs);
			logger.info("Fetched getSitesByProjectCountry successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch sites records !!! " + e);
			response.setMessage("Unable to fetch sites records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/fetchAllTimezones.view", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<List<String>>> getAllTimezones() {
		
		ResponseBodyData<List<String>> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		logger.info("Trying to fetch all timezone entries available!!!");
		List<String> timezones = TimeZoneUility.getAllTimezones();
		try {
			
			if(timezones == null || timezones.isEmpty()) 
				logger.error("Timezones are not found...");
			
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(timezones);
			logger.info("Fetched getSitesByProjectCountry successfully !!!");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Unable to fetch sites records !!! " + e);
			response.setMessage("Unable to fetch sites records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/createSlots.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<String>> createSlots(@RequestBody SlotDetailsDTO slotsDTOs) {
		//2122-11-10T18:30:00.000Z id date is passesd as string
		ResponseBodyData<String> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		logger.info("Creating new slots...!!!");
		
		try {
			HttpSession session = getSession();
			AdminDTO adminDTO = (AdminDTO) SessionManager.getLoggedInUser(session.getId());
			
			boolean isInserted = iSlotService.insertSlotSDetails(slotsDTOs, adminDTO);
			StringBuilder eventMsg = new StringBuilder("Creation of new slots is ");
			
			if(isInserted) {
				logger.info(eventMsg.append("Successful !!!"));	
			}else {
				logger.info(eventMsg.append("Failed !!!"));
			}
			response.setMessage("OK");
			response.setData(eventMsg.toString());
			response.setStatus(HttpStatus.OK);
			iBaseDao.getSession().clear();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Unable create new slots !!! " + e);
			response.setMessage("Unable create new slots !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/fetchSlotsByProject.view", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<List<SlotDTO>>> getSlotsByProject(@RequestParam("projectId") int projectId) {
		
		ResponseBodyData<List<SlotDTO>> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		List<SlotDTO> slotDTOs = new ArrayList<SlotDTO>();
		try {
			logger.info("Trying to fetch Slots by projectid !!!");
			
			List<Slot> slots = iSlotService.getSlotsByProject(projectId);
			
			if(slots != null && !slots.isEmpty()) {
				for(Slot slot : slots) {
					slotDTOs.add(beanToDTOConverter.slotEntityToDTO(slot));
				}
			}else {
				logger.error("No slots are found in database for projectid- " + projectId);
			}
			
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(slotDTOs);
			logger.info("Fetched SlotByProject successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch slots records !!! " + e);
			response.setMessage("Unable to fetch slots records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/updateSlot.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<String>> updateSlot(@RequestBody SlotDTO slotDTO) {
		
		ResponseBodyData<String> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		try {
			logger.info("Updating Slot Details !!!");
			
			HttpSession session = getSession();
			AdminDTO adminDTO = (AdminDTO) SessionManager.getLoggedInUser(session.getId());
			
			boolean isUpdated = iSlotService.updateSlotDetails(slotDTO, adminDTO);
			StringBuilder eventMsg = new StringBuilder("Schedule Updation is ");
			
			if(isUpdated) {
				logger.info(eventMsg.append("Successful !!!"));	
			}else {
				logger.info(eventMsg.append("Failed !!!"));
			}
			response.setMessage("OK");
			response.setData(eventMsg.toString());
			response.setStatus(HttpStatus.OK);
			iBaseDao.getSession().clear();
			logger.info("Slot is Updated successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to update slot record !!! " + e);
			response.setMessage("Unable to update slot record !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/deleteSlot.view", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<String>> deleteSlot(@RequestParam("slotId") int slotID) {
		
		ResponseBodyData<String> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		try {
			logger.info("Deleting Slots Details !!!");
			
			HttpSession session = getSession();
			AdminDTO adminDTO = (AdminDTO) SessionManager.getLoggedInUser(session.getId());
			
			boolean isDeleted = iSlotService.deleteSlot(slotID, adminDTO);
			StringBuilder eventMsg = new StringBuilder("Schedule Deletion is ");
			
			if(isDeleted) {
				logger.info(eventMsg.append("Successful !!!"));	
			}else {
				logger.info(eventMsg.append("Failed !!!"));
			}
			response.setMessage("OK");
			response.setData(eventMsg.toString());
			response.setStatus(HttpStatus.OK);
			iBaseDao.getSession().clear();
			logger.info("Slot Deletion is successfull !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to delete slot details !!! " + e);
			response.setMessage("Unable to delete slot details !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
