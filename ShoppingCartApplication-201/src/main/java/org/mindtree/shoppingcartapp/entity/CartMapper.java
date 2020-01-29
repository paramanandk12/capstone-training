/**
 * 
 */
package org.mindtree.shoppingcartapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/**
 * @author M1048697
 *
 */

@Component
public class CartMapper {

	@OneToOne(mappedBy = "cart", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private UserEntity user = new UserEntity();

	@NotNull(message = "Product shoud not be null")
	@OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<ProductEnity> cartproducts = new ArrayList<ProductEnity>();

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<ProductEnity> getCartproducts() {
		return cartproducts;
	}

	public void setCartproducts(List<ProductEnity> cartproducts) {
		this.cartproducts = cartproducts;
	}
	
}
