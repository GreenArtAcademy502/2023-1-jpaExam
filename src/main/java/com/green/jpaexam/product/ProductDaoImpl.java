package com.green.jpaexam.product;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

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
                .description(result.getProductDetailEntity().getDescription())
                .categoryNm(result.getCateogryEntity().getName())
                .providerNm(result.getProviderEntity().getName())
                //.createdAt(result.getCreatedAtDatetime())
                .build();
    }

    @Override
    public Page<ProductRes> getProductAll(Pageable page) {
        Page<ProductEntity> totalList = rep.findAll(page);
        long totalSize = totalList.getTotalElements();
        List<ProductRes> contents = totalList.getContent().stream().map(item ->
                ProductRes.builder()
                        .number(item.getNumber())
                        .name(item.getName())
                        .price(item.getPrice())
                        .stock(item.getStock())
                        .build()
        ).toList();
        return new PageImpl<>(contents, page, totalSize);
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
