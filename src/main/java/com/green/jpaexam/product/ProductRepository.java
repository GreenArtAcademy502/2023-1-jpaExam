package com.green.jpaexam.product;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductSelAllParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(" select new com.green.jpaexam.product.model.ProductRes" +
            " (p.number, p.name, p.price, p.stock, d.description, c.name, pv.name, p.createdAt)" +
           // "from ProductEntity p join p.productDetailEntity d join CategoryEntity c on p.cateogryEntity.id = c.id")
            " from ProductEntity p join p.productDetailEntity d join p.cateogryEntity c join p.providerEntity pv" +
            " where p.name = :#{#param.productName} and p.price >= :#{#param.price}")
    List<ProductRes> selProductAll(Pageable pageable, ProductSelAllParam param);


    //@Query("select d from ProductEntityDetail d join CategoryEntity c on d.productNumber = c.id")
    /*
    SELECT * FROM t_product_detail d
    LEFT JOIN t_category c
    ON d.product_number = c.id;
     */
}
