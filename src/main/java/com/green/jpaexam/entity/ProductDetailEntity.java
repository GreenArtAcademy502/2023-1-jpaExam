package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "t_product_detail")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetailEntity extends BaseEntity implements Serializable {
    @Id
    private Long number;

    @OneToOne
    @MapsId
    @JoinColumn(updatable = false, nullable = false, name = "product_number", columnDefinition = "BIGINT UNSIGNED")
    private ProductEntity productEntity;

    private String description;
}
