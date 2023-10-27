package com.microservices.workflow.controller;


import com.microservices.workflow.request.OrderRequest;
import com.microservices.workflow.service.OrderService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("")
@RequiredArgsConstructor
@ApplicationScoped
public class WorkflowController {

    @Inject
    private final OrderService orderService;


    @GET
    @Path("/start/{orderId}")
    public String createOrder(@PathParam("orderId") String orderId) {
        return orderService.createOrder(orderId);
    }

    /*@POST
    @Path("/start")
    public String createOrder(OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }*/

    @PATCH
    @Path("/update")
    public OrderRequest updateOrder(OrderRequest orderRequest) {
        return orderService.updateOrder(orderRequest);
    }

    @GET
    @Path("/update/{productId}/{quantity}/{workflowId}")
    public String updateProduct(@PathParam("productId") String productId,
                                @PathParam("quantity") Integer quantity,
                                @PathParam("workflowId") String workflowId) {
        return orderService.updateProduct(productId, quantity, workflowId);
    }

    @GET
    @Path("/update/{orderId}/{workflowId}")
    public String confirmOrder(@PathParam("orderId") String orderId,
                               @PathParam("workflowId") String workflowId) {
        return orderService.acceptOrder(orderId, workflowId);
    }

    @GET
    @Path("/cancel/{orderId}/{workflowId}")
    public String cancelProcess(@PathParam("orderId") String orderId,
                               @PathParam("workflowId") String workflowId) {
        return orderService.cancelProcess(orderId, workflowId);
    }

}
