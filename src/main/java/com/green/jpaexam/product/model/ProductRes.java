package com.green.jpaexam.product.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRes {
    private Long number;
    private String name;
    private int price;
    private int stock;
}
