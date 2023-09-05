package com.green.jpaexam.product.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductSelAllParam {
    private String productName;
    private int price;
}
