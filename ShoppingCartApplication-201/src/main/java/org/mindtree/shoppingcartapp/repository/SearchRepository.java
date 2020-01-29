package org.mindtree.shoppingcartapp.repository;

import java.util.List;

import org.mindtree.shoppingcartapp.entity.ProductEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @author M1048697
 *
 */
@Repository
public interface SearchRepository extends JpaRepository<ProductEnity, Integer> {
	
	@Query("SELECT p FROM Product p where p.productName = ?1")
	public List<ProductEnity> findProductByName(String productName);

	@Query("SELECT p FROM Product p where p.category = ?1")
	public List<ProductEnity> findProductByCategory(String category);

}
