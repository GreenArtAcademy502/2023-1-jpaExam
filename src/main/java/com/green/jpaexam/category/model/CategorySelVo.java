package com.green.jpaexam.category.model;

import lombok.Data;

@Data
public class CategorySelVo {
    private long id;
    private String code;
    private String name;
    private int cnt;
}
