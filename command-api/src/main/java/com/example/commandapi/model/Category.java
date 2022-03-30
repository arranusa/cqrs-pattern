package com.example.commandapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@RequiredArgsConstructor
@Getter
@Setter
public class Category extends AbstractBaseEntity {

    private static final long serialVersionUID = -8025436816011875343L;

    String name;

}
