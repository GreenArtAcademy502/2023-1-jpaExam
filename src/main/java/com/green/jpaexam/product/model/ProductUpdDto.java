package com.green.jpaexam.product.model;

import lombok.Data;

@Data
public class ProductUpdDto {
    private Long number;
    private String name;
    private int price;
    private int stock;
}
