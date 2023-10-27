package com.microservices.orderservice.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntity extends PanacheMongoEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 129348940L;


  private String orderId;
  private Integer status; // 0 Created, 1 Completed, 2 Updated with products, 3 Canceled
  private Long creationDate;
  private Long updateDate;
  private String productId;
  private Integer quantity;
}
