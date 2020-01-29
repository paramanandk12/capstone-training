package com.mindtree.migrationaccelerator.util;

import org.springframework.stereotype.Component;

import com.mindtree.migrationaccelerator.dto.CountryDTO;
import com.mindtree.migrationaccelerator.dto.MastersheetDataDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.dto.SiteDTO;
import com.mindtree.migrationaccelerator.dto.SlotDTO;
import com.mindtree.migrationaccelerator.dto.SlotUsersMigrationDetailsDTO;
import com.mindtree.migrationaccelerator.dto.UserSlotSelectionDTO;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.entity.SlotUsersMigrationDetails;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;

@Component
public class BeanToDTOConverter {

	public ProjectDTO projectEntityToDTO(Project project) {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setId(project.getProjectId());
		projectDTO.setName(project.getProjName());
		projectDTO.setManager(project.getProjectManagerName());
		projectDTO.setManagerEmail(project.getProjectManagerEmail());
		projectDTO.setDescription(project.getDescription());

		return projectDTO;
	}

	public MastersheetDataDTO masterSheetDataEntityToMasterSheetDataDTO(MasterSheetData masterSheetDataUser) {
		MastersheetDataDTO mastersheetDataDTO = new MastersheetDataDTO();
		mastersheetDataDTO.setId(masterSheetDataUser.getMasterSheetRecordsId());
		mastersheetDataDTO.setFirstName(masterSheetDataUser.getFirstName());
		mastersheetDataDTO.setLastName(masterSheetDataUser.getLastName());
		mastersheetDataDTO.setCountry(masterSheetDataUser.getCountry());
		mastersheetDataDTO.setSite(masterSheetDataUser.getSite());
		mastersheetDataDTO.setSamAccountNameAsIs(masterSheetDataUser.getSamAccountNameAsIs());
		mastersheetDataDTO.setLogIdAsIs(masterSheetDataUser.getLoginIdUPNasIs());
		
		if(masterSheetDataUser.getProject() != null)
			mastersheetDataDTO.setProjectDTO(projectEntityToDTO(masterSheetDataUser.getProject()));
		
		return mastersheetDataDTO;
	}
	
	public SiteDTO siteEntityToDTO(Site site) {
		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setSiteId(site.getSiteId());
		siteDTO.setSiteName(site.getSiteName());
		siteDTO.setProjectDTO(projectEntityToDTO(site.getProject()));
		siteDTO.setCountryDTO(countryEntityToDTO(site.getCountry()));
		return siteDTO;
	}
	
	public UserSlotSelectionDTO userSlotSelectionEntityToDTO(UserSlotSelection userSlotSelection) {
		UserSlotSelectionDTO userSlotSelectionDTO = new UserSlotSelectionDTO();
		userSlotSelectionDTO.setUserSlotSelectionId(userSlotSelection.getUserSlotSelectionId());
		userSlotSelectionDTO.setBookingCounter(userSlotSelection.getBookingCounter());
		userSlotSelectionDTO.setMigrationStatus(userSlotSelection.getMigrationStatus());
		userSlotSelectionDTO.setMigrationDate(userSlotSelection.getMigrationDate());
		userSlotSelectionDTO.setSlotStatus(userSlotSelection.getSlotStatus());
		
		if(userSlotSelection.getSlot() != null)
			userSlotSelectionDTO.setSlotDTO(slotEntityToDTO(userSlotSelection.getSlot()));
		
		if(userSlotSelection.getMasterSheetData() != null) {
			userSlotSelectionDTO.setMastersheetDataDTO(masterSheetDataEntityToMasterSheetDataDTO(userSlotSelection.getMasterSheetData()));
		}
		
		if(userSlotSelection.getMasterSheetData() != null && userSlotSelection.getMasterSheetData().getMasterWorkbook() != null && userSlotSelection.getMasterSheetData().getMasterWorkbook().getProject() != null) {
			userSlotSelectionDTO.setProjectDTO(projectEntityToDTO(userSlotSelection.getMasterSheetData().getMasterWorkbook().getProject()));
		}
			
		return userSlotSelectionDTO;
	}
	
	
	public SlotDTO slotEntityToDTO(Slot slot) {
		SlotDTO slotDTO = new SlotDTO();
		slotDTO.setSlotId(slot.getSlotId());
		slotDTO.setSlotName(slot.getSlotName());
		slotDTO.setStartTime(slot.getStartTime());
		slotDTO.setEndTime(slot.getEndTime());
		slotDTO.setStartDate(slot.getStartDate());
		slotDTO.setEndDate(slot.getEndDate());
		slotDTO.setTimezone(slot.getTimeZone());
		slotDTO.setSlotDesc(slot.getSlotName() + " " + slot.getStartTime() + " - " + slot.getEndTime());// To be display meaning fule name on UI
		
		if(slot.getUserCountCapacity() == null && slot.getUserCountCapacity() <= 0)
			slotDTO.setUserCountCapacity(0);
		else
			slotDTO.setUserCountCapacity(slot.getUserCountCapacity());
		
		if(slot.getUserCountOccupied() == null && slot.getUserCountOccupied() <= 0)
			slotDTO.setUserCountOccupied(0);
		else
			slotDTO.setUserCountOccupied(slot.getUserCountOccupied());
		
		if(slot.getScheduleFreezDays() == null && slot.getScheduleFreezDays() <= 0)
			slotDTO.setScheduleFreezDays(0);
		else
			slotDTO.setScheduleFreezDays(slot.getScheduleFreezDays());
		
		slotDTO.setSlotStatus(slot.getSlotStatus());
		slotDTO.setPlace1(slot.getPlace1());
		slotDTO.setPlace2(slot.getPlace2());
		
		if(slot.getCountry() != null)
			slotDTO.setCountryDTO(countryEntityToDTO(slot.getCountry()));
		
		if(slot.getSite() != null)
			slotDTO.setSiteDTO(siteEntityToDTO(slot.getSite()));
		
		if(slot.getProject() != null)
			slotDTO.setProjectDTO(projectEntityToDTO(slot.getProject()));
		
		if(slot.getSlotUsersMigrationDetails() != null)
			slotDTO.setSlotUserMigrationDetailsDTO(slotUsersMigrationDetailsToDTO(slot.getSlotUsersMigrationDetails()));
		
		return slotDTO;
	}

	public CountryDTO countryEntityToDTO(Country country) {
		CountryDTO dto = new CountryDTO();
		dto.setCountryId(country.getCountryId());
		dto.setCountryName(country.getCountryName());
		
		if(country.getSite() != null)
			dto.setAvailableSitesCount(country.getSite().size());
		
		if(country.getProject() != null)
			dto.setProjectDTO(projectEntityToDTO(country.getProject()));
		
		return dto;
	}
	
	public SlotUsersMigrationDetailsDTO slotUsersMigrationDetailsToDTO(SlotUsersMigrationDetails slotUserMigrationDetails) {
		SlotUsersMigrationDetailsDTO slotUserMigrationDetailsDTO = new SlotUsersMigrationDetailsDTO();
		slotUserMigrationDetailsDTO.setMigrationStatus(slotUserMigrationDetails.getMigrationStatus());
		slotUserMigrationDetailsDTO.setSlotMigrationFailureCount(slotUserMigrationDetails.getSlotMigrationFailureCount());
		slotUserMigrationDetailsDTO.setSlotMigrationSuccessCount(slotUserMigrationDetails.getSlotMigrationSuccessCount());
		slotUserMigrationDetailsDTO.setSlotUserMigrationDetailsID(slotUserMigrationDetails.getSlotUserMigrationDetailsID());

		if(slotUserMigrationDetails.getMigrationSuccessDate() != null)
			slotUserMigrationDetailsDTO.setMigrationSuccessDate(slotUserMigrationDetails.getMigrationSuccessDate());
		
		return slotUserMigrationDetailsDTO;
	}
}