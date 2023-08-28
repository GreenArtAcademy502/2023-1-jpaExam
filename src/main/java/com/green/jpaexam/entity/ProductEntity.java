package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@Table(name = "t_product")
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
