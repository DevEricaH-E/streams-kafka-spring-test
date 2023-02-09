package com.biz.streamers.products.repository;

import org.springframework.stereotype.Repository;

import com.biz.streamers.products.entities.Product;


import org.springframework.data.repository.CrudRepository;;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
