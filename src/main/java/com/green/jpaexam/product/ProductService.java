package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductDto;
import com.green.jpaexam.product.model.ProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao dao;

    public ProductRes saveProduct(ProductDto dto) {

    }
}
