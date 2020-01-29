/**
 * 
 */
package org.mindtree.shoppingcartapp.services;

import org.mindtree.shoppingcartapp.entity.BookEntity;

/**
 * @author M1048697
 *
 */
public interface BookService {

	BookEntity addBookProduct(BookEntity book) throws Exception;


	BookEntity updateBookProduct(BookEntity book, int productId) throws Exception;
	
}
