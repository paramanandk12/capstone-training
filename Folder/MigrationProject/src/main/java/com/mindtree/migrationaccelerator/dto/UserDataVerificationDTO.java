package com.mindtree.migrationaccelerator.dto;

import com.mindtree.migrationaccelerator.enums.ModificationStatus;

public class UserDataVerificationDTO {
 
	private Integer Id;
	private String firstName;
	private String firstNameToBe;
	private String lastName;
	private String lastNameToBe;
	private String country;
	private String countryToBe;
	private String site;
	private String siteToBe;
	private String samAccountNameAsIs;
	private String samAccountNameAsIsToBe;
	private String loginIdUPNasIs;
	private ModificationStatus modificationStatus;
	private String comments;
	
	public  UserDataVerificationDTO () {
		
	}

	public Integer getId() {
		return Id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFirstNameToBe() {
		return firstNameToBe;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLastNameToBe() {
		return lastNameToBe;
	}

	public String getCountry() {
		return country;
	}

	public String getCountryToBe() {
		return countryToBe;
	}

	public String getSite() {
		return site;
	}

	public String getSiteToBe() {
		return siteToBe;
	}

	public String getSamAccountNameAsIs() {
		return samAccountNameAsIs;
	}

	public String getSamAccountNameAsIsToBe() {
		return samAccountNameAsIsToBe;
	}

	public String getLoginIdUPNasIs() {
		return loginIdUPNasIs;
	}

	public ModificationStatus getModificationStatus() {
		return modificationStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setFirstNameToBe(String firstNameToBe) {
		this.firstNameToBe = firstNameToBe;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLastNameToBe(String lastNameToBe) {
		this.lastNameToBe = lastNameToBe;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountryToBe(String countryToBe) {
		this.countryToBe = countryToBe;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setSiteToBe(String siteToBe) {
		this.siteToBe = siteToBe;
	}

	public void setSamAccountNameAsIs(String samAccountNameAsIs) {
		this.samAccountNameAsIs = samAccountNameAsIs;
	}

	public void setSamAccountNameAsIsToBe(String samAccountNameAsIsToBe) {
		this.samAccountNameAsIsToBe = samAccountNameAsIsToBe;
	}

	public void setLoginIdUPNasIs(String loginIdUPNasIs) {
		this.loginIdUPNasIs = loginIdUPNasIs;
	}

	public void setModificationStatus(ModificationStatus modificationStatus) {
		this.modificationStatus = modificationStatus;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public UserDataVerificationDTO(Integer id, String firstName, String firstNameToBe, String lastName,
			String lastNameToBe, String country, String countryToBe, String site, String siteToBe,
			String samAccountNameAsIs, String samAccountNameAsIsToBe, String loginIdUPNasIs,
			ModificationStatus modificationStatus, String comments) {
		super();
		Id = id;
		this.firstName = firstName;
		this.firstNameToBe = firstNameToBe;
		this.lastName = lastName;
		this.lastNameToBe = lastNameToBe;
		this.country = country;
		this.countryToBe = countryToBe;
		this.site = site;
		this.siteToBe = siteToBe;
		this.samAccountNameAsIs = samAccountNameAsIs;
		this.samAccountNameAsIsToBe = samAccountNameAsIsToBe;
		this.loginIdUPNasIs = loginIdUPNasIs;
		this.modificationStatus = modificationStatus;
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((countryToBe == null) ? 0 : countryToBe.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((firstNameToBe == null) ? 0 : firstNameToBe.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lastNameToBe == null) ? 0 : lastNameToBe.hashCode());
		result = prime * result + ((loginIdUPNasIs == null) ? 0 : loginIdUPNasIs.hashCode());
		result = prime * result + ((modificationStatus == null) ? 0 : modificationStatus.hashCode());
		result = prime * result + ((samAccountNameAsIs == null) ? 0 : samAccountNameAsIs.hashCode());
		result = prime * result + ((samAccountNameAsIsToBe == null) ? 0 : samAccountNameAsIsToBe.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((siteToBe == null) ? 0 : siteToBe.hashCode());
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
		UserDataVerificationDTO other = (UserDataVerificationDTO) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (countryToBe == null) {
			if (other.countryToBe != null)
				return false;
		} else if (!countryToBe.equals(other.countryToBe))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (firstNameToBe == null) {
			if (other.firstNameToBe != null)
				return false;
		} else if (!firstNameToBe.equals(other.firstNameToBe))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lastNameToBe == null) {
			if (other.lastNameToBe != null)
				return false;
		} else if (!lastNameToBe.equals(other.lastNameToBe))
			return false;
		if (loginIdUPNasIs == null) {
			if (other.loginIdUPNasIs != null)
				return false;
		} else if (!loginIdUPNasIs.equals(other.loginIdUPNasIs))
			return false;
		if (modificationStatus != other.modificationStatus)
			return false;
		if (samAccountNameAsIs == null) {
			if (other.samAccountNameAsIs != null)
				return false;
		} else if (!samAccountNameAsIs.equals(other.samAccountNameAsIs))
			return false;
		if (samAccountNameAsIsToBe == null) {
			if (other.samAccountNameAsIsToBe != null)
				return false;
		} else if (!samAccountNameAsIsToBe.equals(other.samAccountNameAsIsToBe))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (siteToBe == null) {
			if (other.siteToBe != null)
				return false;
		} else if (!siteToBe.equals(other.siteToBe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDataVerificationDTO [Id=" + Id + ", firstName=" + firstName + ", firstNameToBe=" + firstNameToBe
				+ ", lastName=" + lastName + ", lastNameToBe=" + lastNameToBe + ", country=" + country
				+ ", countryToBe=" + countryToBe + ", site=" + site + ", siteToBe=" + siteToBe + ", samAccountNameAsIs="
				+ samAccountNameAsIs + ", samAccountNameAsIsToBe=" + samAccountNameAsIsToBe + ", loginIdUPNasIs="
				+ loginIdUPNasIs + ", modificationStatus=" + modificationStatus + ", comments=" + comments + "]";
	}

	
}
