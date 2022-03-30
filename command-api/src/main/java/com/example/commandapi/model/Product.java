package com.example.commandapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@RequiredArgsConstructor
@Getter
@Setter
public class Product extends AbstractBaseEntity {

    private static final long serialVersionUID = -8025436816011875343L;

    @Column(unique = true)
    String sku;

    String name;
    Double price;
    Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
