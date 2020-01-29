/**
 * 
 */
package org.mindtree.shoppingcartapp.controller;

import java.util.Optional;

import org.mindtree.shoppingcartapp.entity.ApparalEntity;
import org.mindtree.shoppingcartapp.exception.DataNotFoundException;
import org.mindtree.shoppingcartapp.repository.ApparalRepository;
import org.mindtree.shoppingcartapp.services.ApparalService;
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
@RequestMapping("/apparal")
public class ApparalController {

	@Autowired
	ApparalService apparalService;
	@Autowired
	ApparalRepository apparelRepository;
	private static final Logger logger = LoggerFactory.getLogger(ApparalController.class);

	@PostMapping("/save")
	public ResponseEntity<ApparalEntity> addApparalProduct(@RequestBody ApparalEntity apparal) throws Exception {
		logger.debug("in addApparalProduct method calling service ");
		ApparalEntity newapparal = apparalService.addApparalProduct(apparal);
		logger.debug(" apparal added successfully..  ");
		return new ResponseEntity<ApparalEntity>(newapparal, HttpStatus.ACCEPTED);

	}

	@PutMapping(value = "/update/{productId}")
	public ResponseEntity<ApparalEntity> updateApparalProductQuantity(@RequestBody ApparalEntity apparal,
			@PathVariable("productId") int productId) throws Exception {
		logger.debug(" in updateApparalProductQuantity method calling service ");
		ApparalEntity newapparal = apparalService.updateApparalProduct(apparal, productId);
		logger.debug(" apparal updated successfully..  ");
		return new ResponseEntity<ApparalEntity>(newapparal, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{productId}")
	public void deleteApparalProduct(@PathVariable(value = "productId") int productId) throws Exception {

		logger.debug("in deleteApparalProduct method calling service");
		Optional<ApparalEntity> optionalBook = apparelRepository.findById(productId);
		logger.debug(" apparal founded..  ");
		if (optionalBook.isPresent()) {
			apparelRepository.deleteById(productId);
			logger.debug(" apparal deleted successfully..  ");
		} else {
			throw new DataNotFoundException("there is no product by given id");
		}

	}

}
