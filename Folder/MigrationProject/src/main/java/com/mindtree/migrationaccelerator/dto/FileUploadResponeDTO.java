package com.mindtree.migrationaccelerator.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.mindtree.migrationaccelerator.enums.ButtonAction;
import com.mindtree.migrationaccelerator.enums.RecordsStatus;

public class FileUploadResponeDTO {
	private int totalRecordsCount;
	private int existingRecordsCount;
	private int newRecordsCount;
	private String eventMessage;
	private boolean eventStatus;
	private RecordsStatus RecordsStatus;
	private ButtonAction currentButtonAction;
	private String[][] dataRecords2dForOverwriting;
	private String[][] dataRecords2dForAppending;
	private Map<String, Integer> columnIndexmap;
	private Collection<String> similarRecordsList;
	private Collection<String> differentRecordsList;
	
	public FileUploadResponeDTO() {
	}

	public int getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public int getExistingRecordsCount() {
		return existingRecordsCount;
	}

	public int getNewRecordsCount() {
		return newRecordsCount;
	}

	public String getEventMessage() {
		return eventMessage;
	}

	public boolean isEventStatus() {
		return eventStatus;
	}

	public RecordsStatus getRecordsStatus() {
		return RecordsStatus;
	}

	public String[][] getDataRecords2dForOverwriting() {
		return dataRecords2dForOverwriting;
	}

	public String[][] getDataRecords2dForAppending() {
		return dataRecords2dForAppending;
	}

	public Map<String, Integer> getColumnIndexmap() {
		return columnIndexmap;
	}

	public Collection<String> getSimilarRecordsList() {
		return similarRecordsList;
	}

	public Collection<String> getDifferentRecordsList() {
		return differentRecordsList;
	}

	public void setTotalRecordsCount(int totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public void setExistingRecordsCount(int existingRecordsCount) {
		this.existingRecordsCount = existingRecordsCount;
	}

	public void setNewRecordsCount(int newRecordsCount) {
		this.newRecordsCount = newRecordsCount;
	}

	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

	public void setEventStatus(boolean eventStatus) {
		this.eventStatus = eventStatus;
	}

	public void setRecordsStatus(RecordsStatus recordsStatus) {
		RecordsStatus = recordsStatus;
	}

	public void setDataRecords2dForOverwriting(String[][] dataRecords2dForOverwriting) {
		this.dataRecords2dForOverwriting = dataRecords2dForOverwriting;
	}

	public void setDataRecords2dForAppending(String[][] dataRecords2dForAppending) {
		this.dataRecords2dForAppending = dataRecords2dForAppending;
	}

	public void setColumnIndexmap(Map<String, Integer> columnIndexmap) {
		this.columnIndexmap = columnIndexmap;
	}

	public void setSimilarRecordsList(Collection<String> similarRecordsList) {
		this.similarRecordsList = similarRecordsList;
	}

	public void setDifferentRecordsList(Collection<String> differentRecordsList) {
		this.differentRecordsList = differentRecordsList;
	}

	public FileUploadResponeDTO(int totalRecordsCount, int existingRecordsCount, int newRecordsCount,
			String eventMessage, boolean eventStatus,
			com.mindtree.migrationaccelerator.enums.RecordsStatus recordsStatus, String[][] dataRecords2dForOverwriting,
			String[][] dataRecords2dForAppending, Map<String, Integer> columnIndexmap,
			Collection<String> similarRecordsList, Collection<String> differentRecordsList) {
		this.totalRecordsCount = totalRecordsCount;
		this.existingRecordsCount = existingRecordsCount;
		this.newRecordsCount = newRecordsCount;
		this.eventMessage = eventMessage;
		this.eventStatus = eventStatus;
		RecordsStatus = recordsStatus;
		this.dataRecords2dForOverwriting = dataRecords2dForOverwriting;
		this.dataRecords2dForAppending = dataRecords2dForAppending;
		this.columnIndexmap = columnIndexmap;
		this.similarRecordsList = similarRecordsList;
		this.differentRecordsList = differentRecordsList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RecordsStatus == null) ? 0 : RecordsStatus.hashCode());
		result = prime * result + ((columnIndexmap == null) ? 0 : columnIndexmap.hashCode());
		result = prime * result + Arrays.deepHashCode(dataRecords2dForAppending);
		result = prime * result + Arrays.deepHashCode(dataRecords2dForOverwriting);
		result = prime * result + ((differentRecordsList == null) ? 0 : differentRecordsList.hashCode());
		result = prime * result + ((eventMessage == null) ? 0 : eventMessage.hashCode());
		result = prime * result + (eventStatus ? 1231 : 1237);
		result = prime * result + existingRecordsCount;
		result = prime * result + newRecordsCount;
		result = prime * result + ((similarRecordsList == null) ? 0 : similarRecordsList.hashCode());
		result = prime * result + totalRecordsCount;
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
		FileUploadResponeDTO other = (FileUploadResponeDTO) obj;
		if (RecordsStatus != other.RecordsStatus)
			return false;
		if (columnIndexmap == null) {
			if (other.columnIndexmap != null)
				return false;
		} else if (!columnIndexmap.equals(other.columnIndexmap))
			return false;
		if (!Arrays.deepEquals(dataRecords2dForAppending, other.dataRecords2dForAppending))
			return false;
		if (!Arrays.deepEquals(dataRecords2dForOverwriting, other.dataRecords2dForOverwriting))
			return false;
		if (differentRecordsList == null) {
			if (other.differentRecordsList != null)
				return false;
		} else if (!differentRecordsList.equals(other.differentRecordsList))
			return false;
		if (eventMessage == null) {
			if (other.eventMessage != null)
				return false;
		} else if (!eventMessage.equals(other.eventMessage))
			return false;
		if (eventStatus != other.eventStatus)
			return false;
		if (existingRecordsCount != other.existingRecordsCount)
			return false;
		if (newRecordsCount != other.newRecordsCount)
			return false;
		if (similarRecordsList == null) {
			if (other.similarRecordsList != null)
				return false;
		} else if (!similarRecordsList.equals(other.similarRecordsList))
			return false;
		if (totalRecordsCount != other.totalRecordsCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileUploadResponeDTO [totalRecordsCount=" + totalRecordsCount + ", existingRecordsCount="
				+ existingRecordsCount + ", newRecordsCount=" + newRecordsCount + ", eventMessage=" + eventMessage
				+ ", eventStatus=" + eventStatus + ", RecordsStatus=" + RecordsStatus + ", dataRecords2dForOverwriting="
				+ Arrays.toString(dataRecords2dForOverwriting) + ", dataRecords2dForAppending="
				+ Arrays.toString(dataRecords2dForAppending) + ", columnIndexmap=" + columnIndexmap
				+ ", similarRecordsList=" + similarRecordsList + ", differentRecordsList=" + differentRecordsList + "]";
	}

	public ButtonAction getCurrentButtonAction() {
		return currentButtonAction;
	}

	public void setCurrentButtonAction(ButtonAction currentButtonAction) {
		this.currentButtonAction = currentButtonAction;
	}

	

	
}