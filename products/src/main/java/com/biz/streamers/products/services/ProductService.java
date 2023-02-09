package com.biz.streamers.products.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.streamers.products.entities.Product;
import com.biz.streamers.products.entities.Products;
import com.biz.streamers.products.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product getProductById(int id) {
        return repository.findById(id).get();
    }

    public void addProduct(Product product) {
        System.out.println("saving");
        repository.save(product);
    }

    public void putProduct(Product product) {
        System.out.println("putting");
        Product oldProduct = repository.findById(product.getId()).get();

        if (oldProduct != null) {
            product.setMonthlyRevenue(product.getMonthlyRevenue());
            product.setYearlyRevenue(product.getYearlyRevenue());
        }
        repository.save(product);
    }

    public Products getAllProducts() {
        Products productsObj = new Products();
        List<Product> list = new ArrayList<>();
        

        try {
            Iterable<Product> it = repository.findAll();
            it.forEach((p) -> {
                list.add(p);
            });
            productsObj.setProducts(list);
        } catch (Exception e) {
            System.out.println(e);
        }

        return productsObj;
    }

}
