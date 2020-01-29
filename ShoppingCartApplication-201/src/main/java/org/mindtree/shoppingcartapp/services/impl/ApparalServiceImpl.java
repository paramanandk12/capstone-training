/**
 * 
 */
package org.mindtree.shoppingcartapp.services.impl;

import java.util.Optional;

import org.mindtree.shoppingcartapp.entity.ApparalEntity;
import org.mindtree.shoppingcartapp.exception.DataNotFoundException;
import org.mindtree.shoppingcartapp.repository.ApparalRepository;
import org.mindtree.shoppingcartapp.services.ApparalService;
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
public class ApparalServiceImpl implements ApparalService {

	@Autowired
	ApparalRepository apparalRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ApparalServiceImpl.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ApparalEntity addApparalProduct(ApparalEntity apparal) throws Exception {

		ApparalEntity newApparal = apparal;
		
		try {
			newApparal.setQuantity(apparal.getQuantity());
			if (newApparal.getQuantity() > 1) {
				newApparal.setProductPrice(apparal.getProductPrice() * apparal.getQuantity());
			}
			logger.info("in addApparalProduct method calling service ");
			apparal = apparalRepository.save(apparal);
			logger.info(" apparal added successfully..  ");
			if (apparal == null) {
				throw new DataNotFoundException("Product is not available in the cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apparal;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ApparalEntity updateApparalProduct(ApparalEntity apparal, int productId) throws Exception {
		logger.info("in updateApparalProduct method calling service ");
		Optional<ApparalEntity> optionalProduct = apparalRepository.findById(productId);
		logger.info(" apparal updated successfully..  ");
		ApparalEntity newApparal = optionalProduct.get();
		try {
			if (optionalProduct.isPresent()) {
				newApparal.setQuantity(apparal.getQuantity());
				if (newApparal.getQuantity() > 1) {
					newApparal.setProductPrice(apparal.getProductPrice() * apparal.getQuantity());

					System.out.println(apparal.getProductPrice() * apparal.getQuantity());
				}

				else {
					throw new DataNotFoundException("Product is not available in the cart");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newApparal;
	}

}
