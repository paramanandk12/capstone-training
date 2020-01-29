package com.mindtree.migrationaccelerator.dao;

import java.util.List;

import org.hibernate.Session;

import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;
import com.mindtree.migrationaccelerator.exceptions.DaoException;

public interface IUserSlotSelectionDao {

	boolean bookSlot(Session session, UserSlotSelection userSlotSelection) throws DaoException;

	boolean updateSlotSelection(Session session, UserSlotSelection userSlotSelection) throws DaoException;

	boolean deleteSlotSelection(Session session, UserSlotSelection userSlotSelection) throws DaoException;

	List<UserSlotSelection> getUserSlotDetailsByMasterSheetRecord(Session session, MasterSheetData masterSheetData) throws DaoException;

}
