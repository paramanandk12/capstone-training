package com.mindtree.migrationaccelerator.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface ISlotDao {
	List<MasterSheetData> getMastersheetDataByID(Session session, Integer masterWorkbookId) throws DaoException;

	List<MasterWorkbook> getWorkBooksByProjectId(Session session, int projectId) throws DaoException;

	Boolean insertSlot(Session session, Slot slotDetails) throws DaoException;

	List<Slot> getSlotsByProjectId(Session session, int projectId) throws DaoException;

	boolean updateSlot(Session session, Slot slot) throws DaoException;

	boolean deleteSlot(Session session, Slot slot) throws DaoException;

	Slot getSlotBySlotID(Session session, int SlotID) throws DaoException;

	List<Slot> getSlotsByDate(Session session, Date date, MasterSheetData masterSheetDataUser) throws DaoException;

	
}
