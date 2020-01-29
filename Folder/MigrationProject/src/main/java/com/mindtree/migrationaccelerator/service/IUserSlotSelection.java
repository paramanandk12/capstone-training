package com.mindtree.migrationaccelerator.service;

import java.util.List;

import com.mindtree.migrationaccelerator.dto.UserDTO;
import com.mindtree.migrationaccelerator.dto.UserSlotSelectionDTO;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface IUserSlotSelection {

	boolean bookSlot(UserDTO userDTO, UserSlotSelectionDTO userSlotSelectionDTO) throws ServiceException;

	boolean updateSlotSelection(UserSlotSelectionDTO userSlotSelectionDTO, UserDTO userDTO) throws ServiceException;

	boolean deleteSlotSelection(int userSlotSelectionID, UserDTO userDTO) throws ServiceException;

	List<UserSlotSelection> getUserSlotDetailsByUserEmailID(String emailID) throws ServiceException;

}
