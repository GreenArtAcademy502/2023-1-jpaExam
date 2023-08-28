package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    private Long id;

    private String name;

    /*
    @OneToMany
    @ToString.Exclude
    private List<ProductEntity> productEntityList;
     */
}
