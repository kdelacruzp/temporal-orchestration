package com.microservices.productservice.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;

@Setter
@Getter
@Builder
//@Document(collection = "product")
//@Entity
//@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductEntity {

    @Serial
    private static final long serialVersionUID = 129348938L;

    //@Id
    private String productId;
    private String productName;
    private Long price;
    private Integer stock;
    private String productType;


}
