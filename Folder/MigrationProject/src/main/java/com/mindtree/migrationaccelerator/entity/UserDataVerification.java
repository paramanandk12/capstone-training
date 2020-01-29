package com.mindtree.migrationaccelerator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.mindtree.migrationaccelerator.enums.ModificationStatus;

@Entity
@Table(name = "user_data_verification")
public class UserDataVerification extends EventMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_data_verification_id", unique = true, nullable = false)
	private Integer userDataVerificationId;
	
	@Column(name = "existing_first_name")
	private String existingFirstName;
	
	@Column(name = "modfied_first_name")
	private String modifiedFirstName;

	@Column(name = "existing_last_name")
	private String existingLastName;
	
	@Column(name = "modfied_last_name")
	private String modfiedLastName;
	
	@Column(name = "existing_country")
	private String existingCountry;
    
	@Column(name = "modfied_country")
	private String modfiedCountry;
	
	@Column(name = "existing_site")
	private String existingSite;
	
	@Column(name = "modfied_site")
	private String modfiedSite;
	
	@Column(name = "existing_sam_account_name_as_is" , nullable = false, unique = true)
	private String existingSamAccountNameAsIs;
	
	@Column(name = "modfied_sam_account_name_as_is" , nullable = false, unique = true)
	private String modfiedSamAccountNameAsIs;
	
	@Column(name = "login_id_upn_as_is")
	private String loginIdUPNasIs;
	
	@Column(name = "modification_status", nullable = false)
	private ModificationStatus modificationStatus;
	
	@Column(name = "comments")
	private String Comments;

	public UserDataVerification() {}

	public UserDataVerification(Integer userDataVerificationId, String existingFirstName, String modifiedFirstName,
			String existingLastName, String modfiedLastName, String existingCountry, String modfiedCountry,
			String existingSite, String modfiedSite, String existingSamAccountNameAsIs,
			String modfiedSamAccountNameAsIs, String loginIdUPNasIs, ModificationStatus modificationStatus,
			String comments) {
		super();
		this.userDataVerificationId = userDataVerificationId;
		this.existingFirstName = existingFirstName;
		this.modifiedFirstName = modifiedFirstName;
		this.existingLastName = existingLastName;
		this.modfiedLastName = modfiedLastName;
		this.existingCountry = existingCountry;
		this.modfiedCountry = modfiedCountry;
		this.existingSite = existingSite;
		this.modfiedSite = modfiedSite;
		this.existingSamAccountNameAsIs = existingSamAccountNameAsIs;
		this.modfiedSamAccountNameAsIs = modfiedSamAccountNameAsIs;
		this.loginIdUPNasIs = loginIdUPNasIs;
		this.modificationStatus = modificationStatus;
		Comments = comments;
	}

	public Integer getUserDataVerificationId() {
		return userDataVerificationId;
	}

	public void setUserDataVerificationId(Integer userDataVerificationId) {
		this.userDataVerificationId = userDataVerificationId;
	}

	public String getExistingFirstName() {
		return existingFirstName;
	}

	public void setExistingFirstName(String existingFirstName) {
		this.existingFirstName = existingFirstName;
	}

	public String getModifiedFirstName() {
		return modifiedFirstName;
	}

	public void setModifiedFirstName(String modifiedFirstName) {
		this.modifiedFirstName = modifiedFirstName;
	}

	public String getExistingLastName() {
		return existingLastName;
	}

	public void setExistingLastName(String existingLastName) {
		this.existingLastName = existingLastName;
	}

	public String getModfiedLastName() {
		return modfiedLastName;
	}

	public void setModfiedLastName(String modfiedLastName) {
		this.modfiedLastName = modfiedLastName;
	}

	public String getExistingCountry() {
		return existingCountry;
	}

	public void setExistingCountry(String existingCountry) {
		this.existingCountry = existingCountry;
	}

	public String getModfiedCountry() {
		return modfiedCountry;
	}

	public void setModfiedCountry(String modfiedCountry) {
		this.modfiedCountry = modfiedCountry;
	}

	public String getExistingSite() {
		return existingSite;
	}

	public void setExistingSite(String existingSite) {
		this.existingSite = existingSite;
	}

	public String getModfiedSite() {
		return modfiedSite;
	}

	public void setModfiedSite(String modfiedSite) {
		this.modfiedSite = modfiedSite;
	}

	public String getExistingSamAccountNameAsIs() {
		return existingSamAccountNameAsIs;
	}

	public void setExistingSamAccountNameAsIs(String existingSamAccountNameAsIs) {
		this.existingSamAccountNameAsIs = existingSamAccountNameAsIs;
	}

	public String getModfiedSamAccountNameAsIs() {
		return modfiedSamAccountNameAsIs;
	}

	public void setModfiedSamAccountNameAsIs(String modfiedSamAccountNameAsIs) {
		this.modfiedSamAccountNameAsIs = modfiedSamAccountNameAsIs;
	}

	public String getLoginIdUPNasIs() {
		return loginIdUPNasIs;
	}

	public void setLoginIdUPNasIs(String loginIdUPNasIs) {
		this.loginIdUPNasIs = loginIdUPNasIs;
	}

	public ModificationStatus getModificationStatus() {
		return modificationStatus;
	}

	public void setModificationStatus(ModificationStatus modificationStatus) {
		this.modificationStatus = modificationStatus;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Comments == null) ? 0 : Comments.hashCode());
		result = prime * result + ((existingCountry == null) ? 0 : existingCountry.hashCode());
		result = prime * result + ((existingFirstName == null) ? 0 : existingFirstName.hashCode());
		result = prime * result + ((existingLastName == null) ? 0 : existingLastName.hashCode());
		result = prime * result + ((existingSamAccountNameAsIs == null) ? 0 : existingSamAccountNameAsIs.hashCode());
		result = prime * result + ((existingSite == null) ? 0 : existingSite.hashCode());
		result = prime * result + ((loginIdUPNasIs == null) ? 0 : loginIdUPNasIs.hashCode());
		result = prime * result + ((modfiedCountry == null) ? 0 : modfiedCountry.hashCode());
		result = prime * result + ((modfiedLastName == null) ? 0 : modfiedLastName.hashCode());
		result = prime * result + ((modfiedSamAccountNameAsIs == null) ? 0 : modfiedSamAccountNameAsIs.hashCode());
		result = prime * result + ((modfiedSite == null) ? 0 : modfiedSite.hashCode());
		result = prime * result + ((modificationStatus == null) ? 0 : modificationStatus.hashCode());
		result = prime * result + ((modifiedFirstName == null) ? 0 : modifiedFirstName.hashCode());
		result = prime * result + ((userDataVerificationId == null) ? 0 : userDataVerificationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDataVerification other = (UserDataVerification) obj;
		if (Comments == null) {
			if (other.Comments != null)
				return false;
		} else if (!Comments.equals(other.Comments))
			return false;
		if (existingCountry == null) {
			if (other.existingCountry != null)
				return false;
		} else if (!existingCountry.equals(other.existingCountry))
			return false;
		if (existingFirstName == null) {
			if (other.existingFirstName != null)
				return false;
		} else if (!existingFirstName.equals(other.existingFirstName))
			return false;
		if (existingLastName == null) {
			if (other.existingLastName != null)
				return false;
		} else if (!existingLastName.equals(other.existingLastName))
			return false;
		if (existingSamAccountNameAsIs == null) {
			if (other.existingSamAccountNameAsIs != null)
				return false;
		} else if (!existingSamAccountNameAsIs.equals(other.existingSamAccountNameAsIs))
			return false;
		if (existingSite == null) {
			if (other.existingSite != null)
				return false;
		} else if (!existingSite.equals(other.existingSite))
			return false;
		if (loginIdUPNasIs == null) {
			if (other.loginIdUPNasIs != null)
				return false;
		} else if (!loginIdUPNasIs.equals(other.loginIdUPNasIs))
			return false;
		if (modfiedCountry == null) {
			if (other.modfiedCountry != null)
				return false;
		} else if (!modfiedCountry.equals(other.modfiedCountry))
			return false;
		if (modfiedLastName == null) {
			if (other.modfiedLastName != null)
				return false;
		} else if (!modfiedLastName.equals(other.modfiedLastName))
			return false;
		if (modfiedSamAccountNameAsIs == null) {
			if (other.modfiedSamAccountNameAsIs != null)
				return false;
		} else if (!modfiedSamAccountNameAsIs.equals(other.modfiedSamAccountNameAsIs))
			return false;
		if (modfiedSite == null) {
			if (other.modfiedSite != null)
				return false;
		} else if (!modfiedSite.equals(other.modfiedSite))
			return false;
		if (modificationStatus != other.modificationStatus)
			return false;
		if (modifiedFirstName == null) {
			if (other.modifiedFirstName != null)
				return false;
		} else if (!modifiedFirstName.equals(other.modifiedFirstName))
			return false;
		if (userDataVerificationId == null) {
			if (other.userDataVerificationId != null)
				return false;
		} else if (!userDataVerificationId.equals(other.userDataVerificationId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDataVerification [userDataVerificationId=" + userDataVerificationId + ", existingFirstName="
				+ existingFirstName + ", modifiedFirstName=" + modifiedFirstName + ", existingLastName="
				+ existingLastName + ", modfiedLastName=" + modfiedLastName + ", existingCountry=" + existingCountry
				+ ", modfiedCountry=" + modfiedCountry + ", existingSite=" + existingSite + ", modfiedSite="
				+ modfiedSite + ", existingSamAccountNameAsIs=" + existingSamAccountNameAsIs
				+ ", modfiedSamAccountNameAsIs=" + modfiedSamAccountNameAsIs + ", loginIdUPNasIs=" + loginIdUPNasIs
				+ ", modificationStatus=" + modificationStatus + ", Comments=" + Comments + "]";
	}

}
