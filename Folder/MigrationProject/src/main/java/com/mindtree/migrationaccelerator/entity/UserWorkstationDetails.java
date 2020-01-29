package com.mindtree.migrationaccelerator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_workstation_details")
public class UserWorkstationDetails extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_workstation_details_id", unique = true, nullable = false)
	private Integer userWorkstationDetailsId;

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

	@Column(name = "hardware_compatible_with_w10")
	private String hardwareCompatibleWithW10;

	@Column(name = "free_disk_space_mb")
	private String freeDiskSpaceMB;

	@Column(name = "update_to_win10_hardware_replacement")
	private String updateToWin10HardwareReplacement;

	@Column(name = "specify_abc")
	private String specifyABC;

	@Column(name = "local_applications")
	private String localApplications;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@OneToOne(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
	@JoinColumn(name = "mastersheet_data_id")
	private MasterSheetData masterSheetData;
	
	public UserWorkstationDetails() {}

	public UserWorkstationDetails(Integer userWorkstationDetailsId, String computerName, String isLaptopDesktop,
			String isSCCMInstalledActiveInactive, String computerModel, String operatingSystem,
			String operatingSystemVersion, String hardwareCompatibleWithW10, String freeDiskSpaceMB,
			String updateToWin10HardwareReplacement, String specifyABC, String localApplications, String place1,
			String place2, MasterSheetData masterSheetData) {
		super();
		this.userWorkstationDetailsId = userWorkstationDetailsId;
		this.computerName = computerName;
		this.isLaptopDesktop = isLaptopDesktop;
		this.isSCCMInstalledActiveInactive = isSCCMInstalledActiveInactive;
		this.computerModel = computerModel;
		this.operatingSystem = operatingSystem;
		this.operatingSystemVersion = operatingSystemVersion;
		this.hardwareCompatibleWithW10 = hardwareCompatibleWithW10;
		this.freeDiskSpaceMB = freeDiskSpaceMB;
		this.updateToWin10HardwareReplacement = updateToWin10HardwareReplacement;
		this.specifyABC = specifyABC;
		this.localApplications = localApplications;
		this.place1 = place1;
		this.place2 = place2;
		this.masterSheetData = masterSheetData;
	}

	public Integer getUserWorkstationDetailsId() {
		return userWorkstationDetailsId;
	}

	public void setUserWorkstationDetailsId(Integer userWorkstationDetailsId) {
		this.userWorkstationDetailsId = userWorkstationDetailsId;
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

	public String getHardwareCompatibleWithW10() {
		return hardwareCompatibleWithW10;
	}

	public void setHardwareCompatibleWithW10(String hardwareCompatibleWithW10) {
		this.hardwareCompatibleWithW10 = hardwareCompatibleWithW10;
	}

	public String getFreeDiskSpaceMB() {
		return freeDiskSpaceMB;
	}

	public void setFreeDiskSpaceMB(String freeDiskSpaceMB) {
		this.freeDiskSpaceMB = freeDiskSpaceMB;
	}

	public String getUpdateToWin10HardwareReplacement() {
		return updateToWin10HardwareReplacement;
	}

	public void setUpdateToWin10HardwareReplacement(String updateToWin10HardwareReplacement) {
		this.updateToWin10HardwareReplacement = updateToWin10HardwareReplacement;
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

	public MasterSheetData getMasterSheetData() {
		return masterSheetData;
	}

	public void setMasterSheetData(MasterSheetData masterSheetData) {
		this.masterSheetData = masterSheetData;
	}

}
