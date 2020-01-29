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
@Table(name = "user_location_details")
public class UserLocationDetails extends EventMetaData {

	@Id
	@Column(name = "user_location_details_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userLocationDetailsId;

	@Column(name = "region")
	private String Region;

	@Column(name = "country")
	private String Country;

	@Column(name = "site")
	private String Site;

	@Column(name = "location")
	private String Location;

	@Column(name = "place1")
	private String Place1;
	
	@Column(name = "place2")
	private String Place2;

	@OneToOne(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
	@JoinColumn(name = "mastersheet_data_id")
	private MasterSheetData masterSheetData;
	
	public UserLocationDetails() {}

	public UserLocationDetails(Integer userLocationDetailsId, String region, String country, String site,
			String location, String place1, String place2, MasterSheetData masterSheetData) {
		super();
		this.userLocationDetailsId = userLocationDetailsId;
		Region = region;
		Country = country;
		Site = site;
		Location = location;
		Place1 = place1;
		Place2 = place2;
		this.masterSheetData = masterSheetData;
	}

	public Integer getUserLocationDetailsId() {
		return userLocationDetailsId;
	}

	public void setUserLocationDetailsId(Integer userLocationDetailsId) {
		this.userLocationDetailsId = userLocationDetailsId;
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

	public String getPlace1() {
		return Place1;
	}

	public void setPlace1(String place1) {
		Place1 = place1;
	}

	public String getPlace2() {
		return Place2;
	}

	public void setPlace2(String place2) {
		Place2 = place2;
	}

	public MasterSheetData getMasterSheetData() {
		return masterSheetData;
	}

	public void setMasterSheetData(MasterSheetData masterSheetData) {
		this.masterSheetData = masterSheetData;
	}

}