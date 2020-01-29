package com.mindtree.migrationaccelerator.dto;

public class UserLocationDTO {

	private String Country;
	private String Site;
	private String Slot_Status;
	
	
	public  UserLocationDTO() {
		
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


	public String getSlot_Status() {
		return Slot_Status;
	}


	public void setSlot_Status(String slot_Status) {
		Slot_Status = slot_Status;
	}


	public UserLocationDTO(String country, String site, String slot_Status) {
		super();
		Country = country;
		Site = site;
		Slot_Status = slot_Status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Country == null) ? 0 : Country.hashCode());
		result = prime * result + ((Site == null) ? 0 : Site.hashCode());
		result = prime * result + ((Slot_Status == null) ? 0 : Slot_Status.hashCode());
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
		UserLocationDTO other = (UserLocationDTO) obj;
		if (Country == null) {
			if (other.Country != null)
				return false;
		} else if (!Country.equals(other.Country))
			return false;
		if (Site == null) {
			if (other.Site != null)
				return false;
		} else if (!Site.equals(other.Site))
			return false;
		if (Slot_Status == null) {
			if (other.Slot_Status != null)
				return false;
		} else if (!Slot_Status.equals(other.Slot_Status))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserLocationDTO [Country=" + Country + ", Site=" + Site + ", Slot_Status=" + Slot_Status + "]";
	}


	
}
