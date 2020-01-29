package com.eatza.restaurantsearch.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MassegeReceiver {
	private static final Logger logger = LoggerFactory.getLogger(MassegeReceiver.class);

	
	@KafkaListener(topics = "${kafka-topic}", groupId="group_id", containerFactory="jsonkafkaListenerContainerFactory")
	public void receiveMessage(SenderData data) {

		System.out.println("received data='{}'"+ data);
		logger.info("received data='{}'", data);

	}

}
