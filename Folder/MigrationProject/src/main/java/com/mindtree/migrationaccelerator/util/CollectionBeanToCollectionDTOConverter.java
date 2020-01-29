package com.mindtree.migrationaccelerator.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mindtree.migrationaccelerator.dto.MasterWorkbookDTO;
import com.mindtree.migrationaccelerator.dto.MastersheetDataDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.MasterWorkbook;
import com.mindtree.migrationaccelerator.entity.Project;

@Component
public class CollectionBeanToCollectionDTOConverter {

	public List<ProjectDTO> getProjectDTOList(List<Project> projectlist) {
		List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
		for (Project project : projectlist) {
			ProjectDTO projDTO = new ProjectDTO();
			projDTO.setId(project.getProjectId());
			projDTO.setName(project.getProjName());
			projDTO.setManager(project.getProjectManagerName());
			projDTO.setManagerEmail(project.getProjectManagerEmail());
			projDTO.setDescription(project.getDescription());
			projDTO.setCreatedOn(project.getCreatedOn());
			projDTO.setCreatedBy(project.getCreatedBy());
			
			if(project.getLastUpdated() != null)
				projDTO.setLastUpdated(project.getLastUpdated());
			
			projDTO.setUpdatedBy(project.getUpdatedBy());
			projDTO.setStatus(project.getProjectStatus());
			projectDTOList.add(projDTO);
		}
		return projectDTOList;
	}

	public List<MasterWorkbookDTO> getMasterWorkbookDTOList(List<MasterWorkbook> masterWorkbook) {
		List<MasterWorkbookDTO> masterWorkbookDTODTOList = new ArrayList<MasterWorkbookDTO>();
		for (MasterWorkbook workbookList : masterWorkbook) {
			MasterWorkbookDTO workbookDTO = new MasterWorkbookDTO();
			workbookDTO.setName(workbookList.getName());
			workbookDTO.setSize(workbookList.getSize());
			workbookDTO.setFormat(workbookList.getFormat());
			workbookDTO.setCreatedOn(workbookList.getCreatedOn());
			workbookDTO.setCreatedBy(workbookList.getCreatedBy());
			workbookDTO.setMastersheetDataDTO(listMastersheetDataDTO(workbookList.getMasterSheetData()));
			masterWorkbookDTODTOList.add(workbookDTO);

		}
		return masterWorkbookDTODTOList;
	}
	
	public List<MastersheetDataDTO> listMastersheetDataDTO(List<MasterSheetData> masterSheetData) {
		 List<MastersheetDataDTO> listMastersheetDataDTO=new ArrayList<>();
		for(MasterSheetData sheetData:masterSheetData){
			MastersheetDataDTO mastersheetDataDTO=new MastersheetDataDTO();
			
			mastersheetDataDTO.setId(sheetData.getMasterSheetRecordsId());
			mastersheetDataDTO.setRegion(sheetData.getRegion());
			mastersheetDataDTO.setCountry(sheetData.getCountry());
			mastersheetDataDTO.setSite(sheetData.getSite());
			mastersheetDataDTO.setLocation(sheetData.getLocation());
			
			mastersheetDataDTO.setUserDisplayName(sheetData.getUserDisplayName());
			mastersheetDataDTO.setFirstName(sheetData.getFirstName());
			mastersheetDataDTO.setLastName(sheetData.getLastName());
			mastersheetDataDTO.setLogIdAsIs(sheetData.getLoginIdUPNasIs());
			mastersheetDataDTO.setSamAccountNameAsIs(sheetData.getSamAccountNameAsIs());
			mastersheetDataDTO.setAdDomain(sheetData.getAdDomain());
			mastersheetDataDTO.setOrganizationalUnit(sheetData.getOrganizationalUnit());
			mastersheetDataDTO.setUserDisplayNameToBe(sheetData.getUserDisplayNameToBe());
			mastersheetDataDTO.setLoginIdToBe(sheetData.getLoginIdUPNtoBe());
			mastersheetDataDTO.setSamAccountToBe(sheetData.getSamAccountNameToBe());
			mastersheetDataDTO.setO365LicenseToBe(sheetData.getO365LicenseToBe());
			mastersheetDataDTO.setPrimaryEmailAsIs(sheetData.getPrimaryEmailAddressAsIs());
			mastersheetDataDTO.setAliasEmailAsIs(sheetData.getAliasEmailAddress());
			mastersheetDataDTO.setRecipentType(sheetData.getRecipientType());
			mastersheetDataDTO.setMailboxSize(sheetData.getMailboxSize());
			mastersheetDataDTO.setPrimaryEmailToBe(sheetData.getPrimaryEmailAddressToBe());
			mastersheetDataDTO.setAliasEmailToBe(sheetData.getAliasEmailAddressToBe());
			mastersheetDataDTO.setSharedAccess(sheetData.getSharedAccess());
			mastersheetDataDTO.setCalenderDelegates(sheetData.getCalendarAccessDelegates());
			mastersheetDataDTO.setMessagingServer(sheetData.getMessagingServer());
			mastersheetDataDTO.setMessagingDatabase(sheetData.getMessagingDatabse());
			mastersheetDataDTO.setCorporateMobileDevice(sheetData.getCorporateMobileDevices());
			mastersheetDataDTO.setUserSkypeLyncAsIs(sheetData.getUserSkypeLyncIdAsIs());
			mastersheetDataDTO.setUserSkypeLyncToBe(sheetData.getUserSkypeLyncIdToBe());
			mastersheetDataDTO.setSkypeLyncPool(sheetData.getSkypeLyncPool());
			
			mastersheetDataDTO.setComputerName(sheetData.getComputerName());
			mastersheetDataDTO.setIsLaptopDesktop(sheetData.getIsLaptopDesktop());
			mastersheetDataDTO.setIsSCCMInstalled(sheetData.getIsSCCMInstalledActiveInactive());
			mastersheetDataDTO.setComputermodel(sheetData.getComputerModel());
			mastersheetDataDTO.setOperatingSystem(sheetData.getOperatingSystem());
			mastersheetDataDTO.setOperatingSystemVersion(sheetData.getOperatingSystemVersion());
			mastersheetDataDTO.setIsHardwareCompatableWin10(sheetData.getIsHardwareCompatibleWithW10());
			mastersheetDataDTO.setFreeDiskSpace(sheetData.getFreeDiskSpaceMB());
			mastersheetDataDTO.setUpdateW10HardwareReplacement(sheetData.getIs_UpdateToWin10HardwareReplacement());
			mastersheetDataDTO.setSpecifyABC(sheetData.getSpecifyABC());
			mastersheetDataDTO.setLocalApplication(sheetData.getLocalApplications());
			mastersheetDataDTO.setUserHomeDrive(sheetData.getHomeDriveDataSize());
			mastersheetDataDTO.setComments(sheetData.getComments());
			mastersheetDataDTO.setMigrationStatus(sheetData.getMigrationStatus());
			
			if(sheetData.getProject() != null)
				mastersheetDataDTO.setProjectDTO(new BeanToDTOConverter().projectEntityToDTO(sheetData.getProject()));
			
			listMastersheetDataDTO.add(mastersheetDataDTO);
		}
		return listMastersheetDataDTO;
		
	}
}
