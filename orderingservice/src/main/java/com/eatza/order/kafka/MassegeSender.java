package com.eatza.order.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MassegeSender {

	@Autowired
	private KafkaTemplate<String, SenderData> kafkaTemplate;
	private static final Logger LOG = LoggerFactory.getLogger(MassegeSender.class);

	@Value(value = "${kafka-topic}")
	private String topic;

	public void sendMessage(SenderData data) {
		LOG.info("sending data='{}' to topic='{}'", data, topic);

		Message<SenderData> message = MessageBuilder.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, topic).build();
		
		kafkaTemplate.send(message);
	}

}
