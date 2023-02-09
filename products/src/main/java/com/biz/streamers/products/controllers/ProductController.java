package com.biz.streamers.products.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biz.streamers.products.entities.Product;
import com.biz.streamers.products.entities.Products;
import com.biz.streamers.products.services.ProductService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class ProductController {

//   private final KafkaProducer kafkaProducer = new KafkaProducer(null);
  
@Autowired
ProductService service;
    
    // @GetMapping("/products/test")
    // private void getProductsTest(){
    //     String URL = "https://dummyjson.com/products";
     
    //     RestTemplate rt = new RestTemplate();
    //     Products products = rt.getForObject(URL, Products.class);

    //     System.out.println(products);
    // } 

    @GetMapping("/products")
    public Products getProducts(){
        return service.getAllProducts();
    } 

    @PostMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product addProducts(@RequestBody Product product) {
        System.out.println("adding product to db");
        service.addProduct(product);
        return product;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") int id){
        return service.getProductById(id);
    } 

    @PutMapping(value="/product/{id}")
    public Product putMethodName(@PathVariable String id, @RequestBody Product product) {  
        service.putProduct(product);     
        return product;
    }
}
