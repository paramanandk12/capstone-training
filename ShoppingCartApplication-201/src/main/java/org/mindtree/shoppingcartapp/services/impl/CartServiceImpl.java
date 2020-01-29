package org.mindtree.shoppingcartapp.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.mindtree.shoppingcartapp.entity.ProductEnity;
import org.mindtree.shoppingcartapp.exception.DataNotFoundException;
import org.mindtree.shoppingcartapp.repository.CartRepository;
import org.mindtree.shoppingcartapp.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M1048697
 *
 */

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository cartRepository;
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	private Double totalSum;

	@SuppressWarnings("unused")
	@Override
	@Transactional(value = TxType.MANDATORY)
	public Map<String, Object> getAllProduct() throws Exception {
		
		logger.info("in getAllProduct method calling service ");
		List<ProductEnity> productlist = cartRepository.findAll();

		Map<String, Object> message = new HashMap<String, Object>();
		Double price[] = new Double[productlist.size()];
		ArrayList<Double> totalPrice = new ArrayList<Double>();
		ArrayList<Integer> totalItem = new ArrayList<Integer>();
		try {
			int i = 0;
			for (ProductEnity p : productlist) {
				price[i] = p.getProductPrice() * p.getQuantity();
				i++;
			}
			for (int j = 0; j < price.length; j++) {
				totalSum += price[j];
			}
			totalPrice.add(totalSum);
		    totalItem.add(productlist.size());
			logger.info("product size =="+productlist.size());
			logger.info("total size =="+totalPrice);
			System.out.println(productlist.size());
			System.out.println(totalPrice);

			message.put("List of Item", productlist);
			message.put("Total price of cart", totalPrice);
			message.put("Total item in a cart", totalItem);

			if (message == null) {
				throw new DataNotFoundException("Cart is empty");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

}
