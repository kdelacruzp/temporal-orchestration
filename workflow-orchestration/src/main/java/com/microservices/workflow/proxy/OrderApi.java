package com.microservices.workflow.proxy;

import com.microservices.workflow.model.OrderEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/order")
@RegisterRestClient(configKey = "order-api")
@ApplicationScoped
public interface OrderApi {

  @GET
  @Path("/create/{orderId}")
  OrderEntity createOrder(@PathParam("orderId") String orderId);

  @PUT
  @Path("")
  OrderEntity updateOrder(OrderEntity orderEntity);

  @PUT
  @Path("/{orderId}")
  String acceptOrder(@PathParam("orderId") String orderId);

  @PUT
  @Path("/cancel/{orderId}")
  OrderEntity cancelOrder(@PathParam("orderId") String orderId);

  /*
  @GetMapping("/order/create/{orderId}")
  OrderEntity createOrder(@PathVariable("orderId") String orderId);

  @PutMapping("/order")
  OrderEntity updateOrder(@RequestBody OrderEntity orderEntity);

  @PutMapping("/order/{orderId}")
  String acceptOrder(@PathVariable("orderId") String orderId);

  @GetMapping("/order/cancel/{orderId}")
  OrderEntity cancelOrder(@PathVariable("orderId") String orderId);*/
}
