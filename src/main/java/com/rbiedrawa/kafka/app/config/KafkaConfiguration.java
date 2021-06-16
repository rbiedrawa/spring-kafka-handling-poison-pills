package com.rbiedrawa.kafka.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Bean
	public SeekToCurrentErrorHandler errorHandler(DeadLetterPublishingRecoverer dlqRecoverer) {
		return new SeekToCurrentErrorHandler(dlqRecoverer);
	}

	@Bean
	public DeadLetterPublishingRecoverer dlqRecoverer(KafkaTemplate<?, ?> bytesTemplate) {
		return new DeadLetterPublishingRecoverer(bytesTemplate);
	}
}
