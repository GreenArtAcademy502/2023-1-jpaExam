package com.green.jpaexam.product;

import com.green.jpaexam.entity.*;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductResQdsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static com.green.jpaexam.entity.QProductDetailEntity.productDetailEntity;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQdsl {
    private final JPAQueryFactory jpaQueryFactory;
    private final QProductEntity p = QProductEntity.productEntity;
    private final QCategoryEntity c = QCategoryEntity.categoryEntity;
    private final QProviderEntity pv = QProviderEntity.providerEntity;

    public List<ProductResQdsl> selProductAll(Pageable pageable, String search) {
        BooleanBuilder whereBuilder = new BooleanBuilder();
        if(search != null) {
            whereBuilder.and(p.name.contains(search))
                        .or(productDetailEntity.description.contains(search));
        }
        whereBuilder.and(p.number.goe(JPAExpressions.select(p.number.count()).from(p)));


        JPQLQuery<ProductResQdsl> query = jpaQueryFactory
                .select(Projections.bean(ProductResQdsl.class
                        , p.number, p.name, p.price, p.stock, productDetailEntity.description
                        , c.name.as("categoryNm"), p.createdAt, pv.name.as("providerNm")
                        , ExpressionUtils.as(JPAExpressions.select(p.number.count()).from(p), "totalCnt")
                        )
                )
                .from(p)
                .join(p.productDetailEntity, productDetailEntity)
                .join(p.cateogryEntity, c)
                .join(p.providerEntity, pv)
                .orderBy(getAllOrderSpecifiers(pageable))
                .where(whereBuilder)

                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                ;
        return query.fetch();
    }

    private OrderSpecifier[] getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> orders = new LinkedList();
        if(!pageable.getSort().isEmpty()) {
            for(Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch(order.getProperty().toLowerCase()) {
                    case "number": orders.add(new OrderSpecifier(direction, p.number)); break;
                    case "product_name": orders.add(new OrderSpecifier(direction, p.name)); break;
                    case "price": orders.add(new OrderSpecifier(direction, p.price)); break;
                    case "category_name": orders.add(new OrderSpecifier(direction, c.name)); break;
                }
            }
        }
        return orders.stream().toArray(OrderSpecifier[]::new);
    }

}
