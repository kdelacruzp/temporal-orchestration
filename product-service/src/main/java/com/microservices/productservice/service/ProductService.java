package com.microservices.productservice.service;

import com.microservices.productservice.model.entity.ProductEntity;
import com.microservices.productservice.service.repository.ProductRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
//import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

  //@Inject
  //EntityManager em;

  @Inject
  ProductRepository productRepository;

  public List<ProductEntity> getAllProducts() {
    return productRepository.listAll();
  }

  public ProductEntity saveProduct(ProductEntity productEntity) {
    productRepository.saveProduct(productEntity);
    return productEntity;
  }

  public ProductEntity updateProduct(String productId, Integer quantity) {

    ProductEntity pe = productRepository.findByProductiId(productId);

    pe.setStock(pe.getStock() - quantity);

    productRepository.updateProduct(pe);

    return pe;
  }

}
