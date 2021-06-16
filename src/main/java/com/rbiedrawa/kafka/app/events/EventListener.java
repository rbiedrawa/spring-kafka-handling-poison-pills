package com.rbiedrawa.kafka.app.events;

import static com.rbiedrawa.kafka.app.config.KafkaTopicsConfiguration.EVENTS_TOPIC;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventListener {

	@KafkaListener(topics = EVENTS_TOPIC)
	public void handle(Event event) {
		log.info("{} received", event);
	}

}
