package com.mindtree.migrationaccelerator.service;

import java.util.Date;
import java.util.List;

import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.MasterWorkbookDTO;
import com.mindtree.migrationaccelerator.dto.SlotDTO;
import com.mindtree.migrationaccelerator.dto.SlotDetailsDTO;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.exceptions.ServiceException;

public interface ISlotService {

	List<MasterWorkbookDTO> getAllMastersheetDataRecords(int projectId) throws ServiceException;

	boolean insertSlotSDetails(SlotDetailsDTO slotDetailsDTO, AdminDTO adminDTO)  throws ServiceException;

	List<Slot> getSlotsByProject(int projectId)  throws ServiceException;

	boolean updateSlotDetails(SlotDTO slotDTO, AdminDTO adminDTO) throws ServiceException;

	boolean deleteSlot(int slotID, AdminDTO adminDTO) throws ServiceException;

	Slot getSlotBySlotID(int slotID) throws ServiceException;

	List<Slot> getSlotsByDate(Date date, String email) throws ServiceException;
}
