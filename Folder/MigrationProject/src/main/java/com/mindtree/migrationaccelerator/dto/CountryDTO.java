package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.List;

public class CountryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer countryId;
	private String countryName;
	private List<SiteDTO> siteDTOList;
	private ProjectDTO projectDTO;
	private Integer availableSitesCount;
	
	public CountryDTO() {}
	
	public CountryDTO(Integer countryId, String countryName, List<SiteDTO> siteDTOs, ProjectDTO project, ProjectDTO projectDTO, Integer availableSitesCount) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.siteDTOList = siteDTOs;
		this.projectDTO = projectDTO;
		this.availableSitesCount = availableSitesCount;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<SiteDTO> getSiteDTOList() {
		return siteDTOList;
	}

	public void setSiteDTOList(List<SiteDTO> siteDTOList) {
		this.siteDTOList = siteDTOList;
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	@Override
	public String toString() {
		return "CountryDTO [countryId=" + countryId + ", countryName=" + countryName + ", siteDTOList=" + siteDTOList
				+ ", projectDTO=" + projectDTO + "]";
	}

	public Integer getAvailableSitesCount() {
		return availableSitesCount;
	}

	public void setAvailableSitesCount(Integer availableSitesCount) {
		this.availableSitesCount = availableSitesCount;
	}

}
