# Protecting against poison pills in Apache Kafka applications (demo)

This sample project demonstrates how to protect Apache Kafka applications against poison pills (Deserialization exceptions).

Kafka Consumer and KStream configuration included.

## Getting Started

### Prerequisites

* Java 11
* Docker

### Usage

* Start docker-compose.
  ```shell
  docker compose -f docker/docker-compose.yml up -d
  ```

* Start application.
  ```shell
  ./gradlew bootRun
  ```

* Produce valid json messages to Consumer and KStreams.
  ```shell
  # Send message to `events` topic (Kafka Consumer)
  (cd docker && docker-compose exec broker bash -c "echo '{\"message\": \"Hello from Kafka Consumer\"}' | kafka-console-producer --broker-list localhost:9092 --topic events")
  
  # Send message to `kstream.events` topic (Kafka Streams)
  (cd docker && docker-compose exec broker bash -c "echo '{\"message\": \"Hello from Kafka Streams\"}' | kafka-console-producer --broker-list localhost:9092 --topic kstream.events")
  ```

* Verify that messages were successfully handled.
  ```shell
  # 2021-06-17 12:32:36.521  INFO 2947 --- [ntainer#0-0-C-1] c.r.kafka.app.events.EventListener       : Event(message=Hello from Kafka Consumer) received
  # [KSTREAM-SOURCE-0000000000]: null, Event(message=Hello from Kafka Streams)
  ```

* Produce **poison pills**.
  ```shell
  # Send poison pill to 'events' topic (Kafka Consumer)
  (cd docker && docker-compose exec broker bash -c "echo 'poison pill' | kafka-console-producer --broker-list localhost:9092 --topic events")
   
  # Send poison pill to 'kstream.events' topic (Kafka Streams)
  (cd docker && docker-compose exec broker bash -c "echo 'poison pill' | kafka-console-producer --broker-list localhost:9092 --topic kstream.events")
  ```

* Open your web browser and go to [Kowl Web UI topic page](http://localhost:8080/topics).

* Confirm that **poison pills** were successfully handled by checking the content of dead letter topics (`events.DLT`
  and `kstream.events.DLT`).
  
## References

* [Streaming Apps and Poison Pills: handle the unexpected with Kafka Streams](https://www.confluent.io/kafka-summit-san-francisco-2019/streaming-apps-and-poison-pills-handle-the-unexpected-with-kafka-streams/)

## License

Distributed under the MIT License. See `LICENSE` for more information.
