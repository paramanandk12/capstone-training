package com.mindtree.migrationaccelerator.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mindtree.migrationaccelerator.enums.AdminStatus;

@Entity
@Table(name = "admin")
public class Admin extends EventMetaData {

	@Id
	@Column(name = "admin_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile_num", length = 13)
	private String mobilNum;

	@Column(name = "email_id", unique = true, nullable = false)
	private String emailId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "admin_status", nullable = false)
	private AdminStatus adminStatus;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@ManyToMany(mappedBy = "admin", cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	private Set<Project> projectSet;

	public Admin() {}

	public Admin(Integer adminId, String firstName, String lastName, String mobilNum, String emailId, String password,
			AdminStatus adminStatus, String place1, String place2, Set<Project> projectSet) {
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobilNum = mobilNum;
		this.emailId = emailId;
		this.password = password;
		this.adminStatus = adminStatus;
		this.place1 = place1;
		this.place2 = place2;
		this.projectSet = projectSet;
	}
	
	public Integer getAdminId() {
		return adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobilNum() {
		return mobilNum;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPassword() {
		return password;
	}

	public AdminStatus getAdminStatus() {
		return adminStatus;
	}

	public String getPlace1() {
		return place1;
	}

	public String getPlace2() {
		return place2;
	}

	public Set<Project> getProjectSet() {
		return projectSet;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMobilNum(String mobilNum) {
		this.mobilNum = mobilNum;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdminStatus(AdminStatus adminStatus) {
		this.adminStatus = adminStatus;
	}

	public void setPlace1(String place1) {
		this.place1 = place1;
	}

	public void setPlace2(String place2) {
		this.place2 = place2;
	}

	public void setProjectSet(Set<Project> projectSet) {
		this.projectSet = projectSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adminId == null) ? 0 : adminId.hashCode());
		result = prime * result + ((adminStatus == null) ? 0 : adminStatus.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobilNum == null) ? 0 : mobilNum.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((place1 == null) ? 0 : place1.hashCode());
		result = prime * result + ((place2 == null) ? 0 : place2.hashCode());
		result = prime * result + ((projectSet == null) ? 0 : projectSet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (adminId == null) {
			if (other.adminId != null)
				return false;
		} else if (!adminId.equals(other.adminId))
			return false;
		if (adminStatus != other.adminStatus)
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
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
		if (mobilNum == null) {
			if (other.mobilNum != null)
				return false;
		} else if (!mobilNum.equals(other.mobilNum))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		if (projectSet == null) {
			if (other.projectSet != null)
				return false;
		} else if (!projectSet.equals(other.projectSet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", mobilNum="
				+ mobilNum + ", emailId=" + emailId + ", password=" + password + ", adminStatus=" + adminStatus
				+ ", place1=" + place1 + ", place2=" + place2 + ", projectSet=" + projectSet + "]";
	}

}
