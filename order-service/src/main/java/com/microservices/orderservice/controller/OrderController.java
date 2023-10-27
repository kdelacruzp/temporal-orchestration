package com.microservices.orderservice.controller;

import com.microservices.orderservice.model.entity.OrderEntity;
import com.microservices.orderservice.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Slf4j
@Path("/order")
@RequiredArgsConstructor
public class OrderController {

    @Inject
    private final OrderService orderService;

    @GET
    public RestResponse<List<OrderEntity>> getAllOrders() {
        log.info("[OrderController] - Get All");
        return RestResponse.ok(orderService.getAllOrder());
    }
    @GET
    @Path("/{orderId}")
    public RestResponse<OrderEntity> getOrder(@PathParam("orderId") String orderId) {
        log.info("[OrderController] - Get order: " + orderId);
        return RestResponse.ok(orderService.getOrder(orderId));
    }
    @GET
    @Path("/create/{orderId}")
    public RestResponse<OrderEntity> createOrder(@PathParam("orderId") String orderId) {
        log.info("[OrderController] - Creating order: " + orderId);
        return RestResponse.ok(orderService.createOrder(orderId));
    }

    @PUT
    @Path("")
    public RestResponse<OrderEntity> updateOrder(OrderEntity orderEntity) {
        log.info("[OrderController] - Update order: " + orderEntity.getOrderId());
        return RestResponse.ok(orderService.updateOrder(orderEntity));
    }

    @PUT
    @Path("/{orderId}")
    public RestResponse<String> acceptOrder(@PathParam("orderId") String orderId) {
        log.info("[OrderController] - Accept order: " + orderId);
        return RestResponse.ok(orderService.acceptOrder(orderId));
    }

    @PUT
    @Path("/cancel/{orderId}")
    public RestResponse<OrderEntity> cancelOrder(@PathParam("orderId") String orderId) {
        log.info("[OrderController] - Canceling order: " + orderId);
        return RestResponse.ok(orderService.cancelOrder(orderId));
    }

}

