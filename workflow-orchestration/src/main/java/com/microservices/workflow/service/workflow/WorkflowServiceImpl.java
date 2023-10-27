package com.microservices.workflow.service.workflow;

import com.microservices.workflow.model.OrderEntity;
import com.microservices.workflow.service.activity.ActivityService;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService {

    private final RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(100))
            .setMaximumAttempts(50)
            .setBackoffCoefficient(2)
            .build();

    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .setRetryOptions(retryOptions)
            .build();

    private final ActivityService activityService = Workflow.newActivityStub(ActivityService.class, activityOptions);

    private final Saga.Options sagaOptions = new Saga.Options.Builder()
            .setParallelCompensation(true).build();
    private final Saga saga = new Saga(sagaOptions);

    private boolean createdOrder = false;
    private boolean updatedOrder = false;
    private boolean updatedProduct = false;
    private boolean acceptedOrder = false;

    @Override
    public void signalCreateOrder(String orderId) {

        try {

            activityService.createOrder(orderId);

            //saga.addCompensation(activityService::cancelOrder, orderId);

//            OrderEntity orderEntity = OrderEntity.builder()
//                    .orderId(orderRequest.getOrderId())
//                    .quantity(orderRequest.getQuantity())
//                    .productId(orderRequest.getProductId())
//                    .build();
//
//            activityService.updateOrder(orderEntity);
//
//            activityService.updateProduct(orderRequest.getProductId(), orderRequest.getQuantity());
//
//            saga.addCompensation(activityService::reverseProduct, orderRequest.getProductId(), orderRequest.getQuantity());
//
//            activityService.confirmOrder(orderRequest.getOrderId());


            createdOrder = true;

            Workflow.await(() -> createdOrder);
            Workflow.await(() -> updatedOrder);
            Workflow.await(() -> updatedProduct);
            Workflow.await(() -> acceptedOrder);

        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            log.info("Compensating the process");
            saga.compensate();
//            completeWorkflow();
        }

    }

//    @Override
//    public void signalUpdateOrder(OrderEntity orderEntity) {
//
//    }
//
//    @Override
//    public void signalUpdateProduct(String productId, Integer quantity) {
//
//    }
//
//    @Override
//    public void signalAcceptOrder(String orderId) {
//
//    }
//
//    @Override
//    public void signalCancelOrder(String orderId) {
//
//    }

    @Override
    public void signalUpdateOrder(OrderEntity orderEntity) {
        if (createdOrder) {
            try {
                activityService.updateOrder(orderEntity);
//                saga.addCompensation(() -> activityService.cancelOrder(orderEntity.getOrderId()));
                updatedOrder = true;
            } catch (Exception e) {
                log.error("Exception: " + e.getMessage());
                log.info("Compensating the process");
                saga.compensate();
                completeWorkflow();
            }
        } else {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
            //throw new ResponseProcessingException(Exception);
        }
    }

    @Override
    public void signalUpdateProduct(String productId, Integer quantity) {
        if (updatedOrder) {
            try {
                activityService.updateProduct(productId, quantity);
                //saga.addCompensation(() -> activityService.reverseProduct(productId, quantity));
                updatedProduct = true;
            } catch (Exception e) {
                log.error("Exception: " + e.getMessage());
                log.info("Compensating the process");
                saga.compensate();
                completeWorkflow();
            }
        } else {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
        }
    }

    @Override
    public void signalAcceptOrder(String orderId) {
        if (updatedProduct) {
            try {
//                throw new Exception(("Error"));
                activityService.confirmOrder(orderId);
                acceptedOrder = true;
//                saga.addCompensation(activityService::cancelOrder, orderId);

            } catch (Exception e) {
                log.error("Exception: " + e.getMessage());
                log.info("Compensating the process");
                saga.compensate();
                completeWorkflow();
            }
        } else {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");

        }
    }

    @Override
    public void signalCancelOrder(String orderId) {
        activityService.cancelOrder(orderId);

        saga.compensate();
        completeWorkflow();
    }

    private void completeWorkflow() {
        createdOrder = true;
        updatedOrder = true;
        updatedProduct = true;
        acceptedOrder = true;
    }

}
