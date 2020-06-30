package com.springboot.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	@Value("${tpd.topic-one}")
    private String topicOne ;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String empId, String message) {
        logger.info(String.format("$$ -> Producing message --> %s", message));
        this.kafkaTemplate.send(topicOne, empId, message);
    }
}
