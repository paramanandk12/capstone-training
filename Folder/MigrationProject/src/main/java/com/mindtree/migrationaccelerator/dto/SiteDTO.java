package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.Set;

public class SiteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer siteId;
	private String siteName;
	private CountryDTO countryDTO;
	private Set<SlotDTO> slots;
	private ProjectDTO projectDTO;

	public SiteDTO() {}

	public SiteDTO(Integer siteId, String siteName, CountryDTO countryDTO, Set<SlotDTO> slots, ProjectDTO projectDTO) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
		this.countryDTO = countryDTO;
		this.slots = slots;
		this.projectDTO = projectDTO;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public CountryDTO getCountryDTO() {
		return countryDTO;
	}

	public void setCountryDTO(CountryDTO countryDTO) {
		this.countryDTO = countryDTO;
	}

	public Set<SlotDTO> getSlotDTOs() {
		return slots;
	}

	public void setSlots(Set<SlotDTO> slots) {
		this.slots = slots;
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	@Override
	public String toString() {
		return "SiteDTO [siteId=" + siteId + ", siteName=" + siteName + ", countryDTO=" + countryDTO + ", slots=" + slots
				+ ", projectDTO=" + projectDTO + "]";
	}

	

	
}
