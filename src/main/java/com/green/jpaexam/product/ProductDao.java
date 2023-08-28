package com.green.jpaexam.product;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDao {
    ProductRes saveProduct(ProductEntity p);
    Page<ProductRes> getProductAll(Pageable page);
    ProductRes getProduct(Long number);
    ProductRes updProduct(ProductEntity p);
    void delProduct(Long number);
}
