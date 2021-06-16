package com.rbiedrawa.kafka.app.events;

import static com.rbiedrawa.kafka.app.config.KafkaTopicsConfiguration.KSTREAM_EVENT_TOPIC;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;

@Component
public class EventKStream {

	@Bean
	public KStream eventStream(StreamsBuilder streamsBuilder) {
		var stream = streamsBuilder.stream(KSTREAM_EVENT_TOPIC);
		stream.print(Printed.toSysOut());
		return stream;
	}
}
