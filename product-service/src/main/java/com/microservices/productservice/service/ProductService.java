package com.microservices.productservice.service;

import com.microservices.productservice.model.dto.ProductDTO;
import com.microservices.productservice.model.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

  List<ProductEntity> productEntityList
      = Arrays.asList(ProductEntity.builder()
                      .productId("P00002")
                      .productName("Impresa HP Color")
                      .price(800L)
                      .stock(100)
                      .productType("Computo")
                      .build());

  public List<ProductEntity> getAllProducts() {
    return productEntityList;
  }

  public ProductEntity saveProduct(ProductEntity productEntity) {
    productEntityList.add(productEntity);
    return productEntity;
  }

  public ProductEntity updateProduct(String productId, Integer quantity) {

    ProductEntity productEntity =
    productEntityList.stream().filter(p -> p.getProductId().equals(productId))
            .findFirst().orElse(ProductEntity.builder().build());

    productEntity.setStock(productEntity.getStock() - quantity);
    return productEntity;
  }
}
