package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.Date;

import com.mindtree.migrationaccelerator.enums.ProjectStatus;

public class ProjectDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String manager;
	private String managerEmail;
	private String description;
	private Date createdOn;
	private String createdBy;
	private Date lastUpdated;
	private String updatedBy;
	private ProjectStatus status;

	public ProjectDTO() {
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getManager() {
		return manager;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public String getDescription() {
		return description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public ProjectDTO(Integer id, String name, String manager, String managerEmail, String description,
			Date createdOn, String createdBy, Date lastUpdated, String updatedBy, ProjectStatus status) {
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.managerEmail = managerEmail;
		this.description = description;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.lastUpdated = lastUpdated;
		this.updatedBy = updatedBy;
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((managerEmail == null) ? 0 : managerEmail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		ProjectDTO other = (ProjectDTO) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (managerEmail == null) {
			if (other.managerEmail != null)
				return false;
		} else if (!managerEmail.equals(other.managerEmail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectDTO [id=" + id + ", name=" + name + ", manager=" + manager + ", managerEmail=" + managerEmail
				+ ", description=" + description + ", createdOn=" + createdOn + ", createdBy=" + createdBy
				+ ", lastUpdated=" + lastUpdated + ", updatedBy=" + updatedBy + ", status=" + status + "]";
	}

}
