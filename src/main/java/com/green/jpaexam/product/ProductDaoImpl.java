package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository rep;

    @Override
    public ProductEntity insProduct(ProductEntity p) {
        return rep.save(p);
    }
}
