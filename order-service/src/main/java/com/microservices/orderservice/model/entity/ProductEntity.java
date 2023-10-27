package com.microservices.orderservice.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
//@Document(collection = "product")
//@Entity
//@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductEntity extends GenericEntity<ProductEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348938L;

    //@Id
    private String productId;
    private String productName;
    private Long price;
    private Integer stock;
    private String productType;


}
