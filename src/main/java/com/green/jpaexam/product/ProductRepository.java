package com.green.jpaexam.product;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("select p from ProductEntity p")
    List<ProductEntity> selProductAll();
}
