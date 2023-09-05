package com.green.jpaexam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "t_category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "cateogryEntity")
    private List<ProductEntity> productEntityList = new ArrayList();
}
