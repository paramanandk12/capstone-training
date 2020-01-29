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
@Table(name = "user_email_details")
public class UserEmailDetails extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_email_details_id", unique = true, nullable = false)
	private Integer userEmailDetailsId;

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

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@OneToOne(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
	@JoinColumn(name = "mastersheet_data_id")
	private MasterSheetData masterSheetData;

	public UserEmailDetails() {}

	public UserEmailDetails(Integer userEmailDetailsId, String primaryEmailAddressAsIs, String aliasEmailAddress,
			String recipientType, String mailboxSize, String primaryEmailAddressToBe, String aliasEmailAddressToBe,
			String sharedAccess, String calendarAccessDelegates, String messagingServer, String messagingDatabse,
			String corporateMobileDevices, String place1, String place2, MasterSheetData masterSheetData) {
		super();
		this.userEmailDetailsId = userEmailDetailsId;
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
		this.place1 = place1;
		this.place2 = place2;
		this.masterSheetData = masterSheetData;
	}

	public Integer getUserEmailDetailsId() {
		return userEmailDetailsId;
	}

	public void setUserEmailDetailsId(Integer userEmailDetailsId) {
		this.userEmailDetailsId = userEmailDetailsId;
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
