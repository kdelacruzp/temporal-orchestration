package com.microservices.workflow.request;

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
public class OrderRequest {

    @Serial
    private static final long serialVersionUID = 129348938L;

    private String workflowId;
    private String orderId;
    private String productId;
    private Integer quantity;

}
