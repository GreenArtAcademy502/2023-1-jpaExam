package com.green.jpaexam.category;

import com.green.jpaexam.category.model.CategorySelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategorySelVo>> getCategory() {
       // return ResponseEntity.ok(service.)
        return null;
    }

}
