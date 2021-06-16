package com.rbiedrawa.kafka.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class KafkaTopicsConfiguration {
	private static final int DEFAULT_PARTITION_COUNT = 6;

	public static final String KSTREAM_EVENT_TOPIC = "kstream.events";
	public static final String EVENTS_TOPIC = "events";

	@Bean
	NewTopic eventsTopic() {
		return TopicBuilder.name(EVENTS_TOPIC)
						   .partitions(DEFAULT_PARTITION_COUNT)
						   .build();
	}

	@Bean
	NewTopic eventsDlqTopic() {
		return TopicBuilder.name(EVENTS_TOPIC + ".DLT")
						   .partitions(DEFAULT_PARTITION_COUNT)
						   .build();
	}

	@Bean
	NewTopic kstreamTopic() {
		return TopicBuilder.name(KSTREAM_EVENT_TOPIC)
						   .partitions(DEFAULT_PARTITION_COUNT)
						   .build();
	}

	@Bean
	NewTopic kstreamDlqTopic() {
		return TopicBuilder.name(KSTREAM_EVENT_TOPIC + ".DLT")
						   .partitions(DEFAULT_PARTITION_COUNT)
						   .build();
	}
}
