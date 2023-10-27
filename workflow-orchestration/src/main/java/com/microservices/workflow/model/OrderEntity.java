package com.microservices.workflow.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
//@Document(collection = "order")
//@Entity
//@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntity extends GenericEntity<ProductEntity> implements Serializable {

  @Serial
  private static final long serialVersionUID = 129348940L;

  //@Id
  private String orderId;
  private Integer status; // 0 Created, 1 Completed, 2 Updated with products, 3 Canceled
  private Long creationDate;
  private Long updateDate;
  private String productId;
  private Integer quantity;
}
