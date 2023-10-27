package com.microservices.productservice.service.repository;

import com.microservices.productservice.model.entity.ProductEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<ProductEntity> {

  public List<ProductEntity> listAll() {
    return listAll(Sort.by("productId"));
  }

  public ProductEntity findByProductiId(String productId) {
    return find("productId", productId).firstResult();
  }

  public void saveProduct(ProductEntity productEntity) {
    persist(productEntity);
  }

  public void updateProduct(ProductEntity productEntity){
    update(productEntity);
  }
}
