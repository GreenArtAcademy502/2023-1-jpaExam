package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_provider")
@SuperBuilder
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProviderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "providerEntity", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<ProductEntity> productEntityList = new ArrayList();


}
