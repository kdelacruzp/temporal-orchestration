package com.microservices.productservice.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.io.Serial;

@Setter
@Getter
@Builder
//@Entity
//@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@Entity
//@Table(name = "product")
public class ProductEntity extends PanacheMongoEntity {

    @Serial
    private static final long serialVersionUID = 129348938L;

    //@Id
    private String productId;
    private String productName;
    private Long price;
    private Integer stock;
    private String productType;


}
