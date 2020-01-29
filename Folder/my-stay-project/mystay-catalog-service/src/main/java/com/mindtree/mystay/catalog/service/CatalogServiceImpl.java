package com.mindtree.mystay.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.mystay.catalog.config.RedisUtil;
import com.mindtree.mystay.catalog.entity.HotelEntity;
import com.mindtree.mystay.catalog.entity.RoomsEntity;
import com.mindtree.mystay.catalog.model.OfferRequest;
import com.mindtree.mystay.catalog.model.SearchRequest;
import com.mindtree.mystay.catalog.redis.RedisCacheManager;
import com.mindtree.mystay.catalog.repository.CatalogServiceRepository;
import com.mindtree.mystay.catalog.util.RedisName;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@Service
public class CatalogServiceImpl implements CatalogService {
	private static final Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);

	@Autowired
	private CatalogServiceRepository catalogServiceRepository;
	private RedisCacheManager redisCacheManger;
	@Autowired
	private RedisUtil<HotelEntity> redisUtil;

	@Autowired
	public CatalogServiceImpl(RedisCacheManager redisCacheManger) {
		super();
		this.redisCacheManger = redisCacheManger;
	}

	@Override
	public List<HotelEntity> getHotels(SearchRequest searchRequest) {

		List<HotelEntity> hotels = null;
		try {
			Map<Object, HotelEntity> cacheData = redisUtil
					.getMapAsAll(RedisName.HOTEL_BY_LOCATION + searchRequest.getLocation());

			if (cacheData != null) {
				if (cacheData.isEmpty()) {
					hotels = catalogServiceRepository.getHotels(searchRequest);
					hotels.parallelStream().forEach(
							hotel -> redisCacheManger.cacheHotelByLocation(hotel, searchRequest.getLocation()));
				} else {
					hotels = cacheData.values().stream().collect(Collectors.toList());
				}
			}
		} catch (Exception ex) {
			logger.debug("Exception in getHotels()->" + ex);
		}
		return hotels;
	}

	@Override
	public List<HotelEntity> addHotels(List<HotelEntity> hotelList) {
		return catalogServiceRepository.addHotels(hotelList);
	}

	@Override
	public HotelEntity updateHotel(String hotelId, HotelEntity hotel) {

		HotelEntity fetchedHotel = catalogServiceRepository.getHotelById(hotelId);
		if (hotel != null) {
			if (hotel.getAddress() != null) {
				if (hotel.getAddress().getCity() != null && hotel.getAddress().getCity() != "") {
					fetchedHotel.getAddress().setCity(hotel.getAddress().getCity());
				}
				if (hotel.getAddress().getPin() != null) {
					fetchedHotel.getAddress().setPin(hotel.getAddress().getPin());
				}
				if (hotel.getAddress().getStreet() != null && hotel.getAddress().getStreet() != "") {
					fetchedHotel.getAddress().setStreet(hotel.getAddress().getStreet());
				}
				if (hotel.getAddress().getState() != null && hotel.getAddress().getState() != "") {
					fetchedHotel.getAddress().setState(hotel.getAddress().getState());
				}
			}
			if (hotel.getHotelName() != null && hotel.getHotelName() != "") {
				fetchedHotel.setHotelName(hotel.getHotelName());
			}
			if (hotel.getDistance() != null) {
				fetchedHotel.setDistance(hotel.getDistance());
			}
		}
		return catalogServiceRepository.updateHotel(fetchedHotel);
	}

	@Override
	public String deleteHotel(String hotelId) {
		return catalogServiceRepository.deleteHotel(hotelId);
	}

	@Override
	public String upgradeOffer(String hotelId, OfferRequest offerRequest) {

		HotelEntity fetchedHotel = catalogServiceRepository.getHotelById(hotelId);
		if ("delete".equals(offerRequest.getOperation())) {
			List<String> offers = fetchedHotel.getOffers();
			List<String> filteredOffers = offers.stream().filter(offer -> !(offerRequest.getOffers().contains(offer)))
					.collect(Collectors.toList());
			fetchedHotel.setOffers(filteredOffers);

		} else {
			fetchedHotel.getOffers().addAll(offerRequest.getOffers());
		}

		HotelEntity updateHotel = catalogServiceRepository.updateHotel(fetchedHotel);
		if (updateHotel != null)
			return "Offers Updated";
		else
			return "Offers Not Updated";

	}

//	public static Predicate<RoomsEntity> isEqualRoomNo(){
//		return p -> ro
//	}

	@Override
	public HotelEntity addRooms(String hotelId, List<RoomsEntity> rooms) {
		HotelEntity hotelEntity = catalogServiceRepository.getHotelById(hotelId);
		List<RoomsEntity> roomEntity = hotelEntity.getRooms();
		List<RoomsEntity> addRoomList = new ArrayList<>();
		int flag = 1;
		if (rooms != null) {
			for (RoomsEntity room : rooms) {
				flag=1;
				for (RoomsEntity roomE : roomEntity) {
					if (roomE.getRoomNo().equals(room.getRoomNo())) {
						flag=0;
						break;
					} 
				}
				if(flag==1)
					addRoomList.add(room);
			}

			hotelEntity.getRooms().addAll(addRoomList);
		}

		return catalogServiceRepository.updateHotel(hotelEntity);
	}

	@Override
	public HotelEntity updateRoom(String hotelId, String roomId, RoomsEntity room) {
		HotelEntity hotelEntity = catalogServiceRepository.getHotelById(hotelId);
		List<RoomsEntity> roomList = hotelEntity.getRooms();
		for (RoomsEntity fetchedroom : roomList) {
			if (roomId.equals(fetchedroom.getRoomNo())) {
				if (room.getPrice() != null) {
					fetchedroom.setPrice(room.getPrice());
				}
				if (room.getAvailability() != null && room.getAvailability() != "") {
					fetchedroom.setAvailability(room.getAvailability());
				}
				if (room.getRoomType() != null && room.getRoomType() != "") {
					fetchedroom.setRoomType(room.getRoomType());
				}
				if (room.getServices() != null) {
					fetchedroom.setServices(room.getServices());
				}

			}
		}

		hotelEntity.setRooms(roomList);
		return catalogServiceRepository.updateHotel(hotelEntity);
	}

	@Override
	public HotelEntity deleteHotelRoom(String hotelId, String roomId) {
		HotelEntity updateHotel = null;
		try {
			HotelEntity hotelEntity = catalogServiceRepository.getHotelById(hotelId);
			List<RoomsEntity> roomList = hotelEntity.getRooms();
			List<RoomsEntity> roomUpdatedList = new ArrayList<>();
			for (RoomsEntity fetchedroom : roomList) {
				if (roomId.equals(fetchedroom.getRoomNo())) {
					continue;
				}
				roomUpdatedList.add(fetchedroom);
			}
			hotelEntity.setRooms(roomUpdatedList);
			updateHotel = catalogServiceRepository.updateHotel(hotelEntity);
		} catch (Exception e) {
			e.getMessage();

		}
		return updateHotel;
	}

	@Override
	public HotelEntity hotelById(String hotelId) {

		HotelEntity hotel = null;
		try {
			hotel = redisUtil.getMapAsSingleEntry(RedisName.HOTEL_BY_ID.toString(), RedisName.HOTEL_ID + hotelId);
			if (hotel == null) {
				hotel = catalogServiceRepository.getHotelById(hotelId);
				redisCacheManger.cacheHotelById(hotel, hotelId);
			}

		} catch (Exception ex) {
			logger.debug("Exception caught in hotelById() ->" + ex);
		}
		return hotel;
	}

}
