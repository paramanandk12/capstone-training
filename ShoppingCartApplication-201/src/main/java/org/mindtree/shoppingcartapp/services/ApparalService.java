/**
 * 
 */
package org.mindtree.shoppingcartapp.services;

import org.mindtree.shoppingcartapp.entity.ApparalEntity;

/**
 * @author M1048697
 *
 */
public interface ApparalService {
	
	
	ApparalEntity addApparalProduct(ApparalEntity apparal) throws Exception;

	ApparalEntity updateApparalProduct(ApparalEntity apparal, int productId) throws Exception;

}
