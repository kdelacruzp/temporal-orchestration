package com.microservices.orderservice.service.repository;

import com.microservices.orderservice.model.entity.OrderEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {

  public List<OrderEntity> getAllOrder() {
    return listAll(Sort.by("orderId"));
  }

  public OrderEntity getOrder(String orderId) {
    return find("orderId", orderId).firstResult();
  }

  public void createOrder(OrderEntity orderEntity) {
    persist(orderEntity);
  }
  public void updateOrder(OrderEntity orderEntity) {
    update(orderEntity);
  }

}
