package com.green.jpaexam.product;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.entity.QCategoryEntity;
import com.green.jpaexam.entity.QProducerEntity;
import com.green.jpaexam.entity.QProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductResQdsl;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.green.jpaexam.entity.QProductDetailEntity.productDetailEntity;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQdsl {
    private final JPAQueryFactory jpaQueryFactory;

    public List<ProductResQdsl> selProductAll() {
        QProductEntity p = QProductEntity.productEntity;
        QCategoryEntity c = QCategoryEntity.categoryEntity;

        JPQLQuery<ProductResQdsl> query = jpaQueryFactory.select(Projections.bean(ProductResQdsl.class
                        , p.number, p.name, p.price, p.stock, productDetailEntity.description
                        , c.name.as("categoryNm"), p.createdAt
                        ))
                .from(p)
                .join(p.productDetailEntity, productDetailEntity)
                .join(p.cateogryEntity, c);


        return query.fetch();
    }
}
