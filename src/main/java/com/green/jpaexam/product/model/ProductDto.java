package com.green.jpaexam.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private final String name;
    private final int price;
    private final int stock;
}
