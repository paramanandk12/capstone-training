/**
 * 
 */
package org.mindtree.shoppingcartapp.controller;

import java.util.List;

import org.mindtree.shoppingcartapp.entity.ProductEnity;
import org.mindtree.shoppingcartapp.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author M1048697
 *
 */
@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	/* Search product by id */
	@GetMapping("productById/{productId}")
	public ResponseEntity<ProductEnity> searchProductById(@PathVariable(value = "productId") int productId)
			throws Exception {
		logger.debug("in searchProductById method calling service ");
		ProductEnity product = searchService.searchProductById(productId);
		logger.debug(" search product is founded  ");
		return new ResponseEntity<ProductEnity>(product, HttpStatus.OK);

	}

	/* Search product by name */
	@GetMapping("productByName/{productName}")
	public ResponseEntity<List<ProductEnity>> searchProductByName(
			@PathVariable(value = "productName") String productName) throws Exception {
		logger.debug("in searchProductByName method calling service ");
		List<ProductEnity> product = searchService.searchProductByName(productName);
		logger.debug(" search product is founded  ");
		return new ResponseEntity<List<ProductEnity>>(product, HttpStatus.OK);

	}

	/* Search product by category */
	@GetMapping("productByCategory/{category}")
	public ResponseEntity<List<ProductEnity>> searchProductByCategory(@PathVariable(value = "category") String category)
			throws Exception {
		logger.debug("in searchProductByCategory method calling service ");
		List<ProductEnity> product = searchService.searchProductByCategory(category);
		logger.debug(" search product is founded  ");
		return new ResponseEntity<List<ProductEnity>>(product, HttpStatus.OK);

	}

}
