package com.mindtree.migrationaccelerator.dto;

import java.util.List;

import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;

public class AllEntitiesDTO {

	private Project project;
	private List<UserLocationDetails> userLocationDetailsList;
	private List<UserAccountDetails> userAccountDetailsList;
	private List<UserEmailDetails> userEmailDetailsList;
	private List<UserSkypeDetails> userSkypeDetailsList;
	private List<UserWorkstationDetails> userWorkstationDetailsList;
	private List<UserOtherDetails> userOtherDetailsList;
	private List<MasterSheetData> masterSheetDetailsList;
	private List<Country> countryDetailsList;
	private List<Site> siteDetailsList;
	private List<Country> duplicateCountriesList;

	public AllEntitiesDTO() {
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public List<UserLocationDetails> getUserLocationDetailsList() {
		return userLocationDetailsList;
	}

	public List<UserAccountDetails> getUserAccountDetailsList() {
		return userAccountDetailsList;
	}

	public List<UserEmailDetails> getUserEmailDetailsList() {
		return userEmailDetailsList;
	}

	public List<UserSkypeDetails> getUserSkypeDetailsList() {
		return userSkypeDetailsList;
	}

	public List<UserWorkstationDetails> getUserWorkstationDetailsList() {
		return userWorkstationDetailsList;
	}

	public List<UserOtherDetails> getUserOtherDetailsList() {
		return userOtherDetailsList;
	}

	public List<MasterSheetData> getMasterSheetDetailsList() {
		return masterSheetDetailsList;
	}

	public List<Country> getCountryDetailsList() {
		return countryDetailsList;
	}

	public List<Site> getSiteDetailsList() {
		return siteDetailsList;
	}

	public void setUserLocationDetailsList(List<UserLocationDetails> userLocationDetailsList) {
		this.userLocationDetailsList = userLocationDetailsList;
	}

	public void setUserAccountDetailsList(List<UserAccountDetails> userAccountDetailsList) {
		this.userAccountDetailsList = userAccountDetailsList;
	}

	public void setUserEmailDetailsList(List<UserEmailDetails> userEmailDetailsList) {
		this.userEmailDetailsList = userEmailDetailsList;
	}

	public void setUserSkypeDetailsList(List<UserSkypeDetails> userSkypeDetailsList) {
		this.userSkypeDetailsList = userSkypeDetailsList;
	}

	public void setUserWorkstationDetailsList(List<UserWorkstationDetails> userWorkstationDetailsList) {
		this.userWorkstationDetailsList = userWorkstationDetailsList;
	}

	public void setUserOtherDetailsList(List<UserOtherDetails> userOtherDetailsList) {
		this.userOtherDetailsList = userOtherDetailsList;
	}

	public void setMasterSheetDetailsList(List<MasterSheetData> masterSheetDetailsList) {
		this.masterSheetDetailsList = masterSheetDetailsList;
	}

	public void setCountryDetailsList(List<Country> countryDetailsList) {
		this.countryDetailsList = countryDetailsList;
	}

	public void setSiteDetailsList(List<Site> siteDetailsList) {
		this.siteDetailsList = siteDetailsList;
	}

	public AllEntitiesDTO(List<UserLocationDetails> userLocationDetailsList,
			List<UserAccountDetails> userAccountDetailsList, List<UserEmailDetails> userEmailDetailsList,
			List<UserSkypeDetails> userSkypeDetailsList, List<UserWorkstationDetails> userWorkstationDetailsList,
			List<UserOtherDetails> userOtherDetailsList, List<MasterSheetData> masterSheetDetailsList,
			List<Country> countryDetailsList, List<Site> siteDetailsList) {
		this.userLocationDetailsList = userLocationDetailsList;
		this.userAccountDetailsList = userAccountDetailsList;
		this.userEmailDetailsList = userEmailDetailsList;
		this.userSkypeDetailsList = userSkypeDetailsList;
		this.userWorkstationDetailsList = userWorkstationDetailsList;
		this.userOtherDetailsList = userOtherDetailsList;
		this.masterSheetDetailsList = masterSheetDetailsList;
		this.countryDetailsList = countryDetailsList;
		this.siteDetailsList = siteDetailsList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryDetailsList == null) ? 0 : countryDetailsList.hashCode());
		result = prime * result + ((masterSheetDetailsList == null) ? 0 : masterSheetDetailsList.hashCode());
		result = prime * result + ((siteDetailsList == null) ? 0 : siteDetailsList.hashCode());
		result = prime * result + ((userAccountDetailsList == null) ? 0 : userAccountDetailsList.hashCode());
		result = prime * result + ((userEmailDetailsList == null) ? 0 : userEmailDetailsList.hashCode());
		result = prime * result + ((userLocationDetailsList == null) ? 0 : userLocationDetailsList.hashCode());
		result = prime * result + ((userOtherDetailsList == null) ? 0 : userOtherDetailsList.hashCode());
		result = prime * result + ((userSkypeDetailsList == null) ? 0 : userSkypeDetailsList.hashCode());
		result = prime * result + ((userWorkstationDetailsList == null) ? 0 : userWorkstationDetailsList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllEntitiesDTO other = (AllEntitiesDTO) obj;
		if (countryDetailsList == null) {
			if (other.countryDetailsList != null)
				return false;
		} else if (!countryDetailsList.equals(other.countryDetailsList))
			return false;
		if (masterSheetDetailsList == null) {
			if (other.masterSheetDetailsList != null)
				return false;
		} else if (!masterSheetDetailsList.equals(other.masterSheetDetailsList))
			return false;
		if (siteDetailsList == null) {
			if (other.siteDetailsList != null)
				return false;
		} else if (!siteDetailsList.equals(other.siteDetailsList))
			return false;
		if (userAccountDetailsList == null) {
			if (other.userAccountDetailsList != null)
				return false;
		} else if (!userAccountDetailsList.equals(other.userAccountDetailsList))
			return false;
		if (userEmailDetailsList == null) {
			if (other.userEmailDetailsList != null)
				return false;
		} else if (!userEmailDetailsList.equals(other.userEmailDetailsList))
			return false;
		if (userLocationDetailsList == null) {
			if (other.userLocationDetailsList != null)
				return false;
		} else if (!userLocationDetailsList.equals(other.userLocationDetailsList))
			return false;
		if (userOtherDetailsList == null) {
			if (other.userOtherDetailsList != null)
				return false;
		} else if (!userOtherDetailsList.equals(other.userOtherDetailsList))
			return false;
		if (userSkypeDetailsList == null) {
			if (other.userSkypeDetailsList != null)
				return false;
		} else if (!userSkypeDetailsList.equals(other.userSkypeDetailsList))
			return false;
		if (userWorkstationDetailsList == null) {
			if (other.userWorkstationDetailsList != null)
				return false;
		} else if (!userWorkstationDetailsList.equals(other.userWorkstationDetailsList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AllEntitiesDTO [userLocationDetailsList=" + userLocationDetailsList + ", userAccountDetailsList="
				+ userAccountDetailsList + ", userEmailDetailsList=" + userEmailDetailsList + ", userSkypeDetailsList="
				+ userSkypeDetailsList + ", userWorkstationDetailsList=" + userWorkstationDetailsList
				+ ", userOtherDetailsList=" + userOtherDetailsList + ", masterSheetDetailsList="
				+ masterSheetDetailsList + ", countryDetailsList=" + countryDetailsList + ", siteDetailsList="
				+ siteDetailsList + "]";
	}

	public List<Country> getDuplicateCountriesList() {
		return duplicateCountriesList;
	}

	public void setDuplicateCountriesList(List<Country> duplicateCountriesList) {
		this.duplicateCountriesList = duplicateCountriesList;
	}

}
