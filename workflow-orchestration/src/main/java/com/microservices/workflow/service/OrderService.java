package com.microservices.workflow.service;

import com.microservices.workflow.config.TemporalConfig;
import com.microservices.workflow.model.OrderEntity;
import com.microservices.workflow.request.OrderRequest;
import com.microservices.workflow.service.workflow.WorkflowService;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationScoped
//@RequiredArgsConstructor
public class OrderService {

    //    private final WorkflowServiceStubs workflowServiceStubs;
    @Inject
    private WorkflowClient workflowClient;

//    public String createOrder(OrderRequest orderRequest) {
//
//        String workflowId = UUID.randomUUID().toString().substring(0, 6);
//        WorkflowService workflowService = createWorkflowConnection(workflowId);
//        WorkflowClient.start(() -> workflowService.signalCreateOrder(orderRequest));
//
//        return workflowId;
//
//    }

    public String createOrder(String orderId) {

        String workflowId = UUID.randomUUID().toString().substring(0, 6);
        WorkflowService workflowService = createWorkflowConnection(workflowId);
        WorkflowClient.start(() -> workflowService.signalCreateOrder(orderId));

        return workflowId;

    }

    public OrderRequest updateOrder(OrderRequest orderRequest) {

        WorkflowService workflowService = workflowClient.newWorkflowStub(WorkflowService.class,
                "Order_" + orderRequest.getWorkflowId());

        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(orderRequest.getOrderId())
                .quantity(orderRequest.getQuantity())
                .productId(orderRequest.getProductId())
                .build();

        workflowService.signalUpdateOrder(orderEntity);

        return orderRequest;

    }

    public String updateProduct(String productId, Integer quantity, String workflowId) {

        WorkflowService workflowService = workflowClient.newWorkflowStub(WorkflowService.class,
                "Order_" + workflowId);

        workflowService.signalUpdateProduct(productId, quantity);

        return "Product updated";
    }

    public String acceptOrder(String orderId, String workflowId) {

        WorkflowService workflowService = workflowClient.newWorkflowStub(WorkflowService.class,
                "Order_" + workflowId);

        workflowService.signalAcceptOrder(orderId);

        return "Order confirmed";
    }

    public String cancelProcess(String orderId, String workflowId) {

        WorkflowService workflowService = workflowClient.newWorkflowStub(WorkflowService.class,
                "Order_" + workflowId);


        workflowService.signalCancelOrder(orderId);

        return "Process cancelled";
    }

    private WorkflowService createWorkflowConnection(String workflowId) {
        WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                .setTaskQueue(TemporalConfig.QUEUE_NAME)
                    .setWorkflowId("Order_" + workflowId)
                .build();
        return workflowClient.newWorkflowStub(WorkflowService.class, workflowOptions);
    }



}
