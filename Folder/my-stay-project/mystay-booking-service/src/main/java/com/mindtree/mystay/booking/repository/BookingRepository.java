package com.mindtree.mystay.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.mystay.booking.entity.BookingEntity;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@Repository
public interface BookingRepository extends MongoRepository<BookingEntity, String>{

}
