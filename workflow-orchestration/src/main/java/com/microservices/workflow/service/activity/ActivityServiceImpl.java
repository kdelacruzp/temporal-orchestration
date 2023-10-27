package com.microservices.workflow.service.activity;

import com.microservices.workflow.model.OrderEntity;
import com.microservices.workflow.proxy.OrderApi;
import com.microservices.workflow.proxy.ProductApi;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
public class ActivityServiceImpl implements ActivityService {

     //private final ProductFeign productFeign;
    private OrderApi orderApi;
    private ProductApi productApi;

    public ActivityServiceImpl(OrderApi orderApi, ProductApi productApi) {
        this.orderApi = orderApi;
        this.productApi = productApi;
    }

    @Override
    public void createOrder(String orderId) {
        log.info("Creating order");
        orderApi.createOrder(orderId);
        log.info("Order created");
    }

    @Override
    public void updateOrder(OrderEntity orderEntity) {
        log.info("Updating order");
        orderApi.updateOrder(orderEntity);
        log.info("Order updated");
    }

    @Override
    public void updateProduct(String productId, Integer quantity) {
        log.info("Updating product");
        productApi.reserve(productId, quantity);
        log.info("Product updated");
    }

    @Override
    public void confirmOrder(String orderId) {
        log.info("Accepting order");
        orderApi.acceptOrder(orderId);
        log.info("Order accepted");
    }

    @Override
    public void cancelOrder(String orderId) {
        log.info("Cancelling order");
        orderApi.cancelOrder(orderId);
        log.info("Order cancelled");
    }

    @Override
    public void reverseProduct(String productId, Integer quantity) {
        log.info("Reversing product");
        productApi.reserve(productId, quantity * -1);
        log.info("Product reverted");
    }
}
