package com.microservices.orderservice.service;

//import com.mitocode.microservices.orderservice.model.entity.OrderEntity;
import com.microservices.orderservice.model.entity.OrderEntity;
//import com.mitocode.microservices.orderservice.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

@ApplicationScoped
@RequiredArgsConstructor
public class OrderService {

    //private final OrderRepository orderRepository;

    private List<OrderEntity> orderEntityList = new ArrayList<>(0);

    public List<OrderEntity> getAllOrder() {
        return orderEntityList;
    }
    public OrderEntity getOrder(String orderId) {
        return orderEntityList.stream()
            .filter(t -> t.getOrderId().equals(orderId))
            .findFirst()
            .orElse(OrderEntity.builder().build());
    }

    public OrderEntity createOrder(String orderId) {
        OrderEntity orderEntity = OrderEntity.builder()
                .creationDate(System.currentTimeMillis())
                .orderId(orderId)
                .status(0)
                .build();

        orderEntityList.add(orderEntity);

        //return orderRepository.save(orderEntity);
        return orderEntity;
    }

    public OrderEntity cancelOrder(String orderId) {
        //OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() ->
        //        new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Id not found"));

        //return orderEntity;
        OrderEntity orderEntity =
            orderEntityList.stream().filter(
                t -> t.getOrderId().equals(orderId)).findFirst().get();

        orderEntity.setUpdateDate(System.currentTimeMillis());
        orderEntity.setStatus(9);
        //orderEntityList..save(orderEntity);

        return orderEntity;
    }

    public OrderEntity updateOrder(OrderEntity orderEntity) {

        //OrderEntity entity = orderRepository.findById(orderEntity.getOrderId()).orElseThrow(() ->
        //        new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Id not found"));

        //entity.setStatus(2);
        //entity.setQuantity(orderEntity.getQuantity());
        //entity.setProductId(orderEntity.getProductId());
        //entity.setUpdateDate(System.currentTimeMillis());
        //orderRepository.save(entity);
        //return entity;

        OrderEntity newOrderEntity =
            orderEntityList.stream().filter(
                t -> t.getOrderId().equals(orderEntity.getOrderId())).findFirst().get();

        newOrderEntity.setStatus(1);
        newOrderEntity.setQuantity(orderEntity.getQuantity());
        newOrderEntity.setProductId(orderEntity.getProductId());
        newOrderEntity.setUpdateDate(System.currentTimeMillis());

        return newOrderEntity;

    }

    public String acceptOrder(String orderId) {
        //OrderEntity entity = orderRepository.findById(orderId).orElseThrow(() ->
        //        new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Id not found"));

        //entity.setStatus(1);
        //entity.setUpdateDate(System.currentTimeMillis());
        OrderEntity newOrderEntity =
            orderEntityList.stream().filter(
                t -> t.getOrderId().equals(orderId)).findFirst().get();

        newOrderEntity.setStatus(2);
        newOrderEntity.setUpdateDate(System.currentTimeMillis());
        //orderRepository.save(entity);

        return "Ok";

    }
}
