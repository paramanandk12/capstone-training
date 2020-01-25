package com.eatza.order.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MassegeSender {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value="${kafka-topic}")
	private String topic;
	
	public void sendMessage(String massege) {
		kafkaTemplate.send(topic, massege);
	}
	
}
