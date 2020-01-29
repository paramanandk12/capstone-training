package com.mindtree.migrationaccelerator.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.dto.AdminDTO;
import com.mindtree.migrationaccelerator.dto.CountryDTO;
import com.mindtree.migrationaccelerator.dto.ProjectDTO;
import com.mindtree.migrationaccelerator.dto.SiteDTO;
import com.mindtree.migrationaccelerator.dto.SlotDTO;
import com.mindtree.migrationaccelerator.dto.UserDTO;
import com.mindtree.migrationaccelerator.dto.UserDataVerificationDTO;
import com.mindtree.migrationaccelerator.dto.UserSlotSelectionDTO;
import com.mindtree.migrationaccelerator.entity.Admin;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.Project;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.Slot;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.entity.UserDataVerification;
import com.mindtree.migrationaccelerator.entity.UserSlotSelection;

@Component
public class DTOToBeanConverter {

	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao iBaseDao;
	
	public Admin adminDTOToAdminEntity(AdminDTO adminDTO) {
		Admin admin = new Admin();
		admin.setEmailId(adminDTO.getEmail());
		admin.setPassword(adminDTO.getPwd());
		return admin;
	}

	public UserAccountDetails userDTOToUserEntity(UserDTO userDTO) {

		UserAccountDetails user = new UserAccountDetails();
		user.setLoginIdUPNasIs(userDTO.getEmail());
		return user;
	}

	@SuppressWarnings("unchecked")
	public Project projectDTOToProjectEntity(ProjectDTO projectDTO) {
		Project project = null;
		
		if(projectDTO.getId() == null) {
			project = new Project();
		} else {
			project = (Project) iBaseDao.getEntity(iBaseDao.getSession(), Project.class, projectDTO.getId());
		}
		
		project.setProjName(projectDTO.getName());
		project.setProjectManagerName(projectDTO.getManager());
		project.setProjectManagerEmail(projectDTO.getManagerEmail());
		project.setDescription(projectDTO.getDescription());
		return project;
	}
	
	public UserDataVerification userDataVerificationDTOToUserDataVerificationEntity(UserDataVerificationDTO userDataVerificationDTO ) {
		UserDataVerification userDataVerification = new UserDataVerification();
		userDataVerification.setExistingFirstName(userDataVerificationDTO.getFirstName());
		userDataVerification.setModifiedFirstName(userDataVerificationDTO.getFirstNameToBe());
		userDataVerification.setExistingLastName(userDataVerificationDTO.getLastName());
		userDataVerification.setModfiedLastName(userDataVerificationDTO.getLastNameToBe());
		userDataVerification.setExistingCountry(userDataVerificationDTO.getCountry());
		userDataVerification.setModfiedCountry(userDataVerificationDTO.getCountryToBe());
		userDataVerification.setExistingSite(userDataVerificationDTO.getSite());
		userDataVerification.setModfiedSite(userDataVerificationDTO.getSiteToBe());
		userDataVerification.setExistingSamAccountNameAsIs(userDataVerificationDTO.getSamAccountNameAsIs());
		userDataVerification.setModfiedSamAccountNameAsIs(userDataVerificationDTO.getSamAccountNameAsIsToBe());;
		
		return userDataVerification;
		
	}
	
	@SuppressWarnings("unchecked")
	public UserSlotSelection userSlotSelectionDTOToUserSlotSelectionEntity(UserSlotSelectionDTO userSlotSelectionDTO) {
		UserSlotSelection userSlotSelection = null;
		
		if(userSlotSelectionDTO.getUserSlotSelectionId() == null) {
			userSlotSelection = new UserSlotSelection();
		} else {
			userSlotSelection = (UserSlotSelection) iBaseDao.getEntity(iBaseDao.getSession(), UserSlotSelection.class, userSlotSelectionDTO.getUserSlotSelectionId());
		}
		
		if(userSlotSelectionDTO.getSlotDTO() != null)
			userSlotSelection.setSlot(slotDTOToSlotEntity(userSlotSelectionDTO.getSlotDTO()));
		
		userSlotSelection.setMigrationDate(userSlotSelectionDTO.getMigrationDate());
		userSlotSelection.setBookingCounter(userSlotSelectionDTO.getBookingCounter());
		userSlotSelection.setMigrationStatus(userSlotSelectionDTO.getMigrationStatus());
		userSlotSelection.setSlotStatus(userSlotSelectionDTO.getSlotStatus());
		return userSlotSelection;
	}

	@SuppressWarnings("unchecked")
	public Slot slotDTOToSlotEntity(SlotDTO slotDTO) {
		Slot slot = null; 
		
		if(slotDTO.getSlotId() == null) {
			slot = new Slot();
		} else {
			slot = (Slot) iBaseDao.getEntity(iBaseDao.getSession(), Slot.class, slotDTO.getSlotId());
		}
		
		if(slotDTO.getCountryDTO() != null)
			slot.setCountry(countryDTOToCountryEntity(slotDTO.getCountryDTO()));
		
		if(slotDTO.getProjectDTO() != null)
			slot.setProject(projectDTOToProjectEntity(slotDTO.getProjectDTO()));
		
		if(slotDTO.getSiteDTO() != null)
			slot.setSite(siteDTOToSiteEntity(slotDTO.getSiteDTO()));
		
		slot.setSlotName(slotDTO.getSlotName());
		
		if(slotDTO.getStartTime() != null)
			slot.setStartTime(slotDTO.getStartTime());
		
		if(slotDTO.getEndTime() != null)
			slot.setEndTime(slotDTO.getEndTime());
			
		if(slotDTO.getStartDate() != null)
			slot.setStartDate(slotDTO.getStartDate());
		
		if(slotDTO.getEndDate() != null)
			slot.setEndDate(slotDTO.getEndDate());
		
		slot.setUserCountCapacity(slotDTO.getUserCountCapacity());
		slot.setScheduleFreezDays(slotDTO.getScheduleFreezDays());
		slot.setUserCountOccupied(slotDTO.getUserCountOccupied());
		slot.setPlace1(slotDTO.getPlace1());
		slot.setPlace2(slotDTO.getPlace2());
		
		return slot;
	}
	
	@SuppressWarnings("unchecked")
	public Country countryDTOToCountryEntity(CountryDTO countryDTO) {
		Country country = null;
		
		if(countryDTO.getCountryId() == null) {
			country = new Country();
		} else {
			country = (Country) iBaseDao.getEntity(iBaseDao.getSession(), Country.class, countryDTO.getCountryId());
		}
		
		country.setCountryName(countryDTO.getCountryName());
		
		return country;
	}
	
	@SuppressWarnings("unchecked")
	public Site siteDTOToSiteEntity(SiteDTO siteDTO) {
		Site site = null;
		
		if(siteDTO.getSiteId() == null) {
			site = new Site();
		}else {
			site = (Site) iBaseDao.getEntity(iBaseDao.getSession(), Site.class, siteDTO.getSiteId());
		}
		
		site.setSiteName(siteDTO.getSiteName());
		return site;
	}
}