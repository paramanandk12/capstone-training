package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.mindtree.migrationaccelerator.enums.SlotStatus;

public class SlotDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer slotId;
	private String slotName;
	private Date startTime;
	private Date startDate;
	private Date endTime;
	private Date endDate;
	private Integer userCountCapacity;
	private Integer userCountOccupied;
	private Integer scheduleFreezDays;
	private SlotStatus slotStatus;
	private String place1;
	private String place2;
	private ProjectDTO projectDTO;
	private CountryDTO countryDTO;
	private SiteDTO siteDTO;
	private SlotUsersMigrationDetailsDTO slotUserMigrationDetailsDTO;
	private Set<UserSlotSelectionDTO> userSlotSelectionDTOSet;
	private String timezone;
	private String slotDesc;
	
	public SlotDTO() {}

	public SlotDTO(Integer slotId, String slotName, Date startTime, Date startDate, Date endTime, Date endDate,
			Integer userCountCapacity, Integer userCountOccupied, Integer scheduleFreezDays, SlotStatus slotStatus, String place1, String place2,
			ProjectDTO projectDTO, CountryDTO countryDTO, SiteDTO siteDTO, SlotUsersMigrationDetailsDTO slotUserMigrationDetailsDTO, String timezone, String slotDesc, Set<UserSlotSelectionDTO> userSlotSelectionDTOSet) {
		super();
		this.slotId = slotId;
		this.slotName = slotName;
		this.startTime = startTime;
		this.startDate = startDate;
		this.endTime = endTime;
		this.endDate = endDate;
		this.userCountCapacity = userCountCapacity;
		this.userCountOccupied = userCountOccupied;
		this.scheduleFreezDays = scheduleFreezDays;
		this.slotStatus = slotStatus;
		this.place1 = place1;
		this.place2 = place2;
		this.projectDTO = projectDTO;
		this.countryDTO = countryDTO;
		this.siteDTO = siteDTO;
		this.slotUserMigrationDetailsDTO = slotUserMigrationDetailsDTO;
		this.timezone = timezone;
		this.slotDesc = slotDesc;
		this.setUserSlotSelectionDTOSet(userSlotSelectionDTOSet);
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	public CountryDTO getCountryDTO() {
		return countryDTO;
	}

	public void setCountryDTO(CountryDTO countryDTO) {
		this.countryDTO = countryDTO;
	}

	public SiteDTO getSiteDTO() {
		return siteDTO;
	}

	public void setSiteDTO(SiteDTO siteDTO) {
		this.siteDTO = siteDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SlotUsersMigrationDetailsDTO getSlotUserMigrationDetailsDTO() {
		return slotUserMigrationDetailsDTO;
	}

	public void setSlotUserMigrationDetailsDTO(SlotUsersMigrationDetailsDTO slotUserMigrationDetailsDTO) {
		this.slotUserMigrationDetailsDTO = slotUserMigrationDetailsDTO;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getSlotDesc() {
		return slotDesc;
	}

	public void setSlotDesc(String slotDesc) {
		this.slotDesc = slotDesc;
	}

	public Set<UserSlotSelectionDTO> getUserSlotSelectionDTOSet() {
		return userSlotSelectionDTOSet;
	}

	public void setUserSlotSelectionDTOSet(Set<UserSlotSelectionDTO> userSlotSelectionDTOSet) {
		this.userSlotSelectionDTOSet = userSlotSelectionDTOSet;
	}

	public Integer getScheduleFreezDays() {
		return scheduleFreezDays;
	}

	public void setScheduleFreezDays(Integer scheduleFreezDays) {
		this.scheduleFreezDays = scheduleFreezDays;
	}
	
}
