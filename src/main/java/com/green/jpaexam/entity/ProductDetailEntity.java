package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "t_product_detail")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetailEntity extends BaseEntity {
    @Id
    private Long number;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_number", columnDefinition = "BIGINT UNSIGNED")
    private ProductEntity ProductEntity;

    private String description;
}
