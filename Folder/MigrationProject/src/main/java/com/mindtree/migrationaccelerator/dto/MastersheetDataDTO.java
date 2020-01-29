package com.mindtree.migrationaccelerator.dto;

import java.io.Serializable;

import com.mindtree.migrationaccelerator.enums.MigrationStatus;

public class MastersheetDataDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String region;
	private String country;
	private String site;
	private String location;
	private String userDisplayName;
	private String firstName;
	private String lastName;
	private String logIdAsIs;
	private String samAccountNameAsIs;
	private String adDomain;
	private String organizationalUnit;
	private String userDisplayNameToBe;
	private String loginIdToBe;
	private String samAccountToBe;
	private String o365LicenseToBe;
	private String primaryEmailAsIs;
	private String aliasEmailAsIs;
	private String recipentType;
	private String mailboxSize;
	private String primaryEmailToBe;
	private String aliasEmailToBe;
	private String sharedAccess;
	private String calenderDelegates;
	private String messagingServer;
	private String messagingDatabase;
	private String corporateMobileDevice;
	private String userSkypeLyncAsIs;
	private String userSkypeLyncToBe;
	private String skypeLyncPool;
	private String computerName;
	private String isLaptopDesktop;
	private String isSCCMInstalled;
	private String computermodel;
	private String operatingSystem;
	private String operatingSystemVersion;
	private String isHardwareCompatableWin10;
	private String freeDiskSpace;
	private String updateW10HardwareReplacement;
	private String specifyABC;
	private String localApplication;
	private String userHomeDrive;
	private String comments;
	private MigrationStatus migrationStatus;
	private ProjectDTO projectDTO;
	
	public MastersheetDataDTO() {

	}

	public Integer getId() {
		return id;
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

	public String getSite() {
		return site;
	}

	public String getLocation() {
		return location;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogIdAsIs() {
		return logIdAsIs;
	}

	public String getSamAccountNameAsIs() {
		return samAccountNameAsIs;
	}

	public String getAdDomain() {
		return adDomain;
	}

	public String getOrganizationalUnit() {
		return organizationalUnit;
	}

	public String getUserDisplayNameToBe() {
		return userDisplayNameToBe;
	}

	public String getLoginIdToBe() {
		return loginIdToBe;
	}

	public String getSamAccountToBe() {
		return samAccountToBe;
	}

	public String getO365LicenseToBe() {
		return o365LicenseToBe;
	}

	public String getPrimaryEmailAsIs() {
		return primaryEmailAsIs;
	}

	public String getAliasEmailAsIs() {
		return aliasEmailAsIs;
	}

	public String getRecipentType() {
		return recipentType;
	}

	public String getMailboxSize() {
		return mailboxSize;
	}

	public String getPrimaryEmailToBe() {
		return primaryEmailToBe;
	}

	public String getAliasEmailToBe() {
		return aliasEmailToBe;
	}

	public String getSharedAccess() {
		return sharedAccess;
	}

	public String getCalenderDelegates() {
		return calenderDelegates;
	}

	public String getMessagingServer() {
		return messagingServer;
	}

	public String getMessagingDatabase() {
		return messagingDatabase;
	}

	public String getCorporateMobileDevice() {
		return corporateMobileDevice;
	}

	public String getUserSkypeLyncAsIs() {
		return userSkypeLyncAsIs;
	}

	public String getUserSkypeLyncToBe() {
		return userSkypeLyncToBe;
	}

	public String getSkypeLyncPool() {
		return skypeLyncPool;
	}

	public String getComputerName() {
		return computerName;
	}

	public String getIsLaptopDesktop() {
		return isLaptopDesktop;
	}

	public String getIsSCCMInstalled() {
		return isSCCMInstalled;
	}

	public String getComputermodel() {
		return computermodel;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public String getOperatingSystemVersion() {
		return operatingSystemVersion;
	}

	public String getIsHardwareCompatableWin10() {
		return isHardwareCompatableWin10;
	}

	public String getFreeDiskSpace() {
		return freeDiskSpace;
	}

	public String getUpdateW10HardwareReplacement() {
		return updateW10HardwareReplacement;
	}

	public String getSpecifyABC() {
		return specifyABC;
	}

	public String getLocalApplication() {
		return localApplication;
	}

	public String getUserHomeDrive() {
		return userHomeDrive;
	}

	public String getComments() {
		return comments;
	}

	public MigrationStatus getMigrationStatus() {
		return migrationStatus;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLogIdAsIs(String logIdAsIs) {
		this.logIdAsIs = logIdAsIs;
	}

	public void setSamAccountNameAsIs(String samAccountNameAsIs) {
		this.samAccountNameAsIs = samAccountNameAsIs;
	}

	public void setAdDomain(String adDomain) {
		this.adDomain = adDomain;
	}

	public void setOrganizationalUnit(String organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
	}

	public void setUserDisplayNameToBe(String userDisplayNameToBe) {
		this.userDisplayNameToBe = userDisplayNameToBe;
	}

	public void setLoginIdToBe(String loginIdToBe) {
		this.loginIdToBe = loginIdToBe;
	}

	public void setSamAccountToBe(String samAccountToBe) {
		this.samAccountToBe = samAccountToBe;
	}

	public void setO365LicenseToBe(String o365LicenseToBe) {
		this.o365LicenseToBe = o365LicenseToBe;
	}

	public void setPrimaryEmailAsIs(String primaryEmailAsIs) {
		this.primaryEmailAsIs = primaryEmailAsIs;
	}

	public void setAliasEmailAsIs(String aliasEmailAsIs) {
		this.aliasEmailAsIs = aliasEmailAsIs;
	}

	public void setRecipentType(String recipentType) {
		this.recipentType = recipentType;
	}

	public void setMailboxSize(String mailboxSize) {
		this.mailboxSize = mailboxSize;
	}

	public void setPrimaryEmailToBe(String primaryEmailToBe) {
		this.primaryEmailToBe = primaryEmailToBe;
	}

	public void setAliasEmailToBe(String aliasEmailToBe) {
		this.aliasEmailToBe = aliasEmailToBe;
	}

	public void setSharedAccess(String sharedAccess) {
		this.sharedAccess = sharedAccess;
	}

	public void setCalenderDelegates(String calenderDelegates) {
		this.calenderDelegates = calenderDelegates;
	}

	public void setMessagingServer(String messagingServer) {
		this.messagingServer = messagingServer;
	}

	public void setMessagingDatabase(String messagingDatabase) {
		this.messagingDatabase = messagingDatabase;
	}

	public void setCorporateMobileDevice(String corporateMobileDevice) {
		this.corporateMobileDevice = corporateMobileDevice;
	}

	public void setUserSkypeLyncAsIs(String userSkypeLyncAsIs) {
		this.userSkypeLyncAsIs = userSkypeLyncAsIs;
	}

	public void setUserSkypeLyncToBe(String userSkypeLyncToBe) {
		this.userSkypeLyncToBe = userSkypeLyncToBe;
	}

	public void setSkypeLyncPool(String skypeLyncPool) {
		this.skypeLyncPool = skypeLyncPool;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public void setIsLaptopDesktop(String isLaptopDesktop) {
		this.isLaptopDesktop = isLaptopDesktop;
	}

	public void setIsSCCMInstalled(String isSCCMInstalled) {
		this.isSCCMInstalled = isSCCMInstalled;
	}

	public void setComputermodel(String computermodel) {
		this.computermodel = computermodel;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		this.operatingSystemVersion = operatingSystemVersion;
	}

	public void setIsHardwareCompatableWin10(String isHardwareCompatableWin10) {
		this.isHardwareCompatableWin10 = isHardwareCompatableWin10;
	}

	public void setFreeDiskSpace(String freeDiskSpace) {
		this.freeDiskSpace = freeDiskSpace;
	}

	public void setUpdateW10HardwareReplacement(String updateW10HardwareReplacement) {
		this.updateW10HardwareReplacement = updateW10HardwareReplacement;
	}

	public void setSpecifyABC(String specifyABC) {
		this.specifyABC = specifyABC;
	}

	public void setLocalApplication(String localApplication) {
		this.localApplication = localApplication;
	}

	public void setUserHomeDrive(String userHomeDrive) {
		this.userHomeDrive = userHomeDrive;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setMigrationStatus(MigrationStatus migrationStatus) {
		this.migrationStatus = migrationStatus;
	}

	public MastersheetDataDTO(Integer id, String region, String country, String site, String location,
			String userDisplayName, String firstName, String lastName, String logIdAsIs, String samAccountNameAsIs,
			String adDomain, String organizationalUnit, String userDisplayNameToBe, String loginIdToBe,
			String samAccountToBe, String o365LicenseToBe, String primaryEmailAsIs, String aliasEmailAsIs,
			String recipentType, String mailboxSize, String primaryEmailToBe, String aliasEmailToBe,
			String sharedAccess, String calenderDelegates, String messagingServer, String messagingDatabase,
			String corporateMobileDevice, String userSkypeLyncAsIs, String userSkypeLyncToBe, String skypeLyncPool,
			String computerName, String isLaptopDesktop, String isSCCMInstalled, String computermodel,
			String operatingSystem, String operatingSystemVersion, String isHardwareCompatableWin10,
			String freeDiskSpace, String updateW10HardwareReplacement, String specifyABC, String localApplication,
			String userHomeDrive, String comments, MigrationStatus migrationStatus) {
		super();
		this.id = id;
		this.region = region;
		this.country = country;
		this.site = site;
		this.location = location;
		this.userDisplayName = userDisplayName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.logIdAsIs = logIdAsIs;
		this.samAccountNameAsIs = samAccountNameAsIs;
		this.adDomain = adDomain;
		this.organizationalUnit = organizationalUnit;
		this.userDisplayNameToBe = userDisplayNameToBe;
		this.loginIdToBe = loginIdToBe;
		this.samAccountToBe = samAccountToBe;
		this.o365LicenseToBe = o365LicenseToBe;
		this.primaryEmailAsIs = primaryEmailAsIs;
		this.aliasEmailAsIs = aliasEmailAsIs;
		this.recipentType = recipentType;
		this.mailboxSize = mailboxSize;
		this.primaryEmailToBe = primaryEmailToBe;
		this.aliasEmailToBe = aliasEmailToBe;
		this.sharedAccess = sharedAccess;
		this.calenderDelegates = calenderDelegates;
		this.messagingServer = messagingServer;
		this.messagingDatabase = messagingDatabase;
		this.corporateMobileDevice = corporateMobileDevice;
		this.userSkypeLyncAsIs = userSkypeLyncAsIs;
		this.userSkypeLyncToBe = userSkypeLyncToBe;
		this.skypeLyncPool = skypeLyncPool;
		this.computerName = computerName;
		this.isLaptopDesktop = isLaptopDesktop;
		this.isSCCMInstalled = isSCCMInstalled;
		this.computermodel = computermodel;
		this.operatingSystem = operatingSystem;
		this.operatingSystemVersion = operatingSystemVersion;
		this.isHardwareCompatableWin10 = isHardwareCompatableWin10;
		this.freeDiskSpace = freeDiskSpace;
		this.updateW10HardwareReplacement = updateW10HardwareReplacement;
		this.specifyABC = specifyABC;
		this.localApplication = localApplication;
		this.userHomeDrive = userHomeDrive;
		this.comments = comments;
		this.migrationStatus = migrationStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adDomain == null) ? 0 : adDomain.hashCode());
		result = prime * result + ((aliasEmailAsIs == null) ? 0 : aliasEmailAsIs.hashCode());
		result = prime * result + ((aliasEmailToBe == null) ? 0 : aliasEmailToBe.hashCode());
		result = prime * result + ((calenderDelegates == null) ? 0 : calenderDelegates.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
		result = prime * result + ((computermodel == null) ? 0 : computermodel.hashCode());
		result = prime * result + ((corporateMobileDevice == null) ? 0 : corporateMobileDevice.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((freeDiskSpace == null) ? 0 : freeDiskSpace.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isHardwareCompatableWin10 == null) ? 0 : isHardwareCompatableWin10.hashCode());
		result = prime * result + ((isLaptopDesktop == null) ? 0 : isLaptopDesktop.hashCode());
		result = prime * result + ((isSCCMInstalled == null) ? 0 : isSCCMInstalled.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((localApplication == null) ? 0 : localApplication.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((logIdAsIs == null) ? 0 : logIdAsIs.hashCode());
		result = prime * result + ((loginIdToBe == null) ? 0 : loginIdToBe.hashCode());
		result = prime * result + ((mailboxSize == null) ? 0 : mailboxSize.hashCode());
		result = prime * result + ((messagingDatabase == null) ? 0 : messagingDatabase.hashCode());
		result = prime * result + ((messagingServer == null) ? 0 : messagingServer.hashCode());
		result = prime * result + ((migrationStatus == null) ? 0 : migrationStatus.hashCode());
		result = prime * result + ((o365LicenseToBe == null) ? 0 : o365LicenseToBe.hashCode());
		result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
		result = prime * result + ((operatingSystemVersion == null) ? 0 : operatingSystemVersion.hashCode());
		result = prime * result + ((organizationalUnit == null) ? 0 : organizationalUnit.hashCode());
		result = prime * result + ((primaryEmailAsIs == null) ? 0 : primaryEmailAsIs.hashCode());
		result = prime * result + ((primaryEmailToBe == null) ? 0 : primaryEmailToBe.hashCode());
		result = prime * result + ((recipentType == null) ? 0 : recipentType.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((samAccountNameAsIs == null) ? 0 : samAccountNameAsIs.hashCode());
		result = prime * result + ((samAccountToBe == null) ? 0 : samAccountToBe.hashCode());
		result = prime * result + ((sharedAccess == null) ? 0 : sharedAccess.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((skypeLyncPool == null) ? 0 : skypeLyncPool.hashCode());
		result = prime * result + ((specifyABC == null) ? 0 : specifyABC.hashCode());
		result = prime * result
				+ ((updateW10HardwareReplacement == null) ? 0 : updateW10HardwareReplacement.hashCode());
		result = prime * result + ((userDisplayName == null) ? 0 : userDisplayName.hashCode());
		result = prime * result + ((userDisplayNameToBe == null) ? 0 : userDisplayNameToBe.hashCode());
		result = prime * result + ((userHomeDrive == null) ? 0 : userHomeDrive.hashCode());
		result = prime * result + ((userSkypeLyncAsIs == null) ? 0 : userSkypeLyncAsIs.hashCode());
		result = prime * result + ((userSkypeLyncToBe == null) ? 0 : userSkypeLyncToBe.hashCode());
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
		MastersheetDataDTO other = (MastersheetDataDTO) obj;
		if (adDomain == null) {
			if (other.adDomain != null)
				return false;
		} else if (!adDomain.equals(other.adDomain))
			return false;
		if (aliasEmailAsIs == null) {
			if (other.aliasEmailAsIs != null)
				return false;
		} else if (!aliasEmailAsIs.equals(other.aliasEmailAsIs))
			return false;
		if (aliasEmailToBe == null) {
			if (other.aliasEmailToBe != null)
				return false;
		} else if (!aliasEmailToBe.equals(other.aliasEmailToBe))
			return false;
		if (calenderDelegates == null) {
			if (other.calenderDelegates != null)
				return false;
		} else if (!calenderDelegates.equals(other.calenderDelegates))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (computerName == null) {
			if (other.computerName != null)
				return false;
		} else if (!computerName.equals(other.computerName))
			return false;
		if (computermodel == null) {
			if (other.computermodel != null)
				return false;
		} else if (!computermodel.equals(other.computermodel))
			return false;
		if (corporateMobileDevice == null) {
			if (other.corporateMobileDevice != null)
				return false;
		} else if (!corporateMobileDevice.equals(other.corporateMobileDevice))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (freeDiskSpace == null) {
			if (other.freeDiskSpace != null)
				return false;
		} else if (!freeDiskSpace.equals(other.freeDiskSpace))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isHardwareCompatableWin10 == null) {
			if (other.isHardwareCompatableWin10 != null)
				return false;
		} else if (!isHardwareCompatableWin10.equals(other.isHardwareCompatableWin10))
			return false;
		if (isLaptopDesktop == null) {
			if (other.isLaptopDesktop != null)
				return false;
		} else if (!isLaptopDesktop.equals(other.isLaptopDesktop))
			return false;
		if (isSCCMInstalled == null) {
			if (other.isSCCMInstalled != null)
				return false;
		} else if (!isSCCMInstalled.equals(other.isSCCMInstalled))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (localApplication == null) {
			if (other.localApplication != null)
				return false;
		} else if (!localApplication.equals(other.localApplication))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (logIdAsIs == null) {
			if (other.logIdAsIs != null)
				return false;
		} else if (!logIdAsIs.equals(other.logIdAsIs))
			return false;
		if (loginIdToBe == null) {
			if (other.loginIdToBe != null)
				return false;
		} else if (!loginIdToBe.equals(other.loginIdToBe))
			return false;
		if (mailboxSize == null) {
			if (other.mailboxSize != null)
				return false;
		} else if (!mailboxSize.equals(other.mailboxSize))
			return false;
		if (messagingDatabase == null) {
			if (other.messagingDatabase != null)
				return false;
		} else if (!messagingDatabase.equals(other.messagingDatabase))
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
		if (primaryEmailAsIs == null) {
			if (other.primaryEmailAsIs != null)
				return false;
		} else if (!primaryEmailAsIs.equals(other.primaryEmailAsIs))
			return false;
		if (primaryEmailToBe == null) {
			if (other.primaryEmailToBe != null)
				return false;
		} else if (!primaryEmailToBe.equals(other.primaryEmailToBe))
			return false;
		if (recipentType == null) {
			if (other.recipentType != null)
				return false;
		} else if (!recipentType.equals(other.recipentType))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (samAccountNameAsIs == null) {
			if (other.samAccountNameAsIs != null)
				return false;
		} else if (!samAccountNameAsIs.equals(other.samAccountNameAsIs))
			return false;
		if (samAccountToBe == null) {
			if (other.samAccountToBe != null)
				return false;
		} else if (!samAccountToBe.equals(other.samAccountToBe))
			return false;
		if (sharedAccess == null) {
			if (other.sharedAccess != null)
				return false;
		} else if (!sharedAccess.equals(other.sharedAccess))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
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
		if (updateW10HardwareReplacement == null) {
			if (other.updateW10HardwareReplacement != null)
				return false;
		} else if (!updateW10HardwareReplacement.equals(other.updateW10HardwareReplacement))
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
		if (userHomeDrive == null) {
			if (other.userHomeDrive != null)
				return false;
		} else if (!userHomeDrive.equals(other.userHomeDrive))
			return false;
		if (userSkypeLyncAsIs == null) {
			if (other.userSkypeLyncAsIs != null)
				return false;
		} else if (!userSkypeLyncAsIs.equals(other.userSkypeLyncAsIs))
			return false;
		if (userSkypeLyncToBe == null) {
			if (other.userSkypeLyncToBe != null)
				return false;
		} else if (!userSkypeLyncToBe.equals(other.userSkypeLyncToBe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MastersheetDataDTO [id=" + id + ", region=" + region + ", country=" + country + ", site=" + site
				+ ", location=" + location + ", userDisplayName=" + userDisplayName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", logIdAsIs=" + logIdAsIs + ", samAccountNameAsIs=" + samAccountNameAsIs
				+ ", adDomain=" + adDomain + ", organizationalUnit=" + organizationalUnit + ", userDisplayNameToBe="
				+ userDisplayNameToBe + ", loginIdToBe=" + loginIdToBe + ", samAccountToBe=" + samAccountToBe
				+ ", o365LicenseToBe=" + o365LicenseToBe + ", primaryEmailAsIs=" + primaryEmailAsIs
				+ ", aliasEmailAsIs=" + aliasEmailAsIs + ", recipentType=" + recipentType + ", mailboxSize="
				+ mailboxSize + ", primaryEmailToBe=" + primaryEmailToBe + ", aliasEmailToBe=" + aliasEmailToBe
				+ ", sharedAccess=" + sharedAccess + ", calenderDelegates=" + calenderDelegates + ", messagingServer="
				+ messagingServer + ", messagingDatabase=" + messagingDatabase + ", corporateMobileDevice="
				+ corporateMobileDevice + ", userSkypeLyncAsIs=" + userSkypeLyncAsIs + ", userSkypeLyncToBe="
				+ userSkypeLyncToBe + ", skypeLyncPool=" + skypeLyncPool + ", computerName=" + computerName
				+ ", isLaptopDesktop=" + isLaptopDesktop + ", isSCCMInstalled=" + isSCCMInstalled + ", computermodel="
				+ computermodel + ", operatingSystem=" + operatingSystem + ", operatingSystemVersion="
				+ operatingSystemVersion + ", isHardwareCompatableWin10=" + isHardwareCompatableWin10
				+ ", freeDiskSpace=" + freeDiskSpace + ", updateW10HardwareReplacement=" + updateW10HardwareReplacement
				+ ", specifyABC=" + specifyABC + ", localApplication=" + localApplication + ", userHomeDrive="
				+ userHomeDrive + ", comments=" + comments + ", migrationStatus=" + migrationStatus + "]";
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	
}
