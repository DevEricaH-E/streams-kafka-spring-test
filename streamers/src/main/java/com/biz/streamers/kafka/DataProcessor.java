package com.biz.streamers.kafka;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class DataProcessor {

    @Autowired
    private StreamsBuilder streamsBuilder;

    @PostConstruct
    public void streamTopology() {
        KStream<String, String> kStream = streamsBuilder.stream("spring.boot.kafka.stream.input", Consumed.with(Serdes.String(), Serdes.String()));
        kStream.mapValues((k, v) -> this.transformData(v)).peek((k, v) -> System.out.println("Key : " + k + " Value : " + v)).to("spring.boot.kafka.stream.output", Produced.with(Serdes.String(), Serdes.String()));
    } 

    private String transformData(String data){

        ObjectMapper mapper = new ObjectMapper();
        try {
            Product product = mapper.readValue(data, Product.class);
            Random random = new Random();
		    product.setMonthlyRevenue(random.nextInt(100000) / 100.00); 
		    product.setYearlyRevenue(random.nextInt(100000) / 100.00);

            return mapper.writeValueAsString(product);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        
        return data;
    }

}
