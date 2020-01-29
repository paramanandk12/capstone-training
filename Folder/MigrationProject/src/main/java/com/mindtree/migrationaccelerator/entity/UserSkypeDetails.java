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
@Table(name = "user_skype_details")
public class UserSkypeDetails extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_skype_details_id", unique = true, nullable = false)
	private Integer userSkypeDetailsId;

	@Column(name = "user_skype_lync_id_as_is")
	private String userSkypeLyncIdAsIs;

	@Column(name = "user_skype_lync_id_to_be")
	private String userSkypeLyncIdToBe;

	@Column(name = "skype_lync_pool")
	private String skypeLyncPool;

	@Column(name = "place1")
	private String place1;

	@Column(name = "place2")
	private String place2;

	@OneToOne(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
	@JoinColumn(name = "mastersheet_data_id")
	private MasterSheetData masterSheetData;

	public UserSkypeDetails() {}

	public UserSkypeDetails(Integer userSkypeDetailsId, String userSkypeLyncIdAsIs, String userSkypeLyncIdToBe,
			String skypeLyncPool, String place1, String place2, MasterSheetData masterSheetData) {
		super();
		this.userSkypeDetailsId = userSkypeDetailsId;
		this.userSkypeLyncIdAsIs = userSkypeLyncIdAsIs;
		this.userSkypeLyncIdToBe = userSkypeLyncIdToBe;
		this.skypeLyncPool = skypeLyncPool;
		this.place1 = place1;
		this.place2 = place2;
		this.masterSheetData = masterSheetData;
	}

	public Integer getUserSkypeDetailsId() {
		return userSkypeDetailsId;
	}

	public void setUserSkypeDetailsId(Integer userSkypeDetailsId) {
		this.userSkypeDetailsId = userSkypeDetailsId;
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
