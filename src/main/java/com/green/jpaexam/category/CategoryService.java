package com.green.jpaexam.category;

import com.green.jpaexam.category.model.CategorySelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryQdsl categoryQdsl;

    public List<CategorySelVo> selCateogryAll() {
        //return categoryQdsl.
        return null;
    }
}
