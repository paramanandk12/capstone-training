package com.mindtree.migrationaccelerator.entity;

import java.util.List;

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

@Entity
@Table(name = "master_workbook")
public class MasterWorkbook extends EventMetaData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "master_workbook_id", unique = true, nullable = false)
	private Integer masterWorkbookId;

	@Column(name = "name")
	private String name;

	@Column(name = "format")
	private String format;

	@Column(name = "size")
	private Long size;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "masterWorkbook",  fetch = FetchType.EAGER)
	private List<MasterSheetData> masterSheetData;

	public MasterWorkbook() {
	}

	public MasterWorkbook(Integer masterWorkbookId, String name, String format, Long size, Project project,
			List<MasterSheetData> masterSheetData) {
		this.masterWorkbookId = masterWorkbookId;
		this.name = name;
		this.format = format;
		this.size = size;
		this.project = project;
		this.masterSheetData = masterSheetData;
	}

	public Integer getMasterWorkbookId() {
		return masterWorkbookId;
	}

	public String getName() {
		return name;
	}

	public String getFormat() {
		return format;
	}

	public Long getSize() {
		return size;
	}

	public Project getProject() {
		return project;
	}

	public void setMasterWorkbookId(Integer masterWorkbookId) {
		this.masterWorkbookId = masterWorkbookId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<MasterSheetData> getMasterSheetData() {
		return masterSheetData;
	}

	public void setMasterSheetData(List<MasterSheetData> masterSheetData) {
		this.masterSheetData = masterSheetData;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((masterSheetData == null) ? 0 : masterSheetData.hashCode());
		result = prime * result + ((masterWorkbookId == null) ? 0 : masterWorkbookId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterWorkbook other = (MasterWorkbook) obj;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (masterSheetData == null) {
			if (other.masterSheetData != null)
				return false;
		} else if (!masterSheetData.equals(other.masterSheetData))
			return false;
		if (masterWorkbookId == null) {
			if (other.masterWorkbookId != null)
				return false;
		} else if (!masterWorkbookId.equals(other.masterWorkbookId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MasterWorkbook [masterWorkbookId=" + masterWorkbookId + ", name=" + name + ", format=" + format
				+ ", size=" + size + ", project=" + project + ", masterSheetData=" + masterSheetData + "]";
	}

	
}