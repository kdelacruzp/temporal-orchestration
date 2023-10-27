package com.microservices.orderservice.service;

import com.microservices.orderservice.model.entity.OrderEntity;
import com.microservices.orderservice.service.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;


@ApplicationScoped
@RequiredArgsConstructor
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    public List<OrderEntity> getAllOrder() {
        return orderRepository.getAllOrder();
    }
    public OrderEntity getOrder(String orderId) {
        return orderRepository.getOrder(orderId);
    }

    public OrderEntity createOrder(String orderId) {

        OrderEntity orderEntity = OrderEntity.builder()
            .creationDate(System.currentTimeMillis())
            .orderId(orderId)
            .status(0)
            .build();

        orderRepository.createOrder(orderEntity);
        return orderEntity;
    }

    public OrderEntity cancelOrder(String orderId) {

        OrderEntity orderEntity = orderRepository.getOrder(orderId);
        orderEntity.setUpdateDate(System.currentTimeMillis());
        orderEntity.setStatus(9);

        orderRepository.updateOrder(orderEntity);

        return orderEntity;
    }

    public OrderEntity updateOrder(OrderEntity orderEntity) {

        OrderEntity newOrderEntity = orderRepository.getOrder(orderEntity.getOrderId());

        newOrderEntity.setStatus(1);
        newOrderEntity.setQuantity(orderEntity.getQuantity());
        newOrderEntity.setProductId(orderEntity.getProductId());
        newOrderEntity.setUpdateDate(System.currentTimeMillis());
        orderRepository.updateOrder(newOrderEntity);

        return newOrderEntity;

    }

    public String acceptOrder(String orderId) {

        OrderEntity orderEntity = orderRepository.getOrder(orderId);

        orderEntity.setStatus(2);
        orderEntity.setUpdateDate(System.currentTimeMillis());
        //orderRepository.save(entity);
        orderRepository.updateOrder(orderEntity);

        return "Ok";

    }
}
