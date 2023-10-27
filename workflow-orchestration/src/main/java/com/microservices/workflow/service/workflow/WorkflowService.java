package com.microservices.workflow.service.workflow;

import com.microservices.workflow.model.OrderEntity;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowService {

    // Initial step
//    @WorkflowMethod
//    void signalCreateOrder(OrderRequest orderRequest);

    @WorkflowMethod
    void signalCreateOrder(String orderId);


    @SignalMethod
    void signalUpdateOrder(OrderEntity orderEntity);

    @SignalMethod
    void signalUpdateProduct(String productId, Integer quantity);

    @SignalMethod
    void signalAcceptOrder(String orderId);

    @SignalMethod
    void signalCancelOrder(String orderId);

}
