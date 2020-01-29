package com.mindtree.migrationaccelerator.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import com.mindtree.migrationaccelerator.dto.LoggedInUserDetails;
import com.mindtree.migrationaccelerator.dto.SlotDTO;
import com.mindtree.migrationaccelerator.dto.UserDTO;
import com.mindtree.migrationaccelerator.dto.UserDataVerificationDTO;
import com.mindtree.migrationaccelerator.dto.UserDetailsUploadDTO;
import com.mindtree.migrationaccelerator.dto.UserSlotSelectionDTO;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.enums.LoggedInUserType;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;
import com.mindtree.migrationaccelerator.response.ResponseBodyData;
import com.mindtree.migrationaccelerator.service.ISlotService;
import com.mindtree.migrationaccelerator.service.IUserAuthService;
import com.mindtree.migrationaccelerator.service.IUserSlotSelection;
import com.mindtree.migrationaccelerator.util.BeanToDTOConverter;
import com.mindtree.migrationaccelerator.util.SendCalendarInvite;
import com.mindtree.migrationaccelerator.util.SessionManager;

@RestController
@RequestMapping("api/user")
public class UserController extends SessionManager{
	
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserAuthService iUserAuthService;
	
	@Autowired
	private ISlotService iSlotService;

	@Autowired
	private BeanToDTOConverter beanToDTOConverter;
	
	@Autowired
	private IUserSlotSelection iUserSlotSelection;

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;

	@RequestMapping(value = "/auth-user.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<UserDetailsUploadDTO>> getUserAuthenticationStatus(
			@RequestBody UserDTO userDTO) {

		ResponseBodyData<UserDetailsUploadDTO> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);

		UserDetailsUploadDTO userDetailsUploadDTO = null;

		try {
			logger.info("User authentication started !!!");
			userDetailsUploadDTO = iUserAuthService.authenticateUser(userDTO);
			
			if(userDetailsUploadDTO.getUserStatus()) {
				HttpSession session = getSession();
				LoggedInUserDetails<UserDTO> loggedInUser = new LoggedInUserDetails<>(LoggedInUserType.USER,userDTO);
				loggedInUserDetails.put(session.getId(), loggedInUser);
				session.setAttribute("userDetailsDTO", userDetailsUploadDTO);
			}
			
			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(userDetailsUploadDTO);
			logger.info("User authentication started !!! " + response.getData().getUserStatus());
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("User authentication error !!!", e);
			response.setMessage("User authentication error !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/submit-userdata.view", method = { RequestMethod.POST })
	public Boolean updateUserData(@RequestBody UserDataVerificationDTO userDataVerificationDTO) {
		return null;
	}

	@RequestMapping(value = "/loadBokedSlotsByUser.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<List<UserSlotSelectionDTO>>> loadBokedSlotsByUser(@RequestBody UserDetailsUploadDTO userDetailsUploadDTO) {
		ResponseBodyData<List<UserSlotSelectionDTO>> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);

		List<UserSlotSelectionDTO> userSlotSelectionDTOs = new ArrayList<UserSlotSelectionDTO>();
		try {
			logger.info("Trying to fetch User Booked Slots !!!");

			List<UserSlotSelection> userBookedSlots = iUserSlotSelection.getUserSlotDetailsByUserEmailID(userDetailsUploadDTO.getEmail());

			if (userBookedSlots != null && !userBookedSlots.isEmpty()) {
				for (UserSlotSelection bookedSlots : userBookedSlots) {
					userSlotSelectionDTOs.add(beanToDTOConverter.userSlotSelectionEntityToDTO(bookedSlots));
				}
			} else {
				logger.error("No slot is booked by user");
			}

			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(userSlotSelectionDTOs);
			logger.info("Fetched User Booked Slots successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch User Booked Slot records !!! " + e);
			response.setMessage("Unable to fetch User Booked Slot records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/fetchSlotsByDate.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<List<SlotDTO>>> getSlotsByDate(@RequestBody Date date) {

		ResponseBodyData<List<SlotDTO>> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);

		List<SlotDTO> slotDTOs = new ArrayList<SlotDTO>();
		try {
			logger.info("Trying to fetch Slots by date !!!");
			
			HttpSession session = getSession();
			UserDetailsUploadDTO userDetailsUploadDTO = (UserDetailsUploadDTO) session.getAttribute("userDetailsDTO");
			List<Slot> slots = iSlotService.getSlotsByDate(date, userDetailsUploadDTO.getEmail());

			if (slots != null && !slots.isEmpty()) {
				for (Slot slot : slots) {
					slotDTOs.add(beanToDTOConverter.slotEntityToDTO(slot));
				}
			} else {
				logger.error("No slots are found in database for date- " + date);
			}

			response.setMessage("OK");
			response.setStatus(HttpStatus.OK);
			response.setData(slotDTOs);
			logger.info("Fetched SlotByDate successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to fetch slots records !!! " + e);
			response.setMessage("Unable to fetch slots records !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/book-slot.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<UserSlotSelectionDTO>> bookSlot(@RequestBody UserSlotSelectionDTO userSlotSelectionDTO) {
		
		ResponseBodyData<UserSlotSelectionDTO> response = new ResponseBodyData<>("Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		
		try {
			logger.info("UserSlotSelection Booking started !!!");
			HttpSession session = getSession();
			UserDTO userDTO = (UserDTO) SessionManager.getLoggedInUser(session.getId());
			
			if(userSlotSelectionDTO.getSlotDTO() != null) {
				
				boolean isSlotBooked = iUserSlotSelection.bookSlot(userDTO, userSlotSelectionDTO);
				
				StringBuilder eventMsg = new StringBuilder("User Slot Booking is ");
				
				if(isSlotBooked) {
					logger.info(eventMsg.append("Successful !!!"));	
					response.setMessage("OK");
						SendCalendarInvite.sendCalendarNotificationToUser(userDTO
									.getEmail(), userSlotSelectionDTO);
				}else {
					logger.info(eventMsg.append("Failed !!!"));
					response.setMessage("FAILED");
				}
			}
			
			response.setStatus(HttpStatus.OK);
			iBaseDao.getSession().clear();
			logger.info("UserSlotSelection Booking completed !!!");
			return ResponseEntity.ok(response);
		} catch (Exception e)  {
			logger.error("Error while UserSlotSelection Booking !!! " + e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@RequestMapping(value = "/deleteBookedSlot.view", method = { RequestMethod.GET })
	public ResponseEntity<ResponseBodyData<String>> deleteUserSlotBooking(@RequestParam("userSlotSelectionID") int userSlotSelectionID) {
		ResponseBodyData<String> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		try {
			logger.info("Deleting UserSlotSelection Details !!!");
			
			HttpSession session = getSession();
			UserDTO userDTO = (UserDTO) SessionManager.getLoggedInUser(session.getId());
			
			boolean isDeleted = iUserSlotSelection.deleteSlotSelection(userSlotSelectionID, userDTO);
			StringBuilder eventMsg = new StringBuilder("Deletion of Booked Schedule is ");
			
			if(isDeleted) {
				logger.info(eventMsg.append("Successful !!!"));	
				response.setMessage("OK");
			}else {
				logger.info(eventMsg.append("Failed !!!"));
				response.setMessage("FAILED");
			}
			
			response.setData(eventMsg.toString());
			response.setStatus(HttpStatus.OK);
			iBaseDao.getSession().clear();
			logger.info("Deleting UserSlotSelection is successfull !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to delete UserSlotSelection details !!! " + e);
			response.setMessage("Unable to delete UserSlotSelection details !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/updateUserSlotBooking.view", method = { RequestMethod.POST })
	public ResponseEntity<ResponseBodyData<String>> updateUserSlotBooking(@RequestBody UserSlotSelectionDTO userSlotSelectionDTO) {
		
		ResponseBodyData<String> response = new ResponseBodyData<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);

		try {
			logger.info("Updating UserSlotSelection Details !!!");
			
			HttpSession session = getSession();
			UserDTO userDTO = (UserDTO) SessionManager.getLoggedInUser(session.getId());
			
			boolean isUpdated = iUserSlotSelection.updateSlotSelection(userSlotSelectionDTO, userDTO);
			StringBuilder eventMsg = new StringBuilder("Updation of Booked Schedule is ");
			
			if(isUpdated) {
				logger.info(eventMsg.append("Successful !!!"));	
				response.setMessage("OK");
			}else {
				logger.info(eventMsg.append("Failed !!!"));
				response.setMessage("FAILED");
			}
			
			response.setData(eventMsg.toString());
			response.setStatus(HttpStatus.OK);
			iBaseDao.getSession().clear();
			logger.info("UserSlotSelection is Updated successfully !!!");
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			logger.error("Unable to update UserSlotSelection record !!! " + e);
			response.setMessage("Unable to update UserSlotSelection record !!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
