package com.mindtree.migrationaccelerator.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mindtree.migrationaccelerator.enums.MigrationStatus;
import com.mindtree.migrationaccelerator.enums.SlotStatus;

@Entity
@Table(name = "user_slot_selection")
public class UserSlotSelection extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_slot_selection_id", unique = true, nullable = false)
	private Integer userSlotSelectionId;

	@Column(name = "migration_status")
	private MigrationStatus migrationStatus;

	@Column(name = "slot_status")
	private SlotStatus slotStatus;
	
	@Column(name = "booking_counter")
	private int bookingCounter;

	@Column(name = "migration_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date migrationDate;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "slot_id")
    private Slot slot;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "mastersheet_data_id")
	private MasterSheetData masterSheetData;

	@Column(name = "is_deleted" ,  columnDefinition = "boolean default false")
	private Boolean isDeleted;

	public UserSlotSelection() {}


	public UserSlotSelection(Integer userSlotSelectionId, MigrationStatus migrationStatus, SlotStatus slotStatus, int bookingCounter, 
			Date migrationDate, Slot slot, MasterSheetData masterSheetData, Boolean isDeleted) {
		super();
		this.userSlotSelectionId = userSlotSelectionId;
		this.migrationStatus = migrationStatus;
		this.slotStatus = slotStatus;
		this.bookingCounter = bookingCounter;
		this.migrationDate = migrationDate;
		this.slot = slot;
		this.masterSheetData = masterSheetData;
		this.isDeleted = isDeleted;
	}


	public SlotStatus getSlotStatus() {
		return slotStatus;
	}


	public void setSlotStatus(SlotStatus slotStatus) {
		this.slotStatus = slotStatus;
	}


	public Integer getUserSlotSelectionId() {
		return userSlotSelectionId;
	}


	public void setUserSlotSelectionId(Integer userSlotSelectionId) {
		this.userSlotSelectionId = userSlotSelectionId;
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

	
	public Slot getSlot() {
		return slot;
	}


	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public MigrationStatus getMigrationStatus() {
		return migrationStatus;
	}


	public void setMigrationStatus(MigrationStatus migrationStatus) {
		this.migrationStatus = migrationStatus;
	}

	public MasterSheetData getMasterSheetData() {
		return masterSheetData;
	}


	public void setMasterSheetData(MasterSheetData masterSheetData) {
		this.masterSheetData = masterSheetData;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
