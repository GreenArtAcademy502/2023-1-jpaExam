package com.green.jpaexam.product;

import com.green.jpaexam.category.CategoryRepository;
import com.green.jpaexam.entity.CategoryEntity;
import com.green.jpaexam.entity.ProductDetailEntity;
import com.green.jpaexam.entity.ProviderEntity;
import com.green.jpaexam.product.model.ProductDto;
import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductUpdDto;
import com.green.jpaexam.provider.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao dao;
    private final ProductRepository productRep;
    private final ProductDetailRepository productDetailRep;
    private final CategoryRepository categoryRep;
    private final ProviderRepository providerRep;

    public ProductRes saveProduct2(ProductDto dto) {
        CategoryEntity categoryEntity = categoryRep.findById(dto.getCategoryId()).get();
        ProviderEntity providerEntity = providerRep.findById(dto.getProviderId()).get();

        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .description(dto.getDescription())
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .cateogryEntity(categoryEntity)
                .providerEntity(providerEntity)
                .build();

        productEntity.setProductDetailEntity(productDetailEntity);
        productDetailEntity.setProductEntity(productEntity);

        productRep.save(productEntity);
        return null;
    }

    public ProductRes saveProduct1(ProductDto dto) {
        CategoryEntity categoryEntity = categoryRep.findById(dto.getCategoryId()).get();
        ProviderEntity providerEntity = providerRep.findById(dto.getProviderId()).get();

        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .cateogryEntity(categoryEntity)
                .providerEntity(providerEntity)
                .build();

        productRep.save(productEntity);

        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .productEntity(productEntity)
                .description(dto.getDescription())
                .build();

        productDetailRep.save(productDetailEntity);

        return ProductRes.builder()
                .number(productEntity.getNumber())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .description(productDetailEntity.getDescription())
                .categoryNm(productEntity.getCateogryEntity().getName())
                .providerNm(productEntity.getProviderEntity().getName())
                .createdAt(productEntity.getCreatedAtDatetime())
                .build();
    }


    public ProductRes saveProduct(ProductDto dto) {
        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .description(dto.getDescription())
                .build();

        ProductEntity entity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .cateogryEntity(CategoryEntity.builder().id(dto.getCategoryId()).build())
                .providerEntity(ProviderEntity.builder().id(dto.getProviderId()).build())
                .build();

        entity.setProductDetailEntity(productDetailEntity);

        //productDetailEntity.setProductEntity(entity);

        return dao.saveProduct(entity);
    }

    public Page<ProductRes> getProductAll(Pageable page) {
        return dao.getProductAll(page);
    }

    public ProductRes getProduct(Long number) {
       return dao.getProduct(number);
    }

    public ProductRes updProduct(ProductUpdDto dto) {
        ProductEntity entity = ProductEntity.builder()
                .number(dto.getNumber())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        return dao.updProduct(entity);
    }

    public void delProduct(Long number) {
        dao.delProduct(number);
    }
}
