package com.mindtree.migrationaccelerator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mindtree.migrationaccelerator.enums.MigrationStatus;

@Entity
@Table(name = "user_account_details")
public class UserAccountDetails extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_account_details_id", unique = true, nullable = false)
	private Integer userAccountDetailsId;

	@Column(name = "user_display_name")
	private String userDisplayName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "login_id_upn_as_is", nullable = false, unique = true)
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

	@Column(name = "migration_status")
	private MigrationStatus migrationStatus;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "mastersheet_data_id")
	private MasterSheetData masterSheetData;

	public UserAccountDetails() {}

	public UserAccountDetails(Integer userAccountDetailsId, String userDisplayName, String firstName, String lastName,
			String loginIdUPNasIs, String samAccountNameAsIs, String adDomain, String organizationalUnit,
			String userDisplayNameToBe, String loginIdUPNtoBe, String samAccountNameToBe, String o365LicenseToBe,
			MigrationStatus migrationStatus, String place1, String place2, Project project,
			MasterSheetData masterSheetData) {
		super();
		this.userAccountDetailsId = userAccountDetailsId;
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
		this.migrationStatus = migrationStatus;
		this.place1 = place1;
		this.place2 = place2;
		this.project = project;
		this.masterSheetData = masterSheetData;
	}

	public Integer getUserAccountDetailsId() {
		return userAccountDetailsId;
	}

	public void setUserAccountDetailsId(Integer userAccountDetailsId) {
		this.userAccountDetailsId = userAccountDetailsId;
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

	public MigrationStatus getMigrationStatus() {
		return migrationStatus;
	}

	public void setMigrationStatus(MigrationStatus migrationStatus) {
		this.migrationStatus = migrationStatus;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public MasterSheetData getMasterSheetData() {
		return masterSheetData;
	}

	public void setMasterSheetData(MasterSheetData masterSheetData) {
		this.masterSheetData = masterSheetData;
	}

}