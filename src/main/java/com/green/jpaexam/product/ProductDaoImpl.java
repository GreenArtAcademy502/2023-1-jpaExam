package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository rep;

    @Override
    public ProductRes saveProduct(ProductEntity p) {
        ProductEntity result = rep.save(p);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
    }

    @Override
    public List<ProductRes> getProductAll() {
        return rep.findAll(Sort.by("number").descending()).stream().map(item ->
                ProductRes.builder()
                        .number(item.getNumber())
                        .name(item.getName())
                        .price(item.getPrice())
                        .stock(item.getStock())
                        .build()
        ).toList();
    }

    @Override
    public ProductRes getProduct(Long number) {
        Optional<ProductEntity> opt = rep.findById(number);
        if(!opt.isPresent()) {
            return null;
        }
        ProductEntity entity = opt.get();
        return ProductRes.builder()
                .number(entity.getNumber())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }

    @Override
    public ProductRes updProduct(ProductEntity p) {
        Optional<ProductEntity> opt = rep.findById(p.getNumber());
        if(!opt.isPresent()) {
            return null;
        }
        ProductEntity entity = opt.get();
        entity.setName(p.getName());
        entity.setPrice(p.getPrice());
        entity.setStock(p.getStock());
        //entity.setUpdatedAt(LocalDateTime.now());

        ProductEntity result = rep.save(entity);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
    }

    @Override
    public void delProduct(Long number) {
        rep.deleteById(number);
    }
}
