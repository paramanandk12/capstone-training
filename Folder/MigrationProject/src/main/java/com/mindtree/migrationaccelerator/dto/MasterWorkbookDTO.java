package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MasterWorkbookDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;
	private String format;
	private Long size;
	private Date createdOn;
	private String createdBy;
	private List<MastersheetDataDTO> mastersheetDataDTO;

	public MasterWorkbookDTO() {
	}

	public Integer getId() {
		return id;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public List<MastersheetDataDTO> getMastersheetDataDTO() {
		return mastersheetDataDTO;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setMastersheetDataDTO(List<MastersheetDataDTO> mastersheetDataDTO) {
		this.mastersheetDataDTO = mastersheetDataDTO;
	}

	public MasterWorkbookDTO(Integer id, String name, String format, Long size, Date createdOn, String createdBy,
			List<MastersheetDataDTO> mastersheetDataDTO) {
		this.id = id;
		this.name = name;
		this.format = format;
		this.size = size;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.mastersheetDataDTO = mastersheetDataDTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mastersheetDataDTO == null) ? 0 : mastersheetDataDTO.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		MasterWorkbookDTO other = (MasterWorkbookDTO) obj;
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
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mastersheetDataDTO == null) {
			if (other.mastersheetDataDTO != null)
				return false;
		} else if (!mastersheetDataDTO.equals(other.mastersheetDataDTO))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "MasterWorkbookDTO [id=" + id + ", name=" + name + ", format=" + format + ", size=" + size
				+ ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", mastersheetDataDTO=" + mastersheetDataDTO
				+ "]";
	}

}
