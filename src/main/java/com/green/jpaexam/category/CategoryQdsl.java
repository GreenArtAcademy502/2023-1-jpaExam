package com.green.jpaexam.category;

import com.green.jpaexam.category.model.CategorySelVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryQdsl {
    private final JPAQueryFactory jpaQueryFactory;

    public List<CategorySelVo> selCateogryAll() {
        return jpaQueryFactory.select(Projections.bean(CategorySelVo.class)).fetch();
    }
}
