package com.mindtree.migrationaccelerator.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.mindtree.migrationaccelerator.constants.MigrationAcceleratorConstants;
import com.mindtree.migrationaccelerator.entity.Country;
import com.mindtree.migrationaccelerator.entity.MasterSheetData;
import com.mindtree.migrationaccelerator.entity.Site;
import com.mindtree.migrationaccelerator.entity.UserAccountDetails;
import com.mindtree.migrationaccelerator.entity.UserEmailDetails;
import com.mindtree.migrationaccelerator.entity.UserLocationDetails;
import com.mindtree.migrationaccelerator.entity.UserOtherDetails;
import com.mindtree.migrationaccelerator.entity.UserSkypeDetails;
import com.mindtree.migrationaccelerator.entity.UserWorkstationDetails;

public class DataRecordsToEntityConverter {
	public static Properties prop;
	static {
		try {
			prop = PropertiesReader.getPropertiesReaderInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static UserLocationDetails createUserLocationDetails(String[] rowData, Map<String, Integer> map) {

		UserLocationDetails locDetails = new UserLocationDetails();

		locDetails.setRegion(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.REGION))]);
		locDetails.setCountry(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COUNTRY))]);
		locDetails.setSite(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SITE))]);
		locDetails.setLocation(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCATION))]);

		return locDetails;
	}
	
	public static UserLocationDetails updateUserLocationDetails(String[] rowData, Map<String, Integer> map, UserLocationDetails locDetails) {

		locDetails.setRegion(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.REGION))]);
		locDetails.setCountry(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COUNTRY))]);
		locDetails.setSite(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SITE))]);
		locDetails.setLocation(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCATION))]);

		return locDetails;
	}

	public static UserAccountDetails createUserAccountDetails(String[] rowData, Map<String, Integer> map) {

		UserAccountDetails accountDetails = new UserAccountDetails();

		accountDetails.setUserDisplayName(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME))]);
		accountDetails.setFirstName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FIRST_NAME))]);
		accountDetails.setLastName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LAST_NAME))]);
		accountDetails
				.setLoginIdUPNasIs(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGIN_ID_AS_IS))]);
		accountDetails.setSamAccountNameAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_AS_IS))]);
		accountDetails.setAdDomain(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.AD_DOMAIN))]);
		accountDetails.setOrganizationalUnit(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ORGANIZATIONAL_UNIT))]);
		accountDetails.setUserDisplayNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME_TO_BE))]);
		accountDetails
				.setLoginIdUPNtoBe(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGON_ID_TO_BE))]);
		accountDetails.setSamAccountNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_TO_BE))]);
		accountDetails.setO365LicenseToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.O365_LICENSE_TO_BE))]);

		return accountDetails;
	}
	
	public static UserAccountDetails updateUserAccountDetails(String[] rowData, Map<String, Integer> map, UserAccountDetails accountDetails) {

		accountDetails.setUserDisplayName(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME))]);
		accountDetails.setFirstName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FIRST_NAME))]);
		accountDetails.setLastName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LAST_NAME))]);
		accountDetails
				.setLoginIdUPNasIs(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGIN_ID_AS_IS))]);
		accountDetails.setSamAccountNameAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_AS_IS))]);
		accountDetails.setAdDomain(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.AD_DOMAIN))]);
		accountDetails.setOrganizationalUnit(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ORGANIZATIONAL_UNIT))]);
		accountDetails.setUserDisplayNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME_TO_BE))]);
		accountDetails
				.setLoginIdUPNtoBe(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGON_ID_TO_BE))]);
		accountDetails.setSamAccountNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_TO_BE))]);
		accountDetails.setO365LicenseToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.O365_LICENSE_TO_BE))]);

		return accountDetails;
	}

	public static UserEmailDetails createUserEmailDetails(String[] rowData, Map<String, Integer> map) {

		UserEmailDetails emailDetails = new UserEmailDetails();

		emailDetails.setPrimaryEmailAddressAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_AS_IS))]);
		emailDetails.setAliasEmailAddress(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS))]);
		emailDetails.setRecipientType(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.RECIPIENT_TYPE))]);
		emailDetails.setMailboxSize(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MAILBOX_SIZE))]);
		emailDetails.setPrimaryEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_TO_BE))]);
		emailDetails.setAliasEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS_TO_BE))]);
		emailDetails.setSharedAccess(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SHARED_ACCESS))]);
		emailDetails.setCalendarAccessDelegates(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CALENDAR_ACCESS_DELEGATES))]);
		emailDetails
				.setMessagingServer(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_SERVER))]);
		emailDetails.setMessagingDatabse(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_DATABASE))]);
		emailDetails.setCorporateMobileDevices(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CORPORATE_MOBILE_DEVICES))]);

		return emailDetails;

	}
	
	public static UserEmailDetails updateUserEmailDetails(String[] rowData, Map<String, Integer> map, UserEmailDetails emailDetails) {

		emailDetails.setPrimaryEmailAddressAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_AS_IS))]);
		emailDetails.setAliasEmailAddress(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS))]);
		emailDetails.setRecipientType(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.RECIPIENT_TYPE))]);
		emailDetails.setMailboxSize(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MAILBOX_SIZE))]);
		emailDetails.setPrimaryEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_TO_BE))]);
		emailDetails.setAliasEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS_TO_BE))]);
		emailDetails.setSharedAccess(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SHARED_ACCESS))]);
		emailDetails.setCalendarAccessDelegates(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CALENDAR_ACCESS_DELEGATES))]);
		emailDetails
				.setMessagingServer(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_SERVER))]);
		emailDetails.setMessagingDatabse(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_DATABASE))]);
		emailDetails.setCorporateMobileDevices(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CORPORATE_MOBILE_DEVICES))]);

		return emailDetails;

	}

	public static UserSkypeDetails createUserSkypeDetails(String[] rowData, Map<String, Integer> map) {

		UserSkypeDetails skypeDetails = new UserSkypeDetails();

		skypeDetails.setUserSkypeLyncIdAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_AS_IS))]);
		skypeDetails.setUserSkypeLyncIdToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_TO_BE))]);
		skypeDetails
				.setSkypeLyncPool(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SKYPE_LYNC_POOL))]);
		return skypeDetails;
	}
	
	public static UserSkypeDetails updateUserSkypeDetails(String[] rowData, Map<String, Integer> map, UserSkypeDetails skypeDetails) {

		skypeDetails.setUserSkypeLyncIdAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_AS_IS))]);
		skypeDetails.setUserSkypeLyncIdToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_TO_BE))]);
		skypeDetails
				.setSkypeLyncPool(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SKYPE_LYNC_POOL))]);
		return skypeDetails;
	}

	public static UserWorkstationDetails createUserWorkstationDetails(String[] rowData, Map<String, Integer> map) {

		UserWorkstationDetails workstationDetails = new UserWorkstationDetails();
		workstationDetails
				.setComputerName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTER_NAME))]);
		workstationDetails.setIsLaptopDesktop(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_LAPTOP_DESKTOP))]);
		workstationDetails.setIsSCCMInstalledActiveInactive(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_SCCM_INSTALLED_ACTIVE_INACTIVE))]);
		workstationDetails
				.setComputerModel(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTR_MODEL))]);
		workstationDetails
				.setOperatingSystem(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM))]);
		workstationDetails.setOperatingSystemVersion(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM_VERSION))]);
		workstationDetails.setHardwareCompatibleWithW10(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_HARDWARE_COMPATIBLE_WITH_W10))]);
		workstationDetails.setFreeDiskSpaceMB(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FREE_DISK_SPACE_MB))]);
		workstationDetails.setUpdateToWin10HardwareReplacement(rowData[map
				.get(prop.getProperty(MigrationAcceleratorConstants.IS_UPDATE_TO_WIN10_HARDWARE_REPLACEMENT))]);
		workstationDetails.setSpecifyABC(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SPECIFY_ABC))]);
		workstationDetails.setLocalApplications(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCAL_APPLICATIONS))]);

		return workstationDetails;

	}
	
	public static UserWorkstationDetails updateUserWorkstationDetails(String[] rowData, Map<String, Integer> map, UserWorkstationDetails workstationDetails) {

		workstationDetails
				.setComputerName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTER_NAME))]);
		workstationDetails.setIsLaptopDesktop(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_LAPTOP_DESKTOP))]);
		workstationDetails.setIsSCCMInstalledActiveInactive(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_SCCM_INSTALLED_ACTIVE_INACTIVE))]);
		workstationDetails
				.setComputerModel(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTR_MODEL))]);
		workstationDetails
				.setOperatingSystem(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM))]);
		workstationDetails.setOperatingSystemVersion(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM_VERSION))]);
		workstationDetails.setHardwareCompatibleWithW10(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_HARDWARE_COMPATIBLE_WITH_W10))]);
		workstationDetails.setFreeDiskSpaceMB(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FREE_DISK_SPACE_MB))]);
		workstationDetails.setUpdateToWin10HardwareReplacement(rowData[map
				.get(prop.getProperty(MigrationAcceleratorConstants.IS_UPDATE_TO_WIN10_HARDWARE_REPLACEMENT))]);
		workstationDetails.setSpecifyABC(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SPECIFY_ABC))]);
		workstationDetails.setLocalApplications(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCAL_APPLICATIONS))]);

		return workstationDetails;

	}

	public static UserOtherDetails createUserOtherDetails(String[] rowData, Map<String, Integer> map) {
		
		UserOtherDetails otherDetails = new UserOtherDetails();
		otherDetails.setHomeDriveDataSize(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_HOME_DRIVE_DATA_SIZE))]);
		otherDetails.setComments(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMMENTS))]);
		return otherDetails;
	}
	
	public static UserOtherDetails updateUserOtherDetails(String[] rowData, Map<String, Integer> map, UserOtherDetails otherDetails) {
		
		otherDetails.setHomeDriveDataSize(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_HOME_DRIVE_DATA_SIZE))]);
		otherDetails.setComments(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMMENTS))]);
		return otherDetails;
	}

	public static MasterSheetData createMasterSheetDetails(String[] rowData, Map<String, Integer> map) {

		MasterSheetData masterSheetDetails = new MasterSheetData();
		masterSheetDetails.setRegion(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.REGION))]);
		masterSheetDetails.setCountry(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COUNTRY))]);
		masterSheetDetails.setSite(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SITE))]);
		masterSheetDetails.setLocation(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCATION))]);
		masterSheetDetails.setUserDisplayName(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME))]);
		masterSheetDetails.setFirstName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FIRST_NAME))]);
		masterSheetDetails.setLastName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LAST_NAME))]);
		masterSheetDetails
				.setLoginIdUPNasIs(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGIN_ID_AS_IS))]);
		masterSheetDetails.setSamAccountNameAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_AS_IS))]);
		masterSheetDetails.setAdDomain(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.AD_DOMAIN))]);
		masterSheetDetails.setOrganizationalUnit(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ORGANIZATIONAL_UNIT))]);
		masterSheetDetails.setUserDisplayNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME_TO_BE))]);
		masterSheetDetails
				.setLoginIdUPNtoBe(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGON_ID_TO_BE))]);
		masterSheetDetails.setSamAccountNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_TO_BE))]);
		masterSheetDetails.setO365LicenseToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.O365_LICENSE_TO_BE))]);
		masterSheetDetails.setPrimaryEmailAddressAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_AS_IS))]);
		masterSheetDetails.setAliasEmailAddress(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS))]);
		masterSheetDetails
				.setRecipientType(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.RECIPIENT_TYPE))]);
		masterSheetDetails
				.setMailboxSize(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MAILBOX_SIZE))]);
		masterSheetDetails.setPrimaryEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_TO_BE))]);
		masterSheetDetails.setAliasEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS_TO_BE))]);
		masterSheetDetails
				.setSharedAccess(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SHARED_ACCESS))]);
		masterSheetDetails.setCalendarAccessDelegates(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CALENDAR_ACCESS_DELEGATES))]);
		masterSheetDetails
				.setMessagingServer(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_SERVER))]);
		masterSheetDetails.setMessagingDatabse(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_DATABASE))]);
		masterSheetDetails.setCorporateMobileDevices(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CORPORATE_MOBILE_DEVICES))]);
		masterSheetDetails.setUserSkypeLyncIdAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_AS_IS))]);
		masterSheetDetails.setUserSkypeLyncIdToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_TO_BE))]);
		masterSheetDetails
				.setSkypeLyncPool(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SKYPE_LYNC_POOL))]);
		masterSheetDetails
				.setComputerName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTER_NAME))]);
		masterSheetDetails.setIsLaptopDesktop(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_LAPTOP_DESKTOP))]);
		masterSheetDetails.setIsSCCMInstalledActiveInactive(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_SCCM_INSTALLED_ACTIVE_INACTIVE))]);
		masterSheetDetails
				.setComputerModel(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTR_MODEL))]);
		masterSheetDetails
				.setOperatingSystem(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM))]);
		masterSheetDetails.setOperatingSystemVersion(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM_VERSION))]);
		masterSheetDetails.setIsHardwareCompatibleWithW10(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_HARDWARE_COMPATIBLE_WITH_W10))]);
		masterSheetDetails.setFreeDiskSpaceMB(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FREE_DISK_SPACE_MB))]);
		masterSheetDetails.setIs_UpdateToWin10HardwareReplacement(rowData[map
				.get(prop.getProperty(MigrationAcceleratorConstants.IS_UPDATE_TO_WIN10_HARDWARE_REPLACEMENT))]);
		masterSheetDetails.setSpecifyABC(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SPECIFY_ABC))]);
		masterSheetDetails.setLocalApplications(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCAL_APPLICATIONS))]);
		masterSheetDetails.setHomeDriveDataSize(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_HOME_DRIVE_DATA_SIZE))]);
		masterSheetDetails.setComments(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMMENTS))]);

		return masterSheetDetails;
	}
	
	public static MasterSheetData updateMasterSheetDetails(String[] rowData, Map<String, Integer> map, MasterSheetData masterSheetDetails) {

		masterSheetDetails.setRegion(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.REGION))]);
		masterSheetDetails.setCountry(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COUNTRY))]);
		masterSheetDetails.setSite(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SITE))]);
		masterSheetDetails.setLocation(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCATION))]);
		masterSheetDetails.setUserDisplayName(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME))]);
		masterSheetDetails.setFirstName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FIRST_NAME))]);
		masterSheetDetails.setLastName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LAST_NAME))]);
		masterSheetDetails
				.setLoginIdUPNasIs(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGIN_ID_AS_IS))]);
		masterSheetDetails.setSamAccountNameAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_AS_IS))]);
		masterSheetDetails.setAdDomain(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.AD_DOMAIN))]);
		masterSheetDetails.setOrganizationalUnit(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ORGANIZATIONAL_UNIT))]);
		masterSheetDetails.setUserDisplayNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_DISPLAY_NAME_TO_BE))]);
		masterSheetDetails
				.setLoginIdUPNtoBe(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOGON_ID_TO_BE))]);
		masterSheetDetails.setSamAccountNameToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SAM_ACCOUNT_NAME_TO_BE))]);
		masterSheetDetails.setO365LicenseToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.O365_LICENSE_TO_BE))]);
		masterSheetDetails.setPrimaryEmailAddressAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_AS_IS))]);
		masterSheetDetails.setAliasEmailAddress(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS))]);
		masterSheetDetails
				.setRecipientType(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.RECIPIENT_TYPE))]);
		masterSheetDetails
				.setMailboxSize(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MAILBOX_SIZE))]);
		masterSheetDetails.setPrimaryEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.PRIMARY_EMAIL_ADDRESS_TO_BE))]);
		masterSheetDetails.setAliasEmailAddressToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.ALIAS_EMAIL_ADDRESS_TO_BE))]);
		masterSheetDetails
				.setSharedAccess(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SHARED_ACCESS))]);
		masterSheetDetails.setCalendarAccessDelegates(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CALENDAR_ACCESS_DELEGATES))]);
		masterSheetDetails
				.setMessagingServer(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_SERVER))]);
		masterSheetDetails.setMessagingDatabse(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.MESSAGING_DATABASE))]);
		masterSheetDetails.setCorporateMobileDevices(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.CORPORATE_MOBILE_DEVICES))]);
		masterSheetDetails.setUserSkypeLyncIdAsIs(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_AS_IS))]);
		masterSheetDetails.setUserSkypeLyncIdToBe(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_SKYPE_LYNC_ID_TO_BE))]);
		masterSheetDetails
				.setSkypeLyncPool(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SKYPE_LYNC_POOL))]);
		masterSheetDetails
				.setComputerName(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTER_NAME))]);
		masterSheetDetails.setIsLaptopDesktop(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_LAPTOP_DESKTOP))]);
		masterSheetDetails.setIsSCCMInstalledActiveInactive(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_SCCM_INSTALLED_ACTIVE_INACTIVE))]);
		masterSheetDetails
				.setComputerModel(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMPUTR_MODEL))]);
		masterSheetDetails
				.setOperatingSystem(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM))]);
		masterSheetDetails.setOperatingSystemVersion(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.OPERATING_SYSTEM_VERSION))]);
		masterSheetDetails.setIsHardwareCompatibleWithW10(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.IS_HARDWARE_COMPATIBLE_WITH_W10))]);
		masterSheetDetails.setFreeDiskSpaceMB(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.FREE_DISK_SPACE_MB))]);
		masterSheetDetails.setIs_UpdateToWin10HardwareReplacement(rowData[map
				.get(prop.getProperty(MigrationAcceleratorConstants.IS_UPDATE_TO_WIN10_HARDWARE_REPLACEMENT))]);
		masterSheetDetails.setSpecifyABC(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.SPECIFY_ABC))]);
		masterSheetDetails.setLocalApplications(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.LOCAL_APPLICATIONS))]);
		masterSheetDetails.setHomeDriveDataSize(
				rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.USER_HOME_DRIVE_DATA_SIZE))]);
		masterSheetDetails.setComments(rowData[map.get(prop.getProperty(MigrationAcceleratorConstants.COMMENTS))]);

		return masterSheetDetails;
	}

	public static Country createCountryDetails(String[] rowData, Map<String, Integer> mapColumnIndexName) {
		Country countryDetails = new Country();

		countryDetails.setCountryName(
				rowData[mapColumnIndexName.get(prop.getProperty(MigrationAcceleratorConstants.COUNTRY))]);
		return countryDetails;
	}
	
	public static Country updateCountryDetails(String[] rowData, Map<String, Integer> mapColumnIndexName, Country countryDetails) {

		countryDetails.setCountryName(
				rowData[mapColumnIndexName.get(prop.getProperty(MigrationAcceleratorConstants.COUNTRY))]);
		return countryDetails;
	}

	public static Site createSiteDetails(String[] rowData, Map<String, Integer> mapColumnIndexName) {
		Site siteDetails = new Site();

		siteDetails.setSiteName(rowData[mapColumnIndexName.get(prop.getProperty(MigrationAcceleratorConstants.SITE))]);
		return siteDetails;

	}
	
	public static Site updateSiteDetails(String[] rowData, Map<String, Integer> mapColumnIndexName, Site siteDetails) {

		siteDetails.setSiteName(rowData[mapColumnIndexName.get(prop.getProperty(MigrationAcceleratorConstants.SITE))]);
		return siteDetails;

	}
}