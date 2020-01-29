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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mindtree.migrationaccelerator.enums.MigrationStatus;

@Entity
@Table(name = "mastersheet_sheet_data")
public class MasterSheetData extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mastersheet_data_id", unique = true, nullable = false)
	private Integer masterSheetRecordsId;

	@Column(name = "region")
	private String Region;

	@Column(name = "country", nullable = false)
	private String Country;

	@Column(name = "site", nullable = false)
	private String Site;

	@Column(name = "location")
	private String Location;

	@Column(name = "user_display_name")
	private String userDisplayName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "login_id_upn_as_is", nullable = false)
	private String loginIdUPNasIs;

	@Column(name = "sam_account_name_as_is")
	private String samAccountNameAsIs;

	@Column(name = "ad_domain")
	private String adDomain;

	@Column(name = "organizational_unit")
	private String organizationalUnit;

	@Column(name = "user_display_name_to_be")
	private String userDisplayNameToBe;

	@Column(name = "login_id_upn_to_be")
	private String loginIdUPNtoBe;

	@Column(name = "sam_account_name_to_be")
	private String samAccountNameToBe;

	@Column(name = "o365_license_to_be")
	private String o365LicenseToBe;

	@Column(name = "primary_email_address_as_is")
	private String primaryEmailAddressAsIs;

	@Column(name = "alias_email_address")
	private String aliasEmailAddress;

	@Column(name = "recipient_type")
	private String recipientType;

	@Column(name = "mailbox_size")
	private String mailboxSize;

	@Column(name = "primary_email_address_to_be")
	private String primaryEmailAddressToBe;

	@Column(name = "alias_email_address_to_be")
	private String aliasEmailAddressToBe;

	@Column(name = "shared_access")
	private String sharedAccess;

	@Column(name = "calendar_access_delegates")
	private String calendarAccessDelegates;

	@Column(name = "messaging_server")
	private String messagingServer;

	@Column(name = "messaging_databse")
	private String messagingDatabse;

	@Column(name = "corporate_mobile_devices")
	private String CorporateMobileDevices;

	@Column(name = "user_skype_lync_id_as_is")
	private String userSkypeLyncIdAsIs;

	@Column(name = "user_skype_lync_id_to_be")
	private String userSkypeLyncIdToBe;

	@Column(name = "skype_lync_pool")
	private String skypeLyncPool;

	@Column(name = "computer_name")
	private String computerName;

	@Column(name = "is_Laptop_Desktop")
	private String isLaptopDesktop;

	@Column(name = "is_sccm_installed_active_inactive")
	private String isSCCMInstalledActiveInactive;

	@Column(name = "computer_model")
	private String computerModel;

	@Column(name = "operating_system")
	private String operatingSystem;

	@Column(name = "operating_system_version")
	private String operatingSystemVersion;

	@Column(name = "is_hardware_compatible_with_w10")
	private String isHardwareCompatibleWithW10;

	@Column(name = "free_disk_space_mb")
	private String freeDiskSpaceMB;

	@Column(name = "is_update_to_win10_hardware_replacement")
	private String is_UpdateToWin10HardwareReplacement;

	@Column(name = "specify_abc")
	private String specifyABC;

	@Column(name = "local_applications")
	private String localApplications;

	@Column(name = "home_drive_data_size")
	private String homeDriveDataSize;

	@Column(name = "comments")
	private String comments;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@Column(name = "migration_status")
	private MigrationStatus migrationStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "master_workbook_id")
	private MasterWorkbook masterWorkbook;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToOne(mappedBy="masterSheetData", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private UserEmailDetails userEmailAddress;
	
	@OneToOne(mappedBy="masterSheetData", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private UserLocationDetails userLocationDetails;
	
	@OneToOne(mappedBy="masterSheetData", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private UserOtherDetails userOtherDetails;
	
	@OneToOne(mappedBy="masterSheetData", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private UserSkypeDetails userSkypeDetails ;
	
	@OneToOne(mappedBy="masterSheetData", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private UserWorkstationDetails userWorkstationDetails;
	
	@OneToOne(mappedBy="masterSheetData", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private UserAccountDetails userAccountDetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "masterSheetData",  fetch = FetchType.EAGER)
	private List<UserSlotSelection> userSlotSelectionList;

	public MasterSheetData() {}

	public MasterSheetData(Integer masterSheetRecordsId, String region, String country, String site, String location,
			String userDisplayName, String firstName, String lastName, String loginIdUPNasIs, String samAccountNameAsIs,
			String adDomain, String organizationalUnit, String userDisplayNameToBe, String loginIdUPNtoBe,
			String samAccountNameToBe, String o365LicenseToBe, String primaryEmailAddressAsIs, String aliasEmailAddress,
			String recipientType, String mailboxSize, String primaryEmailAddressToBe, String aliasEmailAddressToBe,
			String sharedAccess, String calendarAccessDelegates, String messagingServer, String messagingDatabse,
			String corporateMobileDevices, String userSkypeLyncIdAsIs, String userSkypeLyncIdToBe, String skypeLyncPool,
			String computerName, String isLaptopDesktop, String isSCCMInstalledActiveInactive, String computerModel,
			String operatingSystem, String operatingSystemVersion, String isHardwareCompatibleWithW10,
			String freeDiskSpaceMB, String is_UpdateToWin10HardwareReplacement, String specifyABC,
			String localApplications, String homeDriveDataSize, String comments, String place1, String place2,
			MigrationStatus migrationStatus, MasterWorkbook masterWorkbook,Project project, UserEmailDetails userEmailAddress,
			UserLocationDetails userLocationDetails, UserOtherDetails userOtherDetails,
			UserSkypeDetails userSkypeDetails, UserWorkstationDetails userWorkstationDetails,
			UserAccountDetails userAccountDetails, List<UserSlotSelection> userSlotSelectionList) {
		super();
		this.masterSheetRecordsId = masterSheetRecordsId;
		Region = region;
		Country = country;
		Site = site;
		Location = location;
		this.userDisplayName = userDisplayName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginIdUPNasIs = loginIdUPNasIs;
		this.samAccountNameAsIs = samAccountNameAsIs;
		this.adDomain = adDomain;
		this.organizationalUnit = organizationalUnit;
		this.userDisplayNameToBe = userDisplayNameToBe;
		this.loginIdUPNtoBe = loginIdUPNtoBe;
		this.samAccountNameToBe = samAccountNameToBe;
		this.o365LicenseToBe = o365LicenseToBe;
		this.primaryEmailAddressAsIs = primaryEmailAddressAsIs;
		this.aliasEmailAddress = aliasEmailAddress;
		this.recipientType = recipientType;
		this.mailboxSize = mailboxSize;
		this.primaryEmailAddressToBe = primaryEmailAddressToBe;
		this.aliasEmailAddressToBe = aliasEmailAddressToBe;
		this.sharedAccess = sharedAccess;
		this.calendarAccessDelegates = calendarAccessDelegates;
		this.messagingServer = messagingServer;
		this.messagingDatabse = messagingDatabse;
		CorporateMobileDevices = corporateMobileDevices;
		this.userSkypeLyncIdAsIs = userSkypeLyncIdAsIs;
		this.userSkypeLyncIdToBe = userSkypeLyncIdToBe;
		this.skypeLyncPool = skypeLyncPool;
		this.computerName = computerName;
		this.isLaptopDesktop = isLaptopDesktop;
		this.isSCCMInstalledActiveInactive = isSCCMInstalledActiveInactive;
		this.computerModel = computerModel;
		this.operatingSystem = operatingSystem;
		this.operatingSystemVersion = operatingSystemVersion;
		this.isHardwareCompatibleWithW10 = isHardwareCompatibleWithW10;
		this.freeDiskSpaceMB = freeDiskSpaceMB;
		this.is_UpdateToWin10HardwareReplacement = is_UpdateToWin10HardwareReplacement;
		this.specifyABC = specifyABC;
		this.localApplications = localApplications;
		this.homeDriveDataSize = homeDriveDataSize;
		this.comments = comments;
		this.place1 = place1;
		this.place2 = place2;
		this.migrationStatus = migrationStatus;
		this.masterWorkbook = masterWorkbook;
		this.project = project;
		this.userEmailAddress = userEmailAddress;
		this.userLocationDetails = userLocationDetails;
		this.userOtherDetails = userOtherDetails;
		this.userSkypeDetails = userSkypeDetails;
		this.userWorkstationDetails = userWorkstationDetails;
		this.userAccountDetails = userAccountDetails;
		this.userSlotSelectionList = userSlotSelectionList;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getMasterSheetRecordsId() {
		return masterSheetRecordsId;
	}

	public void setMasterSheetRecordsId(Integer masterSheetRecordsId) {
		this.masterSheetRecordsId = masterSheetRecordsId;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginIdUPNasIs() {
		return loginIdUPNasIs;
	}

	public void setLoginIdUPNasIs(String loginIdUPNasIs) {
		this.loginIdUPNasIs = loginIdUPNasIs;
	}

	public String getSamAccountNameAsIs() {
		return samAccountNameAsIs;
	}

	public void setSamAccountNameAsIs(String samAccountNameAsIs) {
		this.samAccountNameAsIs = samAccountNameAsIs;
	}

	public String getAdDomain() {
		return adDomain;
	}

	public void setAdDomain(String adDomain) {
		this.adDomain = adDomain;
	}

	public String getOrganizationalUnit() {
		return organizationalUnit;
	}

	public void setOrganizationalUnit(String organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
	}

	public String getUserDisplayNameToBe() {
		return userDisplayNameToBe;
	}

	public void setUserDisplayNameToBe(String userDisplayNameToBe) {
		this.userDisplayNameToBe = userDisplayNameToBe;
	}

	public String getLoginIdUPNtoBe() {
		return loginIdUPNtoBe;
	}

	public void setLoginIdUPNtoBe(String loginIdUPNtoBe) {
		this.loginIdUPNtoBe = loginIdUPNtoBe;
	}

	public String getSamAccountNameToBe() {
		return samAccountNameToBe;
	}

	public void setSamAccountNameToBe(String samAccountNameToBe) {
		this.samAccountNameToBe = samAccountNameToBe;
	}

	public String getO365LicenseToBe() {
		return o365LicenseToBe;
	}

	public void setO365LicenseToBe(String o365LicenseToBe) {
		this.o365LicenseToBe = o365LicenseToBe;
	}

	public String getPrimaryEmailAddressAsIs() {
		return primaryEmailAddressAsIs;
	}

	public void setPrimaryEmailAddressAsIs(String primaryEmailAddressAsIs) {
		this.primaryEmailAddressAsIs = primaryEmailAddressAsIs;
	}

	public String getAliasEmailAddress() {
		return aliasEmailAddress;
	}

	public void setAliasEmailAddress(String aliasEmailAddress) {
		this.aliasEmailAddress = aliasEmailAddress;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public String getMailboxSize() {
		return mailboxSize;
	}

	public void setMailboxSize(String mailboxSize) {
		this.mailboxSize = mailboxSize;
	}

	public String getPrimaryEmailAddressToBe() {
		return primaryEmailAddressToBe;
	}

	public void setPrimaryEmailAddressToBe(String primaryEmailAddressToBe) {
		this.primaryEmailAddressToBe = primaryEmailAddressToBe;
	}

	public String getAliasEmailAddressToBe() {
		return aliasEmailAddressToBe;
	}

	public void setAliasEmailAddressToBe(String aliasEmailAddressToBe) {
		this.aliasEmailAddressToBe = aliasEmailAddressToBe;
	}

	public String getSharedAccess() {
		return sharedAccess;
	}

	public void setSharedAccess(String sharedAccess) {
		this.sharedAccess = sharedAccess;
	}

	public String getCalendarAccessDelegates() {
		return calendarAccessDelegates;
	}

	public void setCalendarAccessDelegates(String calendarAccessDelegates) {
		this.calendarAccessDelegates = calendarAccessDelegates;
	}

	public String getMessagingServer() {
		return messagingServer;
	}

	public void setMessagingServer(String messagingServer) {
		this.messagingServer = messagingServer;
	}

	public String getMessagingDatabse() {
		return messagingDatabse;
	}

	public void setMessagingDatabse(String messagingDatabse) {
		this.messagingDatabse = messagingDatabse;
	}

	public String getCorporateMobileDevices() {
		return CorporateMobileDevices;
	}

	public void setCorporateMobileDevices(String corporateMobileDevices) {
		CorporateMobileDevices = corporateMobileDevices;
	}

	public String getUserSkypeLyncIdAsIs() {
		return userSkypeLyncIdAsIs;
	}

	public void setUserSkypeLyncIdAsIs(String userSkypeLyncIdAsIs) {
		this.userSkypeLyncIdAsIs = userSkypeLyncIdAsIs;
	}

	public String getUserSkypeLyncIdToBe() {
		return userSkypeLyncIdToBe;
	}

	public void setUserSkypeLyncIdToBe(String userSkypeLyncIdToBe) {
		this.userSkypeLyncIdToBe = userSkypeLyncIdToBe;
	}

	public String getSkypeLyncPool() {
		return skypeLyncPool;
	}

	public void setSkypeLyncPool(String skypeLyncPool) {
		this.skypeLyncPool = skypeLyncPool;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getIsLaptopDesktop() {
		return isLaptopDesktop;
	}

	public void setIsLaptopDesktop(String isLaptopDesktop) {
		this.isLaptopDesktop = isLaptopDesktop;
	}

	public String getIsSCCMInstalledActiveInactive() {
		return isSCCMInstalledActiveInactive;
	}

	public void setIsSCCMInstalledActiveInactive(String isSCCMInstalledActiveInactive) {
		this.isSCCMInstalledActiveInactive = isSCCMInstalledActiveInactive;
	}

	public String getComputerModel() {
		return computerModel;
	}

	public void setComputerModel(String computerModel) {
		this.computerModel = computerModel;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getOperatingSystemVersion() {
		return operatingSystemVersion;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		this.operatingSystemVersion = operatingSystemVersion;
	}

	public String getIsHardwareCompatibleWithW10() {
		return isHardwareCompatibleWithW10;
	}

	public void setIsHardwareCompatibleWithW10(String isHardwareCompatibleWithW10) {
		this.isHardwareCompatibleWithW10 = isHardwareCompatibleWithW10;
	}

	public String getFreeDiskSpaceMB() {
		return freeDiskSpaceMB;
	}

	public void setFreeDiskSpaceMB(String freeDiskSpaceMB) {
		this.freeDiskSpaceMB = freeDiskSpaceMB;
	}

	public String getIs_UpdateToWin10HardwareReplacement() {
		return is_UpdateToWin10HardwareReplacement;
	}

	public void setIs_UpdateToWin10HardwareReplacement(String is_UpdateToWin10HardwareReplacement) {
		this.is_UpdateToWin10HardwareReplacement = is_UpdateToWin10HardwareReplacement;
	}

	public String getSpecifyABC() {
		return specifyABC;
	}

	public void setSpecifyABC(String specifyABC) {
		this.specifyABC = specifyABC;
	}

	public String getLocalApplications() {
		return localApplications;
	}

	public void setLocalApplications(String localApplications) {
		this.localApplications = localApplications;
	}

	public String getHomeDriveDataSize() {
		return homeDriveDataSize;
	}

	public void setHomeDriveDataSize(String homeDriveDataSize) {
		this.homeDriveDataSize = homeDriveDataSize;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPlace1() {
		return place1;
	}

	public void setPlace1(String place1) {
		this.place1 = place1;
	}

	public String getPlace2() {
		return place2;
	}

	public void setPlace2(String place2) {
		this.place2 = place2;
	}

	public MigrationStatus getMigrationStatus() {
		return migrationStatus;
	}

	public void setMigrationStatus(MigrationStatus migrationStatus) {
		this.migrationStatus = migrationStatus;
	}

	public MasterWorkbook getMasterWorkbook() {
		return masterWorkbook;
	}

	public void setMasterWorkbook(MasterWorkbook masterWorkbook) {
		this.masterWorkbook = masterWorkbook;
	}

	public UserEmailDetails getUserEmailAddress() {
		return userEmailAddress;
	}

	public void setUserEmailAddress(UserEmailDetails userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

	public UserLocationDetails getUserLocationDetails() {
		return userLocationDetails;
	}

	public void setUserLocationDetails(UserLocationDetails userLocationDetails) {
		this.userLocationDetails = userLocationDetails;
	}

	public UserOtherDetails getUserOtherDetails() {
		return userOtherDetails;
	}

	public void setUserOtherDetails(UserOtherDetails userOtherDetails) {
		this.userOtherDetails = userOtherDetails;
	}

	public UserSkypeDetails getUserSkypeDetails() {
		return userSkypeDetails;
	}

	public void setUserSkypeDetails(UserSkypeDetails userSkypeDetails) {
		this.userSkypeDetails = userSkypeDetails;
	}

	public UserWorkstationDetails getUserWorkstationDetails() {
		return userWorkstationDetails;
	}

	public void setUserWorkstationDetails(UserWorkstationDetails userWorkstationDetails) {
		this.userWorkstationDetails = userWorkstationDetails;
	}

	public UserAccountDetails getUserAccountDetails() {
		return userAccountDetails;
	}

	public void setUserAccountDetails(UserAccountDetails userAccountDetails) {
		this.userAccountDetails = userAccountDetails;
	}

	public List<UserSlotSelection> getUserSlotSelectionList() {
		return userSlotSelectionList;
	}

	public void setUserSlotSelectionList(List<UserSlotSelection> userSlotSelectionList) {
		this.userSlotSelectionList = userSlotSelectionList;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((CorporateMobileDevices == null) ? 0 : CorporateMobileDevices.hashCode());
		result = prime * result + ((Country == null) ? 0 : Country.hashCode());
		result = prime * result + ((Location == null) ? 0 : Location.hashCode());
		result = prime * result + ((Region == null) ? 0 : Region.hashCode());
		result = prime * result + ((Site == null) ? 0 : Site.hashCode());
		result = prime * result + ((adDomain == null) ? 0 : adDomain.hashCode());
		result = prime * result + ((aliasEmailAddress == null) ? 0 : aliasEmailAddress.hashCode());
		result = prime * result + ((aliasEmailAddressToBe == null) ? 0 : aliasEmailAddressToBe.hashCode());
		result = prime * result + ((calendarAccessDelegates == null) ? 0 : calendarAccessDelegates.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((computerModel == null) ? 0 : computerModel.hashCode());
		result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((freeDiskSpaceMB == null) ? 0 : freeDiskSpaceMB.hashCode());
		result = prime * result + ((homeDriveDataSize == null) ? 0 : homeDriveDataSize.hashCode());
		result = prime * result + ((isHardwareCompatibleWithW10 == null) ? 0 : isHardwareCompatibleWithW10.hashCode());
		result = prime * result + ((isLaptopDesktop == null) ? 0 : isLaptopDesktop.hashCode());
		result = prime * result
				+ ((isSCCMInstalledActiveInactive == null) ? 0 : isSCCMInstalledActiveInactive.hashCode());
		result = prime * result
				+ ((is_UpdateToWin10HardwareReplacement == null) ? 0 : is_UpdateToWin10HardwareReplacement.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((localApplications == null) ? 0 : localApplications.hashCode());
		result = prime * result + ((loginIdUPNasIs == null) ? 0 : loginIdUPNasIs.hashCode());
		result = prime * result + ((loginIdUPNtoBe == null) ? 0 : loginIdUPNtoBe.hashCode());
		result = prime * result + ((mailboxSize == null) ? 0 : mailboxSize.hashCode());
		result = prime * result + ((masterSheetRecordsId == null) ? 0 : masterSheetRecordsId.hashCode());
		result = prime * result + ((masterWorkbook == null) ? 0 : masterWorkbook.hashCode());
		result = prime * result + ((messagingDatabse == null) ? 0 : messagingDatabse.hashCode());
		result = prime * result + ((messagingServer == null) ? 0 : messagingServer.hashCode());
		result = prime * result + ((migrationStatus == null) ? 0 : migrationStatus.hashCode());
		result = prime * result + ((o365LicenseToBe == null) ? 0 : o365LicenseToBe.hashCode());
		result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
		result = prime * result + ((operatingSystemVersion == null) ? 0 : operatingSystemVersion.hashCode());
		result = prime * result + ((organizationalUnit == null) ? 0 : organizationalUnit.hashCode());
		result = prime * result + ((place1 == null) ? 0 : place1.hashCode());
		result = prime * result + ((place2 == null) ? 0 : place2.hashCode());
		result = prime * result + ((primaryEmailAddressAsIs == null) ? 0 : primaryEmailAddressAsIs.hashCode());
		result = prime * result + ((primaryEmailAddressToBe == null) ? 0 : primaryEmailAddressToBe.hashCode());
		result = prime * result + ((recipientType == null) ? 0 : recipientType.hashCode());
		result = prime * result + ((samAccountNameAsIs == null) ? 0 : samAccountNameAsIs.hashCode());
		result = prime * result + ((samAccountNameToBe == null) ? 0 : samAccountNameToBe.hashCode());
		result = prime * result + ((sharedAccess == null) ? 0 : sharedAccess.hashCode());
		result = prime * result + ((skypeLyncPool == null) ? 0 : skypeLyncPool.hashCode());
		result = prime * result + ((specifyABC == null) ? 0 : specifyABC.hashCode());
		result = prime * result + ((userAccountDetails == null) ? 0 : userAccountDetails.hashCode());
		result = prime * result + ((userDisplayName == null) ? 0 : userDisplayName.hashCode());
		result = prime * result + ((userDisplayNameToBe == null) ? 0 : userDisplayNameToBe.hashCode());
		result = prime * result + ((userEmailAddress == null) ? 0 : userEmailAddress.hashCode());
		result = prime * result + ((userLocationDetails == null) ? 0 : userLocationDetails.hashCode());
		result = prime * result + ((userOtherDetails == null) ? 0 : userOtherDetails.hashCode());
		result = prime * result + ((userSkypeDetails == null) ? 0 : userSkypeDetails.hashCode());
		result = prime * result + ((userSkypeLyncIdAsIs == null) ? 0 : userSkypeLyncIdAsIs.hashCode());
		result = prime * result + ((userSkypeLyncIdToBe == null) ? 0 : userSkypeLyncIdToBe.hashCode());
		result = prime * result + ((userWorkstationDetails == null) ? 0 : userWorkstationDetails.hashCode());
		return result;
	}*/

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterSheetData other = (MasterSheetData) obj;
		if (CorporateMobileDevices == null) {
			if (other.CorporateMobileDevices != null)
				return false;
		} else if (!CorporateMobileDevices.equals(other.CorporateMobileDevices))
			return false;
		if (Country == null) {
			if (other.Country != null)
				return false;
		} else if (!Country.equals(other.Country))
			return false;
		if (Location == null) {
			if (other.Location != null)
				return false;
		} else if (!Location.equals(other.Location))
			return false;
		if (Region == null) {
			if (other.Region != null)
				return false;
		} else if (!Region.equals(other.Region))
			return false;
		if (Site == null) {
			if (other.Site != null)
				return false;
		} else if (!Site.equals(other.Site))
			return false;
		if (adDomain == null) {
			if (other.adDomain != null)
				return false;
		} else if (!adDomain.equals(other.adDomain))
			return false;
		if (aliasEmailAddress == null) {
			if (other.aliasEmailAddress != null)
				return false;
		} else if (!aliasEmailAddress.equals(other.aliasEmailAddress))
			return false;
		if (aliasEmailAddressToBe == null) {
			if (other.aliasEmailAddressToBe != null)
				return false;
		} else if (!aliasEmailAddressToBe.equals(other.aliasEmailAddressToBe))
			return false;
		if (calendarAccessDelegates == null) {
			if (other.calendarAccessDelegates != null)
				return false;
		} else if (!calendarAccessDelegates.equals(other.calendarAccessDelegates))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (computerModel == null) {
			if (other.computerModel != null)
				return false;
		} else if (!computerModel.equals(other.computerModel))
			return false;
		if (computerName == null) {
			if (other.computerName != null)
				return false;
		} else if (!computerName.equals(other.computerName))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (freeDiskSpaceMB == null) {
			if (other.freeDiskSpaceMB != null)
				return false;
		} else if (!freeDiskSpaceMB.equals(other.freeDiskSpaceMB))
			return false;
		if (homeDriveDataSize == null) {
			if (other.homeDriveDataSize != null)
				return false;
		} else if (!homeDriveDataSize.equals(other.homeDriveDataSize))
			return false;
		if (isHardwareCompatibleWithW10 == null) {
			if (other.isHardwareCompatibleWithW10 != null)
				return false;
		} else if (!isHardwareCompatibleWithW10.equals(other.isHardwareCompatibleWithW10))
			return false;
		if (isLaptopDesktop == null) {
			if (other.isLaptopDesktop != null)
				return false;
		} else if (!isLaptopDesktop.equals(other.isLaptopDesktop))
			return false;
		if (isSCCMInstalledActiveInactive == null) {
			if (other.isSCCMInstalledActiveInactive != null)
				return false;
		} else if (!isSCCMInstalledActiveInactive.equals(other.isSCCMInstalledActiveInactive))
			return false;
		if (is_UpdateToWin10HardwareReplacement == null) {
			if (other.is_UpdateToWin10HardwareReplacement != null)
				return false;
		} else if (!is_UpdateToWin10HardwareReplacement.equals(other.is_UpdateToWin10HardwareReplacement))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (localApplications == null) {
			if (other.localApplications != null)
				return false;
		} else if (!localApplications.equals(other.localApplications))
			return false;
		if (loginIdUPNasIs == null) {
			if (other.loginIdUPNasIs != null)
				return false;
		} else if (!loginIdUPNasIs.equals(other.loginIdUPNasIs))
			return false;
		if (loginIdUPNtoBe == null) {
			if (other.loginIdUPNtoBe != null)
				return false;
		} else if (!loginIdUPNtoBe.equals(other.loginIdUPNtoBe))
			return false;
		if (mailboxSize == null) {
			if (other.mailboxSize != null)
				return false;
		} else if (!mailboxSize.equals(other.mailboxSize))
			return false;
		if (masterSheetRecordsId == null) {
			if (other.masterSheetRecordsId != null)
				return false;
		} else if (!masterSheetRecordsId.equals(other.masterSheetRecordsId))
			return false;
		if (masterWorkbook == null) {
			if (other.masterWorkbook != null)
				return false;
		} else if (!masterWorkbook.equals(other.masterWorkbook))
			return false;
		if (messagingDatabse == null) {
			if (other.messagingDatabse != null)
				return false;
		} else if (!messagingDatabse.equals(other.messagingDatabse))
			return false;
		if (messagingServer == null) {
			if (other.messagingServer != null)
				return false;
		} else if (!messagingServer.equals(other.messagingServer))
			return false;
		if (migrationStatus != other.migrationStatus)
			return false;
		if (o365LicenseToBe == null) {
			if (other.o365LicenseToBe != null)
				return false;
		} else if (!o365LicenseToBe.equals(other.o365LicenseToBe))
			return false;
		if (operatingSystem == null) {
			if (other.operatingSystem != null)
				return false;
		} else if (!operatingSystem.equals(other.operatingSystem))
			return false;
		if (operatingSystemVersion == null) {
			if (other.operatingSystemVersion != null)
				return false;
		} else if (!operatingSystemVersion.equals(other.operatingSystemVersion))
			return false;
		if (organizationalUnit == null) {
			if (other.organizationalUnit != null)
				return false;
		} else if (!organizationalUnit.equals(other.organizationalUnit))
			return false;
		if (place1 == null) {
			if (other.place1 != null)
				return false;
		} else if (!place1.equals(other.place1))
			return false;
		if (place2 == null) {
			if (other.place2 != null)
				return false;
		} else if (!place2.equals(other.place2))
			return false;
		if (primaryEmailAddressAsIs == null) {
			if (other.primaryEmailAddressAsIs != null)
				return false;
		} else if (!primaryEmailAddressAsIs.equals(other.primaryEmailAddressAsIs))
			return false;
		if (primaryEmailAddressToBe == null) {
			if (other.primaryEmailAddressToBe != null)
				return false;
		} else if (!primaryEmailAddressToBe.equals(other.primaryEmailAddressToBe))
			return false;
		if (recipientType == null) {
			if (other.recipientType != null)
				return false;
		} else if (!recipientType.equals(other.recipientType))
			return false;
		if (samAccountNameAsIs == null) {
			if (other.samAccountNameAsIs != null)
				return false;
		} else if (!samAccountNameAsIs.equals(other.samAccountNameAsIs))
			return false;
		if (samAccountNameToBe == null) {
			if (other.samAccountNameToBe != null)
				return false;
		} else if (!samAccountNameToBe.equals(other.samAccountNameToBe))
			return false;
		if (sharedAccess == null) {
			if (other.sharedAccess != null)
				return false;
		} else if (!sharedAccess.equals(other.sharedAccess))
			return false;
		if (skypeLyncPool == null) {
			if (other.skypeLyncPool != null)
				return false;
		} else if (!skypeLyncPool.equals(other.skypeLyncPool))
			return false;
		if (specifyABC == null) {
			if (other.specifyABC != null)
				return false;
		} else if (!specifyABC.equals(other.specifyABC))
			return false;
		if (userAccountDetails == null) {
			if (other.userAccountDetails != null)
				return false;
		} else if (!userAccountDetails.equals(other.userAccountDetails))
			return false;
		if (userDisplayName == null) {
			if (other.userDisplayName != null)
				return false;
		} else if (!userDisplayName.equals(other.userDisplayName))
			return false;
		if (userDisplayNameToBe == null) {
			if (other.userDisplayNameToBe != null)
				return false;
		} else if (!userDisplayNameToBe.equals(other.userDisplayNameToBe))
			return false;
		if (userEmailAddress == null) {
			if (other.userEmailAddress != null)
				return false;
		} else if (!userEmailAddress.equals(other.userEmailAddress))
			return false;
		if (userLocationDetails == null) {
			if (other.userLocationDetails != null)
				return false;
		} else if (!userLocationDetails.equals(other.userLocationDetails))
			return false;
		if (userOtherDetails == null) {
			if (other.userOtherDetails != null)
				return false;
		} else if (!userOtherDetails.equals(other.userOtherDetails))
			return false;
		if (userSkypeDetails == null) {
			if (other.userSkypeDetails != null)
				return false;
		} else if (!userSkypeDetails.equals(other.userSkypeDetails))
			return false;
		if (userSkypeLyncIdAsIs == null) {
			if (other.userSkypeLyncIdAsIs != null)
				return false;
		} else if (!userSkypeLyncIdAsIs.equals(other.userSkypeLyncIdAsIs))
			return false;
		if (userSkypeLyncIdToBe == null) {
			if (other.userSkypeLyncIdToBe != null)
				return false;
		} else if (!userSkypeLyncIdToBe.equals(other.userSkypeLyncIdToBe))
			return false;
		if (userWorkstationDetails == null) {
			if (other.userWorkstationDetails != null)
				return false;
		} else if (!userWorkstationDetails.equals(other.userWorkstationDetails))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MasterSheetData [masterSheetRecordsId=" + masterSheetRecordsId + ", Region=" + Region + ", Country="
				+ Country + ", Site=" + Site + ", Location=" + Location + ", userDisplayName=" + userDisplayName
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", loginIdUPNasIs=" + loginIdUPNasIs
				+ ", samAccountNameAsIs=" + samAccountNameAsIs + ", adDomain=" + adDomain + ", organizationalUnit="
				+ organizationalUnit + ", userDisplayNameToBe=" + userDisplayNameToBe + ", loginIdUPNtoBe="
				+ loginIdUPNtoBe + ", samAccountNameToBe=" + samAccountNameToBe + ", o365LicenseToBe=" + o365LicenseToBe
				+ ", primaryEmailAddressAsIs=" + primaryEmailAddressAsIs + ", aliasEmailAddress=" + aliasEmailAddress
				+ ", recipientType=" + recipientType + ", mailboxSize=" + mailboxSize + ", primaryEmailAddressToBe="
				+ primaryEmailAddressToBe + ", aliasEmailAddressToBe=" + aliasEmailAddressToBe + ", sharedAccess="
				+ sharedAccess + ", calendarAccessDelegates=" + calendarAccessDelegates + ", messagingServer="
				+ messagingServer + ", messagingDatabse=" + messagingDatabse + ", CorporateMobileDevices="
				+ CorporateMobileDevices + ", userSkypeLyncIdAsIs=" + userSkypeLyncIdAsIs + ", userSkypeLyncIdToBe="
				+ userSkypeLyncIdToBe + ", skypeLyncPool=" + skypeLyncPool + ", computerName=" + computerName
				+ ", isLaptopDesktop=" + isLaptopDesktop + ", isSCCMInstalledActiveInactive="
				+ isSCCMInstalledActiveInactive + ", computerModel=" + computerModel + ", operatingSystem="
				+ operatingSystem + ", operatingSystemVersion=" + operatingSystemVersion
				+ ", isHardwareCompatibleWithW10=" + isHardwareCompatibleWithW10 + ", freeDiskSpaceMB="
				+ freeDiskSpaceMB + ", is_UpdateToWin10HardwareReplacement=" + is_UpdateToWin10HardwareReplacement
				+ ", specifyABC=" + specifyABC + ", localApplications=" + localApplications + ", homeDriveDataSize="
				+ homeDriveDataSize + ", comments=" + comments + ", place1=" + place1 + ", place2=" + place2
				+ ", migrationStatus=" + migrationStatus + ", masterWorkbook=" + masterWorkbook + ", userEmailAddress="
				+ userEmailAddress + ", userLocationDetails=" + userLocationDetails + ", userOtherDetails="
				+ userOtherDetails + ", userSkypeDetails=" + userSkypeDetails + ", userWorkstationDetails="
				+ userWorkstationDetails + ", userAccountDetails=" + userAccountDetails + "]";
	}*/

}