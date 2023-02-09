package com.biz.streamers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;


@SpringBootApplication
@EnableKafka
@EnableKafkaStreams
public class StreamersApplication {


	public static void main(String[] args) {
		SpringApplication.run(StreamersApplication.class, args);
		System.out.println("APPLICATION STARTED");

	}

}
