package com.biz.streamers.products.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.biz.streamers.products.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductConsumer {

    // private static final Logger log = LoggerFactory.getLogger(ProductConsumer.class.getSimpleName());



    // private KafkaConsumer<String, String> consumer;

    private final String URL = "http://localhost:8090/product";
    private final String URL2 = "http://localhost:8091/kafka/publish";
    
    // public ProductConsumer(KafkaConsumer<String, String> consumer){
    //     this.consumer = consumer;

    // }
   
    @KafkaListener(topics = "spring.boot.kafka.stream.output")
    public void consumeProducts(String data){
        System.out.println(data);
        System.out.println("*********************************");

        
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product productData = mapper.readValue(data, Product.class);
            RestTemplate rt =  new RestTemplate();
            rt.postForEntity(URL, productData, Product.class);
            rt.postForEntity(URL2, productData, String.class);
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }
}
