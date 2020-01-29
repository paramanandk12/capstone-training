package com.mindtree.mystay.catalog.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.mystay.catalog.entity.HotelEntity;
import com.mindtree.mystay.catalog.exceptions.HotelNotFound;
import com.mindtree.mystay.catalog.model.SearchRequest;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@Repository
@Transactional
public class CatalogServiceRepositoryImpl implements CatalogServiceRepository {
	private static final Logger logger = LoggerFactory.getLogger(CatalogServiceRepositoryImpl.class);
	private static final String HOTEL_ID = "hotelId";
	private final MongoTemplate mongoTemplate;

	@Autowired
	public CatalogServiceRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<HotelEntity> getHotels(SearchRequest searchRequest) {
		List<HotelEntity> hotelList = new ArrayList<>();
		try {
			hotelList = mongoTemplate.find(
					new Query().addCriteria(
							new Criteria().orOperator(Criteria.where("address.street").is(searchRequest.getLocation()),
									Criteria.where("address.city").is(searchRequest.getLocation()))),
					HotelEntity.class);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return hotelList;
	}

	@Override
	public List<HotelEntity> addHotels(List<HotelEntity> hotelList) {
		List<HotelEntity> hotels = new ArrayList<>();
		hotelList.stream().forEach(hotel -> hotels.add(mongoTemplate.save(hotel)));

		return hotels;
	}

	@Override
	public HotelEntity updateHotel(HotelEntity hotel) {
		return mongoTemplate.save(hotel);
	}

	@Override
	public HotelEntity getHotelById(String hotelId) {
		return mongoTemplate.findOne(new Query().addCriteria(Criteria.where(HOTEL_ID).is(hotelId)), HotelEntity.class);
	}

	@Override
	public String deleteHotel(String hotelId) {
		String msg = "";
		try {
			HotelEntity entity = mongoTemplate.findOne(new Query().addCriteria(Criteria.where(HOTEL_ID).is(hotelId)),
					HotelEntity.class);
			if (entity != null) {
				mongoTemplate.remove(new Query().addCriteria(Criteria.where(HOTEL_ID).is(hotelId)), HotelEntity.class);
				msg = "Hotel deleted Succesfully.";
			} else {
				throw new HotelNotFound("Please select proper hotel.");
			}
		} catch (HotelNotFound ex) {
			msg = "Please select proper hotel.";
		} catch (Exception e) {
			msg = "Hotel cannot be deleted.";
		}
		return msg;
	}

}
