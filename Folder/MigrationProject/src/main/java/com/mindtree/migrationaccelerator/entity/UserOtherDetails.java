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
@Table(name = "user_other_details")
public class UserOtherDetails extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_other_details_id", unique = true, nullable = false)
	private Integer userOtherDetailsId;

	@Column(name = "home_drive_data_size")
	private String homeDriveDataSize;

	@Column(name = "comments")
	private String comments;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@OneToOne(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
	@JoinColumn(name = "mastersheet_data_id")
	private MasterSheetData masterSheetData;

	public UserOtherDetails() {}

	public UserOtherDetails(Integer userOtherDetailsId, String homeDriveDataSize, String comments, String place1,
			String place2, MasterSheetData masterSheetData) {
		super();
		this.userOtherDetailsId = userOtherDetailsId;
		this.homeDriveDataSize = homeDriveDataSize;
		this.comments = comments;
		this.place1 = place1;
		this.place2 = place2;
		this.masterSheetData = masterSheetData;
	}

	public Integer getUserOtherDetailsId() {
		return userOtherDetailsId;
	}

	public void setUserOtherDetailsId(Integer userOtherDetailsId) {
		this.userOtherDetailsId = userOtherDetailsId;
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

	public MasterSheetData getMasterSheetData() {
		return masterSheetData;
	}

	public void setMasterSheetData(MasterSheetData masterSheetData) {
		this.masterSheetData = masterSheetData;
	}

}
