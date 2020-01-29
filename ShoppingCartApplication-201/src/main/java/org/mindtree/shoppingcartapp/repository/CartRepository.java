package org.mindtree.shoppingcartapp.repository;

import org.mindtree.shoppingcartapp.entity.ProductEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author M1048697
 *
 */
@Repository
public interface CartRepository extends JpaRepository<ProductEnity, Integer> {

}
