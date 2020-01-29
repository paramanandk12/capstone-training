package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SlotDetailsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SlotDTO> slotDetails;
	private int projectId;
	private int countryId;
	private int siteId;
	private Date startDate;
	private Date endDate;
	private String responseMsg;
	private String timezone;
	
	public SlotDetailsDTO() {}

	public List<SlotDTO> getSlotDetails() {
		return slotDetails;
	}

	public void setSlotDetails(List<SlotDTO> slotDetails) {
		this.slotDetails = slotDetails;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "SlotDetailsDTO [slotDetails=" + slotDetails + ", projectId=" + projectId + ", countryId=" + countryId
				+ ", siteId=" + siteId + ", startDate=" + startDate + ", endDate=" + endDate + ", responseMsg="
				+ responseMsg + ", timezone=" + timezone + "]";
	}
	
	
	
}
