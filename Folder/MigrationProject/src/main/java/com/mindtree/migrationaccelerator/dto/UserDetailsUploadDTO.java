package com.mindtree.migrationaccelerator.dto;

import java.util.Date;

public class UserDetailsUploadDTO {

	private String firstName;
	private String lastName;
	private String country;
	private String site;
	private String samAccountNameAsIs;
	private Boolean userStatus;
	private String email;
	private ProjectDTO projectDTO;
	
	public UserDetailsUploadDTO() {}
	
	public UserDetailsUploadDTO(String firstName, String lastName, String country, String site,
			String samAccountNameAsIs, Boolean userStatus,  String email, ProjectDTO projectDTO, Date userSelectedDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.site = site;
		this.samAccountNameAsIs = samAccountNameAsIs;
		this.userStatus = userStatus;
		this.email = email;
		this.setProjectDTO(projectDTO);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCountry() {
		return country;
	}

	public String getSite() {
		return site;
	}

	public String getSamAccountNameAsIs() {
		return samAccountNameAsIs;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setSamAccountNameAsIs(String samAccountNameAsIs) {
		this.samAccountNameAsIs = samAccountNameAsIs;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((samAccountNameAsIs == null) ? 0 : samAccountNameAsIs.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
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
		UserDetailsUploadDTO other = (UserDetailsUploadDTO) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (samAccountNameAsIs == null) {
			if (other.samAccountNameAsIs != null)
				return false;
		} else if (!samAccountNameAsIs.equals(other.samAccountNameAsIs))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (userStatus == null) {
			if (other.userStatus != null)
				return false;
		} else if (!userStatus.equals(other.userStatus))
			return false;
		return true;
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

}
