package com.mindtree.migrationaccelerator.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mindtree.migrationaccelerator.enums.SlotStatus;

@Entity
@Table(name = "slot")
public class Slot extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "slot_id", unique = true, nullable = false)
	private Integer slotId;

	@Column(name = "slot_name", nullable = false)
	private String slotName;

	@Column(name = "start_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "start_time", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date startTime;
	
	@Column(name = "end_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "end_time", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date endTime;
	
	@Column(name = "user_count_capacity", nullable = false)
	private Integer userCountCapacity;

	@Column(name = "user_count_occupied")
	private Integer userCountOccupied;

	@Column(name = "freez_schedule_booking_days", nullable = false)
	private Integer scheduleFreezDays;

	@Column(name = "slot_status")
	private SlotStatus slotStatus;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;
	
	@Column(name = "timezone")
	private String timeZone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id")
	private Site site;

	@Column(name = "is_deleted" ,  columnDefinition = "boolean default false")
	private Boolean isDeleted;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="slot", cascade = CascadeType.ALL)
    private Set<UserSlotSelection> userSlotSelection;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_users_migration_details_id")
	private SlotUsersMigrationDetails slotUsersMigrationDetails;
	
	public Slot() {}

	public Slot(Integer slotId, String slotName, Date startDate, Date startTime, Date endDate, Date endTime,
			Integer userCountCapacity, Integer userCountOccupied, Integer scheduleFreezDays, SlotStatus slotStatus, String place1, String place2,
			String timeZone, Project project, Country country, Site site, Boolean isDeleted,
			Set<UserSlotSelection> userSlotSelection, SlotUsersMigrationDetails slotUsersMigrationDetails) {
		super();
		this.slotId = slotId;
		this.slotName = slotName;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.userCountCapacity = userCountCapacity;
		this.userCountOccupied = userCountOccupied;
		this.scheduleFreezDays = scheduleFreezDays;
		this.slotStatus = slotStatus;
		this.place1 = place1;
		this.place2 = place2;
		this.timeZone = timeZone;
		this.project = project;
		this.country = country;
		this.site = site;
		this.isDeleted = isDeleted;
		this.userSlotSelection = userSlotSelection;
		this.slotUsersMigrationDetails = slotUsersMigrationDetails;
	}

	public Integer getScheduleFreezDays() {
		return scheduleFreezDays;
	}

	public void setScheduleFreezDays(Integer scheduleFreezDays) {
		this.scheduleFreezDays = scheduleFreezDays;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getUserCountCapacity() {
		return userCountCapacity;
	}

	public void setUserCountCapacity(Integer userCountCapacity) {
		this.userCountCapacity = userCountCapacity;
	}

	public Integer getUserCountOccupied() {
		return userCountOccupied;
	}

	public void setUserCountOccupied(Integer userCountOccupied) {
		this.userCountOccupied = userCountOccupied;
	}

	public SlotStatus getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(SlotStatus slotStatus) {
		this.slotStatus = slotStatus;
	}

	public String getPlace1() {
		return place1;
	}

	public void setPlace1(String place1) {
		this.place1 = place1;
	}

	public String getPlace2() {
		return place2;
	}

	public void setPlace2(String place2) {
		this.place2 = place2;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<UserSlotSelection> getUserSlotSelection() {
		return userSlotSelection;
	}

	public void setUserSlotSelection(Set<UserSlotSelection> userSlotSelection) {
		this.userSlotSelection = userSlotSelection;
	}

	public SlotUsersMigrationDetails getSlotUsersMigrationDetails() {
		return slotUsersMigrationDetails;
	}

	public void setSlotUsersMigrationDetails(SlotUsersMigrationDetails slotUsersMigrationDetails) {
		this.slotUsersMigrationDetails = slotUsersMigrationDetails;
	}

}
