package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.Date;

import com.mindtree.migrationaccelerator.enums.MigrationStatus;

public class SlotUsersMigrationDetailsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer slotUserMigrationDetailsID;
	private Integer slotMigrationFailureCount;
	private Integer slotMigrationSuccessCount;
	private Date migrationSuccessDate;
	private MigrationStatus migrationStatus;
	
	public SlotUsersMigrationDetailsDTO() {}
	
	public SlotUsersMigrationDetailsDTO(Integer slotUserMigrationDetailsID, Integer slotMigrationFailureCount,
			Integer slotMigrationSuccessCount, Date migrationSuccessDate, MigrationStatus migrationStatus) {
		super();
		this.slotUserMigrationDetailsID = slotUserMigrationDetailsID;
		this.slotMigrationFailureCount = slotMigrationFailureCount;
		this.slotMigrationSuccessCount = slotMigrationSuccessCount;
		this.migrationSuccessDate = migrationSuccessDate;
		this.migrationStatus = migrationStatus;
	}

	public Integer getSlotUserMigrationDetailsID() {
		return slotUserMigrationDetailsID;
	}

	public void setSlotUserMigrationDetailsID(Integer slotUserMigrationDetailsID) {
		this.slotUserMigrationDetailsID = slotUserMigrationDetailsID;
	}

	public Integer getSlotMigrationFailureCount() {
		return slotMigrationFailureCount;
	}

	public void setSlotMigrationFailureCount(Integer slotMigrationFailureCount) {
		this.slotMigrationFailureCount = slotMigrationFailureCount;
	}

	public Integer getSlotMigrationSuccessCount() {
		return slotMigrationSuccessCount;
	}

	public void setSlotMigrationSuccessCount(Integer slotMigrationSuccessCount) {
		this.slotMigrationSuccessCount = slotMigrationSuccessCount;
	}

	public Date getMigrationSuccessDate() {
		return migrationSuccessDate;
	}

	public void setMigrationSuccessDate(Date migrationSuccessDate) {
		this.migrationSuccessDate = migrationSuccessDate;
	}

	public MigrationStatus getMigrationStatus() {
		return migrationStatus;
	}

	public void setMigrationStatus(MigrationStatus migrationStatus) {
		this.migrationStatus = migrationStatus;
	}

	
	
}
