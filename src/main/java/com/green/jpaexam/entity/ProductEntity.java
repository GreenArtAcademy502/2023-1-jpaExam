package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@Table(name = "t_product")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private ProviderEntity providerEntity;

    @OneToOne(mappedBy = "productEntity", cascade = { CascadeType.PERSIST })
    private ProductDetailEntity productDetailEntity;

    @ManyToOne
    @JoinColumn(name= "category_id")
    @ToString.Exclude
    private CategoryEntity cateogryEntity;

    public void setProductDetailEntity(ProductDetailEntity productDetailEntity) {
        if(this.productDetailEntity != null) {
            this.productDetailEntity.setProductEntity(null);
        }
        this.productDetailEntity = productDetailEntity;
        this.productDetailEntity.setProductEntity(this);
    }
}
