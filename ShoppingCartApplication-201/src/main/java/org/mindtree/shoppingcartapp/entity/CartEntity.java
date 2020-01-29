package org.mindtree.shoppingcartapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author M1048697
 *
 */
@Entity
@Table(name = "cart")
public class CartEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer userCartId;
	
	

	public CartEntity() {
		super();
	}


	public Integer getUserCartId() {
		return userCartId;
	}

	public void setUserCartId(Integer userCartId) {
		this.userCartId = userCartId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userCartId == null) ? 0 : userCartId.hashCode());
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
		CartEntity other = (CartEntity) obj;
		if (userCartId == null) {
			if (other.userCartId != null)
				return false;
		} else if (!userCartId.equals(other.userCartId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CartEntity [userCartId=" + userCartId + "]";
	}


	

	



	
}
