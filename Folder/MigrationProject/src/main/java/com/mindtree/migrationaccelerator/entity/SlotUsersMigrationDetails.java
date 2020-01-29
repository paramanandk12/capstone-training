package com.mindtree.migrationaccelerator.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mindtree.migrationaccelerator.enums.MigrationStatus;

@Entity
@Table(name = "slot_users_migration_details")
public class SlotUsersMigrationDetails extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "slot_users_migration_details_id", unique = true, nullable = false)
	private Integer slotUserMigrationDetailsID;
	
	@Column(name = "slot_users_migration_failure_count")
	private Integer slotMigrationFailureCount;
	
	@Column(name = "slot_users_migration_success_count")
	private Integer slotMigrationSuccessCount;
	
	@Column(name = "slot_users_migration_success_date")
	@Temporal(TemporalType.DATE)
	private Date migrationSuccessDate;
	
	@Column(name = "slot_users_migration_status")
	private MigrationStatus migrationStatus;
	
	@OneToOne(mappedBy="slotUsersMigrationDetails", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Slot slot;
	
	public SlotUsersMigrationDetails() {}
	
	public SlotUsersMigrationDetails(Integer slotUserMigrationDetailsID, Integer slotMigrationFailureCount,
			Integer slotMigrationSuccessCount, Date migrationSuccessDate, MigrationStatus migrationStatus, Slot slot) {
		super();
		this.slotUserMigrationDetailsID = slotUserMigrationDetailsID;
		this.slotMigrationFailureCount = slotMigrationFailureCount;
		this.slotMigrationSuccessCount = slotMigrationSuccessCount;
		this.migrationSuccessDate = migrationSuccessDate;
		this.migrationStatus = migrationStatus;
		this.slot = slot;
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

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	
	
	
}
