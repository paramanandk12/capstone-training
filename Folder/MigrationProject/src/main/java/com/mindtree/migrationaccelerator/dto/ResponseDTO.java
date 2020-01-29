package com.mindtree.migrationaccelerator.dto;

public class ResponseDTO {

	AllEntitiesDTO allEntities;
	boolean isProcessed;
	
	public ResponseDTO(AllEntitiesDTO allEntitiesDTO, boolean isProcessed) {
		this.allEntities = allEntitiesDTO;
		this.isProcessed = isProcessed;
	}
	
	
	public ResponseDTO() {
	}


	public AllEntitiesDTO getAllEntities() {
		return allEntities;
	}
	public void setAllEntities(AllEntitiesDTO allEntities) {
		this.allEntities = allEntities;
	}
	public boolean isProcessed() {
		return isProcessed;
	}
	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	
}
