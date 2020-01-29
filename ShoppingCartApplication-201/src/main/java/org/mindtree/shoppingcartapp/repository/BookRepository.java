package org.mindtree.shoppingcartapp.repository;

import org.mindtree.shoppingcartapp.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author M1048697
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}
