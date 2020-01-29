/**
 * 
 */
package org.mindtree.shoppingcartapp.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.mindtree.shoppingcartapp.entity.ProductEnity;
import org.mindtree.shoppingcartapp.exception.DataNotFoundException;
import org.mindtree.shoppingcartapp.repository.SearchRepository;
import org.mindtree.shoppingcartapp.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M1048697
 *
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchRepository searchRepository;
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Transactional(value = TxType.MANDATORY)
	@Override
	public ProductEnity searchProductById(int productId) throws Exception {
		logger.info("in searchProductById method calling service ");
		Optional<ProductEnity> optionalProduct = searchRepository.findById(productId);
		logger.info("product founded ");
		ProductEnity newProduct = optionalProduct.get();
		try {
			if (!optionalProduct.isPresent()) {
				throw new DataNotFoundException("Product is not available in cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newProduct;
	}

	@Transactional(value = TxType.MANDATORY)
	@Override
	public List<ProductEnity> searchProductByName(String productName) throws Exception {
		logger.info("in searchProductByName method calling service ");
		List<ProductEnity> product = searchRepository.findProductByName(productName);
		logger.info("founded product name ");
		try {
			if (product == null) {
				throw new DataNotFoundException("Product is not available in cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Transactional(value = TxType.MANDATORY)
	@Override
	public List<ProductEnity> searchProductByCategory(String category) throws Exception {
		logger.info("in searchProductByCategory method calling service ");
		List<ProductEnity> product = searchRepository.findProductByCategory(category);
		logger.info("product founded by category");
		try {
			if (product == null) {
				throw new DataNotFoundException("Product is not available in cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

}
