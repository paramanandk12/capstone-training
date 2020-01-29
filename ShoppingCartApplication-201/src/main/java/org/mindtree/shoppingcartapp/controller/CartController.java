/**
 * 
 */
package org.mindtree.shoppingcartapp.controller;

import java.util.Map;

import org.mindtree.shoppingcartapp.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author M1048697
 *
 */
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	
	@GetMapping(value = "/getAll")
	public ResponseEntity<Map<String, Object>> getAllProductInCart() throws Exception {
		logger.debug("in getAllProductInCart method calling service ");
		Map<String, Object> map = cartService.getAllProduct();
		logger.debug(" founded all product..  ");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);

	}

}
