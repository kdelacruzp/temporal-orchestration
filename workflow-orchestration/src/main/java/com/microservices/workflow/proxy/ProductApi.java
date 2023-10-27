package com.microservices.workflow.proxy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/product")
@RegisterRestClient(configKey = "product-api")
@ApplicationScoped
public interface ProductApi {

  @PUT
  @Path("/reserve/{productId}/{quantity}")
  String reserve(@PathParam("productId") String productId,
                 @PathParam("quantity") Integer quantity);
}
