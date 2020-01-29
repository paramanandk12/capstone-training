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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "site", uniqueConstraints=@UniqueConstraint(columnNames={"site_name", "project_id", "country_id"}))
public class Site extends EventMetaData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "site_id", unique = true, nullable = false)
	private Integer siteId;

	@Column(name = "site_name", nullable = false)
	private String siteName;

	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "site")
	private Set<Slot> slot;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

	public Site() {}

	public Site(Integer siteId, String siteName, Country country, Set<Slot> slot, Project project) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
		this.country = country;
		this.slot = slot;
		this.project = project;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Slot> getSlot() {
		return slot;
	}

	public void setSlot(Set<Slot> slot) {
		this.slot = slot;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
		result = prime * result + ((siteName == null) ? 0 : siteName.hashCode());
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
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
		Site other = (Site) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (siteId == null) {
			if (other.siteId != null)
				return false;
		} else if (!siteId.equals(other.siteId))
			return false;
		if (siteName == null) {
			if (other.siteName != null)
				return false;
		} else if (!siteName.equals(other.siteName))
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", country=" + country + ", slot=" + slot
				+ ", project=" + project + "]";
	}

}
