package com.mindtree.migrationaccelerator.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mindtree.migrationaccelerator.enums.ProjectStatus;

@Entity
@Table(name = "project")
public class Project extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id", unique = true, nullable = false)
	private Integer projectId;

	@Column(name = "proj_name", nullable = false)
	private String projName;

	@Column(name = "description")
	private String description;

	@Column(name = "project_manager_name")
	private String projectManagerName;

	@Column(name = "project_manager_email")
	private String projectManagerEmail;

	@Column(name = "contact_number", length = 13)
	private Integer contactNumber;

	@Column(name = "project_status")
	private ProjectStatus projectStatus;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "admin_project", joinColumns = { @JoinColumn(name = "proj_id") }, inverseJoinColumns = {
			@JoinColumn(name = "admin_id") })
	@Column(name = "admin")
	private Set<Admin> admin;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "project")
	private Set<Slot> slot;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "project")
	private Set<UserAccountDetails> userAccountDetailsSet;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	private Set<MasterWorkbook> masterWorkbook;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="project", cascade = CascadeType.ALL)
    private Set<Country> countrySet;
    
	@OneToMany(fetch = FetchType.EAGER, mappedBy="project", cascade = CascadeType.ALL)
    private Set<Site> siteSet;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project",  fetch = FetchType.LAZY)
	private Set<MasterSheetData> masterSheetDataSet;

	public Project() {}

	public Project(Integer projectId, String projName, String description, String projectManagerName,
			String projectManagerEmail, Integer contactNumber, ProjectStatus projectStatus, String place1,
			String place2, Set<Admin> admin, Set<Slot> slot, Set<UserAccountDetails> userAccountDetailsSet,
			Set<MasterWorkbook> masterWorkbook, Set<Country> countrySet, Set<Site> siteSet, Set<MasterSheetData> masterSheetDataSet) {
		super();
		this.projectId = projectId;
		this.projName = projName;
		this.description = description;
		this.projectManagerName = projectManagerName;
		this.projectManagerEmail = projectManagerEmail;
		this.contactNumber = contactNumber;
		this.projectStatus = projectStatus;
		this.place1 = place1;
		this.place2 = place2;
		this.admin = admin;
		this.slot = slot;
		this.userAccountDetailsSet = userAccountDetailsSet;
		this.masterWorkbook = masterWorkbook;
		this.countrySet = countrySet;
		this.siteSet = siteSet;
		this.masterSheetDataSet = masterSheetDataSet;
	}

	public Set<MasterSheetData> getMasterSheetDataSet() {
		return masterSheetDataSet;
	}

	public void setMasterSheetDataSet(Set<MasterSheetData> masterSheetDataSet) {
		this.masterSheetDataSet = masterSheetDataSet;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	public String getProjectManagerEmail() {
		return projectManagerEmail;
	}

	public void setProjectManagerEmail(String projectManagerEmail) {
		this.projectManagerEmail = projectManagerEmail;
	}

	public Integer getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Integer contactNumber) {
		this.contactNumber = contactNumber;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
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

	public Set<Admin> getAdmin() {
		return admin;
	}

	public void setAdmin(Set<Admin> admin) {
		this.admin = admin;
	}

	public Set<Slot> getSlot() {
		return slot;
	}

	public void setSlot(Set<Slot> slot) {
		this.slot = slot;
	}

	public Set<UserAccountDetails> getUserAccountDetailsSet() {
		return userAccountDetailsSet;
	}

	public void setUserAccountDetailsSet(Set<UserAccountDetails> userAccountDetailsSet) {
		this.userAccountDetailsSet = userAccountDetailsSet;
	}

	public Set<MasterWorkbook> getMasterWorkbook() {
		return masterWorkbook;
	}

	public void setMasterWorkbook(Set<MasterWorkbook> masterWorkbook) {
		this.masterWorkbook = masterWorkbook;
	}

	public Set<Country> getCountrySet() {
		return countrySet;
	}

	public void setCountrySet(Set<Country> countrySet) {
		this.countrySet = countrySet;
	}

	public Set<Site> getSiteSet() {
		return siteSet;
	}

	public void setSiteSet(Set<Site> siteSet) {
		this.siteSet = siteSet;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((countrySet == null) ? 0 : countrySet.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((masterWorkbook == null) ? 0 : masterWorkbook.hashCode());
		result = prime * result + ((place1 == null) ? 0 : place1.hashCode());
		result = prime * result + ((place2 == null) ? 0 : place2.hashCode());
		result = prime * result + ((projName == null) ? 0 : projName.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectManagerEmail == null) ? 0 : projectManagerEmail.hashCode());
		result = prime * result + ((projectManagerName == null) ? 0 : projectManagerName.hashCode());
		result = prime * result + ((projectStatus == null) ? 0 : projectStatus.hashCode());
		result = prime * result + ((siteSet == null) ? 0 : siteSet.hashCode());
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
		result = prime * result + ((userAccountDetailsSet == null) ? 0 : userAccountDetailsSet.hashCode());
		return result;
	}*/

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (countrySet == null) {
			if (other.countrySet != null)
				return false;
		} else if (!countrySet.equals(other.countrySet))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (masterWorkbook == null) {
			if (other.masterWorkbook != null)
				return false;
		} else if (!masterWorkbook.equals(other.masterWorkbook))
			return false;
		if (place1 == null) {
			if (other.place1 != null)
				return false;
		} else if (!place1.equals(other.place1))
			return false;
		if (place2 == null) {
			if (other.place2 != null)
				return false;
		} else if (!place2.equals(other.place2))
			return false;
		if (projName == null) {
			if (other.projName != null)
				return false;
		} else if (!projName.equals(other.projName))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectManagerEmail == null) {
			if (other.projectManagerEmail != null)
				return false;
		} else if (!projectManagerEmail.equals(other.projectManagerEmail))
			return false;
		if (projectManagerName == null) {
			if (other.projectManagerName != null)
				return false;
		} else if (!projectManagerName.equals(other.projectManagerName))
			return false;
		if (projectStatus != other.projectStatus)
			return false;
		if (siteSet == null) {
			if (other.siteSet != null)
				return false;
		} else if (!siteSet.equals(other.siteSet))
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		if (userAccountDetailsSet == null) {
			if (other.userAccountDetailsSet != null)
				return false;
		} else if (!userAccountDetailsSet.equals(other.userAccountDetailsSet))
			return false;
		return true;
	}*/

	/*@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projName=" + projName + ", description=" + description
				+ ", projectManagerName=" + projectManagerName + ", projectManagerEmail=" + projectManagerEmail
				+ ", contactNumber=" + contactNumber + ", projectStatus=" + projectStatus + ", place1=" + place1
				+ ", place2=" + place2 + ", admin=" + admin + ", slot=" + slot + ", userAccountDetailsSet="
				+ userAccountDetailsSet + ", masterWorkbook=" + masterWorkbook + ", countrySet=" + countrySet
				+ ", siteSet=" + siteSet + "]";
	}*/

}