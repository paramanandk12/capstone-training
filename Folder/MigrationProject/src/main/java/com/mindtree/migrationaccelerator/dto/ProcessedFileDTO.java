package com.mindtree.migrationaccelerator.dto;

import java.util.Arrays;
import java.util.Map;

import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.entity.Project;

public class ProcessedFileDTO {

	private Map<String, Integer> mapColumnAndIndex;
	private String[][] dataRecords2D;
	private Project project;
	private MasterWorkbook masterWorkBook;
	
	
	public ProcessedFileDTO() {
	}

	public Map<String, Integer> getMapColumnAndIndex() {
		return mapColumnAndIndex;
	}

	public String[][] getDataRecords2D() {
		return dataRecords2D;
	}

	public Project getProject() {
		return project;
	}

	public void setMapColumnAndIndex(Map<String, Integer> mapColumnAndIndex) {
		this.mapColumnAndIndex = mapColumnAndIndex;
	}

	public void setDataRecords2D(String[][] dataRecords2D) {
		this.dataRecords2D = dataRecords2D;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProcessedFileDTO(Map<String, Integer> mapColumnAndIndex, String[][] dataRecords2D, Project project) {
		this.mapColumnAndIndex = mapColumnAndIndex;
		this.dataRecords2D = dataRecords2D;
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(dataRecords2D);
		result = prime * result + ((mapColumnAndIndex == null) ? 0 : mapColumnAndIndex.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
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
		ProcessedFileDTO other = (ProcessedFileDTO) obj;
		if (!Arrays.deepEquals(dataRecords2D, other.dataRecords2D))
			return false;
		if (mapColumnAndIndex == null) {
			if (other.mapColumnAndIndex != null)
				return false;
		} else if (!mapColumnAndIndex.equals(other.mapColumnAndIndex))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProcessedFileDTO [mapColumnAndIndex=" + mapColumnAndIndex + ", dataRecords2D="
				+ Arrays.toString(dataRecords2D) + ", project=" + project + "]";
	}

	public MasterWorkbook getMasterWorkBook() {
		return masterWorkBook;
	}

	public void setMasterWorkBook(MasterWorkbook masterWorkBook) {
		this.masterWorkBook = masterWorkBook;
	}

}
