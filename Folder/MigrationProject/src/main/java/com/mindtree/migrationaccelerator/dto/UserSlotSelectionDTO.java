package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.Date;

import com.mindtree.migrationaccelerator.enums.MigrationStatus;
import com.mindtree.migrationaccelerator.enums.SlotStatus;

public class UserSlotSelectionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userSlotSelectionId;
	private MigrationStatus migrationStatus;
	private SlotStatus slotStatus;
	private int bookingCounter;
	private Date migrationDate;
	private SlotDTO slotDTO;
	private ProjectDTO projectDTO;
	private MastersheetDataDTO mastersheetDataDTO;
	
	public UserSlotSelectionDTO() {}

	public UserSlotSelectionDTO(Integer userSlotSelectionId, MigrationStatus migrationStatus, SlotStatus slotStatus, int bookingCounter,
			Date migrationDate, SlotDTO slotDTO, ProjectDTO projectDTO, MastersheetDataDTO mastersheetDataDTO) {
		super();
		this.userSlotSelectionId = userSlotSelectionId;
		this.migrationStatus = migrationStatus;
		this.setSlotStatus(slotStatus);
		this.bookingCounter = bookingCounter;
		this.migrationDate = migrationDate;
		this.slotDTO = slotDTO;
		this.projectDTO = projectDTO;
		this.setMastersheetDataDTO(mastersheetDataDTO);
	}

	public Integer getUserSlotSelectionId() {
		return userSlotSelectionId;
	}

	public void setUserSlotSelectionId(Integer userSlotSelectionId) {
		this.userSlotSelectionId = userSlotSelectionId;
	}

	public MigrationStatus getMigrationStatus() {
		return migrationStatus;
	}

	public void setMigrationStatus(MigrationStatus migrationStatus) {
		this.migrationStatus = migrationStatus;
	}

	public int getBookingCounter() {
		return bookingCounter;
	}

	public void setBookingCounter(int bookingCounter) {
		this.bookingCounter = bookingCounter;
	}

	public Date getMigrationDate() {
		return migrationDate;
	}

	public void setMigrationDate(Date migrationDate) {
		this.migrationDate = migrationDate;
	}

	public SlotDTO getSlotDTO() {
		return slotDTO;
	}

	public void setSlotDTO(SlotDTO slotDTO) {
		this.slotDTO = slotDTO;
	}

	public SlotStatus getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(SlotStatus slotStatus) {
		this.slotStatus = slotStatus;
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	public MastersheetDataDTO getMastersheetDataDTO() {
		return mastersheetDataDTO;
	}

	public void setMastersheetDataDTO(MastersheetDataDTO mastersheetDataDTO) {
		this.mastersheetDataDTO = mastersheetDataDTO;
	}
	
}
