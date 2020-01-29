/**
 * 
 */
package org.mindtree.shoppingcartapp.services.impl;

import java.util.Optional;

import org.mindtree.shoppingcartapp.entity.BookEntity;
import org.mindtree.shoppingcartapp.exception.DataNotFoundException;
import org.mindtree.shoppingcartapp.repository.BookRepository;
import org.mindtree.shoppingcartapp.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author M1048697
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public BookEntity addBookProduct(BookEntity book) throws Exception {
		BookEntity newBook = book;
		newBook.setQuantity(book.getQuantity());
		try {
			if (newBook.getQuantity() > 1) {
				newBook.setProductPrice(book.getProductPrice() * book.getQuantity());
			}
			logger.info("in addBookProduct method calling service ");
			book = bookRepository.save(book);
			logger.info(" book added successfully..  ");
			if (book == null) {
				throw new DataNotFoundException("Book is not available in cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public BookEntity updateBookProduct(BookEntity book, int productId) throws Exception {
		Optional<BookEntity> optionalProduct = bookRepository.findById(productId);
		BookEntity newBook = optionalProduct.get();
		try {
			if (optionalProduct.isPresent()) {
				logger.info("in updateBookProduct method calling service ");
				newBook.setQuantity(book.getQuantity());
				if (newBook.getQuantity() > 1) {
					newBook.setProductPrice(book.getProductPrice() * book.getQuantity());
					logger.info("quantity updated .. "+book.getProductPrice() * book.getQuantity());
				}
			} else {
				throw new DataNotFoundException("Book is not available in cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newBook;
	}

}
