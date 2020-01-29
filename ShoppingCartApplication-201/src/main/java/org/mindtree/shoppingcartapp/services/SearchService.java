/**
 * 
 */
package org.mindtree.shoppingcartapp.services;

import java.util.List;

import org.mindtree.shoppingcartapp.entity.ProductEnity;

/**
 * @author M1048697
 *
 */
public interface SearchService {

	public ProductEnity searchProductById(int productId) throws Exception;

	public List<ProductEnity> searchProductByName(String productName) throws Exception;

	List<ProductEnity> searchProductByCategory(String category) throws Exception;

}
