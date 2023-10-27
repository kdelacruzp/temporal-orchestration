package com.microservices.productservice.controller;

import com.microservices.productservice.model.entity.ProductEntity;
import com.microservices.productservice.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Path("/product")
@RequiredArgsConstructor
public class ProductController {

  @Inject
  ProductService productService;

  @GET
  @Path("")
  public List<ProductEntity> getAllProducto() {
    return productService.getAllProducts();
  }

  @POST
  @Path("")
  public ProductEntity saveProduct(ProductEntity productEntity) {
    return productService.saveProduct(productEntity);
  }

  @PUT
  @Path("/reserve/{productId}/{quantity}")
  public String reserve(@PathParam("productId") String productId,
                        @PathParam("quantity") Integer quantity){
    return productService.updateProduct(productId, quantity).getProductId();
  }
}
