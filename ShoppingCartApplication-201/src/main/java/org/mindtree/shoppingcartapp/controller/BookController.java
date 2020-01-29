/**
 * 
 */
package org.mindtree.shoppingcartapp.controller;

import java.util.Optional;

import org.mindtree.shoppingcartapp.entity.BookEntity;
import org.mindtree.shoppingcartapp.exception.DataNotFoundException;
import org.mindtree.shoppingcartapp.repository.BookRepository;
import org.mindtree.shoppingcartapp.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author M1048697
 *
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	BookRepository bookRepository;
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@PostMapping("/save")
	public ResponseEntity<BookEntity> addBookProduct(@RequestBody BookEntity book) throws Exception {
		logger.debug("in addBookProduct method calling service ");
		BookEntity newbook = bookService.addBookProduct(book);
		logger.debug(" book added successfully..  ");
		return new ResponseEntity<BookEntity>(newbook, HttpStatus.ACCEPTED);

	}

	
	@PutMapping(value = "/update/{productId}")
	public ResponseEntity<BookEntity> updateBookProductQuantity(@RequestBody BookEntity book,
			@PathVariable("productId") int productId) throws Exception {
		logger.debug("in updateBookProductQuantity method calling service ");
		BookEntity newbook = bookService.updateBookProduct(book, productId);
		logger.debug(" book updated successfully..  ");
		return new ResponseEntity<BookEntity>(newbook, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{productId}")
	public void deleteBookProduct(@PathVariable(value = "productId") int productId) throws Exception {
		logger.debug("in deleteBookProduct method calling service ");
		Optional<BookEntity> optionalBook = bookRepository.findById(productId);
		if (optionalBook.isPresent()) {
			bookRepository.deleteById(productId);
			logger.debug(" book deleted successfully..  ");
		} else {
			throw new DataNotFoundException("there is no product by given id");
			
		}

	}

}
