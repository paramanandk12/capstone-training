package org.mindtree.shoppingcartapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * @author M1048697
 *
 */
@Entity
public class ApparalEntity extends ProductEnity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String apparalType;

	@Column
	private String apparalName;

	@Column
	private String apparalBrand;

	public ApparalEntity() {
		super();
	}

	public String getApparalType() {
		return apparalType;
	}

	public void setApparalType(String apparalType) {
		this.apparalType = apparalType;
	}

	public String getApparalName() {
		return apparalName;
	}

	public void setApparalName(String apparalName) {
		this.apparalName = apparalName;
	}

	public String getApparalBrand() {
		return apparalBrand;
	}

	public void setApparalBrand(String apparalBrand) {
		this.apparalBrand = apparalBrand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apparalBrand == null) ? 0 : apparalBrand.hashCode());
		result = prime * result + ((apparalName == null) ? 0 : apparalName.hashCode());
		result = prime * result + ((apparalType == null) ? 0 : apparalType.hashCode());
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
		ApparalEntity other = (ApparalEntity) obj;
		if (apparalBrand == null) {
			if (other.apparalBrand != null)
				return false;
		} else if (!apparalBrand.equals(other.apparalBrand))
			return false;
		if (apparalName == null) {
			if (other.apparalName != null)
				return false;
		} else if (!apparalName.equals(other.apparalName))
			return false;
		if (apparalType == null) {
			if (other.apparalType != null)
				return false;
		} else if (!apparalType.equals(other.apparalType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApparalEntity [apparalType=" + apparalType + ", apparalName=" + apparalName + ", apparalBrand="
				+ apparalBrand + "]";
	}

	
	
}
