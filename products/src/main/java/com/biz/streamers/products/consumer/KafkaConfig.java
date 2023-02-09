package com.biz.streamers.products.consumer;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaConfig {

    private String bootstrapAddress = "localhost:9092";

    @Bean
    public Properties consumerFactory() {
        Properties configProps = new Properties();
        configProps.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-kafka-consumer-id");
        return  configProps;
    }

	@Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        return new KafkaConsumer <>(consumerFactory());
    }
}